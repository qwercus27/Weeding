package Maps;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Plants.Plant;

public class RetrieveMap {
	
	ArrayList<Plant> species;
	Map map;

	public RetrieveMap(){
		
		
	}
	
	public  ArrayList<Plant> getSpecies(int plotNum, GameContainer gc) throws SlickException{
		
		if(plotNum == 1)map = new Plot01(gc);
		if(plotNum == 2)map = new Plot02(gc);
		if(plotNum == 3)map = new Plot03(gc);
		if(plotNum == 4)map = new Plot04(gc);
		if(plotNum == 5)map = new Plot06(gc);
		if(plotNum == 6)map = new Plot05(gc);
		if(plotNum == 7)map = new Plot07(gc);
		if(plotNum == 8)map = new Plot08(gc);
		if(plotNum == 9)map = new Plot09(gc);
		if(plotNum == 10)map = new Plot10(gc);
		if(plotNum == 11)map = new Plot11(gc);
		if(plotNum == 12)map = new Plot12(gc);
		if(plotNum == 13)map = new Plot13(gc);
		if(plotNum == 14)map = new Plot14(gc);
		if(plotNum == 15)map = new Plot15(gc);
		if(plotNum == 16)map = new Plot16(gc);
		if(plotNum == 17)map = new Plot17(gc);
		if(plotNum == 18)map = new Plot18(gc);
		if(plotNum == 19)map = new Plot19(gc);
		if(plotNum == 20)map = new Plot20(gc);
		if(plotNum == 21)map = new Plot21(gc);
		if(plotNum == 22)map = new Plot22(gc);
		if(plotNum == 23)map = new Plot23(gc);
		if(plotNum == 24)map = new Plot24(gc);
		
		if(plotNum > 24 || plotNum < 1)map = new Plot01(gc);
		
		return map.getSpecies();
	}
}
