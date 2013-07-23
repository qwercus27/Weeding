package GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Audio.WeedingMusic;
import Maps.CurrentMap;
import Weeding.Font;
import Weeding.TileSize;



public class LevelSelect extends BasicGameState {
	
	private int tileSize, gScale, cursorX, cursorY, selectedPlot;
	private Font font;
	private Image cursor, plot, path;
	private WeedingMusic wm;

	
	public LevelSelect(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		cursor = new Image("res/cursorDark.png", false, Image.FILTER_NEAREST);
		plot = new Image("res/plot.png", false, Image.FILTER_NEAREST);
		path = new Image("res/pathSmallWet.png", false, Image.FILTER_NEAREST);
		
		cursorX = 0;
		cursorY = 0;
		
		font = new Font();
		
		wm = new WeedingMusic();		
		
		selectedPlot = cursorX + (cursorY * 6) ;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.scale(gScale, gScale);
		g.setBackground(Color.white);
		font.draw("Plot " + selectedPlot, (gc.getWidth()/(gScale * 2) - (tileSize/2) * 3), 8, new Color(0, 100,0));
		
		
		int numY = 0;
		int numX = 0;
		int plotX = 0;
		int plotY = 0;
		int pathX = 0;
		int pathY = 0;
		
		int screenX = gc.getWidth()/16/4;
		int screenY = gc.getHeight()/16/4;
		
		int grid = (16 * 8 * screenY);
		
		for(int i = 0; i < grid - (screenY * 4); i ++){
			
			pathX += 16;
			
			if(i % (screenX) == 0){
				pathY += 16;
				pathX = 0;
			}
			
			if(pathY/16 < screenY - 2)	path.draw(pathX, pathY + 4);
			
			
		}
		
			
		
		
		int distance = 28;
	
		
		for(int i = 0; i < 28; i++){
			
			String num = new String("" + (i + 1));
			numX += distance;
			plotX += distance;
			
			if(i % 8 == 0) {
				numY += distance;
				numX = 0;
				plotY += distance;
				plotX = 0;
			}
			
			if(i == 10)numX -= 4;
			if(i > 10 && i % 8 == 0)numX -= 4;
			
			plot.draw(28 + plotX, plotY );
			//font.draw(num, 32 + numX, numY, Color.white);
		}
		
		
		cursor.draw(28 + cursorX * distance, distance + cursorY * distance);
		
		
		

		for(int i = 0; i< CurrentMap.getCurrentMap().getSpecies().size(); i++){
			
				CurrentMap.getCurrentMap().getSpecies().get(i).getImage().draw(32 + (i * 32), gc.getHeight()/4 - 24);
			}
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		selectedPlot = cursorX + (cursorY * 6) + 1;
		

		if(!WeedingMusic.getMenu().playing() && !WeedingMusic.getTitle().playing())WeedingMusic.getMenu().loop(1f, .5f);
		CurrentMap.setCurrentMap(selectedPlot, gc);
		
		
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_A)){
			cursorX -= 1;
		}
		
		if(input.isKeyPressed(Input.KEY_D)){
			cursorX += 1;
		}
		
		if(input.isKeyPressed(Input.KEY_W)){
			cursorY -= 1;
		}
		
		if(input.isKeyPressed(Input.KEY_S)){
			cursorY += 1;
		}
		
		if(input.isKeyPressed(Input.KEY_ENTER)){
	
			sbg.enterState(0);
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			WeedingMusic.stop();
			WeedingMusic.getTitle().play(1f, 0.5f);
			sbg.enterState(2);
			
		}

		
	}

	
	public int getID() {

		return 1;
	}

}
