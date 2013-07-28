package Maps;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Plants.Achillea;
import Plants.Andropogon;
import Plants.AsclepiasS;
import Plants.AsclepiasT;
import Plants.BareGround;
import Plants.Lupinus;
import Plants.Panicum;
import Plants.Plant;
import Plants.Rumex;
import Plants.Schizachyrium;
import Plants.Taraxacum;
import Plants.Tradescantia;
import Weeding.ImageResources;
import Weeding.TileSize;


public class Map {

	int tileSize, gScale, tileTotal, columns, rows, x, y, buffer;
	protected double time, timeLimit;
	protected int weedCounter, nonWeedCounter, numWeeds, nonWeeds, mapID;
	private int weedsLeft;
	protected Plant[] plantArray;
	protected static ArrayList<Plant> speciesList;
	protected Random random;
	protected boolean pause;
	private Image background, ground0, ground1, ground2, ground3, ground4, ground5, ground6, ground7, ground8;
	private SpriteSheet groundSheet;
	private Image[] ground;
	
	
	
	public Map(GameContainer gc) throws SlickException {
		
		random = new Random();
		
		pause = false;
		
		buffer = TileSize.buffer;
		
		x = 0;
		y = 0;
		
		weedsLeft = 10;
		
		
		
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
				
		columns = gc.getWidth()/tileSize/gScale;
		rows = gc.getHeight()/tileSize/gScale;
		
		columns -= buffer * 2;
		rows -= buffer * 2;
		
		tileTotal = columns * rows;
		
		plantArray = new Plant[tileTotal];
		
		speciesList = new ArrayList<Plant>();
		
		//groundSheet = new SpriteSheet("res/ground.png", 16, 16);
		
		//background = new Image("res/dirt.png", false, Image.FILTER_NEAREST);
		
		background = ImageResources.getDirt();
		
		ground = ImageResources.getGround();
		
		ground0 = ground[0]; //center
		ground1 = ground[1]; //top left
		ground2 = ground[2]; //top
		ground3 = ground[3]; //top right
		ground4 = ground[4]; //left
		ground5 = ground[5]; //right
		ground6 = ground[6]; //bottom left
		ground7 = ground[7]; //bottom
		ground8 = ground[8]; //bottom right
		
		/*ground0 = groundSheet.getSprite(1, 1); //center
		ground1 = groundSheet.getSprite(0, 0); //top left
		ground2 = groundSheet.getSprite(1, 0); //top
		ground3 = groundSheet.getSprite(2, 0); //top right
		ground4 = groundSheet.getSprite(0, 1); //left
		ground5 = groundSheet.getSprite(2, 1); //right
		ground6 = groundSheet.getSprite(0, 2); //bottom left
		ground7 = groundSheet.getSprite(1, 2); //bottom
		ground8 = groundSheet.getSprite(2, 2); //bottom right*/
		
		
		
		
		
		/*for(int i = 0; i < (tileTotal - 15); i++){
			int roll = random.nextInt(4);
			int temp = random.nextInt(tileTotal);
			if(roll == 0)plantArray[temp] = new plantZero();
			if(roll == 1)plantArray[temp] = new plantTwo(2);
			if(roll == 2)plantArray[temp] = new plantThree(2);
			if(roll == 3)plantArray[temp] = new plantFour(2);
			
		}
		
		for(int i = 0; i < tileTotal; i++){
			if(plantArray[i] == null)plantArray[i] = new plantOne(2);
		}*/
		
		
		
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
	
		
		int tempRow = 0;
		int tempColumn = 0;
		
		/*for(int i = 0; i < (columns + (buffer * 2)); i++){
			for(int j = 0; j < (rows + (buffer * 2)); j++){
				background.draw(i * tileSize, j * tileSize);
			}
			
			
		}*/
		
		

		for(int b = 0; b < buffer - 1; b++){
			
			for(int i = 0; i < columns + (buffer * 2); i++){
				ground0.draw(i * tileSize, b * 16);
				ground0.draw(i * tileSize, gc.getHeight()/gScale - 16 - (b * 16));
			}
			
			for(int i = 0; i < rows + (buffer * 2); i++){
				ground0.draw(b * 16, i * tileSize);
				ground0.draw(gc.getWidth()/gScale - 16 - (b*16), i * 16);
				
			}
		}
		
		int downBuff = buffer - 1;
		int upBuff = buffer + 1;
		ground1.draw(downBuff * tileSize, downBuff * tileSize);
		ground3.draw(gc.getWidth()/gScale - tileSize - (downBuff*tileSize), downBuff * tileSize);
		ground6.draw(downBuff * tileSize, gc.getHeight()/gScale -tileSize - downBuff * tileSize);
		ground8.draw(gc.getWidth()/gScale - tileSize - (downBuff*tileSize), gc.getHeight()/gScale -tileSize - downBuff * tileSize);
		
		for(int i = 0; i < columns; i++){
			
			ground2.draw(buffer * tileSize + i * tileSize, downBuff * tileSize);
			ground7.draw(buffer * tileSize + i * tileSize, gc.getHeight()/gScale - tileSize - downBuff * tileSize);
		}
		
		for(int i = 0; i < rows; i++){
			
			ground4.draw(downBuff * tileSize , buffer * tileSize + i * tileSize);
			ground5.draw(gc.getWidth()/gScale - tileSize - downBuff * tileSize, buffer * tileSize + i * tileSize);
		}
		
		
			
		
		
		for(int i = 0; i < columns ; i++){
			for(int j = 0; j < (rows); j++){
			background.draw(buffer * tileSize + i * tileSize, buffer * tileSize + j * tileSize);
			}
		
		
		}
		
		for(int i = 0; i < tileTotal; i++){
			
			
			if(tempColumn > columns - 1){
				tempRow += 1;
				tempColumn = 0;
			}
			
			
			plantArray[i].getImage().draw(tileSize * buffer + (tempColumn * tileSize), tileSize * buffer  + (tempRow * tileSize));
			
			tempColumn += 1;
		}
		
		
	
		
	
	}
	
	
	public void update(int delta) throws SlickException {
		
		if(!pause)time -= delta * 0.001;
	
	
		for(int i = 0; i < tileTotal; i++) plantArray[i].update(delta);
		
		for(int i = 0; i < tileTotal; i++){
			
			int numSeeds = random.nextInt(9);
			
			int spread = random.nextInt(8);
			
			Plant plant = plantArray[i];
			
			int plantID = plantArray[i].getPlantID();
			
			Integer[] placementArray = new Integer[8];
	
			Integer[] rollArray = new Integer[8];
			
					for(int j = 0; j < 8; j++){
					int roll = random.nextInt(3);
					if(roll > 1)	rollArray[j] = 1;
					else rollArray[j] = 0;
			
			}
			
			if(plant.getTime() > 20 && 
					(  (plant.hasRhizome() && (plant.getGrowthStage() >= 2)) || (!plant.hasRhizome() && (plant.getGrowthStage() == 3))  ) ){
				
				if(spread == 1){
					
					
				
				//The following determines whether or not a new plant will be placed in a given square around the seeding plant.
				
					//Up
					
					if(rollArray[0] > 0 && i > columns && plantArray[i-columns].getPlantID() == 0){
						
						placementArray[0] = 1;
					}
						
					//Down
					
					if(rollArray[1] > 0  && i < (tileTotal - columns)	&& plantArray[i + columns].getPlantID() == 0){
						
						placementArray[1] = 1;
					}
					
					//Left
					if(rollArray[2] > 0 && (i - 1) % columns != 0 && i > 0){
						if(	plantArray[i - 1].getPlantID() == 0){
							
							placementArray[2] = 1;
						}
					}
						
					//Right
					if(rollArray[3] > 0 && i % columns != 0 && i + 1 < tileSize){
						if( plantArray[i + 1].getPlantID() == 0){
							
							placementArray[3] = 1;
						}
					}
					
					//UpLeft
					
					
					if( rollArray[4] > 0 && i > columns  && (i - 1) % columns != 0 && plantArray[i-columns-1].getPlantID() == 0){
						
						placementArray[4] = 1;
					}
					
					//UpRight
					
					
					if(rollArray[5] > 0 && i > columns  && i % columns != 0 && plantArray[i-columns+1].getPlantID() == 0){
						
						placementArray[5] = 1;
					}
					
					//DownLeft
					
					
					if(rollArray[6] > 0  && i < (tileTotal - columns)	&& (i - 1) % columns != 0 && plantArray[i + columns - 1].getPlantID() == 0){
						
						placementArray[6] = 1;
					}
					
					//DownRight
						
					
					if(rollArray[7] > 0  && i < (tileTotal - columns - 1)	&& i % columns != 0 && plantArray[i + columns + 1].getPlantID() == 0){
						
						placementArray[7] = 1;
					}
					
	      
					//The following pair of switches places a new plant in the correct squares.
					
					for(int j = 0; j < 8; j ++){
						
						int square = 0;
						
						if(placementArray[j] != null && placementArray[j] > -1)
						switch(j){ 
							
							case 0: square = i - columns;
								break;
							case 1: square = i + columns;
								break;
							case 2: square = i - 1;
								break;
							case 3: square = i + 1;
								break;
							case 4: square = i - columns - 1;
								break;
							case 5: square = i - columns + 1;
								break;
							case 6: square = i + columns - 1;
								break;
							case 7: square = i + columns + 1;
								break;
							default: square = 0;
							
							
						}
						
						int sproutType = 0;
						
						
						
						if(square > 0){
							switch(plantID) {
							case 1: plantArray[square] = new Andropogon(0);
								break;
							case 2: plantArray[square] = new AsclepiasS(0);
								break;
							case 3: plantArray[square] = new Tradescantia(0);
								break;
							case 4: plantArray[square] = new Schizachyrium(0);
								break;
							case 5: plantArray[square] = new AsclepiasT(0);
								break;
							case 6: plantArray[square] = new Achillea(0);
								break;
							case 7: plantArray[square] = new Rumex(0);
								break;
							case 8: plantArray[square] = new Taraxacum(0);
								break;
							case 9: plantArray[square] = new Lupinus(0);
								break;
							case 10: plantArray[square] = new Panicum(0);
								break;
							default: plantArray[square] = new BareGround();
								break;
							
							}
							
							plantArray[i].resetTimer();
							if(plantArray[i].isPerennial()) plantArray[i].setGrowthStage(2);
							else plantArray[i].setGrowthStage(0);
							plantArray[square].resetTimer();
						}
						
						
					}
					
					for(int j = 0; j < 8; j++){
						placementArray[j] = -1;
					}
						
					
					
				}  
				
				plantArray[i].resetTimer();
			}
			

		}
		
		int check = 0;
		int count = 0;
		
			for(int i = 0; i < tileTotal; i++){
				
				for(int j = 0; j < speciesList.size(); j++){
					
					if(plantArray[i].getPlantID() == speciesList.get(j).getPlantID())check += 1;  
				}	
				
				if(check == 0 && plantArray[i].getPlantID() != 0)count += 1;
				
				check = 0;
			
				weedsLeft = count;
		}
	}
	
	public void removePlant(int xTile, int yTile) throws SlickException {
		
		
		try {
			plantArray[(yTile * columns) + xTile] = new BareGround();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		} 
		
	}
	
	public int getWeedsLeft(){
		return weedsLeft;
	}
	
	public int getPlantID(int xTile, int yTile) throws SlickException {
		
		return plantArray[(yTile * columns) + xTile].getPlantID();
	}
	
	public boolean isWeed(int xTile, int yTile) throws SlickException {
		
		int sum = 0;
		Plant temp = plantArray[(yTile * columns) + xTile];
		
		for(int i = 0; i < speciesList.size(); i++){
			if(speciesList.get(i).getPlantID() == temp.getPlantID() && temp.getPlantID() != 0) sum += 1 ;
		}
		
		if(sum > 0)	return true;
		else return false ;
	}
	
	public int getPlantResistance(int xTile, int yTile){
		
		try {
			return plantArray[(yTile * columns) + xTile].getResistance();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} 
	
	}
	
	public int getTileTotal(){
		return tileTotal;
	}
	
	public Plant[] getPlantArray(){
		return plantArray;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int getRows(){
		return rows;
	}
	
	public double getTime(){
		return time;
	}
	
	public double getTimeLimit(){
		return timeLimit;
	}
	
	public void setPause(boolean value){
		pause = value;
	}
	
	public void setTime(double number){
		time = number;
	}
	
	public void setPlant(int arrayIndex, Plant plant){
		
		plantArray[arrayIndex] = plant;
	}
	
	public static ArrayList<Plant> getSpecies(){
		return speciesList;
	}
	
	public int getID(){
		return mapID;
	}
}
