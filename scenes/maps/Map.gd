extends Node

## This is the map class
class_name Map

var mapID: int
var timeLimit: float
var numWeeds: int
var nonWeeds: int
var speciesList: Array
var speciesNames: Array
var weedNames: Array

var tileSize
var scale
var tileTotal
var columns
var rows
var x
var y
var buffer

var time

var weedCounter = 0
var nonWeedCounter = 0

var weedsLeft
var plantArray: Dictionary

var random
var pause

# Called when the node enters the scene tree for the first time.

func _init():
	pause = false
	buffer = global.buffer
	
	x = 0
	y = 0
	
	
	weedsLeft = 10
	childInit()
	fillSpeciesList()
	fillEmptySpecies()
	
func _ready():
	
	timeLimit = snapped(timeLimit * 1.25, 1)
	tileSize = global.tileSize
	scale = global.scale
	
	columns = get_viewport().size.x/tileSize/scale
	rows = get_viewport().size.y/tileSize/scale
	
	columns -= buffer * 2
	rows -= buffer * 2
	
	tileTotal = columns * rows
	
	#plantArray.resize(tileTotal)
	
	for i in range(tileTotal):
		plantArray[i] = ["", 0, 0]
	
	#childReady()
	fillPlantArray()
	
	var tempRow = 0
	var tempColumn = 0
	
	for i in tileTotal:
		
		if tempColumn > columns - 1:
			tempRow += 1
			tempColumn = 0
		
		plantArray[i][0] = plantArray[i][0].instantiate()
		plantArray[i][1] = tileSize * buffer * scale + (tempColumn * tileSize * scale) + 24
		plantArray[i][2] = tileSize * buffer * scale + (tempRow * tileSize * scale) + 24
		plantArray[i][0].position  = Vector2(plantArray[i][1], plantArray[i][2])
		add_child(plantArray[i][0])
		
		tempColumn += 1


func _process(delta):
	
	#if not pause: time -= delta
	
	
	#update plants in plantArray here
		
	for i in tileTotal:
		
		var spread = randi_range(0, 8)
		var plant = plantArray[i][0]
		var pName = plant.plantName
		var placementArray: Array
		placementArray.resize(8)
		var rollArray: Array
		rollArray.resize(8)
			
		for j in range(8):
			var roll = randi_range(0, 3)
			if roll > 1:
				rollArray[j] = 1
			else: 
				rollArray[j] = 0
		
		if (plant.timer > 20 
				and ((plant.rhizome and plant.growthStage >=2) 
				or (not plant.rhizome and plant.growthStage == 3))
		):
			
			if spread == 1:
				
				# The following determines whether or not a new plant will be placed in a given square around the seeding plant.
				
				# Up
				if (rollArray[0] > 0 
						and i > columns 
						and plantArray[i-columns][0].plantNumber == 0
				):
					placementArray[0] = 1
				
				# Down
				if (rollArray[1] > 0 
						and i < (tileTotal - columns) 
						and plantArray[i + columns][0].plantNumber == 0
				):
					placementArray[1] = 1
					
				# Left
				if (rollArray[2] > 0 
						and (i - 1) % columns != 0 
						and i > 0 
						and plantArray[i - 1][0].plantNumber == 0
				):
					placementArray[2] = 1
				
				# Right
				if (rollArray[3] > 0 
						and i % columns != 0 
						and i + 1 < tileSize 
						and plantArray[i + 1][0].plantNumber == 0
				):
					placementArray[3] = 1
					
				#UpLeft
				if (rollArray[4] > 0 
						and i > columns 
						and (i - 1) % columns != 0 
						and plantArray[i - columns - 1][0].plantNumber == 0
				):
					placementArray[4] = 1
				
				#UpRight
				if (rollArray[5] > 0 
						and i > columns 
						and i % columns != 0 
						and plantArray[i - columns + 1][0].plantNumber == 0
				):
					placementArray[5] = 1
				
				#DownLeft
				if (rollArray[6] > 0 
						and i < (tileTotal - columns)
						and (i - 1) % columns != 0 
						and plantArray[i + columns - 1][0].plantNumber == 0
				):
					placementArray[6] = 1
				
				#DownRight
				if (rollArray[7] > 0 
						and i < (tileTotal - columns - 1)
						and i % columns != 0 
						and plantArray[i + columns + 1][0].plantNumber == 0
				):
					placementArray[7] = 1			
			
			# The following pair of switches places a new plant in the correct squares.
			
				for j in range(8):
					
					var square = 0
					
					if placementArray[j] != null and placementArray[j] > -1:
						
						match j:
							0: 
								square = i - columns
							1:
								square = i + columns
							2:
								square = i - 1
							3:
								square = i + 1
							4:
								square = i - columns - 1
							5:
								square = i - columns + 1
							6:
								square = i + columns - 1
							7:
								square = i + columns + 1
							_:
								square = 0
						
						if square > 0:
							
							plantArray[square][0].queue_free()
							#var pos = Vector2(plantArray[square][1], plantArray[square][2])
							#plantArray[square][0].queue_free()
							
							plantArray[square][0] = instantiatePlant(pName)
							
							plantArray[i][0].timer = 0
							if plantArray[i][0].perennial:
								plantArray[i][0].growthStage = 2
							else:
								plantArray[i][0].growthStage = 0
							
							
							var tempX = plantArray[square][1]
							var tempY = plantArray[square][2]
							plantArray[square][0].position = Vector2(tempX, tempY)
							plantArray[square][0].timer = 0
							plantArray[square][0].growthStage = 0
							add_child(plantArray[square][0])
				
				for j in range(8):
					placementArray[j] = -1
			
			plantArray[i][0].timer = 0
			
	var check = false
	var count = 0

	for i in tileTotal:
		for j in weedNames:
			
			var pName = plantArray[i][0].plantName
	
			if pName == j and pName != "BareGround":
				count += 1

		weedsLeft = count

func appendSpeciesList(species):
	speciesList.append(load(global.sceneDictionary[species]))
	
func fillEmptySpecies():
	if speciesList.size() < 4:
		for i in range(4):
			if speciesList.size() < 4:
				appendSpeciesList("BareGround")
			else: 
				break

func fillPlantArray():
	
	var passes = 0
	var i = 0
	
	while i < tileTotal:
		
		var roll1 = randi_range(0, 9)	# determines if a weed, non-weed, or bare ground is placed
		var roll2 = randi_range(0, weedNames.size())	# determines which weed species is placed
		var roll3 = randi_range(0, speciesNames.size())		# determines which non-weed species is placed
		var temp = randi_range(0, tileTotal - 1)	# determines which tile is filled
		
		if(not plantArray[temp][0]):
			
			if(roll1 == 2 and weedCounter < numWeeds):

				for index in range(weedNames.size()):
					if roll2 == index:
						plantArray[temp][0] = load(global.sceneDictionary[weedNames[index]])
						weedCounter += 1
						
			if(roll1 == 3 and nonWeedCounter < nonWeeds):
				for index in range(speciesNames.size()):
					if roll3 == index:
						plantArray[temp][0] = load(global.sceneDictionary[speciesNames[index]])
						nonWeedCounter += 1				
		
		if i == tileTotal - 2 and (weedCounter < numWeeds or nonWeedCounter < nonWeeds): 
			i = 0
		if weedCounter == numWeeds and nonWeedCounter == nonWeeds:
			break
		
		passes += 1
		
	for j in range(tileTotal):
		if not plantArray[j][0]:
			plantArray[j][0] = load(global.sceneDictionary["BareGround"])


func fillSpeciesList():
	for plantName in speciesNames:
		appendSpeciesList(plantName)

func instantiatePlant(plantName):
	return load(global.sceneDictionary[plantName]).instantiate()
	
	

#func removePlant(xTile, yTile):
#	plantArray[(yTile * columns) + xTile][0] = BareGround.new()

func removePlant(plant):
	
	var index = -1
	
	for item in plantArray.values():
		if item[0] == plant:
			index = plantArray.find_key(item)

	if(index > -1):
		plantArray[index][0].queue_free()
		plantArray[index][0] = instantiatePlant("BareGround")
		plantArray[index][0].position = Vector2(plantArray[index][1], plantArray[index][2])
		plantArray[index][0].timer = 0
		plantArray[index][0].growthStage = 0
		add_child(plantArray[index][0])
	else:
		print("Error: pulled plant not found within map's plantArray")
	
	
func getPlantID(xTile, yTile):
	return plantArray[(yTile * columns) + xTile][0].plantNumber


func isBare(xTile, yTile):
	
	var sum = 0
	var temp = plantArray[(yTile * columns) + xTile][0]
	if temp.plantName == "BareGround":
		return true
	else:
		return false


func isWeed(xTile, yTile):
	
	var sum = 0
	var temp = plantArray[(yTile * columns) + xTile][0]
	
	for i in speciesList:
		if i.plantName == temp.plantName and temp.plantName != "BareGround":
			sum += 1
		if sum > 0:
			return true
		else:
			return false


func checkIfWeed(plant):
	
	if weedNames.find(plant.plantName) > -1:
		return true
	else:
		return false
		


func getPlantResistance(xTile, yTile):
	
	return plantArray[(yTile * columns) + xTile][0].resistance


func setPlant(arrayIndex, plant):
	plantArray[arrayIndex][0] = plant

func getTimeLimit():
	return timeLimit
	
func getWeedsLeft():
	return weedsLeft	

func getSpeciesNames():
	return speciesNames
	
func childReady():
	pass

func childInit():
	pass
