package Weeding;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameStates.TileSize;

public class Cursor {

	private Image highlight, arrowLeft, arrowRight;
	private int tileSize, xDistance, yDistance, x, y, xLocation, yLocation, highlightX, highlightY;
	private int remainderX, remainderY;
	private Vector2f up, down, left, right, currentPosition;
	private float timer = 0;
	
	public Cursor() throws SlickException{
		
		highlight = new Image("res/cursor.png", false, Image.FILTER_NEAREST);
		arrowLeft = new Image("res/arrowLeft.png", false, Image.FILTER_NEAREST);
		arrowRight = new Image("res/arrowRight.png", false, Image.FILTER_NEAREST);
		tileSize = TileSize.tileSize;
		
		xDistance = 0;
		yDistance = 1;
		
		up = new Vector2f(Player.getX(), Player.getY() - tileSize);
		down = new Vector2f(Player.getX(), Player.getY() + tileSize);
		left = new Vector2f(Player.getX() - tileSize, Player.getY());
		right = new Vector2f(Player.getX() + tileSize, Player.getY());
		
		currentPosition = down;
	}
	
	public void render(GameContainer gc, Graphics g){
		
		//xLocation = Player.getXLocation();
    //	highlightX = Player.getXLocation() - 1 + xDistance;
	//	highlightY = Player.getYLocation() - 1 + yDistance;
	//	yLocation = Player.getYLocation();
		
		
		x = Player.getX() ;
		y = Player.getY() ;
		
		remainderX = x%16;
		remainderY = y%16;
		
		xLocation = ((x - remainderX)/16);
		yLocation = ((y - remainderY)/16);
		
		highlightX = xLocation + xDistance;
		highlightY = yLocation + yDistance;
		

		
		
		/*if(Player.getYLocation() != 1 && Player.getYLocation() != gc.getHeight()/(tileSize *4)){
			highlight.draw(highlightX * tileSize, yLocation * tileSize);
		}
		
		else if(Player.getYLocation() == 1 || Player.getYLocation() == gc.getHeight()/(tileSize * 4)){
			arrowLeft.draw((xLocation - 1) * tileSize, yLocation * tileSize);
			arrowRight.draw((xLocation + 1) * tileSize, yLocation * tileSize);
		}
		*/
		
		
		highlight.draw(highlightX * 16, highlightY * 16);
	}
	
	public void update(GameContainer gc, int delta){
		
		/*up.set(Player.getX(), Player.getY() - tileSize);
		down.set(Player.getX(), Player.getY() + tileSize);
		left.set(Player.getX() - tileSize, Player.getY());
		right.set(Player.getX() + tileSize, Player.getY());*/
		
		System.out.println(xLocation + " " + yLocation + " " + highlightX + " " + highlightY);
		
		if(Player.isUp()){
			xDistance = 0;
			yDistance = -1;
		}
		else if(Player.isDown()){
			xDistance = 0;
			yDistance = 1;
		}
		else if(Player.isLeft()){
			xDistance = -1;
			yDistance = 0;
		}
		else if(Player.isRight()){
			xDistance = 1;
			yDistance = 0;
		}
		
		
		
		
		/*Input input = gc.getInput();
		
		timer += delta * 0.5;
		
		if(Player.getYLocation() != 1 && Player.getYLocation() != gc.getHeight()/(tileSize *4)){
			
			if(input.isKeyPressed(Input.KEY_A)){
				
				timer = -50;
				if(distance == 1) distance = -1;
				else if(distance > -2) distance -= 1;
			}
			
			if(input.isKeyPressed(Input.KEY_D)){
				
				timer = -50;
				if(distance == -1) distance = 1;
				else if(distance < 2)distance += 1;
			}
			
			if(timer > 50){
			
				if(input.isKeyDown(Input.KEY_A)){
					timer = 0;
				
					if(distance == 1) distance = -1;
					else if(distance > -2) distance -= 1;
				}
			
				if(input.isKeyDown(Input.KEY_D)){
					timer = 0;
				
					if(distance == -1) distance = 1;
					else if(distance < 2)distance += 1;
				
				}
			
			}
		}*/
		
		
	
		
	}
	
	public int getX(){
		return highlightX;
	}
	
	public int getY(){
		return highlightY;
	}
}
