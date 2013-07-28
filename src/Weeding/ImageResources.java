package Weeding;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageResources {

	private static Image dandelion, leaf, clock, dirt, plot, lockedPlot, cursor;
	private static Image[] sprite, ground;
	private SpriteSheet sheet;
	private Image sheetImage;
	
	public ImageResources() throws SlickException{
		
		if(sheetImage == null)sheetImage = new Image("res/tileSheet.png", false, Image.FILTER_NEAREST);
		sheet = new SpriteSheet(sheetImage, 16, 16);
		
		sprite = new Image[12];
		sprite[0] = sheet.getSprite(0,0);
		sprite[1] = sheet.getSprite(1,0);
		sprite[2] = sheet.getSprite(2,0);
		sprite[3] = sheet.getSprite(3,0);
		sprite[4] = sheet.getSprite(4,0);
		sprite[5] = sheet.getSprite(5,0);
		sprite[6] = sheet.getSprite(6,0);
		sprite[7] = sheet.getSprite(7,0);
		sprite[8] = sheet.getSprite(8,0);
		sprite[9] = sheet.getSprite(9,0);
		sprite[10] = sheet.getSprite(10,0);
		sprite[11] = sheet.getSprite(11,0);
		
		
		ground = new Image[9];
		ground[0] = sheet.getSprite(1,2);
		ground[1] = sheet.getSprite(0,1);
		ground[2] = sheet.getSprite(1,1);
		ground[3] = sheet.getSprite(2,1);
		ground[4] = sheet.getSprite(0,2);
		ground[5] = sheet.getSprite(2,2);
		ground[6] = sheet.getSprite(0,3);
		ground[7] = sheet.getSprite(1,3);
		ground[8] = sheet.getSprite(2,3);
		
		dandelion = sheet.getSprite(3,1);
		leaf = sheet.getSprite(4,1);
		clock = sheet.getSprite(5,1);
		
		dirt = sheet.getSprite(3,2);
		plot = sheet.getSprite(4,2);
		lockedPlot = sheet.getSprite(6,2);
		
		cursor = sheet.getSprite(3,3);
		
	}
	
	public static Image[] getSprite(){
		return sprite;
	}
	
	public static Image[] getGround(){
		return ground;
	}
	
	public static Image getDandelion(){
		return dandelion;
	}
	
	public static Image getLeaf(){
		return leaf;
	}
	
	public static Image getClock(){
		return clock;
	}
	
	public static Image getDirt(){
		return dirt;
	}
	
	public static Image getPlot(){
		return plot;
	}
	
	public static Image getLockedPlot(){
		return lockedPlot;
	}
	
	public static Image getPath(){
		return ground[0];
	}
	
	public static Image getCursor(){
		return cursor;
	}
}
