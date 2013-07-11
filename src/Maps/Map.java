package Maps;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Plants.Plant;
import Plants.Schiz;
import Plants.Grass;
import Plants.Spiderwort;
import Plants.Milkweed;
import Plants.BareGround;
import Weeding.TileSize;


public class Map {

	int tileSize, gScale, tileTotal, columns, rows, x, y, buffer;
	protected Plant[] plantArray;
	protected static ArrayList<Plant> speciesList;
	protected Random random;
	private Image background;
	
	
	public Map(GameContainer gc) throws SlickException {
		
		random = new Random();
		
		buffer = TileSize.buffer;
		
		x = 0;
		y = 0;
		
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
				
		columns = gc.getWidth()/tileSize/gScale;
		rows = gc.getHeight()/tileSize/gScale;
		
		columns -= buffer * 2;
		rows -= buffer * 2;
		
		tileTotal = columns * rows;
		
		plantArray = new Plant[tileTotal];
		
		speciesList = new ArrayList<Plant>();
		
		background = new Image("res/dirt.png", false, Image.FILTER_NEAREST);
		
		
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
		
		for(int i = 0; i < (columns + (buffer * 2)); i++){
			for(int j = 0; j < (rows + (buffer * 2)); j++){
				background.draw(i * tileSize, j * tileSize);
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
		
		
		for(int i = 0; i < tileTotal; i++) plantArray[i].update(delta);
		
		for(int i = 0; i < tileTotal; i++){
			
			int numSeeds = random.nextInt(4);
			
			int spread = random.nextInt(10);
			
			int plantID = plantArray[i].getPlantID();
	
					
			if(plantArray[i].getTime() > 20 && (plantArray[i].getGrowthStage() == 3 || plantArray[i].getGrowthStage() == 0)){
				
				if(spread == 1){
					
					
					
				//	int direction = random.nextInt(4);
					
					//Up
					
					if(numSeeds == 1 && i > columns
							//&& plantArray[i].getStrength() > plantArray[i - columns].getStrength()
							&& plantArray[i - columns].getPlantID() == 0){
						//plantArray[i - columns] = plantArray[i];
						switch (plantID) {
						case 1: plantArray[i - columns] = new Grass(0);
							break;
						case 2: plantArray[i - columns] = new Milkweed(0);
							break;
						case 3: plantArray[i - columns] = new Spiderwort(0);
							break;
						case 4: plantArray[i - columns] = new Schiz(0);
							break;

						default: plantArray[i - columns] = new BareGround();
							break;
						}
						
						plantArray[i].resetTimer();
						plantArray[i].setGrowthStage(0);
						plantArray[i - columns].resetTimer();
					}
					
					//Down
					
					if(numSeeds <= 2  && i < (tileTotal - columns)
							//&& plantArray[i].getStrength() > plantArray[i + columns].getStrength()
							&& plantArray[i + columns].getPlantID() == 0){
					//	plantArray[i + columns] = plantArray[i];
						switch (plantID) {
						case 1: plantArray[i + columns] = new Grass(0);
							break;
						case 2: plantArray[i + columns] = new Milkweed(0);
							break;
						case 3: plantArray[i + columns] = new Spiderwort(0);
							break;
						case 4: plantArray[i + columns] = new Schiz(0);
							break;

						default: plantArray[i + columns] = new BareGround();
							break;
						}
						plantArray[i].resetTimer();
						plantArray[i].setGrowthStage(0);
						plantArray[i + columns].resetTimer();
					}
					
					//Left
					if(numSeeds <= 3 && (i - 1) % columns != 0 && i > 0){
						if( // plantArray[i].getStrength() > plantArray[i - 1].getStrength() &&
								plantArray[i - 1].getPlantID() == 0){
							
						//	plantArray[i - 1] = plantArray[i];
							
							switch (plantID) {
							case 1: plantArray[i - 1] = new Grass(0);
								break;
							case 2: plantArray[i - 1] = new Milkweed(0);
								break;
							case 3: plantArray[i - 1] = new Spiderwort(0);
								break;
							case 4: plantArray[i - 1] = new Schiz(0);
								break;

							default: plantArray[i - 1] = new BareGround();
								break;
							}
							plantArray[i].resetTimer();
							plantArray[i].setGrowthStage(0);
							plantArray[i - 1].resetTimer();
						}
					}
					
					//Right
					if(numSeeds <= 4 && i % columns != 0 && i + 1 < tileSize){
						
						if( // plantArray[i].getStrength() > plantArray[i + 1].getStrength() &&
								 plantArray[i + 1].getPlantID() == 0){
							
							//plantArray[i + 1] = plantArray[i];
							
							switch (plantID) {
							case 1: plantArray[i + 1] = new Grass(0);
								break;
							case 2: plantArray[i + 1] = new Milkweed(0);
								break;
							case 3: plantArray[i + 1] = new Spiderwort(0);
								break;
							case 4: plantArray[i + 1] = new Schiz(0);
								break;

							default: plantArray[i + 1] = new BareGround();
								break;
							}
							plantArray[i].resetTimer();
							plantArray[i].setGrowthStage(0);
							plantArray[i + 1].resetTimer();
						}
					}
					
				}
				
				plantArray[i].resetTimer();
			}
			

		}
	}
	
	public void removePlant(int xTile, int yTile) throws SlickException {
		
		
		
		try {
			plantArray[(yTile * columns) + xTile] = new BareGround();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		} 
		
	}
	
	public int getPlantResistance(int xTile, int yTile){
		
		try {
			return plantArray[(yTile * columns) + xTile].getResistance();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} 
	
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
	
	public void setPlant(int arrayIndex, Plant plant){
		
		plantArray[arrayIndex] = plant;
	}
	
	public static ArrayList<Plant> getSpecies(){
		return speciesList;
	}
}
