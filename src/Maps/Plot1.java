package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.plantFour;
import Plants.plantOne;
import Plants.plantThree;
import Plants.plantTwo;
import Plants.plantZero;

public class Plot1 extends Map {

	public Plot1(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new plantOne(2));
		speciesList.add(1, new plantFour(2));
	
		for(int i = 0; i < (tileTotal - 15); i++){
			int roll = random.nextInt(4);
			int temp = random.nextInt(tileTotal);
			if(roll == 0)plantArray[temp] = new plantZero();
			if(roll == 1)plantArray[temp] = new plantTwo(2);
			if(roll == 2)plantArray[temp] = new plantThree(2);
			if(roll == 3)plantArray[temp] = new plantFour(2);
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new plantOne(2);
		}
	}
	


}
