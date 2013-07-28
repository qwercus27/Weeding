package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Schizachyrium;
import Plants.Andropogon;
import Plants.Tradescantia;
import Plants.AsclepiasS;
import Plants.BareGround;

public class Plot01 extends Map {

	public Plot01(GameContainer gc) throws SlickException {
		super(gc);
		
		mapID = 1;
		
		timeLimit = 10;
		time = timeLimit;
		
		numWeeds = 3;
		nonWeeds = 0;
		weedCounter = 0;
		nonWeedCounter = 0;
		
		//Weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(10);
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				plantArray[temp] = new Andropogon(2);
				
				weedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && weedCounter < numWeeds)i = 0;
			if(weedCounter == numWeeds)i = tileTotal;
			
		}
		
		//Non-weeds
		
		/*for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(10);
		
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				//if(roll2 == 2 ) plantArray[temp] = new Andropogon(2);
				
				nonWeedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && nonWeedCounter < nonWeeds)i = 0;
			if(nonWeedCounter == nonWeeds)i = tileTotal;
			
		}*/
		
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new BareGround();
		}
	}
	


}
