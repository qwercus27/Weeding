package GameStates;



import java.io.IOException;
import java.math.BigDecimal;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Audio.WeedingMusic;
import Maps.CurrentMap;
import Maps.Map;
import Plants.Plant;
import Weeding.Cursor;
import Weeding.Font;
import Weeding.ImageResources;
import Weeding.Player;
import Weeding.Progress;
import Weeding.Save;
import Weeding.TileSize;

public class WeedingState extends BasicGameState {
	
	private int tileSize, gScale, penalty, weedTotal, startX, startY;
	private Player player;
	private Map map;
	private Cursor cursor;
	private float pullTimer;
	private boolean fail, spaceRelease, leaveState, success, digging;
	private CurrentMap currentMap;
	private Image plantFrame, leaf, dandelion, clock;
	private Font font;
	private Sound dig, pop;
	private Music running;
	private BigDecimal timeRounded;
	private static int saveTest;
	private Save save;


	
	
	public WeedingState(int state) throws SlickException {
		
		
	}

	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		startX = 32;
		startY = 32;
		
	
		player = new Player(startX, startY);
		
		
		dig = new Sound("res/audio/digging.ogg");
		pop = new Sound("res/audio/pop.ogg");
		running = WeedingMusic.getWeeding();
		
		leaveState = false;
		pullTimer = 0;
		
		penalty = 3;
		
		spaceRelease = true;
		
		currentMap = new CurrentMap(gc);
		
		plantFrame = new Image("res/plantFrame.png", false, Image.FILTER_NEAREST);
		
		/*leaf = new Image("res/leaf.png", false, Image.FILTER_NEAREST);
		dandelion = new Image("res/dandelion.png", false, Image.FILTER_NEAREST);
		clock = new Image("res/clock.png", false, Image.FILTER_NEAREST);*/
		
		leaf = ImageResources.getLeaf();
		dandelion = ImageResources.getDandelion();
		clock = ImageResources.getClock();
		
		map = CurrentMap.getCurrentMap();
		
		font = new Font();
		
		digging = false;
		
		weedTotal = 0;
		fail = false;
		success = false;
		
		save = new Save();

	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics g)	throws SlickException {
		
		
		
		g.scale(gScale, gScale);
	
		map = CurrentMap.getCurrentMap();
		map.render(gc, g);
		player.render(gc, g);
		
		for(int i = 0; i < map.getSpecies().size(); i++){
			
			plantFrame.draw(6 + i * 24, 2);
			map.getSpecies().get(i).getImage().draw(8 + i * 24, 4);
		}
		
		dandelion.draw(gc.getWidth()/gScale - 145 - 20, 4);
		font.draw("" + weedTotal, gc.getWidth()/gScale - 145, 10, Color.white);
		
		for(int i = 0; i < penalty; i++){
			leaf.draw(gc.getWidth()/gScale - 128 + (i * 16), 4);
		}
		
		BigDecimal timeRounded = new BigDecimal(map.getTime());
		timeRounded = timeRounded.setScale(1, BigDecimal.ROUND_UP);
		
		clock.draw(gc.getWidth()/gScale - 64, 4);
		font.draw("" + timeRounded, gc.getWidth()/gScale - 46, 10, Color.white);
		
		//font.draw("" + penalty, gc.getWidth()/4 - 96, 10, Color.white);
		
		
		int w2 = gc.getWidth()/gScale/2;
		int h2 = gc.getHeight()/gScale/2;
		Image failImage = ImageResources.getFail();
		Image success = ImageResources.getSuccess();
		if(fail)failImage.draw(w2 - failImage.getWidth()/2, h2 - failImage.getHeight()/2);
		if(map.getWeedsLeft() == 0)success.draw(w2 - success.getWidth()/2, h2 - success.getHeight()/2);
		
		
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		
		if(!running.playing())running.play(1f, 0.5f);
		
		System.out.println(map.getID());
	
		
		Input input = gc.getInput();
		
		
		if(input.isKeyPressed(Input.KEY_P)){
			saveTest += 1;
		}
		Plant[] plantArray = map.getPlantArray();
	
		int remX = Player.getX()%16;
		int remY = Player.getY()%16;
		int playerX = (Player.getX() - remX)/16;
		int playerY = (Player.getY() - remY)/16;
		int columns = map.getColumns();
		int buffer = TileSize.buffer;
		

		
		if(input.isKeyDown(Input.KEY_SPACE) && !Player.isMoving() &&
				playerY >= buffer && (playerY - buffer) < map.getRows() && 
				playerX >= buffer && (playerX - buffer) < map.getColumns()){
			
			
			pullTimer += delta * 0.005;
			digging = true;
			//add restriction for top and bottom of screen
			
			if(!map.isBare(playerX - buffer, playerY - buffer))player.setPull(true);
			
			
			if(Player.isMoving()) pullTimer = 0;
			
			
			
			
			if(pullTimer > map.getPlantResistance(playerX - buffer, playerY - buffer) ){
				
					
				if(map.isWeed(playerX - buffer, playerY - buffer) && map.getPlantID(playerX - buffer, playerY - buffer) != 0){
					penalty -= 1;
				}
					
				//pop.play(1f, 0.25f);
				digging = false;
				map.removePlant(playerX - buffer, playerY - buffer);
				pullTimer = 0;
				spaceRelease = false;
				player.setPull(false);
					
					
				}
			
			
		}else {
			player.setPull(false);
			digging = false;
		}
		
		if(digging  && !dig.playing())	dig.play();
		if(!digging)dig.stop();
		
		
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
		
		/*ArrayList<Plant> species = map.getSpecies();
		int check = 0;
		int count = 0;
	
		for(int i = 0; i < map.getTileTotal(); i++){
			
			for(int j = 0; j < species.size(); j++){
				
				if(plantArray[i].getPlantID() == species.get(j).getPlantID())check += 1;  
			}	
			
			if(check == 0 && plantArray[i].getPlantID() != 0)count += 1;
			
			check = 0;
		
			weedTotal = count;
		
		}*/
		
		weedTotal = map.getWeedsLeft();
		
		/*if(weedTotal == 0)success = true;
			else success = false;*/
		
		if(penalty ==0)fail = true;
		
		if(	map.getTime() <= 0){
			fail = true;
			map.setTime(0);
		}
		
		if(fail && input.isKeyPressed(Input.KEY_ENTER)){
			sb.enterState(1);
			fail = false;
			penalty = 3;
			map.setTime(map.getTimeLimit());
			leaveState = true;
			
		}
		
		if(map.getWeedsLeft() == 0 && input.isKeyPressed(Input.KEY_ENTER)){
			//weedTotal = 1;
			Progress.unlock(map.getID() + 1);
			try {
				save.SaveGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(map.getID() == 25)sb.enterState(3);
			else sb.enterState(1);
			leaveState = true;
			
			//success = false;
		}
		
		if(leaveState){
			
			Player.setX(startX);
			Player.setY(startY);
			running.stop();
			success = false;
			fail = false;
			leaveState = false;
			
		}
		
		if(!fail && map.getWeedsLeft() > 0){
			player.update(gc, delta);
			
			map.update(delta);
		}
		
		if(fail)map.setPause(true);
		else map.setPause(false);
		
	}
	
	public static int getSaveTest(){
		return saveTest;
	}

	@Override
	public int getID() {
		return 0;
	}

}
