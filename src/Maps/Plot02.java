package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Achillea;
import Plants.AsclepiasS;
import Plants.BareGround;
import Plants.Schizachyrium;
import Plants.Tradescantia;

public class Plot02 extends Map {

	public Plot02(GameContainer gc) throws SlickException {
		super(gc);
		
		mapID = 2;
		
		timeLimit = 13;
		time = timeLimit;
		
		numWeeds = 3;
		nonWeeds = 4;
		weedCounter = 0;
		nonWeedCounter = 0;
		
		speciesList.add(0, new Achillea(2));

		
		//Weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(10);
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				plantArray[temp] = new AsclepiasS(2);
				
				
				weedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && weedCounter < numWeeds)i = 0;
			if(weedCounter == numWeeds)i = tileTotal;
			
		}
		
		//non-weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
		
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				plantArray[temp] = new Achillea(2);		
				nonWeedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && nonWeedCounter < nonWeeds)i = 0;
			if(nonWeedCounter == nonWeeds)i = tileTotal;
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new BareGround();
		}
		
		
	/*	timeLimit = 40;
		time = timeLimit;
	
		for(int i = 0; i < tileTotal; i++){
			int roll = random.nextInt(4);
			
			if(roll == 0)plantArray[i] = new BareGround();
			if(roll == 1)plantArray[i] = new Tradescantia(2);
	
			if(roll == 2)plantArray[i] = new Schizachyrium(2);
			if(roll == 3)plantArray[i] = new  Panicum(2);
			
		}*/
		
		
	}
	


	
}
