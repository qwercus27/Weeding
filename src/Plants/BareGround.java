package Plants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BareGround extends Plant {
	
	public BareGround() throws SlickException {
		
		image = new Image("res/plant0.png", false, Image.FILTER_NEAREST);
		weed = false;
		strength = 0;
		resistance = 0;
		
		plantNumber = 0;
	}

}
