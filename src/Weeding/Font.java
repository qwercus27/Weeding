package Weeding;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Font {
	
	public Image text;
	public ArrayList<Image> letters;
	
	public Font() throws SlickException {
		
		text = new Image("res/textAllWhite.png",false, Image.FILTER_NEAREST);
		letters = new ArrayList<Image>();
		
		for(int i=0; i<81; i++){
			letters.add(text.getSubImage(i * 8, 0, 8, 8));
		}
	}

	private static String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" + //
			"abcdefghijklmnopqrstuvwxyz" + //
			"0123456789.,!?'\"-+=/\\%()<>:; " + //
			"";

	public  void draw(String msg, int x, int y, Color color) {
		
		for (int i = 0; i < msg.length(); i++) {
			
			int ix = chars.indexOf(msg.charAt(i));
			if (ix >= 0) {
				
				if(i > 0){
				char temp = msg.charAt(i - 1);
				if(temp == chars.charAt(34)) x -= 4; // lower case i
				
				if(temp == chars.charAt(80)) x-=3;  // space
				if(temp == chars.charAt(37)) x-=2;  // lower case l
				if(temp == chars.charAt(11) || temp == chars.charAt(8) || temp == chars.charAt(17)) x -=1; // L, I, R	
				for(int j = 26; j <52; j++){
					if(temp == chars.charAt(j)){  //all lower case
						x -= 1;
					}
				}
				}
				
				letters.get(ix).draw(x + i * 8, y, color);
				//screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
			}
		}
	}
}
