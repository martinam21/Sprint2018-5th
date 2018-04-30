import java.awt.Color;
import java.awt.Graphics;


public class Log {
	private double x,y,vX,vY,size;
	
	public Log(int x, int y){
		size = 45;
		this.x = x;
		this.y = y;
		vX=1;
	}
	
	public void draw(Graphics bg) {
		bg.setColor(Color.gray);
		bg.fillRect((int)x, (int)y, (int)size, (int)size);
		
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	

}
