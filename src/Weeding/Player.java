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

	private Image image;
	private static int x, y;
	private int tileSize = TileSize.tileSize, gScale = TileSize.gScale;
	private static int yLocation, xLocation;
	private float timer = 0;
	private SpriteSheet spriteSheet;
	private static boolean isRight, isLeft, isUp, isDown;
	private Animation rightStill, leftStill, upStill, downStill, right, left, up, down, sprite;
	private Image[] downy, lefty, uppy, righty, down0, left0, up0, right0;
	
	
	public Player(int x, int y) throws SlickException{
		
		image = new Image("res/red.png", false, Image.FILTER_NEAREST);
		
		
		
		spriteSheet = new SpriteSheet("res/characterSprites.png", 16, 16);
		
		downy = new Image[]{spriteSheet.getSprite(1, 0), spriteSheet.getSprite(2, 0)};
		lefty = new Image[]{spriteSheet.getSprite(4, 0), spriteSheet.getSprite(5, 0)};
		uppy = new Image[]{spriteSheet.getSprite(7, 0), spriteSheet.getSprite(8, 0)};
		righty = new Image[]{spriteSheet.getSprite(10, 0), spriteSheet.getSprite(11, 0)};
		
		down0 = new Image[]{spriteSheet.getSprite(0, 0)};
		left0 = new Image[]{spriteSheet.getSprite(3, 0)};
		up0 = new Image[]{spriteSheet.getSprite(6, 0)};
		right0 = new Image[]{spriteSheet.getSprite(9, 0)};
		
		
		
		
		right = new Animation(righty, 200);
		left = new Animation(lefty, 200);
		up = new Animation(uppy, 200);
		down = new Animation(downy, 200);
		
		rightStill = new Animation(right0, 200);
		leftStill = new Animation(left0, 200);
		upStill = new Animation(up0, 200);
		downStill = new Animation(down0, 200);
		
		sprite = downStill;
		
		isUp = false;
		isDown = true;
		isLeft = false;
		isRight = false;
		
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
		
		System.out.println(x + " " + y);

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
		
		
		if(timer > 50){
		
			if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)){
				timer = 0;
			
				if(yLocation > 1)y -= tileSize;
			}
		
			if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)){
				timer = 0;
			
				if(yLocation < gc.getHeight()/(tileSize * gScale))y += tileSize;
			
			}
			
			if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){
				timer = 0;
			
				if(xLocation > 1)x -= tileSize;
			
			}
			
			if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
				timer = 0;
			
				if(xLocation < gc.getWidth()/(tileSize * gScale))x += tileSize;
			
			}
		
		}
		
		if(isRight && !input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_RIGHT)) sprite = rightStill;
		if(isLeft && !input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_LEFT)) sprite = leftStill;
		if(isUp && !input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_UP)) sprite = upStill;
		if(isDown && !input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_DOWN)) sprite = downStill;
		
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
}
