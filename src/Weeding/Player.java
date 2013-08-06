package Weeding;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Player {

	private static int x, y;
	private int tileSize = TileSize.tileSize, gScale = TileSize.gScale;
	private static int yLocation, xLocation;
	private float timer = 0;
	private static boolean isRight, isLeft, isUp, isDown, isMoving, isPull;
	private Animation rightStill, leftStill, upStill, downStill, right, left, up, down, sprite,
		pFront, pBack, pLeft, pRight;
	private Image[] downy, lefty, uppy, righty, down0, left0, up0, right0, 
		pullyFront, pullyBack, pullyLeft, pullyRight;
	private Image[] spriteSheet, spritePull;
	
	
	
	public Player(int x, int y) throws SlickException{
		
		spriteSheet = ImageResources.getSprite();
		spritePull = ImageResources.getSpritePull();
		
		
		downy = new Image[]{spriteSheet[1], spriteSheet[2]};
		lefty = new Image[]{spriteSheet[4], spriteSheet[5]};
		uppy = new Image[]{spriteSheet[7], spriteSheet[8]};
		righty = new Image[]{spriteSheet[10], spriteSheet[11]};
		
		down0 = new Image[]{spriteSheet[0]};
		left0 = new Image[]{spriteSheet[3]};
		up0 = new Image[]{spriteSheet[6]};
		right0 = new Image[]{spriteSheet[9]};
		
		pullyFront = new Image[]{spritePull[0], spritePull[1]};
		pullyLeft = new Image[]{spritePull[2], spritePull[3]};
		pullyBack = new Image[]{spritePull[4], spritePull[5]};
		pullyRight = new Image[]{spritePull[6], spritePull[7]};
		
		
		right = new Animation(righty, 200);
		left = new Animation(lefty, 200);
		up = new Animation(uppy, 200);
		down = new Animation(downy, 200);
		
		rightStill = new Animation(right0, 200);
		leftStill = new Animation(left0, 200);
		upStill = new Animation(up0, 200);
		downStill = new Animation(down0, 200);
		
		pFront = new Animation(pullyFront, 400);
		pBack = new Animation(pullyBack, 400);
		pLeft = new Animation(pullyLeft, 400);
		pRight = new Animation(pullyRight, 400);
		
		sprite = downStill;
		
		isUp = false;
		isDown = true;
		isLeft = false;
		isRight = false;
		
		isMoving = false;
		
		isPull = false;
		
		this.x = x;
		this.y = y;
		
		yLocation = (tileSize + y)/tileSize;
		xLocation = (tileSize + x)/tileSize;
		
		
		
		
	}
	
	public void render(GameContainer gc, Graphics g){
		
		/*x = Mouse.getX()/4;
		y = (-Mouse.getY() + gc.getHeight()) / 4;
		sprite.draw(x - 8 , y - 8); */
		
		//sprite.draw(Mouse.getX()/4 , (-Mouse.getY() + gc.getHeight())/4 );
		
		sprite.draw(x, y);
		
	}
	
	public void update(GameContainer gc, int delta){
		
		int buffer = TileSize.buffer;
		
		yLocation = (tileSize  + y)/tileSize;
		xLocation = (tileSize  + x)/tileSize;
		
		
		Input input = gc.getInput();
		
	
		timer += delta * 0.25;
		
		

		if(input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_UP)){
			
			
			
				if(yLocation > 1)y -= tileSize;
			
			
			isUp = true;
			isDown = false;
			isLeft = false;
			isRight = false;
			
			sprite = up;
			
			timer = 0;
			
			input.clearKeyPressedRecord();
		}
		
		if(input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_DOWN)){
			
			
				if(yLocation < gc.getHeight()/(tileSize * gScale))y += tileSize;
			
			
			isUp = false;
			isDown = true;
			isLeft = false;
			isRight = false;
			
			sprite = down;
			
			timer = 0;
			
			input.clearKeyPressedRecord();
		}
		
		if(input.isKeyPressed(Input.KEY_A) || input.isKeyPressed(Input.KEY_LEFT)){
			
			
				if(xLocation > 1)x -= tileSize;
			
			
			isUp = false;
			isDown = false;
			isLeft = true;
			isRight = false;
			
			sprite = left;
			
			timer = 0;
			
			input.clearKeyPressedRecord();
		}
		
		if(input.isKeyPressed(Input.KEY_D) || input.isKeyPressed(Input.KEY_RIGHT)){
			
			
				if(xLocation < gc.getWidth()/(tileSize * gScale))x += tileSize;
			
			
			isUp = false;
			isDown = false;
			isLeft = false;
			isRight = true;
			
			sprite = right;
			
			timer = 0;
			
			input.clearKeyPressedRecord();
		}
		
		boolean one, two, three, four;
		
		
		
			if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)){
				
				if(timer > 50){
					timer = 0;
					if(yLocation > 1)y -= tileSize;
				}
				one = true;
				isPull = false;
				
				
			}else one = false;
		
			if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)){
				
				if(timer > 50){
					timer = 0;
					if(yLocation < gc.getHeight()/(tileSize * gScale))y += tileSize;
				}
			
				two = true;
				isPull = false;
				
			
			}else two = false;
			
			if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){
				if(timer > 50){
					timer = 0;
					
					if(xLocation > 1)x -= tileSize;
				}
				
				three = true;
				isPull = false;
			
			}else three = false;
			
			if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
				if(timer > 50){
					timer = 0;
					if(xLocation < gc.getWidth()/(tileSize * gScale))x += tileSize;
				}
				
				four = true;
				isPull = false;
			
			
			}else four = false;
			
			if(one || two || three || four)isMoving = true;
			else isMoving = false;
			
			
		
		
		
		if(isRight && !input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_RIGHT)) sprite = rightStill;
		if(isLeft && !input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_LEFT)) sprite = leftStill;
		if(isUp && !input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_UP)) sprite = upStill;
		if(isDown && !input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_DOWN)) sprite = downStill;
		
		if(isPull){
			if(isRight)sprite = pRight;
			if(isLeft)sprite = pLeft;
			if(isUp)sprite = pBack;
			if(isDown)sprite = pFront;
		}
		
	}
	
	public void setPull(boolean value){
		 
		isPull = value;
	}
	
	
	
	public static int getYLocation(){
		return yLocation;
	}
	
	public static int getXLocation(){
		return xLocation;
	}
	
	public static int getX(){
		return x;
	}
	
	public static int getY(){
		return y;
	}
	
	public static void addX(int ex){
		x += ex;
		
	}
	
	public static void addY(int why ){
		y += why;
	}
	
	public static void setX( int ex){
		x = ex;
	}
	
	public static void setY( int why){
		y = why;
	}
	
	public static boolean isLeft(){
		return isLeft;
	}
	
	public static boolean isRight(){
		return isRight;
	}
	
	public static boolean isUp(){
		return isUp;
	}
	
	public static boolean isDown(){
		return isDown;
	}
	
	public static boolean isMoving(){
		return isMoving;
	}
	
	public static void setForward(){
		isDown = true;
	}
}
