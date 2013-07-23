package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Andropogon;
import Plants.AsclepiasS;
import Plants.BareGround;
import Plants.Panicum;
import Plants.Schizachyrium;
import Plants.Tradescantia;

public class Plot04 extends Map {

	public Plot04(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new Andropogon(2));
		
		timeLimit = 40;
		time = timeLimit;
	
		for(int i = 0; i < (tileTotal - 15); i++){
			int roll = random.nextInt(10);
			int temp = random.nextInt(tileTotal);
			if(roll == 1)plantArray[temp] = new AsclepiasS(2);
			if(roll == 2)plantArray[temp] = new Andropogon(2);
			//if(roll == 3)plantArray[temp] = new Schizachyrium(2);
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new BareGround();
		}
	}
	


}
