extends Node

## This is the level select scene

var achillea = preload("res://scenes/plants/Achillea.gd")
var tileSize
var scale
var centerPlot
var plotY

var progress
var save
var locked: Array
var pressRight
var pressLeft

var plotArray: Array
var rm

# Called when the node enters the scene tree for the first time.
func _ready():
	
	if(global.progress == null or global.progress == 0): 
		global.setProgress(1)
	
	if(global.titleMusicPos > -1):
		$TitleMusic.play(global.titleMusicPos)
	elif(global.menuMusicPos > -1):
		$MenuMusic.play(global.menuMusicPos)
	else:
		$MenuMusic.play()

	tileSize = global.tileSize
	scale = global.scale
	
	plotY = get_viewport().size.y/2 - 32
	
	centerPlot = global.currentMapInt
	
	#locked = Progress.getLocked()
	
	pressLeft = false
	pressRight = false
	
	rm = load("res://scenes/maps/retrieve_map.gd").new()
	
	$LeftArrow.z_index = 1
	$RightArrow.z_index = 1
	
	var bigPlotScene = load("res://scenes/big_plot.tscn")
	
	for i in range(5):
		plotArray.append(bigPlotScene.instantiate())
		plotArray[i].position = Vector2(24 + (i * 64 * scale), get_viewport().size.y/2)
		plotArray[i].get_node("Label").text = str(i)
		
		add_child(plotArray[i])
	
	updateVisibility()
	updatePlots()
		
# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	
	if(centerPlot > global.progress):
		centerPlot = global.progress
		
	updateVisibility()
	
	global.setCurrentMap(centerPlot)
		
	if Input.is_action_just_pressed("enter"):
		global.resetMusic()
		get_tree().change_scene_to_file("res://scenes/weeding_state.tscn")
	
	if Input.is_action_just_pressed("escape"):
		global.resetMusic()
		if($TitleMusic.playing): 
			global.titleMusicPos = $TitleMusic.get_playback_position()
		elif($MenuMusic.playing):
			global.menuMusicPos = $MenuMusic.get_playback_position()
		
		global.levelSelectToTitle = true
		get_tree().change_scene_to_file("res://scenes/title.tscn")

	
	if Input.is_action_just_pressed("left"):
		if(centerPlot > 1):
			centerPlot -= 1
		pressLeft = true
		$ArrowTimer.start(0.2)
		$LeftArrow.region_rect = Rect2(96, 80, 16, 16)
		updatePlots()
		
	if Input.is_action_just_pressed("right"):
		
		if(centerPlot + 1 <= global.progress):
			if(centerPlot < 25):
				centerPlot += 1
			pressRight = true
			$ArrowTimer.start(0.2)
			$RightArrow.region_rect = Rect2(112, 80, 16, 16)
			updatePlots()

func updatePlots():
	var count = 0
	
	for plot in plotArray:
		var zeroString = ""
		if centerPlot - 2 + count < 10:
			zeroString = "0"
		var tempString = zeroString + str(centerPlot - 2 + count)
		plot.get_node("Label").text = tempString
		count += 1
		
	var loopSize = plotArray.size()
	var species: Array
	
	for i in loopSize:
		
		var tempPlot = centerPlot - 2 + i
		
		if(global.progress >= tempPlot):
			
			plotArray[i].get_node("QuestionMark").hide()
			species = rm.getSpecies(tempPlot)
			
			showPlants(plotArray[i])
			for j in range(4):
				var spriteName = "Plant1"
				if j == 1:
					spriteName = "Plant2"
				elif j == 2: 
					spriteName = "Plant3"
				elif j == 3:
					spriteName = "Plant4"
				setPlantSprite(species[j], plotArray[i].get_node(spriteName))
		else:
			plotArray[i].get_node("QuestionMark").show()
			hidePlants(plotArray[i])
			
		
func updateVisibility():
	
	if centerPlot < 2: 
		if plotArray[1].visible: 
			plotArray[1].hide()
		if $LeftArrow.visible:
			$LeftArrow.hide()
	if centerPlot >= 2:
		if not plotArray[1].visible:
			plotArray[1].show()
		if not $LeftArrow.visible:
			$LeftArrow.show()
	if centerPlot < 3 and plotArray[0].visible:
		plotArray[0].hide()
	if centerPlot >= 3 and not plotArray[0].visible:
		plotArray[0].show()
	
	if centerPlot >= 24 and plotArray[4].visible:
		plotArray[4].hide()
	if centerPlot < 24 and not plotArray[4].visible:
		plotArray[4].show()
	if centerPlot >= 25:
		if plotArray[3].visible: 
			plotArray[3].hide()
		if $RightArrow.visible:
			$RightArrow.hide()
	if centerPlot < 25:
		if not plotArray[3].visible: 
			plotArray[3].show()
		if not $RightArrow.visible:
			$RightArrow.show()
	
	if centerPlot + 1 > global.progress:
		$RightArrow.hide()


func _on_arrow_timer_timeout():
	$LeftArrow.region_rect = Rect2(32, 80, 16, 16)
	$RightArrow.region_rect = Rect2(48, 80, 16, 16)
	
func setPlantSprite(plantScene, plotSprite):
		var tempPlant = plantScene.instantiate()
		var yCoord = (tempPlant.plantNumber - 1) * tileSize
		var flowerRegion = Rect2(tileSize * 2, yCoord, tileSize, tileSize)
		plotSprite.texture = load("res://res/plantSheet.png")
		plotSprite.region_enabled = true
		plotSprite.region_rect = flowerRegion
		
func showPlants(plot):
	plot.get_node("Plant1").show()
	plot.get_node("Plant2").show()
	plot.get_node("Plant3").show()
	plot.get_node("Plant4").show()


func hidePlants(plot):
	plot.get_node("Plant1").hide()
	plot.get_node("Plant2").hide()
	plot.get_node("Plant3").hide()
	plot.get_node("Plant4").hide()


func _on_title_music_finished():
	$MenuMusic.play()
	


