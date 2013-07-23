package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.BareGround;
import Plants.Panicum;
import Plants.Schizachyrium;
import Plants.Tradescantia;

public class Plot11 extends Map {

	public Plot11(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new Tradescantia(2));
		speciesList.add(1, new Schizachyrium(2));
		
		timeLimit = 40;
		time = timeLimit;
	
		for(int i = 0; i < tileTotal; i++){
			int roll = random.nextInt(4);
			
			if(roll == 0)plantArray[i] = new BareGround();
			if(roll == 1)plantArray[i] = new Tradescantia(2);
	
			if(roll == 2)plantArray[i] = new Schizachyrium(2);
			if(roll == 3)plantArray[i] = new  Panicum(2);
			
		}
		
		
	}
	


}
