package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.AsclepiasT;
import Plants.BareGround;
import Plants.Andropogon;
import Plants.Lupinus;
import Plants.AsclepiasS;
import Plants.Panicum;
import Plants.Rumex;
import Plants.Schizachyrium;
import Plants.Tradescantia;
import Plants.Taraxacum;
import Plants.Achillea;

public class Plot0 extends Map {

		public Plot0(GameContainer gc) throws SlickException {
			super(gc);
		
			speciesList.add(0, new Andropogon(2));
			speciesList.add(1, new Schizachyrium(2));
			speciesList.add(2, new Lupinus(2));
	
			timeLimit = 3 * 60;
			
			for(int i = 0; i < tileTotal; i++){
				
				/*int roll = random.nextInt(14);
				if(roll == 0)plantArray[i] = new BareGround();
				if(roll == 1 || roll == 2)plantArray[i] = new Andropogon(2);
				if(roll == 3 || roll == 4)plantArray[i] = new Schizachyrium(2);
				if(roll == 5 || roll == 6)plantArray[i] = new Lupinus(2);
				if(roll == 7)plantArray[i] = new AsclepiasS(2);
				if(roll == 8)plantArray[i] = new Tradescantia(2);
				if(roll == 9)plantArray[i] = new Panicum(2);
				if(roll == 10)plantArray[i] = new Rumex(2);
				if(roll == 11)plantArray[i] = new Taraxacum(2);
				if(roll == 12)plantArray[i] = new Achillea(2);
				if(roll == 13)plantArray[i] = new AsclepiasT(2);*/
				
				int roll = random.nextInt(11);
				if(roll == 0)plantArray[i] = new BareGround();
				if(roll == 1)plantArray[i] = new Andropogon(2);
				if(roll == 2)plantArray[i] = new Schizachyrium(2);
				if(roll == 3)plantArray[i] = new Lupinus(2);
				if(roll == 4)plantArray[i] = new AsclepiasS(2);
				if(roll == 5)plantArray[i] = new Tradescantia(2);
				if(roll == 6)plantArray[i] = new Panicum(2);
				if(roll == 7)plantArray[i] = new Rumex(2);
				if(roll == 8)plantArray[i] = new Taraxacum(2);
				if(roll == 9)plantArray[i] = new Achillea(2);
				if(roll == 10)plantArray[i] = new AsclepiasT(2);
	
			
		}
		


	}
}
