extends Node2D

func _ready():
	pass
	
func _process(delta):
	
	if($Headers.position.y > -450):
		$Headers.position.y -= delta * 10
		$Footers.position.y -= delta * 10

	if($Headers.position.y < -445):
		if(Input.is_action_just_pressed("enter")
				or Input.is_action_just_pressed("escape")
				or Input.is_action_just_pressed("space")):
		
			get_tree().change_scene_to_file("res://scenes/title.tscn")
