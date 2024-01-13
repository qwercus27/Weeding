extends Area2D
class_name Plant

@export var plantName: String
@export var plantNumber: int
@export var weed: bool
@export var strength: int
@export var timerSpeed = 1
@export var resistance: int
@export var rhizome: bool
@export var perennial: bool

var atlas = AtlasTexture.new()
var seedRegion
var sproutRegion
var flowerRegion
var seedingRegion

var growthRoll
var timer = 0
var growthTimer = randf_range(-30.0, 0.0)

var growthStage = 2

var random
var spreadList = [8]
var active = true

# Called when the node enters the scene tree for the first time.
func _ready():
	
	$Sprite2D.texture = load("res://res/plantSheet.png")

	var size = global.tileSize
	var yCoord = (plantNumber - 1) * size
	
	seedRegion = Rect2(size * 0, yCoord, size, size)
	sproutRegion = Rect2(size * 1, yCoord, size, size)
	flowerRegion = Rect2(size * 2, yCoord, size, size)
	seedingRegion = Rect2(size * 3, yCoord, size, size)
	
	$Sprite2D.region_enabled = true
	$Sprite2D.region_rect = flowerRegion
	
	if plantNumber == 0: $Sprite2D.hide()
	#childReady()
	


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	
	if(active):
		
		timer += (delta * 2.5 * timerSpeed)
		growthTimer += delta * 2.5 * timerSpeed

		if growthTimer > 20:
			growthRoll = randi_range(0, 5)
			if growthRoll == 2 && growthStage < 3:
				growthStage += 1
			growthTimer = randf_range(-30.0, 0.0)
		
		
		if growthStage == 0: 
			$Sprite2D.region_rect = seedRegion
		if growthStage == 1: 
			$Sprite2D.region_rect = sproutRegion
		if growthStage == 2: 
			$Sprite2D.region_rect = flowerRegion
		if growthStage == 3: 
			$Sprite2D.region_rect = seedingRegion
		if growthStage == 4: 
			growthStage = 0



func setWeed(boolean):
	weed = boolean


func addGrowthStage():
	if growthStage <=2:
		growthStage += 1
	else:
		growthStage = 0


func setImage():
	pass
	#if growthTimer == 0: image = seed
	#if growthTimer == 1: image = sprout
	#if growthTimer == 2: image = flower
	#if growthTimer == 3: image = seeding

func childReady():
	pass

	
