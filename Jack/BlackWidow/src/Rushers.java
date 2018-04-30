import java.awt.Color;
import java.awt.Graphics;

public class Rushers {
	private double x, y, vx, vy, size;

	public Rushers() {
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
		g.setColor(Color.yellow);
		g.drawOval((int) x, (int) y, (int) size, (int) size);
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
	}

	public boolean hitspider() {
		double dx = x - Spider.Wx;
		double dy = y - Spider.Wy;
		return Math.sqrt(dx * dx + dy * dy) < size / 2 + 15;
	}
}
