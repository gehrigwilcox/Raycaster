package main;

public class Settings {

	
	//Window settings
	public static final int WIDTH = 320;
	public static final int HEIGHT = 500;
	
	
	//Game settings
	public static final int BLOCK_SIZE = 64;
	public static final double UDP = 60;//updates per second
	public static final double FPS = 60;//updates per second
	
	//Rendering stuff
	public static final int VIEW_DIST = 400;
	public static final double FOV = Math.toRadians(60);
	public static final double distFromViewPane = (int)((WIDTH/2)/Math.tan(FOV/2));
	public static final double wallDistHeightRatio = BLOCK_SIZE*distFromViewPane;//when rendering, the height of the column is this number divided by the distance
	
}
