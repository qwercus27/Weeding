package GameStates;



import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Maps.CurrentMap;
import Maps.Map;
import Maps.Plot0;
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
		
		
		
		
		

	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics g)	throws SlickException {
		
		g.scale(gScale, gScale);
	
		map = CurrentMap.getCurrentMap();
		map.render(gc, g);
	//	cursor.render(gc, g);
		//scaffold.render(gc, g);
		player.render(gc, g);
		
		
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		
		player.update(gc, delta);
		//scaffold.update(gc, delta);
	//	cursor.update(gc, delta);
		map.update(delta);
		
		Input input = gc.getInput();
		
		Plant[] plantArray = map.getPlantArray();
	
		int remX = Player.getX()%16;
		int remY = Player.getY()%16;
		int playerX = (Player.getX() - remX)/16;
		int playerY = (Player.getY() - remY)/16;
		int columns = map.getColumns();
		
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			
			
			pullTimer += delta * 0.005;
			
			
			//add restriction for top and bottom of screen
			
			if(pullTimer > map.getPlantResistance(playerX - 1, playerY) ){
				if(playerY > 0 && playerY <= map.getRows() && playerX >= 0 && playerX <= map.getColumns()){
					map.removePlant(playerX -1, playerY);
					pullTimer = 0;
					spaceRelease = false;
				}
			}
			
			
			
		
		
		}
		
		if(!input.isKeyDown(Input.KEY_SPACE)){
			pullTimer = 0;
			spaceRelease = true;
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
