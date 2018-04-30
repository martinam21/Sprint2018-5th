import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MainClass extends JFrame implements KeyListener, Runnable {
	public static final int HEIGHT = 1080;
	public static final int WIDTH = 1920;

	BufferedImage offscreen;
	Graphics bg;

	Spider mySpider;
	List<Webs> allWebs;
	List<Rushers> allRushers;
	List<Exploders> allExploders;
	List<Slowers> allSlowers;
	private boolean Shoot;
	public static boolean justhit;
	public static long score;
	private final int x = WIDTH / 2;
	private final int y = HEIGHT / 2;

	public MainClass() {
		justhit = false;
		Shoot = true;
		score = 0;
		allWebs = new ArrayList<Webs>();
		allRushers = new ArrayList<Rushers>();
		allExploders = new ArrayList<Exploders>();
		allSlowers = new ArrayList<Slowers>();
		mySpider = new Spider();
		offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.addKeyListener(this);
		this.addKeyListener(mySpider);
		bg = offscreen.getGraphics();
		((Graphics2D) bg).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		Font f = bg.getFont().deriveFont(30f);
		bg.setFont(f);
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
		bg.setColor(Color.blue);
		bg.drawOval((WIDTH / 2) - 120, (HEIGHT / 2) - 80, 240, 160);
		bg.drawOval((WIDTH / 2) - 240, (HEIGHT / 2) - 160, 480, 320);
		bg.drawOval((WIDTH / 2) - 360, (HEIGHT / 2) - 240, 720, 480);
		bg.drawOval((WIDTH / 2) - 480, (HEIGHT / 2) - 320, 960, 640);
		bg.drawOval((WIDTH / 2) - 600, (HEIGHT / 2) - 400, 1200, 800);
		bg.drawLine(WIDTH / 2 - 760, HEIGHT / 2, WIDTH / 2 + 760, HEIGHT / 2);
		bg.drawLine(WIDTH / 2, HEIGHT / 2 - 500, WIDTH / 2, HEIGHT / 2 + 500);
		bg.drawLine(WIDTH / 2 + 680, HEIGHT - 63, WIDTH / 2 - 680, HEIGHT - 1016);
		bg.drawLine(WIDTH / 2 - 680, HEIGHT - 63, WIDTH / 2 + 680, HEIGHT - 1016);
		bg.setColor(Color.WHITE);
		bg.drawString(" " + score, 50, 50);
		bg.setColor(Color.RED);
		bg.drawString(" " + mySpider.lifes, 200, 50);
		bg.setColor(Color.blue);
		bg.drawString("BLACK WIDOW", 860, 50);
		mySpider.draw(bg);
		
		if (mySpider.isdead()) {
			bg.setColor(Color.black);
			bg.fillRect(0, 0, WIDTH, HEIGHT);
			bg.setColor(Color.blue);
			bg.drawString("" + score, WIDTH / 2 - 33, HEIGHT / 2 - 200);
			bg.setColor(Color.red);
			bg.drawString("GAME_OVER", WIDTH / 2 - 95, HEIGHT / 2 - 400);
		}
		for (Webs w : allWebs) {
			if (!mySpider.isdead()) {
				w.draw(bg);
			}
		}
		for (Rushers r : allRushers) {
			if (!mySpider.isdead()) {
				r.draw(bg);
			}
		}
		for (Exploders e : allExploders) {
			if (!mySpider.isdead()) {
				e.draw(bg);
			}
		}
		for (Slowers s : allSlowers) {
			if (!mySpider.isdead()) {
				s.draw(bg);
			}
		}
		g.drawImage(offscreen, 0, 0, null);
	}

	public static double getScore() {
		return score;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && mySpider.deathint == 0 && Shoot == true && mySpider.isdead() == false) {
			Shoot = false;
			allWebs.add(new Webs(-1.51));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Shoot = true;

		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
				mySpider.myX += mySpider.vx;
				mySpider.myY += mySpider.vy;
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mySpider.update();
			if (Math.random() < .03) {
				allRushers.add(new Rushers());
			}
			if (Math.random() < .02) {
				allExploders.add(new Exploders());
			}
			if (Math.random() < .008) {
				allSlowers.add(new Slowers());
			}
			double dx = 0;
			double dy = 1;
			for (int i = 0; i < allRushers.size(); i++) {
				Rushers r = allRushers.get(i);
				r.update(dx, dy);
				for (int j = 0; j < allWebs.size(); j++) {
					Webs w = allWebs.get(j);
					if (r.hit(w)) {
						allRushers.remove(r);
						allWebs.remove(w);
						if (!mySpider.isdead()) {
							score += 250;
						}
					}
				}
				if (r.hitspider() && mySpider.deathint == 0) {
					allRushers.remove(r);
					mySpider.takedamage();
				}
			}
			for (int i = 0; i < allExploders.size(); i++) {
				Exploders e = allExploders.get(i);
				e.update(dx, dy);
				for (int j = 0; j < allWebs.size(); j++) {
					Webs w = allWebs.get(j);
					if (e.hit(w)) {
						allExploders.remove(e);
						allWebs.remove(w);
						if (!mySpider.isdead()) {
							score += 500;
							
						}
					}
				}
				if (e.isDead() && mySpider.deathint == 0) {
					allExploders.remove(e); 
					mySpider.takedamage();
					justhit = true;
					
				}
			}
			for (int i = 0; i < allSlowers.size(); i++) {
				Slowers s = allSlowers.get(i);
				s.update(dx, dy);
				for (int j = 0; j < allWebs.size(); j++) {
					Webs w = allWebs.get(j);
					if (s.hit(w)) {
						allSlowers.remove(s);
						allWebs.remove(w);
						if (!mySpider.isdead()) {
							score += 300;
						}
					}
				}
				if (s.hitspider() && mySpider.deathint == 0) {
					allSlowers.remove(s); 
					mySpider.slow();
					justhit = true;
				}
			}
			for (Webs w : allWebs)
				w.update(dx, dy);

			repaint();
		}

	}
}
