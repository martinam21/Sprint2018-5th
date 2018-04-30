import java.awt.Color;
import java.awt.Graphics;


public class Paintball {
	private double x,y,vX,vY;
	private Color myColor;
	Human shooter;
	
	
	public Paintball(double direction, int x, int y, Human me){
		shooter = me;
		myColor = Color.orange; 
		this.x = x;
		this.y = y;
		vX = Math.sin(direction)*10; 
		vY = -Math.cos(direction)*10; 
	}

	public void update() {
		x += vX;
		y += vY;
		
		
	}

	public void draw(Graphics g) {
		g.setColor(myColor);
		g.fillOval((int)x-5, (int)y-5, 10, 10);
		
	}
	
	public double getX(){
		return x; 
	}
	
	public double getY(){
		return y; 
	}

}
