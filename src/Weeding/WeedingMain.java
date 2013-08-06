package Weeding;
import java.awt.AWTException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Audio.WeedingMusic;
import GameStates.Credits;
import GameStates.LevelSelect;
import GameStates.Title;
import GameStates.WeedingState;


public class WeedingMain extends StateBasedGame {
	
	public static final int weeding = 0;
	public static final int levelSelect = 1;
	public static final int title = 2;
	public static final int credits = 3;
	
	public ImageResources iR;
	public WeedingMusic wM;
	public Save s;

	
	public WeedingMain() throws SlickException, AWTException {
		super("Weeding");
		
		this.addState(new LevelSelect(levelSelect));
		this.addState(new WeedingState(weeding));
		this.addState(new Title(title));
		this.addState(new Credits(credits));
		
		this.enterState(title);
		
		
		
	}

	
	public void initStatesList(GameContainer gc) throws SlickException {
		
		//this.getState(levelSelect).init(gc, this);
		//this.getState(weeding).init(gc, this);
		//this.getState(title).init(gc,  this);
		iR = new ImageResources();
		wM = new WeedingMusic();
		s = new Save();
		
		
	
		
	}
		
	public static void main(String[] args) throws SlickException, AWTException {
		
		AppGameContainer appgc = new AppGameContainer(new WeedingMain());
		appgc.setDisplayMode(272 *  TileSize.gScale, 192 * TileSize.gScale, false);
		appgc.setTargetFrameRate(60);
		//appgc.setShowFPS(false);
		appgc.start();
		
	}




	

}
