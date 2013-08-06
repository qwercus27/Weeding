package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Achillea;
import Plants.Andropogon;
import Plants.BareGround;
import Plants.Schizachyrium;
import Plants.Tradescantia;

public class Plot11 extends Map {

	public Plot11(GameContainer gc) throws SlickException {
		super(gc);
		
		mapID = 11;
		
		timeLimit = 54;
		time = timeLimit;
		
		numWeeds = 18;
		nonWeeds = 0;
		weedCounter = 0;
		nonWeedCounter = 0;
		

		//Weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(3);
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				if(roll2 == 0) plantArray[temp] = new Achillea(2);
				if(roll2 == 1) plantArray[temp] = new Schizachyrium(2);
				if(roll2 == 2) plantArray[temp] = new Andropogon(2);
				
				weedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && weedCounter < numWeeds)i = 0;
			if(weedCounter == numWeeds)i = tileTotal;
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new BareGround();
		}
		
		
	}
	


}
