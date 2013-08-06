package Weeding;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageResources {

	private static Image dandelion, leaf, clock, dirt, plot, bigPlot, lockedPlot, cursor, question,
		fail, success;
	private static Image[] sprite, spritePull, ground, arrow;
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
		
		spritePull = new Image[8];
		spritePull[0] = sheet.getSprite(0,1);
		spritePull[1] = sheet.getSprite(1,1);
		spritePull[2] = sheet.getSprite(3,1);
		spritePull[3] = sheet.getSprite(4,1);
		spritePull[4] = sheet.getSprite(6,1);
		spritePull[5] = sheet.getSprite(7,1);
		spritePull[6] = sheet.getSprite(9,1);
		spritePull[7] = sheet.getSprite(10,1);
		
		ground = new Image[9];
		ground[0] = sheet.getSprite(1,3);
		ground[1] = sheet.getSprite(0,2);
		ground[2] = sheet.getSprite(1,2);
		ground[3] = sheet.getSprite(2,2);
		ground[4] = sheet.getSprite(0,3);
		ground[5] = sheet.getSprite(2,3);
		ground[6] = sheet.getSprite(0,4);
		ground[7] = sheet.getSprite(1,4);
		ground[8] = sheet.getSprite(2,4);
		
		dandelion = sheet.getSprite(3,2);
		leaf = sheet.getSprite(4,2);
		clock = sheet.getSprite(5,2);
		
		dirt = sheet.getSprite(3,3);
		plot = sheet.getSprite(4,3);
		bigPlot = sheet.getSubImage(128, 32, 64, 64);
		lockedPlot = sheet.getSprite(6,3);
		
		question = sheet.getSubImage(112, 48, 16, 32);
		
		arrow = new Image[8];
		arrow[0] = sheet.getSprite(0,5);
		arrow[1] = sheet.getSprite(1,5);
		arrow[2] = sheet.getSprite(2,5);
		arrow[3] = sheet.getSprite(3,5);
		arrow[4] = sheet.getSprite(4,5);
		arrow[5] = sheet.getSprite(5,5);
		arrow[6] = sheet.getSprite(6,5);
		arrow[7] = sheet.getSprite(7,5);
		
		cursor = sheet.getSprite(3,4);
		
		fail = new Image("res/fail.png");
		success = new Image("res/success.png");
		
	}
	
	public static Image[] getSprite(){
		return sprite;
	}
	
	public static Image[] getSpritePull(){
		return spritePull;
	}
	
	public static Image[] getGround(){
		return ground;
	}
	
	public static Image[] getArrow(){
		return arrow;
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
	
	public static Image getBigPlot(){
		return bigPlot;
	}
	
	public static Image getLockedPlot(){
		return lockedPlot;
	}
	
	public static Image getQuestion(){
		return question;
	}
	
	public static Image getPath(){
		return ground[0];
	}
	
	public static Image getCursor(){
		return cursor;
	}
	
	public static Image getFail(){
		return fail;
	}
	
	public static Image getSuccess(){
		return success;
	}
}
