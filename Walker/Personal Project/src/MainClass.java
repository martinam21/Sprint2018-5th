import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable {

	public static final int WIDTH = 1800;
	public static final int HEIGHT = 600;
	public static boolean gameover;
	public boolean thing;
	public static int score;
	Ground myGround;
	Target myTarget;
	Arrow myArrow;
	BufferedImage offscreen;
	Graphics bg;

	public MainClass() {
		score = 0;
		thing = true;
		gameover = false;
		myGround = new Ground();
		myArrow = new Arrow();
		myTarget = new Target();
		offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offscreen.getGraphics();
		Font f = bg.getFont().deriveFont(50f);
		bg.setFont(f);
		new Thread(this).start();
		this.addKeyListener(myArrow);
	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}

	public void paint(Graphics g) {
		bg.setColor(new Color(0xC7FFFF));
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		myGround.draw(bg);
		myArrow.draw(bg);
		myTarget.draw(bg);
		if (myArrow.didWin()) {
			bg.setColor(new Color(0x1ADB2A));
			bg.drawString("You Win", WIDTH / 2, HEIGHT / 2);
			bg.setColor(Color.black);
			bg.drawString("Press Enter To Continue", WIDTH / 2 - 150,
					HEIGHT / 2 + 100);
		}
		bg.setColor(Color.black);
		bg.drawString("" + score, 40, 70);
		if (gameover) {
			bg.setColor(Color.red);
			bg.drawString("You Lose", WIDTH / 2, HEIGHT / 2);
			bg.setColor(Color.black);
			bg.drawString("Press Enter To Restart", WIDTH / 2 - 150,
					HEIGHT / 2 + 100);
		}
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public void run() {
		while (thing) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myArrow.update();
			if (myArrow.getY() > HEIGHT || myArrow.getY() < HEIGHT - 700) {
				if (!gameover)
					myTarget.restart();
				gameover = true;
				score = 0;
			}
			if (myArrow.hitTarget(myTarget)) {
				myTarget.restart();
				myArrow.restart();
				score += 1;
			}
			if (myArrow.hitGround(offscreen)) {
				myTarget.restart();
				myArrow.restart();
				score = 0;
				gameover = true;
			}
			repaint();
		}

	}
}
