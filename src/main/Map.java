package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {

	private int[][] map;
	
	public void loadMap(String fileName) throws IOException{
		
		BufferedImage img = ImageIO.read(new File("./res/"+fileName));
		
		map = new int[img.getHeight()][img.getWidth()];
		
		for(int i = 0; i<img.getHeight(); i++){
			for(int j = 0; j<img.getWidth(); j++){
				if((img.getRGB(j,i) >> 16 & 0xFF) == 255){
					map[i][j] = 1;
				}else{
					map[i][j] = 0;
				}
			}
		}
	}
	
	
	public int checkPos(int x, int y){
		
		//saftey check, returns -1 if out of bounds, else returns the object at position x, y on map
		return (x<0 || y<0 || x>map.length-1 || y>map[0].length-1) ? -1 : map[y][x];
		
	}
	
}
