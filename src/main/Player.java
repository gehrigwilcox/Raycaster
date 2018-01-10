package main;

public class Player {

	private int[] pos = new int[2];
	private double[] rot = new double[2];
	
	public Player(){
		pos[0]=97;
		pos[1]=62;
	}
	
	public int getX(){
		return pos[0];
	}
	
	public int getY(){
		return pos[1];
	}
	
	public int[] getPos(){
		return pos;
	}
	
	public double getRX(){
		return rot[0];
	}
	
	public double getRY(){
		return rot[1];
	}
	
	public double[] getRot(){
		return rot;
	}
	
	public void move(int x, int y){
		pos[0]+=x;
		pos[1]+=y;
	}
	
	public void rotate(double x, double y){
		rot[0]+=x;
		rot[1]+=y;
	}
	
}
