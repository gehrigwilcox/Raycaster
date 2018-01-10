package main;

import java.awt.AWTException;

public class Main {

	
	public static void main(String args[]){
		
		try {
			
			new Game().gameLoop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
