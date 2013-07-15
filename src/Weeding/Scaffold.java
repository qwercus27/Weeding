package Weeding;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Scaffold {

	private Image image;
	private int x, tileSize;
	private int location;
	private float timer = 0;
	
	public Scaffold(int x) throws SlickException {
		
		image = new Image("res/gray.png", false, Image.FILTER_NEAREST);
		
		tileSize = TileSize.tileSize;
		
		this.x = x;
		
		//location = (this.x + tileSize)/tileSize;
	}
	
	/*public void render(GameContainer gc, Graphics g){
		
		for(int j = 0; j < gc.getHeight()/16; j++ ){
			image.draw(x, j * tileSize);
		}
	}
	
	public void update(GameContainer gc, int delta){
	
		//location = (x + tileSize)/tileSize;
		
		Input input = gc.getInput();
		
		if(Player.getYLocation() == 1 || Player.getYLocation() == (gc.getHeight()/(tileSize * 4))){
			
		
		
		
		timer += delta * 0.5;
		
		if(input.isKeyPressed(Input.KEY_A)){
			
			timer = -50;
			x -= tileSize;
			Player.addX(-tileSize);
		}
		
		if(input.isKeyPressed(Input.KEY_D)){
			
			timer = -50;
			x += tileSize;
			Player.addX(tileSize);
		}
		
		if(timer > 50){
		
			if(input.isKeyDown(Input.KEY_A)){
				timer = 0;
			
				x -= tileSize;
				Player.addX(-tileSize);
			}
		
			if(input.isKeyDown(Input.KEY_D)){
				timer = 0;
			
				x += tileSize;
				Player.addX(tileSize);
			}
		
		}
		
	}
		
	
	
		
		
		
	}
	
	public int getLocation(){
		return location;
	}*/
}
