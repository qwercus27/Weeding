package GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Weeding.Font;
import Weeding.TileSize;

public class Credits extends BasicGameState {
	
	String[] headers, footers, text;
	float time, y;
	Font font;
	int gScale;
	
	public Credits(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
	
	 time = 0;
	 gScale = TileSize.gScale;
	 y = gc.getHeight()/gScale/2;
	 
	 font = new Font();
	
	 headers = new String[100];
	 footers = new String[100];
	 
	 for(int i = 0; i < 99; i ++){
		 headers[i] = "";
		 footers[i] = "";
	 }
	 
	 headers[0] = "Bio-Ex 127";
	 headers[1] = "";
	 headers[2] = "Executive Producer";
	 footers[3] = "Michael Lowry";
	 headers[4] = "";
	 headers[5] = "Director";
	 footers[6] = "Michael Lowry";
	 headers[7] = "";
	 headers[8] = "Lead Programmer";
	 footers[9] = "Michael Lowry";
	 headers[10] = "";
	 headers[11] = "Head Artist";
	 footers[12] = "Michael Lowry";
	 headers[13] = "";
	 headers[14] = "Creative Assistant";
	 footers[15] = "Michael Lowry";
	 headers[16] = "";
	 headers[17] = "Composer";
	 footers[18] = "Michael Lowry";
	 headers[19] = "";
	 headers[20] = "";
	 headers[21] = "Special Thanks";
	 footers[22] = "Cedar Creek";
	 footers[23] = "TigSource";
	 footers[24] = "Romm22";
	 footers[25] = "Carissa, Jacob, and Matt";
	 headers[26] = "";
	 headers[27] = "Thanks for Playing!";

	 
			
	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		
		g.scale(gScale, gScale);
		
		for(int i = 0; i < 30; i++){
			
			font.draw(headers[i], gc.getWidth()/gScale/2 - (headers[i].length()/3) * 8, (int)y + i * 20, Color.white);
			font.draw(footers[i], gc.getWidth()/gScale/2 - (footers[i].length()/3) * 8, (int)y + i * 20, Color.gray);
		}
		
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		
		Input input = gc.getInput();
		
		y -= delta * 0.01;
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			sb.enterState(2);
			y = gc.getHeight()/gScale/2;
		}
	}
	
	

	@Override
	public int getID() {
		
		return 3;
	}

}
