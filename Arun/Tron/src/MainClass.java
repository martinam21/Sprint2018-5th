import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable, KeyListener {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private BufferedImage offScreen;
	private Graphics bg;
	private Car1 myCar1;
	private Car2 myCar2;
	private ArrayList<Spark> mySparks;
	private boolean gameOver;
	public int Car1score, Car2score, maxscore;

	public MainClass() {
		offScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(30f);
		bg.setFont(f);
		maxscore = 3;
		restart();
		new Thread(this).start();
		this.addKeyListener(this);
	}

	public void restart() {
		if(gameOver)paint2(bg);
		if(gameOver)scoreboard(bg);
		gameOver = false;
		myCar1 = new Car1();
		myCar2 = new Car2();
		mySparks = new ArrayList<Spark>();
		this.addKeyListener(myCar1);
		this.addKeyListener(myCar2);
	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(true);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}

	public void paint(Graphics g) {
		//bg.setColor(Color.black);
		//bg.fillRect(0, 0, WIDTH, HEIGHT);
		myCar1.draw(bg);
		myCar2.draw(bg);
		//end game
		if(Car1score==maxscore||Car2score==maxscore){
			victoryRoyale(bg);
		}
		//borders/obstacles/Score board
		bg.setColor(Color.white);
		bg.fillRect(0, 25, 10, HEIGHT);
		bg.fillRect(395, 0, 10, 75);
		bg.fillRect(0, 590, WIDTH, 10);
		bg.fillRect(790, 25, 10, HEIGHT);
		bg.fillRect(0, 25, WIDTH, 10);
		bg.fillRect(0, 75, WIDTH, 10);
		bg.setColor(Color.red);
		bg.drawRect(15, 40, 118, 30);
		bg.drawRect(143, 40, 118, 30);
		bg.drawRect(271, 40, 118, 30);
		bg.setColor(Color.blue);
		bg.drawRect(409, 40, 118, 30);
		bg.drawRect(537, 40, 118, 30);
		bg.drawRect(665, 40, 118, 30);
		//sparks
		if (mySparks != null)
			for (Spark s : mySparks)
				s.draw(bg);
		g.drawImage(offScreen, 0, 0, null);
	}
	
	public void paint2(Graphics g){
		bg.setColor(Color.black);
		bg.fillRect(0, 85, WIDTH, HEIGHT);
	}
	
	public void scoreboard(Graphics g){
		if(Car1score==1){
			bg.setColor(Color.red);
			bg.fillRect(15, 40, 118, 30);
		}
		if(Car1score==2){
			bg.setColor(Color.red);
			bg.fillRect(143, 40, 118, 30);
		}
		if(Car1score==3){
			bg.setColor(Color.red);
			bg.fillRect(271, 40, 118, 30);
		}
		if(Car2score==1){
			bg.setColor(Color.blue);
			bg.fillRect(409, 40, 118, 30);
		}
		if(Car2score==2){
			bg.setColor(Color.blue);
			bg.fillRect(537, 40, 118, 30);
		}
		if(Car2score==3){
			bg.setColor(Color.blue);
			bg.fillRect(665, 40, 118, 30);
		}
	}
	
	public void victoryRoyale(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(WIDTH/2-200, HEIGHT/5, 400, 50);
		if(Car1score==maxscore){
			g.setColor(Color.yellow);
			g.drawString("#1 Victory Royale! Red Car", 225, 150);
		}
		if(Car2score==maxscore){
			g.setColor(Color.yellow);
			g.drawString("#1 Victory Royale! Blue Car", 225, 150);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
				if (!gameOver) {
					myCar1.update();
					myCar2.update();
					if (myCar1.checkHit(offScreen)) {
						gameOver = true;
						mySparks.addAll(myCar1.explode());
						Car2score++;
					}
					if (myCar2.checkHit(offScreen)) {
						gameOver = true;
						mySparks.addAll(myCar2.explode());
						Car1score++;
					}
					if(myCar1.checkHit(offScreen)&&myCar2.checkHit(offScreen)){
						Car1score--;
						Car2score--;
					}
				} else {
					for (Spark s : mySparks)
						s.update();
				}
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver && e.getKeyCode()==KeyEvent.VK_SPACE) restart();
		if(gameOver && e.getKeyCode()==KeyEvent.VK_SPACE && Car1score==maxscore);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
