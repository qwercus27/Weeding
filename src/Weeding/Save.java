package Weeding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	
	Progress progress;
	String file, folder;
	private static File f, f2;
	
	public Save() {
		progress = new Progress();
		
		folder = System.getProperty("user.home") + File.separator + ".bio-ex127";
		file = folder + File.separator + "progress.txt";
		
		f = new File(folder);
		f2 = new File(file);
		
	}

	public void SaveGame() throws IOException{
		
		boolean[] locked = Progress.getLocked();
		int numUnlocked = 0;
		for(int i = 0; i < 25; i++){
			if(locked[i] == false)numUnlocked +=1;
		}
		
		if(!f.exists())f.mkdir();
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter((fstream));
		out.write(numUnlocked);
		out.close();

	}
	
	public void Load() throws IOException {
		
	
		if(f2.exists()){
			BufferedReader in = new BufferedReader(new FileReader(file));
			int numUnlocked = in.read();
			System.out.println(numUnlocked);
			for(int i = 0; i < numUnlocked; i ++){
				Progress.unlock(i);
			}
		}
	       
	        
	     // progress.setLocked(progress.getLocked());
	       
	    }
	
	public static boolean exists(){
		if(f2.exists())return true;
		else return false;
	}
	
	public static void delete(){
		f2.delete();
		
		for(int i = 0; i < 24; i++){
			Progress.lock(i);
		}
		Progress.unlock(1);
		
	}
	
	
	
}
