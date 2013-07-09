package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class CurrentMap {

	private static Map currentMap;

	
	public CurrentMap(GameContainer gc) throws SlickException {
		
		
		currentMap = new Plot0(gc);
		
	}
	
	public static Map getCurrentMap(){
		return currentMap;
	}
	
	public static void setCurrentMap(Map map){
		currentMap = map;
	}
	
	public static void setCurrentMap(int plotNum, GameContainer gc) throws SlickException{
		
		if(plotNum == 0)currentMap = new Plot0(gc);
		if(plotNum == 1)currentMap = new Plot1(gc);
		if(plotNum == 2)currentMap = new Plot2(gc);
		//if(plotNum == 3)currentMap = new Plot3(gc);
		
	}
}
