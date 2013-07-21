package Maps;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
import Weeding.TileSize;


public class Map {

	int tileSize, gScale, tileTotal, columns, rows, x, y, buffer;
	protected double timeLimit;
	protected int weedCounter, nonWeedCounter, numWeeds, nonWeeds;
	protected Plant[] plantArray;
	protected static ArrayList<Plant> speciesList;
	protected Random random;
	protected boolean pause;
	private Image background;
	
	
	
	public Map(GameContainer gc) throws SlickException {
		
		random = new Random();
		
		pause = false;
		
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
		
		if(!pause)timeLimit -= delta * 0.001;
	
	
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
	}
	
	public void removePlant(int xTile, int yTile) throws SlickException {
		
		
		try {
			plantArray[(yTile * columns) + xTile] = new BareGround();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		} 
		
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
		return timeLimit;
	}
	
	public void setPause(boolean value){
		pause = value;
	}
	
	public void setTime(double number){
		timeLimit = number;
	}
	
	public void setPlant(int arrayIndex, Plant plant){
		
		plantArray[arrayIndex] = plant;
	}
	
	public static ArrayList<Plant> getSpecies(){
		return speciesList;
	}
}
