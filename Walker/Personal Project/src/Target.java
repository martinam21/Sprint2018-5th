import java.awt.Color;
import java.awt.Graphics;

public class Target {

	private static double y;
	private static double x;
	public static int W;
	public static int H;

	public Target() {
		W = 20;
		H = 60;
		x = 1600;
		y = Math.random() * (MainClass.HEIGHT - 111) + 30;
	}

	public void restart() {
		y = Math.random() * (MainClass.HEIGHT - 111) + 30;
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0x733E3E));
		g.fillRect((int) x, (int) y, W, H);
		int[] k = { (int) x, (int) x + 10, (int) x };
		int[] l = { (int) y + 60, (int) y + 60, (int) y + 71 };
		g.setColor(Color.black);
		g.fillPolygon(k, l, 3);
		int[] n = { (int) x + 10, (int) x + 20, (int) x + 20 };
		int[] m = { (int) y + 60, (int) y + 60, (int) y + 71 };
		g.setColor(Color.black);
		g.fillPolygon(n, m, 3);
		g.setColor(Color.red);
		g.fillRect((int) x - 2, (int) y + 5, 5, H - 10);

	}

	public double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

}
