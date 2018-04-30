import java.awt.Color;
import java.awt.Graphics;


public class Ground {
	
	private double x;
	private static double y;
	private double WIDTH;
	private double HEIGHT;
	
	public Ground(){
		x = 0;
		y = 590;
		WIDTH = 1800;
		HEIGHT = 10;
	}
	
	public void draw(Graphics g){
		g.setColor((new Color(0x13BD4E)));
		g.fillRect((int) x,(int) y,(int) WIDTH,(int) HEIGHT);
		int[] k = { (int) x + 1500, (int) x + 1720, (int) x + 1630, (int) x + 1590};
		int[] l = { (int) y, (int) y, (int) Target.getY() + 70, (int) Target.getY() + 70 };
		g.setColor((new Color(0x13BD4E)));
		g.fillPolygon(k, l, 4);
	}
	
	public double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}


}
