import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Pacman implements KeyListener{

	private static final int SIZE = 30;
	private double x,y,vX,vY,oldX,oldY,speed;
	private boolean leftPressed,rightPressed,upPressed,downPressed;
	
	public Pacman(){
		oldX = x = MainClass.WIDTH/2;
		oldY = y = MainClass.HEIGHT/2+130;
		vX = 0;
		vY = 0;
	}
	
	public void draw(Graphics p){
		p.setColor(Color.yellow);
		p.fillOval((int)x, (int)y, SIZE, SIZE);
	}
	
	
	public void update(){
		oldX = x;
		oldY = y;
		x += vX;
		y += vY;
	
		if(MainClass.level<=49){
			speed = 3;
		}else{
			speed = 5;
		}
		
		if(leftPressed)vX=-speed;
		if(rightPressed)vX=speed;
		if(upPressed)vY=-speed;
		if(downPressed)vY=speed;
		
		if(leftPressed)vY=0;
		if(rightPressed)vY=0;
		if(downPressed)vX=0;
		if(upPressed)vX=0;
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT)leftPressed=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)rightPressed=true;
		if(e.getKeyCode()==KeyEvent.VK_UP)upPressed=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)downPressed=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT)leftPressed=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)rightPressed=false;
		if(e.getKeyCode()==KeyEvent.VK_UP)upPressed=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)downPressed=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	public void stop() {
		x = oldX;
		y = oldY;
	}
	
	public boolean hit(Ghost g){
		double dX = SIZE/2+ this.x - g.getX()-15;
		double dY = SIZE/2+ this.y - g.getY()-15;
		return Math.sqrt(dX*dX+dY*dY)<SIZE;
	}

	public double getX(){ return x; }
	public double getY(){ return y; }
	public double getSize(){ return SIZE;}
	
	
}
