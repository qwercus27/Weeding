extends Node

@export var title_button_scene: PackedScene

var tileSize
var scale
var yDiff
var selection
var optionsSize

var goingUp
var goingDown
var allUp
var allDown

var imageX
var imageY
var rate
var maxY = 288
var minY = -336
var halfwayY = maxY - (maxY - minY)/2
var options

var color
var selected
var unselected

var buttonList: Array


# Called when the node enters the scene tree for the first time.
func _ready():
	tileSize = global.tileSize
	scale = global.scale
	
	goingUp = false
	goingDown = false
	allUp = false
	allDown = true
	
	yDiff = -624
	imageX = 0
	imageY = -624
	
	rate = 0
	selection = 0
	
	global.load_game()
	
	if global.progress != null and global.progress > 1:
		optionsSize = 5
		options = []
		options.append("Continue")
		options.append("New Game")
		options.append("Options")
		options.append("Help")
		options.append("Quit")
		
	else:
		optionsSize = 4
		options = []
		options.append("New Game")
		options.append("Options")
		options.append("Help")
		options.append("Quit")
		

	var x = get_viewport().size.x/scale
	
	for i in optionsSize:
		var titleButton = title_button_scene.instantiate()
		titleButton.position = Vector2(x, -624 + (48 * scale) + i * 20 * scale)
		titleButton.text = options[i]
		titleButton.set_name("Button" + str(i+1))
		add_child(titleButton)
		buttonList.append(titleButton)
		
	get_node("Button1").grab_focus()
	
	if(global.titleMusicPos > -1):
		$TitleMusic.play(global.titleMusicPos)
	elif(global.menuMusicPos > -1):
		$MenuMusic.play(global.menuMusicPos)
	else:
		$TitleMusic.play()
	
	if global.levelSelectToTitle:
		allUp = true
		allDown = false
		$Camera2D.position.y = minY

	$TallImage.position = Vector2(imageX, yDiff)	
	
# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	
	# This allows time for the camera to move up when switching to this 
	#		scene from the Level Select scene. Otherwise the bottom of
	#		the TallImage is seen for a half second.
	if not $TallImage.visible:
		$TallImage.show()
	
	var yPos = $Camera2D.position.y
	
	if Input.is_action_just_pressed("Photo"):
		var image = get_viewport().get_texture().get_image()
		image.save_png("res://screenshots/screenshot.png")
		
	if allDown and Input.is_action_just_pressed("enter"): 
		goingUp = true
		allDown = false
	
	if allUp and Input.is_action_just_pressed("escape"):
		goingDown = true
		allUp = false
		
	if goingUp:
		goUp(yPos, delta)
	
	if goingDown:
		goDown(yPos, delta)
	
		
	if allUp:
		
		for button in buttonList:
			button.show()
			
		if Input.is_action_just_pressed("up"):
			selection -= 1
		
		if Input.is_action_just_pressed("down"):
			selection += 1
		
		if selection > optionsSize - 1: 
			selection = 0
		if selection < 0: 
			selection = optionsSize - 1
			
		if selection == 0: get_node("Button1").grab_focus()
		if selection == 1: get_node("Button2").grab_focus()
		if selection == 2: get_node("Button3").grab_focus()
		if selection == 3: get_node("Button4").grab_focus()
		if selection == 4: get_node("Button5").grab_focus()
			
		if Input.is_action_just_pressed("enter"):
			
			if optionsSize == 5:
				if selection == 0:
					enterTitleScene()
					
				elif selection == 1:
					
					global.setProgress(1)
					global.save_game()
					enterTitleScene()
					
				elif selection == 4:
					get_tree().quit()
					
			
			elif optionsSize == 4:
				if selection == 0:
					enterTitleScene()
					#delete save

				elif selection == 3:
					get_tree().quit()
	
	else:
		for button in buttonList:
			button.hide()
			


func goUp(yPos, delta):
	
	if yPos > minY and yPos > halfwayY:
		rate += delta * 10
		$Camera2D.position.y -= rate
		allDown = false
	
	if yPos <= halfwayY:
		rate -= delta * 9.9
		if rate < 0.5:
			rate = 0.5
		$Camera2D.position.y -= rate
		
	if yPos <= minY:
		$Camera2D.position.y = minY
		rate = 0
		allUp = true
		goingUp = false	


func goDown(yPos, delta):
	
	if yPos < maxY and yPos < halfwayY:
		rate += delta * 10
		$Camera2D.position.y += rate
		allUp = false
	
	if yPos >= halfwayY:
		rate -= delta * 9.9
		if rate < 0.1:
			rate = 0.1
		$Camera2D.position.y += rate
	
	if yPos >= maxY:
		$Camera2D.position.y = 288
		rate = 0
		allDown = true
		goingDown = false


func enterTitleScene():
	
	global.resetMusic()
	
	if $TitleMusic.playing:
		global.titleMusicPos = $TitleMusic.get_playback_position()
	
	elif $MenuMusic.playing:
		global.menuMusicPos = $MenuMusic.get_playback_position()
		global.titleMusicPos = -1
	else:
		global.titleMusicPos = -1
		global.menuMusicPos = -1
		
	get_tree().change_scene_to_file("res://scenes/level_select.tscn")


func _on_title_music_finished():
	$MenuMusic.play()
