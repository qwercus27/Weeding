package GameStates;



import org.lwjgl.input.Mouse;
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
import Weeding.Player;
import Weeding.Scaffold;
import Weeding.TileSize;

public class WeedingState extends BasicGameState {
	
	private int tileSize, gScale;
	private Player player;
	private Map map;
	private Scaffold scaffold;
	private Cursor cursor;
	private float pullTimer;
	private boolean spaceRelease;
	private CurrentMap currentMap;
	private Image plantFrame;
	
	public WeedingState(int state) throws SlickException {
		
		
	}

	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		
		Mouse.setCursorPosition((Player.getX() + 8 )* gScale, gc.getHeight() - ((Player.getY() + 8)* gScale) );
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		player = new Player(16, 16);
		
		pullTimer = 0;
		
		spaceRelease = true;
		
		currentMap = new CurrentMap(gc);
		
		plantFrame = new Image("res/plantFrame.png", false, Image.FILTER_NEAREST);
		
		map = CurrentMap.getCurrentMap();
		
		
		

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
		
		
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		
		player.update(gc, delta);
	
		map.update(delta);
		
		Input input = gc.getInput();
		
		Plant[] plantArray = map.getPlantArray();
	
		int remX = Player.getX()%16;
		int remY = Player.getY()%16;
		int playerX = (Player.getX() - remX)/16;
		int playerY = (Player.getY() - remY)/16;
		int columns = map.getColumns();
		
		int buffer = TileSize.buffer;
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			
			
			pullTimer += delta * 0.005;
			
			
			//add restriction for top and bottom of screen
			
			if(pullTimer > map.getPlantResistance(playerX - buffer, playerY - buffer) ){
				if(playerY > 0 && (playerY - buffer) <= map.getRows() && playerX >= 0 && (playerX - buffer) <= map.getColumns()){
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
			sb.enterState(1);
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
