package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.plantFour;
import Plants.plantOne;
import Plants.plantThree;
import Plants.plantTwo;
import Plants.plantZero;

public class Plot2 extends Map {

	public Plot2(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new plantThree(2));
	
		for(int i = 0; i < tileTotal; i++){
			int roll = random.nextInt(3);
			
			if(roll == 0)plantArray[i] = new plantZero();
			if(roll == 1)plantArray[i] = new plantTwo(2);
	
			if(roll == 2)plantArray[i] = new plantFour(2);
			
		}
		
		
	}
	


}
