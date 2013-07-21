package GameStates;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Audio.WeedingMusic;
import Weeding.Font;
import Weeding.TileSize;



public class Title extends BasicGameState {
	
	private int tileSize, gScale;
	private Font font;
	private Image titleImage;
	private Music titleMusic, menuMusic;
	private WeedingMusic wm;
	
	public Title(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		titleImage = new Image("res/title.png", false, Image.FILTER_NEAREST);
		
		wm = new WeedingMusic();
		
		
		WeedingMusic.getTitle().play(1f, 0.5f);
	
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.scale(gScale, gScale);
		
		titleImage.draw(0,0);
		
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	
			
		if(!WeedingMusic.getTitle().playing()){
			if(!WeedingMusic.getMenu().playing())WeedingMusic.getMenu().loop(1f, 0.5f);
		}
		
		
		
		
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)){
	
			sbg.enterState(1);
		}

		
	}

	
	public int getID() {

		return 2;
	}

}

