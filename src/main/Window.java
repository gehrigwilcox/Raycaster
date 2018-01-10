package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	
	private Robot robot;
	public JFrame frame;
	
	private Game game;
	
	
	public Window(Game game) throws AWTException{
		
		
		this.game = game;
		
		
		frame = new JFrame("Raycasting demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Settings.WIDTH, Settings.HEIGHT);
		
		robot = new Robot();
		
		initGraphics(frame);
		initControl(frame);
		
		frame.setVisible(true);
		
	}
	
	
	//Draw canvas
	private void draw(Graphics g){
		
		double[][] data = RayCaster.raycast(game.player, game.map);
		
		int column = 0;
		
		for(double[] i : data){
			
			switch((int) i[0]){
			case 1:
				if(i[1]==0)
					g.setColor(new Color(255,0,0));
				else
					g.setColor(new Color(127,0,0));
				break;
			case 2:
				if(i[1]==0)
					g.setColor(new Color(0,255,0));
				else
					g.setColor(new Color(0,127,0));
				break;
			case 3:
				if(i[1]==0)
					g.setColor(new Color(0,0,255));
				else
					g.setColor(new Color(0,0,127));
				break;
			default:
				g.setColor(new Color(0,0,0));
			}
			
			if(i[0]>0){
				
				int height = (int) (Settings.wallDistHeightRatio/i[2]-game.player.getX()%Settings.BLOCK_SIZE/Settings.wallDistHeightRatio);
				
				int space = (Settings.HEIGHT - height)/2;
				
				g.drawLine(column, space, column, height+space);
				
			}
			
			column++;
			
		}
		
	}
	
	
	//Handle mouse input
	private void handleMouse(MouseEvent e){
		
		//if centering mouse ignore
		if(e.getX() == Settings.WIDTH/2 && e.getY() == Settings.HEIGHT/2) return;
		
		//set mouse to center of window
		robot.mouseMove(Settings.WIDTH/2+frame.getX(), Settings.HEIGHT/2+frame.getY());
		
		//get camera movement
		game.mouseMove[0] = e.getX() - Settings.WIDTH/2;
		game.mouseMove[1] = e.getY() - Settings.HEIGHT/2;
		
	}
	
	
	private void handleKeyPressed(KeyEvent e){
		
		//if escape, close
		if(e.getKeyCode() == e.VK_ESCAPE) game.close = true;
		
		else if(e.getKeyCode() == e.VK_UP) game.keys[0] = true;
		
		else if(e.getKeyCode() == e.VK_DOWN) game.keys[1] = true;
		
		else if(e.getKeyCode() == e.VK_LEFT) game.keys[2] = true;
		
		else if(e.getKeyCode() == e.VK_RIGHT) game.keys[3] = true;
		
	}
	
	private void handleKeyReleased(KeyEvent e){
		
		if(e.getKeyCode() == e.VK_UP) game.keys[0] = false;
		
		else if(e.getKeyCode() == e.VK_DOWN) game.keys[1] = false;
		
		else if(e.getKeyCode() == e.VK_LEFT) game.keys[2] = false;
		
		else if(e.getKeyCode() == e.VK_RIGHT) game.keys[3] = false;
		
	}
	
	
	
	
	
	
	//Setup graphics
	private void initGraphics(JFrame f){
		
		f.add(new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				
				draw(g);
			}
		});
		
	}
	
	
	//Setup user input
	private void initControl(JFrame f){
		
		f.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				handleMouse(e);
			}
		});
		
		
		
		f.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				handleKeyReleased(e);
			}
			
		});
		
		
		
		
		//hide cursor
		BufferedImage c = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		
		Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(c,new Point(0,0),"blank cursor");
		
		f.getContentPane().setCursor(blank);
		
	}
	
	public void cleanup(){
		frame.setVisible(false);
		frame.dispose();
	}
	
}
