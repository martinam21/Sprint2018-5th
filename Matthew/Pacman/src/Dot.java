import java.awt.Color;
import java.awt.Graphics;


public class Dot {

	private double x;
	private double y;
	private static final int SIZE = 10;
	public Color myColor;
	
	public Dot(int x, int y,Color c){
		this.y = y;
		this.x = x;
		myColor = c;
	}
	
	public void draw(Graphics g){
		g.setColor(myColor);
		g.drawOval((int)x, (int)y, (int)SIZE, (int)SIZE);
	}
	
	
	public boolean eat(Pacman p){
		double dX = SIZE/2+ this.x - p.getX()-15;
		double dY = SIZE/2+ this.y - p.getY()-15;
		return Math.sqrt(dX*dX+dY*dY)<SIZE+10;
	}
	
	public double getX(){ return x; }
	public double getY(){ return y; }
	public double getSize(){ return SIZE;}
	public Color getColor(){ return myColor;}
	
}
