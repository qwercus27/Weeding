package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.AsclepiasS;
import Plants.AsclepiasT;
import Plants.BareGround;
import Plants.Panicum;
import Plants.Tradescantia;

public class Plot16 extends Map {

	public Plot16(GameContainer gc) throws SlickException {
		super(gc);
		
		mapID = 16;
		
		timeLimit = 84;
		time = timeLimit;
		
		numWeeds = 28;
		nonWeeds = 0;
		weedCounter = 0;
		nonWeedCounter = 0;
		
		
		//Weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(4);
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				if(roll2 == 0)plantArray[temp] = new Tradescantia(2);
				if(roll2 == 1)plantArray[temp] = new AsclepiasS(2);
				if(roll2 == 2)plantArray[temp] = new Panicum(2);
				if(roll2 == 3)plantArray[temp] = new AsclepiasT(2);
				
				
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
