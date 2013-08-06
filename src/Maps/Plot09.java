package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Andropogon;
import Plants.AsclepiasS;
import Plants.BareGround;
import Plants.Lupinus;
import Plants.Panicum;
import Plants.Schizachyrium;

public class Plot09 extends Map {

	public Plot09(GameContainer gc) throws SlickException {
		super(gc);
		
		
		mapID = 9;
		
				
		timeLimit = 42;
		time = timeLimit;
		
		numWeeds = 14;
		nonWeeds = 18;
		weedCounter = 0;
		nonWeedCounter = 0;
		
		speciesList.add(0, new Schizachyrium(2));
		speciesList.add(1, new Andropogon(2));
		speciesList.add(2, new Lupinus(2));
		
		//Weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(2);
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				if(roll2 == 0)plantArray[temp] = new AsclepiasS(2);
				if(roll2 == 1)plantArray[temp] = new Panicum(2);
				
				weedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && weedCounter < numWeeds)i = 0;
			if(weedCounter == numWeeds)i = tileTotal;
			
		}
		
		//non-weeds
		
		for(int i = 0; i < (tileTotal); i++){
			int roll1 = random.nextInt(10);
			int roll2 = random.nextInt(3);
		
			int temp = random.nextInt(tileTotal);
			
			if(plantArray[temp] == null && roll1 == 2){
				if(roll2 == 0) plantArray[temp] = new Schizachyrium(2);
				if(roll2 == 1 ) plantArray[temp] = new Andropogon(2);
				if(roll2 == 2) plantArray[temp] = new Lupinus(2);				
				nonWeedCounter +=1;
				
			}
			
			
			if( i == tileTotal - 1 && nonWeedCounter < nonWeeds)i = 0;
			if(nonWeedCounter == nonWeeds)i = tileTotal;
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new BareGround();
		}
		
		
	}
	


}
