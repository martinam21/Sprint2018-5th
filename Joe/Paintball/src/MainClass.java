import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable{

	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;

	private BufferedImage offScreen;
	private Graphics bg;
	private boolean gameOver;
	private boolean isDead; 
 


	Human[] myHumans;
	private List<Paintball> allPaintballs;

	public MainClass() {
		isDead = false; 
		gameOver = false;
		allPaintballs = new ArrayList<Paintball>();
		myHumans = new Human[2];
		myHumans[0] = new Human(WIDTH-50, 500, true);
		myHumans[1] = new Human(50, 500, false);
		System.out.println("adding key listener");
		this.addKeyListener(myHumans[0]);
		this.addKeyListener(myHumans[1]);
		offScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(30f); 
		bg.setFont(f);
		new Thread(this).start();

		bg.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(isDead = true);
			gameOver = true; 

	}
	
	
	

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}

	public void paint(Graphics g) {
		bg.setColor(Color.white);
		bg.fillRect(0,0,WIDTH,HEIGHT);
		for (Human h : myHumans)
			h.draw(bg);
		for(Paintball p : allPaintballs){
			p.draw(bg);
		}
		g.drawImage(offScreen, 0, 0, null);
		if(gameOver){
			bg.setColor(Color.black);
			bg.drawString("Game Over", WIDTH/2, HEIGHT/2); 
		}
		g.drawImage(offScreen, 0, 0, null); 
		
		
	}
	

	
	public void run() {
		while (!gameOver) {
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(Human h : myHumans){
				h.update();
				Paintball p = h.shoot();
				if(p!=null) allPaintballs.add(p);
			}
			for(Paintball p : allPaintballs)p.update();
			for(int i=0 ; i<allPaintballs.size() ; i++){
				Paintball p = allPaintballs.get(i);
				for(Human g : myHumans){
					if(g.hit(p)){
						g.takeDamage(); 
						allPaintballs.remove(p); 
			
				
						
					}
				}
			}
			repaint();
		}
	
		
	}

}
