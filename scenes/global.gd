extends Node

var scale = 3
var tileSize = 16
var buffer = 2
var sceneDictionary = {}
var currentMap
var currentMapInt
var progress
var titleMusicPos = -1
var menuMusicPos = -1
var levelSelectToTitle = false

func _init():
	setSceneDictionary()
	
# Called when the node enters the scene tree for the first time.
func _ready():
	currentMap = load("res://scenes/maps/plot_01.tscn")
	currentMapInt = 1
	

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass

func setSceneDictionary():
	var tempDict = {
		"Achillea" : "res://scenes/plants/achillea.tscn",
		"Andropogon" : "res://scenes/plants/andropogon.tscn",
		"AsclepiasS" : "res://scenes/plants/asclepias_s.tscn",
		"AsclepiasT" : "res://scenes/plants/asclepias_t.tscn",
		"BareGround" : "res://scenes/plants/bare_ground.tscn",
		"Lupinus" : "res://scenes/plants/lupinus.tscn",
		"Panicum" : "res://scenes/plants/panicum.tscn",
		"Rumex" : "res://scenes/plants/rumex.tscn",
		"Schizachyrium" : "res://scenes/plants/schizachyrium.tscn",
		"Taraxacum" : "res://scenes/plants/taraxacum.tscn",
		"Tradescantia" : "res://scenes/plants/tradescantia.tscn",
	}
	
	sceneDictionary = tempDict

func getCurrentMap():
	return currentMap
	


func setCurrentMap(plotNum):
	currentMapInt = plotNum
	match plotNum:
		1:
			currentMap = load("res://scenes/maps/plot_01.tscn")	
		2:
			currentMap = load("res://scenes/maps/plot_02.tscn")	
		3:
			currentMap = load("res://scenes/maps/plot_03.tscn")
		4:
			currentMap = load("res://scenes/maps/plot_04.tscn")
		5:
			currentMap = load("res://scenes/maps/plot_05.tscn")
		6:
			currentMap = load("res://scenes/maps/plot_06.tscn")
		7:
			currentMap = load("res://scenes/maps/plot_07.tscn")
		8:
			currentMap = load("res://scenes/maps/plot_08.tscn")	
		9:
			currentMap = load("res://scenes/maps/plot_09.tscn")
		10:
			currentMap = load("res://scenes/maps/plot_10.tscn")
		11:
			currentMap = load("res://scenes/maps/plot_11.tscn")
		12:
			currentMap = load("res://scenes/maps/plot_12.tscn")
		13:
			currentMap = load("res://scenes/maps/plot_13.tscn")
		14:
			currentMap = load("res://scenes/maps/plot_14.tscn")	
		15:
			currentMap = load("res://scenes/maps/plot_15.tscn")
		16:
			currentMap = load("res://scenes/maps/plot_16.tscn")
		17:
			currentMap = load("res://scenes/maps/plot_17.tscn")
		18:
			currentMap = load("res://scenes/maps/plot_18.tscn")
		19:
			currentMap = load("res://scenes/maps/plot_19.tscn")
		20:
			currentMap = load("res://scenes/maps/plot_20.tscn")
		21:
			currentMap = load("res://scenes/maps/plot_21.tscn")
		22:
			currentMap = load("res://scenes/maps/plot_22.tscn")
		23:
			currentMap = load("res://scenes/maps/plot_23.tscn")
		24:
			currentMap = load("res://scenes/maps/plot_24.tscn")
		25:
			currentMap = load("res://scenes/maps/plot_25.tscn")


func setProgress(value):
	progress = value
	
func save():
	
	var save_dict = {
		"progress" : progress
	}
	
	return save_dict
	
func save_game():
	var save_game = FileAccess.open("user://savegame.save", FileAccess.WRITE)
	var json_string = JSON.stringify(save())

	save_game.store_line(json_string)

func load_game():
	
	if not FileAccess.file_exists("user://savegame.save"):
		return # Error! We don't have a save to load.

	# Load the file line by line and process that dictionary to restore
	# the object it represents.
	var save_game = FileAccess.open("user://savegame.save", FileAccess.READ)
	while save_game.get_position() < save_game.get_length():
		var json_string = save_game.get_line()

		# Creates the helper class to interact with JSON
		var json = JSON.new()

		# Check if there is any error while parsing the JSON string, skip in case of failure
		var parse_result = json.parse(json_string)
		if not parse_result == OK:
			print("JSON Parse Error: ", json.get_error_message(), " in ", json_string, " at line ", json.get_error_line())
			continue

		# Get the data from the JSON object
		var node_data = json.get_data()

		# Firstly, we need to create the object and add it to the tree and set its position.
#		var new_object = load(node_data["filename"]).instantiate()
#		get_node(node_data["parent"]).add_child(new_object)
#		new_object.position = Vector2(node_data["pos_x"], node_data["pos_y"])
#
#		# Now we set the remaining variables.
#		for i in node_data.keys():
#			if i == "filename" or i == "parent" or i == "pos_x" or i == "pos_y":
#				continue
#			new_object.set(i, node_data[i])
		
		progress = node_data["progress"]

func resetMusic():
	titleMusicPos = -1
	menuMusicPos = -1	
