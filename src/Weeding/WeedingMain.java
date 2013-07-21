package Weeding;
import java.awt.AWTException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.LevelSelect;
import GameStates.Title;
import GameStates.WeedingState;


public class WeedingMain extends StateBasedGame {
	
	public static final int weeding = 0;
	public static final int levelSelect = 1;
	public static final int title = 2;
	

	
	public WeedingMain() throws SlickException, AWTException {
		super("Weeding");
		
		this.addState(new LevelSelect(levelSelect));
		this.addState(new WeedingState(weeding));
		this.addState(new Title(title));
		
		
		this.enterState(title);
	}

	
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(levelSelect).init(gc, this);
		this.getState(weeding).init(gc, this);
		this.getState(title).init(gc,  this);
		
	}
		
	public static void main(String[] args) throws SlickException, AWTException {
		
		AppGameContainer appgc = new AppGameContainer(new WeedingMain());
		appgc.setDisplayMode(272 *  4, 192 * 4 , false);
		appgc.setTargetFrameRate(60);
		
		appgc.start();
	}




	

}
