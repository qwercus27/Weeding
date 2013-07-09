package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Schiz;
import Plants.Grass;
import Plants.Spiderwort;
import Plants.Milkweed;
import Plants.BareGround;

public class Plot0 extends Map {

		public Plot0(GameContainer gc) throws SlickException {
			super(gc);
		
			speciesList.add(0, new Milkweed(2));
			speciesList.add(1, new Schiz(2));
			speciesList.add(2, new Spiderwort(2));
	
			
			for(int i = 0; i < tileTotal; i++){
				int roll = random.nextInt(5);
				if(roll == 0)plantArray[i] = new BareGround();
				if(roll == 1)plantArray[i] = new Milkweed(2);
				if(roll == 2)plantArray[i] = new Grass(2);
				if(roll == 3)plantArray[i] = new Spiderwort(2);
				if(roll == 4)plantArray[i] = new Schiz(2);
	
			
		}
		


	}
}
