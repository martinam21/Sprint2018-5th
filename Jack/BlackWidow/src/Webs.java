import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Webs {
	private double vx, vy;
	int x, y;
	BufferedImage myImage;

	public Webs(double direction) {
		try {
			myImage = ImageIO.read(getClass().getClassLoader().getResource(
					"resources/web.png"));
		} catch (IOException e) {
		}
		x = Spider.Wx;
		y = Spider.Wy + 13;
		this.vx = Math.sin(direction) * 20;
		this.vy = -Math.cos(direction) * 20;
		update(0, 0);
		update(0, 0);
	}

	public int size() {
		return myImage.getHeight();
	}

	public void draw(Graphics g) {
		g.drawImage(myImage, (int) x - 15, (int) y - 15, null);
	}

	public void update(double dx, double dy) {
		x += vx + dx * 2;
		y += vy + dy * 2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
