package Plants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BareGround extends Plant {
	
	private static Image plant0Image;
	
	public BareGround() throws SlickException {
		
		if(plant0Image == null) plant0Image = new Image("res/plant0.png", false, Image.FILTER_NEAREST);
		image = plant0Image;
		weed = false;
		strength = 0;
		resistance = 0;
		
		plantNumber = 0;
	}

}
