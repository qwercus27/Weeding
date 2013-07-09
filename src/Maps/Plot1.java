package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Schiz;
import Plants.Grass;
import Plants.Spiderwort;
import Plants.Milkweed;
import Plants.BareGround;

public class Plot1 extends Map {

	public Plot1(GameContainer gc) throws SlickException {
		super(gc);
		
		speciesList.add(0, new Grass(2));
		speciesList.add(1, new Schiz(2));
	
		for(int i = 0; i < (tileTotal - 15); i++){
			int roll = random.nextInt(4);
			int temp = random.nextInt(tileTotal);
			if(roll == 0)plantArray[temp] = new BareGround();
			if(roll == 1)plantArray[temp] = new Milkweed(2);
			if(roll == 2)plantArray[temp] = new Spiderwort(2);
			if(roll == 3)plantArray[temp] = new Schiz(2);
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new Grass(2);
		}
	}
	


}
