import java.awt.Color;
import java.awt.Graphics;


public class Spark {
	private double x,y,vX,vY;
	private Color myColor;
	
	public Spark(double x, double y, Color c){
		myColor = c;
		this.x = x;
		this.y = y;
		vX = (Math.random()*10)-5;
		vY = (Math.random()*10)-5;
		double distance = Math.sqrt(vX*vX+vY*vY);
		while(distance>5){
			vX = (Math.random()*10)-5;
			vY = (Math.random()*10)-5;
			distance = Math.sqrt(vX*vX+vY*vY);
		}
	}
	
	public void draw(Graphics g){
		g.setColor(myColor);
		g.fillOval((int)x+2, (int)y+2, 3, 3);
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}

}
