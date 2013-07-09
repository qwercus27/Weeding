package Weeding;
import java.awt.AWTException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.WeedingState;


public class WeedingMain extends StateBasedGame {
	
	public static final int weeding = 0;
	

	
	public WeedingMain() throws SlickException, AWTException {
		super("Weeding");
		this.addState(new WeedingState(weeding));
		
		
		this.enterState(weeding);
	}

	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(weeding).init(gc, this);

		
	}
		
	public static void main(String[] args) throws SlickException, AWTException {
		
		AppGameContainer appgc = new AppGameContainer(new WeedingMain());
		appgc.setDisplayMode(240 *  4, 160 * 4 , false);
		//appgc.setTargetFrameRate(60);
		appgc.start();
	}




	

}
