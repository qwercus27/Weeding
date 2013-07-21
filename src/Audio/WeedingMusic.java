package Audio;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class WeedingMusic {

	private static Music title, menu;
	
	public WeedingMusic() throws SlickException{
		
		title = new Music("res/title.ogg");
		menu = new Music("res/menu.ogg");
		
	}
	
	public static Music getTitle(){
		return title;
	}
	
	public static Music getMenu(){
		return menu;
	}
	
	public static void stop(){
		if(title.playing())title.stop();
		if(menu.playing())menu.stop();
	}
}
