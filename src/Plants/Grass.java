package Plants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Grass extends Plant {
	
	public Grass(int stage) throws SlickException {
		
		plantNumber = 1;
		
		seed = sheet.getSprite(0, 0);
		sprout = sheet.getSprite(1, 0);
		flower = sheet.getSprite(2, 0);
		seeding = sheet.getSprite(3, 0);
	
		image = flower;
		
		growthStage = stage;
		
		weed = false;
		strength = 1;
		timerSpeed = 1;
		resistance = 4;
		
		growthTimer = -(random.nextInt(30));
	}
	
	public void update(int delta){
		
		
		timer += delta * 0.0025 * timerSpeed;
		 
		 growthTimer += delta * 0.0025 * timerSpeed;
				 
		 
		
		if(growthTimer > 20){
			growthRoll = random.nextInt(5);
			
			if(growthRoll == 2 && growthStage < 3)	growthStage += 1;
			
			int timerReset = random.nextInt(30);
			growthTimer = -(timerReset);
		}
		
		if(growthStage == 0)image = seed;
		if(growthStage == 1)image = sprout;
		if(growthStage == 2)image = flower;
		if(growthStage == 3)image = seeding;
		
		
	}

}
