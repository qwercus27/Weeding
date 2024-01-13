extends Node
class_name CurrentMap

var currentMap: Map

# Called when the node enters the scene tree for the first time.
func _ready():
	
	currentMap = Plot01.new()

func getCurrentMap():
	return currentMap
	
	
func setCurrentMap(plotNum):
	
	match plotNum:
		1:
			currentMap = Plot01.new()	
		2:
			currentMap = Plot02.new()	
		3:
			currentMap = Plot03.new()	
		4:
			currentMap = Plot04.new()	
		5:
			currentMap = Plot05.new()	
		6:
			currentMap = Plot06.new()	
		7:
			currentMap = Plot07.new()	
		8:
			currentMap = Plot08.new()	
		9:
			currentMap = Plot09.new()	
		10:
			currentMap = Plot10.new()	
		11:
			currentMap = Plot11.new()	
		12:
			currentMap = Plot12.new()	
		13:
			currentMap = Plot13.new()	
		14:
			currentMap = Plot14.new()	
		15:
			currentMap = Plot15.new()	
		16:
			currentMap = Plot16.new()	
		17:
			currentMap = Plot17.new()	
		18:
			currentMap = Plot18.new()	
		19:
			currentMap = Plot19.new()	
		20:
			currentMap = Plot20.new()	
		21:
			currentMap = Plot21.new()	
		22:
			currentMap = Plot22.new()	
		23:
			currentMap = Plot23.new()	
		24:
			currentMap = Plot24.new()	
		25:
			currentMap = Plot25.new()	
