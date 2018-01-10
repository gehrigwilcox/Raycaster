package main;

import java.awt.AWTException;
import java.io.IOException;

public class Game {

	public Map map;
	
	public RayCaster caster;
	
	public Window window;
	
	public Player player;
	
	
	public int[] mouseMove = new int[2];
	
	public boolean[] keys = new boolean[4]; //0 = w, 1 = s, 2 = a, 3 = d
	
	
	public boolean close = false;
	
	public Game() throws AWTException, IOException{
		map = new Map();
		
		map.loadMap("Map.jpg");
		
		caster = new RayCaster();
		
		window = new Window(this);
		
		player = new Player();
		
		
	}
	
	
	public void gameLoop(){
		
		
		double now;
		
		double lastUpdateTime = System.nanoTime();
		
		double lastRenderTime = System.nanoTime();
		
		
		
		final double TIME_BETWEEN_RENDERS=1/Settings.FPS*1000000000;
		
		final double TIME_BETWEEN_UPDATES=1/Settings.UDP*1000000000;
		
		
		while(!close){
			
			now = System.nanoTime();
			
			while(now-lastUpdateTime > TIME_BETWEEN_UPDATES){
				
				handleGameLogic();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				//pros: If update cycles lag behind, it catches up
				//cons: While it catches up, it doesn't render, so the player can't react
				
			}
			
			if(now-lastRenderTime > TIME_BETWEEN_RENDERS){
				
				window.frame.repaint();
				lastRenderTime = System.nanoTime();
				
			}
			
			
		}
		
		window.cleanup();
		
	}
	
	
	private void handleGameLogic(){
		if(keys[0]) player.move(1, 0);
		
		if(keys[1]) player.move(-1,0);
		
		if(keys[2]) player.move(0, 1);
		
		if(keys[3]) player.move(0, -1);
		
		player.rotate(mouseMove[0]*0.01,mouseMove[1]*0.01);
		
		mouseMove[0]=0;
		mouseMove[1]=0;
		
		//System.out.println("X: " + player.getX() + " Y: " + player.getY());
	}
	
}
