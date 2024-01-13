extends Node
class_name RetrieveMap

var plot01 = preload("res://scenes/maps/plot_01.gd")
var plot02 = preload("res://scenes/maps/plot_02.gd")
var plot03 = preload("res://scenes/maps/plot_03.gd")
var plot04 = preload("res://scenes/maps/plot_04.gd")
var plot05 = preload("res://scenes/maps/plot_05.gd")
var plot06 = preload("res://scenes/maps/plot_06.gd")
var plot07 = preload("res://scenes/maps/plot_07.gd")
var plot08 = preload("res://scenes/maps/plot_08.gd")
var plot09 = preload("res://scenes/maps/plot_09.gd")
var plot10 = preload("res://scenes/maps/plot_10.gd")
var plot11 = preload("res://scenes/maps/plot_11.gd")
var plot12 = preload("res://scenes/maps/plot_12.gd")
var plot13 = preload("res://scenes/maps/plot_13.gd")
var plot14 = preload("res://scenes/maps/plot_14.gd")
var plot15 = preload("res://scenes/maps/plot_15.gd")
var plot16 = preload("res://scenes/maps/plot_16.gd")
var plot17 = preload("res://scenes/maps/plot_17.gd")
var plot18 = preload("res://scenes/maps/plot_18.gd")
var plot19 = preload("res://scenes/maps/plot_19.gd")
var plot20 = preload("res://scenes/maps/plot_20.gd")
var plot21 = preload("res://scenes/maps/plot_21.gd")
var plot22 = preload("res://scenes/maps/plot_22.gd")
var plot23 = preload("res://scenes/maps/plot_23.gd")
var plot24 = preload("res://scenes/maps/plot_24.gd")
var plot25 = preload("res://scenes/maps/plot_25.gd")

var species: Array
var map

func _ready():
	pass
	
func getSpecies(plotNum):
	
	match plotNum:
		1:
			map = plot01.new()
		2:
			map = plot02.new()
		3:
			map = plot03.new()
		4:
			map = plot04.new()	
		5:
			map = plot05.new()	
		6:
			map = plot06.new()	
		7:
			map = plot07.new()	
		8:
			map = plot08.new()	
		9:
			map = plot09.new()	
		10:
			map = plot10.new()	
		11:
			map = plot11.new()	
		12:
			map = plot12.new()	
		13:
			map = plot13.new()	
		14:
			map = plot14.new()	
		15:
			map = plot15.new()	
		16:
			map = plot16.new()	
		17:
			map = plot17.new()	
		18:
			map = plot18.new()	
		19:
			map = plot19.new()	
		20:
			map = plot20.new()	
		21:
			map = plot21.new()	
		22:
			map = plot22.new()	
		23:
			map = plot23.new()	
		24:
			map = plot24.new()	
		25:
			map = plot25.new()	
		_:
			map = plot01.new()	
			
	return map.speciesList
