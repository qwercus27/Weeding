package GameStates;



import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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
import Weeding.Cursor;
import Weeding.Font;
import Weeding.Player;
import Weeding.Scaffold;
import Weeding.TileSize;

public class WeedingState extends BasicGameState {
	
	private int tileSize, gScale, penalty, weedTotal, startX, startY;
	private Player player;
	private Map map;
	private Scaffold scaffold;
	private Cursor cursor;
	private float pullTimer;
	private boolean fail, spaceRelease, leaveState, success;
	private CurrentMap currentMap;
	private Image plantFrame;
	private Font font;
	
	public WeedingState(int state) throws SlickException {
		
		
	}

	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		startX = 32;
		startY = 32;
		
		player = new Player(startX, startY);
		
		leaveState = false;
		pullTimer = 0;
		
		penalty = 3;
		
		spaceRelease = true;
		
		currentMap = new CurrentMap(gc);
		
		plantFrame = new Image("res/plantFrame.png", false, Image.FILTER_NEAREST);
		
		map = CurrentMap.getCurrentMap();
		
		font = new Font();
		
		weedTotal = 0;
		fail = false;
		success = false;

	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics g)	throws SlickException {
		
		g.scale(gScale, gScale);
	
		map = CurrentMap.getCurrentMap();
		map.render(gc, g);
		player.render(gc, g);
		
		for(int i = 0; i < map.getSpecies().size(); i++){
			
			plantFrame.draw(6 + i * 24, 6);
			map.getSpecies().get(i).getImage().draw(8 + i * 24, 8);
		}
		
	
		font.draw("" + (float)map.getTime(), gc.getWidth()/4 - 64, 10, Color.white);
		
		font.draw("" + penalty, gc.getWidth()/4 - 96, 10, Color.white);
		
		font.draw("" + weedTotal, gc.getWidth()/4 - 128, 10, Color.white);
		
		if(fail)font.draw("Fail!  Press Enter", gc.getWidth()/8 - 64, gc.getHeight()/8 - 8, Color.red);
		if(success)font.draw("Success!  Press Enter", gc.getWidth()/8 - 64, gc.getHeight()/8 - 8, Color.white);
		
		
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		
		if(!fail){
			player.update(gc, delta);
			
			map.update(delta);
		}
		
		if(fail || success)map.setPause(true);
		else map.setPause(false);
		
		Input input = gc.getInput();
		
		Plant[] plantArray = map.getPlantArray();
	
		int remX = Player.getX()%16;
		int remY = Player.getY()%16;
		int playerX = (Player.getX() - remX)/16;
		int playerY = (Player.getY() - remY)/16;
		int columns = map.getColumns();
		int buffer = TileSize.buffer;
		
		System.out.println(playerX + ", " + playerY);
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			
			pullTimer += delta * 0.005;
			
			//add restriction for top and bottom of screen
			
			if(pullTimer > map.getPlantResistance(playerX - buffer, playerY - buffer) ){
				if(playerY >= buffer && (playerY - buffer) < map.getRows() && playerX >= buffer && (playerX - buffer) < map.getColumns()){
					
					if(map.isWeed(playerX - buffer, playerY - buffer) && map.getPlantID(playerX - buffer, playerY - buffer) != 0){
						penalty -= 1;
					}
					
					map.removePlant(playerX - buffer, playerY - buffer);
					pullTimer = 0;
					spaceRelease = false;
					
					
				}
			}
			
		}
		
		if(!input.isKeyDown(Input.KEY_SPACE)){
			pullTimer = 0;
			spaceRelease = true;
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			penalty = 3;
			weedTotal = 0;
			leaveState = true;
			sb.enterState(1);
			
		}
		
		
		//weed counter
		
		ArrayList<Plant> species = map.getSpecies();
		int check = 0;
		int count = 0;
	
		for(int i = 0; i < map.getTileTotal(); i++){
			
			for(int j = 0; j < species.size(); j++){
				
				if(plantArray[i].getPlantID() == species.get(j).getPlantID())check += 1;  
			}	
			
			if(check == 0 && plantArray[i].getPlantID() != 0)count += 1;
			
			check = 0;
		
			weedTotal = count;
		
		}
		
		if(weedTotal == 0)success = true;
			
		if(penalty ==0)fail = true;
		
		if(	map.getTime() <= 0){
			fail = true;
			map.setTime(0);
		}
		
		if(fail && input.isKeyPressed(Input.KEY_ENTER)){
			sb.enterState(1);
			fail = false;
			penalty = 3;
			leaveState = true;
			
		}
		
		if(success && input.isKeyPressed(Input.KEY_ENTER)){
			sb.enterState(1);
			leaveState = true;
			success = false;
		}
		
		if(leaveState){
			
			Player.setX(startX);
			Player.setY(startY);
			leaveState = false;
			
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
