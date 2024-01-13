extends Map
class_name Plot01


# Called when the node enters the scene tree for the first time.
func childInit():
	mapID = 1
	timeLimit = 15
	numWeeds = 3
	nonWeeds = 0
	
	weedNames.append("Achillea")

func childReady():
	pass
#	for i in tileTotal:
#		var roll1 = randi_range(0, 10)
#		var roll2 = randi_range(0, 10)
#		var temp = randi_range(0, tileTotal)
#
#		if plantArray[temp] == null and roll1 == 2:
#			plantArray[temp] = Achillea.new()
#
#			weedCounter += 1
#
#		if i == tileTotal - 1 and weedCounter < numWeeds:
#			i = 0
#		if weedCounter == numWeeds:
#			i = tileTotal
#
#	for i in tileTotal:
#		if plantArray[i] == null:
#			plantArray[i] == BareGround.new()
