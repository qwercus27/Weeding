package Plants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Achillea extends Plant {
	
	public Achillea(int stage) throws SlickException {
		
		//Achillea millefolium, yarrow
		
		plantNumber = 6;
		
		seed = sheet.getSprite(0, 5);
		sprout = sheet.getSprite(1, 5);
		flower = sheet.getSprite(2, 5);
		seeding = sheet.getSprite(3, 5);
		
		//image = flower;
		
		growthStage = stage;
		
		
		if(growthStage == 0)image = seed;
		if(growthStage == 1)image = sprout;
		if(growthStage == 2)image = flower;
		if(growthStage == 3)image = seeding;
		if(growthStage == 4)growthStage = 0;
		
		weed = true;
		strength = 4;
		timerSpeed = 3;
		resistance = 2;
		
		rhizome = true;
		perennial = true;
		
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
		if(growthStage == 4)growthStage = 0;
		
	}

}
