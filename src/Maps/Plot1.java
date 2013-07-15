package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Schizachyrium;
import Plants.Andropogon;
import Plants.Tradescantia;
import Plants.AsclepiasS;
import Plants.BareGround;

public class Plot1 extends Map {

	public Plot1(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new Andropogon(2));
		
		timeLimit = 30;
	
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
