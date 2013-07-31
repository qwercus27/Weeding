package GameStates;



import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Audio.WeedingMusic;
import Weeding.Font;
import Weeding.Save;
import Weeding.TileSize;



public class Title extends BasicGameState {
	
	private int tileSize, gScale, yDiff, selection, optionsSize;
	private Font font;
	private Image titleImage, tallImage, textFrame;
	private Music titleMusic, menuMusic;
	private boolean up, allUp, saveExists, allDown;
	private float imageX, imageY, rate;
	private String[] options;
	private Color color, selected, unselected;
	
	
	public Title(int state) {
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
		tileSize = TileSize.tileSize;
		gScale = TileSize.gScale;
		
		titleImage = new Image("res/title.png", false, Image.FILTER_NEAREST);
		tallImage = new Image("res/titleTall.png", false, Image.FILTER_NEAREST);
		textFrame = new Image("res/textFrame.png", false, Image.FILTER_NEAREST);
		
		WeedingMusic.getTitle().play(1f, 0.5f);
		
		up = false;
		allUp = false;
		allDown = true;
		
		yDiff = gc.getHeight()/gScale - tallImage.getHeight();
		imageX = 0;
		imageY =  yDiff;
		
		rate = 0;
		
		saveExists = false;
		
		
		
	
		unselected = new Color(Color.white);
		selected = new Color(77, 162, 77);
		color = unselected;
		
		font = new Font();
		
		selection = 0;
	
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		if(saveExists){
			optionsSize = 5;
			options = new String[5];
			options[0] = "Continue";
			options[1] = "New Game";
			options[2] = "Options";
			options[3] = "Help";
			options[4] = "Quit";
		}
		else{
			optionsSize = 4;
			options = new String[4];
			options[0] = "New Game";
			options[1] = "Options";
			options[2] = "Help";
			options[3] = "Quit";
		}
		
		g.scale(gScale, gScale);
		
		tallImage.draw(imageX, imageY);
		
		if(allUp){
			
			
			int x = gc.getWidth()/gScale/2 - textFrame.getWidth()/2;
			for(int i = 0; i < optionsSize; i++){
				textFrame.draw(x, 48 + i * 20);
				int length = (x - (options[i].length() * 8))/2;
				if(i == 3 || i == 4)length -= 5;
				if(selection == i)color = selected;
				else color = unselected;
				font.draw(options[i], x + length, 52 + i * 20, color);
				//font.draw(options[i], x, 52 + i * 20, color);
			}
			
		}
		
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(Save.exists())saveExists = true;
		else saveExists = false;
		
		System.out.println(imageY);
			
		if(!WeedingMusic.getTitle().playing()){
			if(!WeedingMusic.getMenu().playing())WeedingMusic.getMenu().loop(1f, 0.5f);
		}
		
		
		
		
		Input input = gc.getInput();
		
		
		if(allDown && input.isKeyPressed(Input.KEY_ENTER)){
			up = true;
			input.clearKeyPressedRecord();
		}
		if(allUp && input.isKeyPressed(Input.KEY_ENTER)){
			
			if(optionsSize == 5){
				if(selection == 0){
					sbg.enterState(1);
				}
				if(selection == 1){
					if(Save.exists())Save.delete();
					sbg.enterState(1);
				}
				if(selection == 4)gc.exit();
			}
			if(optionsSize == 4){
				if(selection == 0){
					if(Save.exists())Save.delete();
					sbg.enterState(1);
				}
				if(selection == 3){
					gc.exit();
				}
			}
			
			input.clearKeyPressedRecord();
		}
	
			
			
			
		
		
		if(allUp && input.isKeyPressed(Input.KEY_ESCAPE)){
			up = false;
			input.clearKeyPressedRecord();
		}
		
		if(up){
			if(imageY < 0 && imageY < yDiff/2){
				rate += delta * 0.005;
				imageY +=  rate;
				allDown = false;
			}
			
			if(imageY >= yDiff/2 && imageY < 0){
				rate -= delta * 0.005;
				imageY += rate;
			}
			
			if(imageY >= 0){
				rate = 0;
				imageY = 0;
				allUp = true;
			}
		}
		
		if(!up){
			
			if(imageY > yDiff && imageY > yDiff/2){
				rate += delta * 0.005;
				imageY -= rate;
				allUp = false;
			}
			
			if(imageY <= yDiff/2 && imageY > yDiff){
				rate -= delta * 0.005;
				imageY -= rate;
				
			}
			
			if(imageY <= yDiff){
				imageY = yDiff;
				rate = 0;
				allDown = true;
			}
			
		}
		
		if(allUp){
			if(input.isKeyPressed(Input.KEY_W)){
				selection -= 1;
			}
			
			if(input.isKeyPressed(Input.KEY_S)){
				selection += 1;
			}
			
			if(selection > optionsSize - 1)selection = 0;
			if(selection < 0)selection = optionsSize - 1;
		}

		input.clearKeyPressedRecord();
	}

	
	public int getID() {

		return 2;
	}

}

