package GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Maps.CurrentMap;
import Maps.Map;
import Plants.Plant;
import Weeding.Font;
import Weeding.TileSize;



public class LevelSelect extends BasicGameState {
	
	private int tileSize, gScale, cursorX, cursorY, selectedPlot;
	private Font font;
	private Image cursor;

	
	public LevelSelect(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		cursor = new Image("res/cursorDark.png", false, Image.FILTER_NEAREST);
		
		cursorX = 0;
		cursorY = 0;
		
		font = new Font();

		
		selectedPlot = cursorX + (cursorY * 6);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.scale(gScale, gScale);
		g.setBackground(Color.white);
		font.draw("Plot " + selectedPlot, (gc.getWidth()/(gScale * 2) - (tileSize/2) * 3), 8);
		
		int tempY = 0;
		int tempX = 0;
		
		for(int i = 0; i < 24; i++){
			
			String num = new String("" + i);
			tempX += 32;
			
			if(i % 6 == 0) {
				tempY += 32;
				tempX = 0;
			}
			
			if(i == 10)tempX -= 4;
			if(i > 10 && i % 6 == 0)tempX -= 4;
			
			font.draw(num, 32 + tempX, tempY);
		}
		
		
		cursor.draw(28 + cursorX * 32, 28 + cursorY * 32);
		
		
		

		for(int i = 0; i< CurrentMap.getCurrentMap().getSpecies().size(); i++){
			
				CurrentMap.getCurrentMap().getSpecies().get(i).getImage().draw(32 + (i * 32), gc.getHeight()/4 - 24);
			}
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		selectedPlot = cursorX + (cursorY * 6);
		
		System.out.println(selectedPlot);
		
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

		
	}

	
	public int getID() {

		return 1;
	}

}
