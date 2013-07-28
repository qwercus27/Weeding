package Audio;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class WeedingMusic {

	private static Music title, menu, weeding;
	
	public WeedingMusic() throws SlickException{
		
		title = new Music("res/audio/title.ogg");
		menu = new Music("res/audio/menu.ogg");
		weeding = new Music("res/audio/Running.ogg");
		
	}
	
	public static Music getTitle(){
		return title;
	}
	
	public static Music getMenu(){
		return menu;
	}
	
	public static Music getWeeding(){
		return weeding;
	}
	
	public static void stop(){
		if(title.playing())title.stop();
		if(menu.playing())menu.stop();
		if(weeding.playing())weeding.stop();
	}
}
