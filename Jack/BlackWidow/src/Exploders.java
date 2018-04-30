import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Exploders {
	private double vx, vy;
	public double x, y, size;
	private int life;
	public static boolean explodeable;

	public Exploders() {
		life = 3;
		size = 10;
		x = Math.random() * MainClass.WIDTH;
		y = Math.random() * MainClass.HEIGHT;
		vx = Math.random() * 5 - 2.5;
		vy = Math.random() * 5 - 2.5;
		int temp = (int) (Math.random() * 4);
		switch (temp) {
		case 0:
			y = -size;
			if (vy < 0)
				vy *= -1;
			break;
		case 1:
			x = -size;
			if (vx < 0)
				vx *= -1;
			break;
		case 2:
			y = MainClass.HEIGHT;
			if (vy > 0)
				vy *= -1;
			break;
		case 3:
			x = MainClass.WIDTH;
			if (vx > 0)
				vx *= -1;
			break;
		}
	}

	public boolean hit(Webs w) {
		double dx = x - w.getX();
		double dy = y - w.getY();
		return Math.sqrt(dx * dx + dy * dy) < (w.size() / 2);

	}

	public void draw(Graphics g) {
		if (hitspider() && explodeable == true) {
			g.setColor(Color.yellow);
			g.fillOval((int) x - 150, (int) y - 150, (int) size + 300,
					(int) size + 300);
			g.setColor(Color.orange);
			g.fillOval((int) x - 100, (int) y - 100, (int) size + 200,
					(int) size + 200);
			g.setColor(Color.red);
			g.fillOval((int) x - 50, (int) y - 50, (int) size + 100,
					(int) size + 100);
		} else {
			g.setColor(Color.red);
			g.drawOval((int) x, (int) y, (int) size, (int) size);
		}
	}
	
	public boolean isDead(){
		return life<0;
	}

	public void update(double dx, double dy) {
		x += vx + dx;
		y += vy + dy;
		if (x < -size)
			x = MainClass.WIDTH;
		if (x > MainClass.WIDTH)
			x = -size;
		if (y < -size)
			y = MainClass.HEIGHT;
		if (y > MainClass.HEIGHT)
			y = -size;
		if(hitspider())life--;
	}

	public boolean hitspider() {
		double dx = x - Spider.Wx;
		double dy = y - Spider.Wy;
		return Math.sqrt(dx * dx + dy * dy) < size / 2 + 65;
	}
}
