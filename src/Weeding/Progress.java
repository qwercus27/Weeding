package Weeding;

import java.io.Serializable;

import GameStates.WeedingState;

public class Progress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int savedValue;
	private static boolean[] locked;

	public Progress(){
		
		locked = new boolean[25];
		
		locked[0] = false;
		locked[1] = false;
		
		for(int i = 2; i < 25; i ++){
			locked[i] = true;
		}
		
		savedValue = WeedingState.getSaveTest();
	}
	
	public static boolean[] getLocked(){
		return locked;
	}
	
	public static void unlock(int index){
		
		locked[index] = false;
	}
	
	public static void lock(int index){
		
		locked[index] = true;
	}
	
	
	public static void setLocked(boolean[] newLocked){
		locked = newLocked;
	}
	

}
