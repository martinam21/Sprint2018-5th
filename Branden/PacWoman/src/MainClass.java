import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable {

	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;

	BufferedImage offscreen;
	Graphics bg;

	Pac1 myPac;
	Walls myWalls;

	// Food myFood;
	// List<Food> allFood;
	// List<Pills> allPills;

	public MainClass() {
		myWalls = new Walls();
		myPac = new Pac1();
		offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offscreen.getGraphics();
		this.addKeyListener(myPac);
		new Thread(this).start();
	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);

	}

	public void paint(Graphics g) {
		bg.setColor(Color.black);
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		myWalls.draw(bg);
		myPac.draw1(bg);
		// myFood.draw(bg);
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
			myPac.update();
			if (myPac.hitWall(offscreen))myPac.backUp();
			repaint();
		}
	}
}
