package Maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class CurrentMap {

	private static Map currentMap;

	
	public CurrentMap(GameContainer gc) throws SlickException {
		
		
		currentMap = new Plot01(gc);
		
	}
	
	public static Map getCurrentMap(){
		return currentMap;
	}
	
	public static void setCurrentMap(Map map){
		currentMap = map;
	}
	
	public static void setCurrentMap(int plotNum, GameContainer gc) throws SlickException{
		

		if(plotNum == 1)currentMap = new Plot01(gc);
		if(plotNum == 2)currentMap = new Plot02(gc);
		if(plotNum == 3)currentMap = new Plot03(gc);
		if(plotNum == 4)currentMap = new Plot04(gc);
		if(plotNum == 5)currentMap = new Plot06(gc);
		if(plotNum == 6)currentMap = new Plot05(gc);
		if(plotNum == 7)currentMap = new Plot07(gc);
		if(plotNum == 8)currentMap = new Plot08(gc);
		if(plotNum == 9)currentMap = new Plot09(gc);
		if(plotNum == 10)currentMap = new Plot10(gc);
		if(plotNum == 11)currentMap = new Plot11(gc);
		if(plotNum == 12)currentMap = new Plot12(gc);
		if(plotNum == 13)currentMap = new Plot13(gc);
		if(plotNum == 14)currentMap = new Plot14(gc);
		if(plotNum == 15)currentMap = new Plot15(gc);
		if(plotNum == 16)currentMap = new Plot16(gc);
		if(plotNum == 17)currentMap = new Plot17(gc);
		if(plotNum == 18)currentMap = new Plot18(gc);
		if(plotNum == 19)currentMap = new Plot19(gc);
		if(plotNum == 20)currentMap = new Plot20(gc);
		if(plotNum == 21)currentMap = new Plot21(gc);
		if(plotNum == 22)currentMap = new Plot22(gc);
		if(plotNum == 23)currentMap = new Plot23(gc);
		if(plotNum == 24)currentMap = new Plot24(gc);
		
		
		
	}
}
