package main;

public class RayCaster {
	
	public static double[][] raycast(Player p, Map m){
		
		double[][] data = new double[Settings.WIDTH][];
		
		for(int i = 0; i<Settings.WIDTH; i++){
			data[i] = castRay( Settings.FOV/Settings.WIDTH*i-Settings.FOV/2, p, m);
		}
		
		return data;
	}
	
	
public static double[] castRay(double angle, Player p, Map m){
		
		//Get player coordinates in map coordinates
		int x = (int) Math.floor(p.getX()/Settings.BLOCK_SIZE);
		int y = (int) Math.floor(p.getY()/Settings.BLOCK_SIZE);
		
		angle+=p.getRX();
		
		//check each change in x and y and figure out if that coordinate has a block
		double slope = Math.tan(angle);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		
		//x and y coordinates based off the slope of the ray
		int testX = 0;
		int testY = 0;
		
		int block = 0;
		
		int side = 0;
		
		while(true){
			if(Math.abs(testY)+1>testX/Math.abs(slope)){// x/slope because the tangent of this angle gives dX/dY
				testX += sin>0 ? 1 : -1;
				side = 0;
			}else{
				testY += cos>0 ? 1 : -1;
				side = 1;
			}
			
			block=m.checkPos(testX+x, testY+y);
			
			if(block != 0) break;
		}
		
		return new double[]{block, side,
				Math.sqrt(testX*testX+testY*testY)*Math.cos(p.getRX()-angle) //Pythagorean theorem to find distance away multiplied by the cosine of the angle to fix fish bowl side effect from jumping between cartesian and polar coordinate systems 
		};
		
	}
	
	
	public static double[] castRay(double angle, int x, int y, Map m){
		
		//Get player coordinates in map coordinates
		x = (int) Math.floor(x/Settings.BLOCK_SIZE);
		y = (int) Math.floor(y/Settings.BLOCK_SIZE);
		
		
		//check each change in x and y and figure out if that coordinate has a block
		double slope = Math.tan(angle);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		
		//x and y coordinates based off the slope of the ray
		int testX = 0;
		int testY = 0;
		
		int block = 0;
		
		int side = 0;
		
		while(true){
			if(Math.abs(testY)+1>testX/Math.abs(slope)){// x/slope because the tangent of this angle gives dX/dY
				testX += sin>0 ? 1 : -1;
				side = 0;
			}else{
				testY += cos>0 ? 1 : -1;
				side = 1;
			}
			
			block=m.checkPos(testX+x, testY+y);
			
			if(block != 0) break;
		}
		
		return new double[]{block, side, 
				Math.sqrt(testX*testX+testY*testY)*Math.cos(angle) //Pythagorean theorem to find distance away multiplied by the cosine of the angle to fix fish bowl side effect from jumping between cartesian and polar coordinate systems 
		};
		
	}
	
}
