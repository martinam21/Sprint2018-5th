import java.awt.Color;
import java.awt.Graphics;


public class Wall {

	private double x;
	private double y;
	private double size;
	
	public Wall(int x, int y){
		size = 25;
		this.y = y;
		this.x = x;
	}
	
	public boolean hit(Pacman p){
		// above
		if(p.getY()+p.getSize()<y)return false;
		// below
		if(p.getY()>y+size)return false;
		// left 
		if(p.getX()+p.getSize()<x)return false;
		// right
		if(p.getX()>x+size)return false;
		return true;
	}
	
	public boolean hitGhost(Ghost g){
		// above
		if(g.getY()+g.getSize()<y)return false;
		// below
		if(g.getY()>y+size)return false;
		// left 
		if(g.getX()+g.getSize()<x)return false;
		// right
		if(g.getX()>x+size)return false;
		return true;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, (int)size, (int)size);
	}
	
}
