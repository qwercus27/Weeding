package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.plantFour;
import Plants.plantOne;
import Plants.plantThree;
import Plants.plantTwo;
import Plants.plantZero;

public class Plot0 extends Map {

		public Plot0(GameContainer gc) throws SlickException {
			super(gc);
		
			speciesList.add(0, new plantTwo(2));
			speciesList.add(1, new plantFour(2));
			
			for(int i = 0; i < tileTotal; i++){
				int roll = random.nextInt(3);
				
			
				if(roll == 0)plantArray[i] = new plantZero();
				if(roll == 1)plantArray[i] = new plantTwo(2);
				if(roll == 2)plantArray[i] = new plantOne(2);
	
			
		}
		


	}
}
