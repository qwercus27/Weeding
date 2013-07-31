package GameStates;

import java.io.IOException;
import java.util.ArrayList;

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
import Maps.RetrieveMap;
import Plants.Plant;
import Weeding.Font;
import Weeding.ImageResources;
import Weeding.Progress;
import Weeding.Save;
import Weeding.TileSize;



public class LevelSelect extends BasicGameState {
	
	private int tileSize, gScale, cursorX, cursorY, selectedPlot, centerPlot, plotY;
	private Font font;
	private Image cursor, plot, bigPlot, path, lockedPlot,
		arrowUp, arrowDown, arrowLeft, arrowRight, arrowLeft2, arrowRight2,
		question;
	private Progress progress;
	private Save save;
	private boolean[] locked;
	private boolean pressRight, pressLeft;
	private float arrowTimer;
	
	public LevelSelect(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale ;
		
		cursor = ImageResources.getCursor();
		plot = ImageResources.getPlot();
		bigPlot = ImageResources.getBigPlot();
		lockedPlot = ImageResources.getLockedPlot();
		path = ImageResources.getPath();
		question = ImageResources.getQuestion();
		
		Image[] arrow = ImageResources.getArrow();
		arrowUp = arrow[0];
		arrowDown = arrow[1];
		arrowLeft = arrow[2];
		arrowRight = arrow[3];
		arrowLeft2 = arrow[6];
		arrowRight2 = arrow[7];
		
		cursorX = 0;
		cursorY = 0;
		
		plotY = gc.getHeight()/gScale/2 - 32;
		font = new Font();	
		
		//selectedPlot = cursorX + (cursorY * 6) ;
		
		selectedPlot = 3;
		centerPlot = 1;
		
		progress = new Progress();
		save = new Save();
		
		locked = Progress.getLocked();
		
		pressLeft = false;
		pressRight = false;
		
		arrowTimer = 0;
		
		try {
			save.Load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.scale(gScale, gScale );
		
		g.setBackground(Color.black);
		
		font.draw("Select a Plot", (gc.getWidth()/gScale/2 - (tileSize/2) * 6), 16, Color.white);
		
		ArrayList<Plant> species = new ArrayList<Plant>();
		RetrieveMap rm = new RetrieveMap();
		

		for(int i = 0; i < (gc.getWidth()/gScale/64) + 1; i ++){
			
			species = rm.getSpecies(centerPlot - 2 + i, gc);
			
			if(centerPlot - 2 + i >= 1)bigPlot.draw(-24 + i * 64, plotY);
			
			try {
				if(!locked[centerPlot - 2 + i]){
					if(species.size() >= 1)species.get(0).getImage().draw( -8 + i * 64, plotY + 16);
					if(species.size() >= 2)species.get(1).getImage().draw(  8 + i * 64, plotY + 16);
					if(species.size() >= 3)species.get(2).getImage().draw( -8 + i * 64, plotY + 32);
					if(species.size() >= 4)species.get(3).getImage().draw( 8 + i * 64, plotY + 32);
					
					if(centerPlot - 2 + i >= 1){
						if(centerPlot < 10)		font.draw("0" + (centerPlot - 2 + i), -24 + 24 + i * 64, plotY + 50, Color.white);
						else font.draw("" + (centerPlot - 2 + i), -24 + 24 + i * 64, plotY + 50, Color.white);
					}
				
				}
				else if(centerPlot - 2 + i > 0) question.draw(  i * 64, plotY + 16);
			} catch (ArrayIndexOutOfBoundsException e) {
				
			}
		
			//arrowUp.draw(gc.getWidth()/gScale/2 - 8, gc.getHeight()/gScale/2 - 48);
			//arrowDown.draw(gc.getWidth()/gScale/2 - 8, gc.getHeight()/gScale/2 + 32);
			
			
		}
		
		if(!pressLeft && centerPlot > 1)arrowLeft.draw(gc.getWidth()/gScale/2 - 48, gc.getHeight()/gScale/2 - 8);
		else if(pressLeft && centerPlot > 1)arrowLeft2.draw(gc.getWidth()/gScale/2 - 48, gc.getHeight()/gScale/2 - 8);
		if(!pressRight && !locked[centerPlot + 1])arrowRight.draw(gc.getWidth()/gScale/2 + 32, gc.getHeight()/gScale/2 - 8);
		else if(pressRight && !locked[centerPlot + 1])arrowRight2.draw(gc.getWidth()/gScale/2 + 32, gc.getHeight()/gScale/2 - 8);
		//g.setBackground(Color.white);
		//font.draw("Plot " + selectedPlot, (gc.getWidth()/(gScale * 2) - (tileSize/2) * 3), 8, new Color(0, 100,0));
		
		
		/*int numY = 0;
		int numX = 0;
		int plotX = 0;
		int plotY = 0;
		int pathX = 0;
		int pathY = 0;
		
		int screenX = gc.getWidth()/16/gScale;
		int screenY = gc.getHeight()/16/gScale;
		
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

		
		for(int i = 0; i < 24; i++){
			
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
			
			
				if(locked[i + 1] == true)lockedPlot.draw(28 + plotX, plotY);
				if(locked[i + 1] == false) plot.draw(28 + plotX, plotY );

		}
		
		
		cursor.draw(28 + cursorX * distance, distance + cursorY * distance);
		
		
		

		for(int i = 0; i< CurrentMap.getCurrentMap().getSpecies().size(); i++){
			
				CurrentMap.getCurrentMap().getSpecies().get(i).getImage().draw(32 + (i * 32), gc.getHeight()/gScale - 24);
			}
			
			*/
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		locked = Progress.getLocked();
		
		//selectedPlot = cursorX + (cursorY * 8) + 1;
		
		
		if(!WeedingMusic.getMenu().playing() && !WeedingMusic.getTitle().playing())WeedingMusic.getMenu().loop(1f, .5f);
		CurrentMap.setCurrentMap(centerPlot, gc);
		//System.out.println(CurrentMap.getCurrentMap().getID());

		
	
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_A)){
			//cursorX -= 1;
			if(centerPlot > 1)	centerPlot -= 1;
			pressLeft = true;
			arrowTimer = 0;
		}
		
		if(input.isKeyPressed(Input.KEY_D)){
			//cursorX += 1;
			if(!locked[centerPlot + 1]){
				centerPlot += 1;
				pressRight = true;
				arrowTimer = 0;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_W)){
			//cursorY -= 1;
		}
		
		if(input.isKeyPressed(Input.KEY_S)){
			//cursorY += 1;
		}
		
		if(input.isKeyPressed(Input.KEY_ENTER)){
			boolean[] locked = Progress.getLocked();
			
			if(locked[centerPlot] == false)	sbg.enterState(0);
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			WeedingMusic.stop();
			WeedingMusic.getTitle().play(1f, 0.5f);
			sbg.enterState(2);
			
		}
		
		if(pressLeft){
			arrowTimer += delta * 0.01;
			if(arrowTimer > 2)pressLeft = false;
			
		}
		
		if(pressRight){
			arrowTimer += delta * 0.01;
			if(arrowTimer > 2)pressRight = false;
			
		}

		
	}

	
	public int getID() {

		return 1;
	}

}
