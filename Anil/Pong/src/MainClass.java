import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable, KeyListener {

	public static final int WIDTH = 1900;
	public static final int HEIGHT = 1000;

	BufferedImage offScreen;
	Graphics bg;
	Paddle_P1 myPaddle;
	Paddle_P2 xPaddle;
	Puck myPuck;
	private boolean Startgame;
	private boolean gameOver;
	


	public MainClass() {
		Startgame = false;
		gameOver = false;
		
		xPaddle = new Paddle_P2();
		this.addKeyListener(xPaddle);
		myPaddle = new Paddle_P1();
		this.addKeyListener(myPaddle);
		myPuck = new Puck();
		offScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(50f);
		try {
			InputStream is = MainClass.class
					.getResourceAsStream("resources/BabooInSpace.ttf");
			f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(50f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bg.setFont(f);
		new Thread(this).start();
		this.addKeyListener(this);

	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setVisible(true);
		mc.setSize(WIDTH, HEIGHT);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setResizable(false);
	}

	public void paint(Graphics g) {
		bg.setColor(Color.black);
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		// Board (White Outline)
		bg.setColor(Color.white);
		bg.fillRect(WIDTH / 10, HEIGHT / 10, WIDTH - 400, HEIGHT - 200);
		// Board (Blue Cover)
		bg.setColor(Color.blue);
		bg.fillRect(WIDTH / 10 + 25, HEIGHT / 10 + 25, 1450, 750);
		// Middle Line
		bg.setColor(Color.white);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 75, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 150, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 225, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 300, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 375, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 450, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 525, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 600, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 675, 25, 50);
		bg.fillRect(WIDTH / 2 - 25, HEIGHT / 10 + 750, 25, 50);
		
		if(Startgame == false){
			bg.setColor(Color.green);
			bg.drawString("PRESS SPACEBAR TO START", MainClass.WIDTH/4+100, MainClass.HEIGHT / 2);
			bg.setColor(Color.red);
			bg.drawString("^", MainClass.WIDTH/4-155, MainClass.HEIGHT/2-165);
			bg.drawString("|", MainClass.WIDTH/4-150, MainClass.HEIGHT/2-160);
			bg.drawString("W", MainClass.WIDTH/4-170, MainClass.HEIGHT/2-100);
			bg.drawString("S", MainClass.WIDTH/4-160, MainClass.HEIGHT/2+100);
			bg.drawString("|", MainClass.WIDTH/4-150, MainClass.HEIGHT/2+160);
			bg.drawString("v", MainClass.WIDTH/4-157, MainClass.HEIGHT/2+175);
			bg.setColor(Color.magenta);
			bg.drawString("^", MainClass.WIDTH/2+520, MainClass.HEIGHT/2-165);
			bg.drawString("|", MainClass.WIDTH/2+525, MainClass.HEIGHT/2-160);
			bg.drawString("UP", MainClass.WIDTH/2+500, MainClass.HEIGHT/2-100);
			bg.drawString("DOWN", MainClass.WIDTH/2+450, MainClass.HEIGHT/2+100);
			bg.drawString("|", MainClass.WIDTH/2+525, MainClass.HEIGHT/2+160);
			bg.drawString("v", MainClass.WIDTH/2+518, MainClass.HEIGHT/2+175);
			
		}
		
		bg.setColor(Color.red);
		bg.drawString("" + (myPuck.myScore), 75, MainClass.HEIGHT / 2);
		if(myPuck.myScored)bg.setColor(Color.green);
		bg.drawString("" + (myPuck.myScore), 75, MainClass.HEIGHT / 2);
		bg.setColor(Color.magenta);
		bg.drawString("" + (myPuck.xScore), 1750, MainClass.HEIGHT / 2);
		if(myPuck.xScored)bg.setColor(Color.green);
		bg.drawString("" + (myPuck.xScore), 1750, MainClass.HEIGHT / 2);
		
		if(Startgame == true){
		myPaddle.draw(bg);
		xPaddle.draw(bg);
		myPuck.draw(bg);
		}
		
		if (gameOver) {
			bg.setColor(Color.black);
			bg.drawString("GAME  OVER", WIDTH/2-173, HEIGHT/2-100);
		}
		
		if(gameOver && myPuck.myScore>myPuck.xScore){
			bg.setColor(Color.yellow);
			bg.fillRect(WIDTH/4+35, HEIGHT/2-100, 115, 50);
			bg.fillRect(WIDTH/4+35, HEIGHT/2-120, 15, 25);
			bg.fillRect(WIDTH/4+60, HEIGHT/2-115, 15, 25);
			bg.fillRect(WIDTH/4+85, HEIGHT/2-120, 15, 25);
			bg.fillRect(WIDTH/4+110, HEIGHT/2-115, 15, 25);
			bg.fillRect(WIDTH/4+135, HEIGHT/2-120, 15, 25);
			bg.setColor(Color.red);
			bg.fillOval(WIDTH/4+80, HEIGHT/2-85, 25, 25);
		}
		
		if(gameOver && myPuck.myScore<myPuck.xScore){
			bg.setColor(Color.yellow);
			bg.fillRect(WIDTH/2+315, HEIGHT/2-100, 115, 50);
			bg.fillRect(WIDTH/2+315, HEIGHT/2-120, 15, 25);
			bg.fillRect(WIDTH/2+340, HEIGHT/2-115, 15, 25);
			bg.fillRect(WIDTH/2+365, HEIGHT/2-120, 15, 25);
			bg.fillRect(WIDTH/2+390, HEIGHT/2-115, 15, 25);
			bg.fillRect(WIDTH/2+415, HEIGHT/2-120, 15, 25);
			bg.setColor(Color.red);
			bg.fillOval(WIDTH/2+360, HEIGHT/2-85, 25, 25);
		}
		g.drawImage(offScreen, 0, 0, null);

	}

	public void run() {
		while (!gameOver) {
			try {
				Thread.sleep(5);
				if(Startgame == true){
				xPaddle.update();
				myPaddle.update();
				myPuck.update();
				if (myPuck.hitPaddle_P1(myPaddle)) {
					myPuck.reverse();
				}
				if (myPuck.hitPaddle_P2(xPaddle)) {
					myPuck.reverse();
				}
				if (myPuck.myScore==10 || myPuck.xScore==10) {
					gameOver = true;
					myPuck.Stop();
				}
				repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			Startgame = true;
			}
		}
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

// Special Notes - Rest of Milestones
	//Rotate "Player 1" && "Player 2" on board
	// Project Finished