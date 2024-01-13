extends Node

var tileSize
var scale
var penalty
var weedTotal
var startX
var startY
var player
var map
var pullTimer
var fail = false
var success = false
var leaveState
var digging
var plantFrame

var currentMap

var timeRounded
var save


func _ready():
	tileSize = global.tileSize
	scale = global.scale
	
	startX = 32
	startY = 32
	
	#player = Player.new()
	
	leaveState = false
	pullTimer = 0
	penalty = 3
	
	currentMap = CurrentMap.new()
	
	map = global.getCurrentMap()
	map = map.instantiate()
	add_child(map)
	
	digging = false
	
	weedTotal = 0
	fail = false
	
	$Timer.start(map.getTimeLimit())
	
	setPlantSprite()
	#save = Save.new()
	


func _process(delta):
	
	if Input.is_action_just_pressed("Photo"):
		var image = get_viewport().get_texture().get_image()
		image.save_png("res://screenshots/screenshot.png")
	
	$CanvasLayer/TimeLeft.text = str("%.1f" % $Timer.time_left)
	$CanvasLayer/WeedsLeft.text = str(map.getWeedsLeft())
	
	if(Input.is_action_just_pressed("escape")):
		success = false
		fail = false
		get_tree().paused = false
		get_tree().change_scene_to_file("res://scenes/level_select.tscn")
		
	
	if(penalty < 3): 
		$CanvasLayer/Leaf3.hide()
	if(penalty < 2): 
		$CanvasLayer/Leaf2.hide()
	if(penalty < 1): 
		$CanvasLayer/Leaf.hide()
		fail = true
	
	if(map.getWeedsLeft() == 0):
		success = true
		$CanvasLayer/Success.show()
	
	if(fail):
		$CanvasLayer/Fail.show()
		
	if(fail or success):
		get_tree().paused = true
		$Timer.paused = true

			
	if(Input.is_action_just_pressed("enter")):
		if(success):
			if(global.progress <= global.currentMapInt): 
				global.setProgress(global.currentMapInt + 1)
				global.save_game()
			if(global.progress > 25):
				get_tree().change_scene_to_file("res://scenes/credits.tscn")
				get_tree().paused = false
				success = false
		if(success or fail):
			get_tree().change_scene_to_file("res://scenes/level_select.tscn")
			get_tree().paused = false
			success = false
			fail = false
		
		
func _on_player_pulled_plant(plant):
	var isWeed = map.checkIfWeed(plant)
	map.removePlant(plant)
	if not isWeed: 
		penalty -= 1
	

func setPlantSprite():
		
		var speciesNames = map.getSpeciesNames()
		if speciesNames:
			var plants = []
			
			for i in range(speciesNames.size()):
				var name = speciesNames[i]
				var p = load(global.sceneDictionary[name]).instantiate()
			
				var yCoord = (p.plantNumber - 1) * tileSize
				var flowerRegion = Rect2(tileSize * 2, yCoord, tileSize, tileSize)
				var frameSprite = ""
				if i == 0: 
					frameSprite = $CanvasLayer/PlantFrame/Sprite2D
					$CanvasLayer/PlantFrame.show()
				elif i == 1:
					frameSprite = $CanvasLayer/PlantFrame2/Sprite2D
					$CanvasLayer/PlantFrame2.show()
				elif i == 2:
					frameSprite = $CanvasLayer/PlantFrame3/Sprite2D
					$CanvasLayer/PlantFrame3.show()
				elif i == 3:
					frameSprite = $CanvasLayer/PlantFrame4/Sprite2D
					$CanvasLayer/PlantFrame4.show()
				frameSprite.texture = load("res://res/plantSheet.png")
				frameSprite.region_enabled = true
				frameSprite.region_rect = flowerRegion
				


func _on_timer_timeout():
	fail = true
