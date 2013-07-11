package Plants;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Weeding.TileSize;


public class Plant {
	
	protected Image image, seed, sprout, flower, seeding;
	protected static SpriteSheet sheet;
	protected boolean weed;
	protected int strength, resistance, growthRoll;
	protected float timer, growthTimer;
	protected int timerSpeed, tileSize, growthStage, plantNumber;
	protected Random random;
	
	public Plant() throws SlickException {
		
		tileSize = TileSize.tileSize;
	
		if(sheet == null)sheet = new SpriteSheet("res/plantSheet.png", tileSize, tileSize);
	
		
		growthStage = 2;
		
		
		random = new Random();
		
	
	}
	
	
	public int getPlantID(){
		return plantNumber;
	}
	
	public Image getImage(){
		return image;
	}
	
	public boolean isWeed(){
		return weed;
	}
	
	public int getStrength(){
		return strength;
	}
	
	public int getResistance(){
		return resistance;
	}
	
	public void update(int delta){
		 timer += delta * 0.0025 * timerSpeed;
		 
		 growthTimer += delta * 0.0025 * timerSpeed;
		 
		
		 }
	
	
	public void resetTimer(){
		timer = 0;
	}
	
	public float getTime(){
		return timer;
	}
	
	public float getGrowthTime(){
		return growthTimer;
	}
	
	public void resetGrowthTimer(){
		growthTimer = 0;
	}
	
	public int getGrowthStage(){
		return growthStage;
	}
	
	public void addGrowthStage(){
		if(growthStage <= 2)growthStage += 1;
		else growthStage = 0;
	}
	
	public void setGrowthStage(int number){
		growthStage = number;
	}
	
	public void setImage(){
		if(growthTimer == 0)image = seed;
		if(growthTimer == 1)image = sprout;
		if(growthTimer == 2)image = flower;
		if(growthTimer == 3)image = seeding;
	}
	
	public void setImage(Image i){
		image = i;
	}
	
	

}
