extends Node2D

var x
var y
var tileSize = global.tileSize
var pScale = global.scale
var buffer = global.buffer
var yLocation
var xLocation
var isRight = false
var isLeft = false
var isUp = false
var isDown = true
var isMoving = false
var isPull = false
var plantEntered: Plant
var plantExited: Plant
var timer
signal pulled_plant(plant)

func _ready():
	$PlayerSprite.animation = "down_still"
	scale = Vector2(pScale, pScale)
	$PlayerSprite.play()
	position = Vector2(get_viewport().size.x/2, get_viewport().size.y/2 + (tileSize * pScale / 2))

func _process(delta):
	
	if plantEntered and plantExited and plantEntered == plantExited:
		plantEntered = null
		plantExited = null
		
	if(Input.is_action_just_released("up")):
		$PlayerSprite.animation = "up_still"
		
	if(Input.is_action_just_released("down")):
		$PlayerSprite.animation = "down_still"
		
	if(Input.is_action_just_released("left")):
		$PlayerSprite.animation = "left_still"
		
	if(Input.is_action_just_released("right")):
		$PlayerSprite.animation = "right_still"
		
	if(Input.is_action_pressed("space") and plantEntered):
		if plantEntered.plantName != "BareGround":
			

			
			var anim = $PlayerSprite.animation
			if(anim == "up_still"):
				$PlayerSprite.animation = "up_pull"
			if(anim == "down_still"):
				$PlayerSprite.animation = "down_pull"
			if(anim == "left_still"):
				$PlayerSprite.animation = "left_pull"
			if(anim == "right_still"):
				$PlayerSprite.animation = "right_pull"
	
	if(Input.is_action_just_pressed("space") and plantEntered):
		if plantEntered.plantName != "BareGround":
			var t = plantEntered.resistance / float(5)
			$PullTimer.start(t)
			$DiggingSound.play()
			
	if(Input.is_action_just_released("space") or $PullTimer.time_left == 0):
		$PullTimer.stop()
		$DiggingSound.stop()
		var anim = $PlayerSprite.animation
		if(anim == "up_pull"):
			$PlayerSprite.animation = "up_still"
		if(anim == "down_pull"):
			$PlayerSprite.animation = "down_still"
		if(anim == "left_pull"):
			$PlayerSprite.animation = "left_still"
		if(anim == "right_pull"):
			$PlayerSprite.animation = "right_still"
	
	
	if($MovementTimer.time_left == 0 and not Input.is_action_pressed("space")):
		
		if(Input.is_action_pressed("up")):
			position.y -= tileSize * pScale
			$PlayerSprite.animation = "up_walk"
			$MovementTimer.start()
			
		if(Input.is_action_pressed("down")):
			position.y += tileSize * pScale
			$PlayerSprite.animation = "down_walk"
			$MovementTimer.start()
		
		if(Input.is_action_pressed("left")):
			position.x -= tileSize * pScale
			$PlayerSprite.animation = "left_walk"
			$MovementTimer.start()
				
		if(Input.is_action_pressed("right")):
			position.x += tileSize * pScale
			$PlayerSprite.animation = "right_walk"
			$MovementTimer.start()
		


func _on_area_entered(area):
	
	if(area.plantName != null):
		plantEntered = area
		
		


func _on_area_exited(area):
	if(area.plantName != null):
		plantExited = area
		
		


func _on_pull_timer_timeout():
	pulled_plant.emit(plantEntered)
	$DiggingSound.stop()
