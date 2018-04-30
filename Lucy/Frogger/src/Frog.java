import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Frog implements KeyListener {
	private boolean leftPressed, rightPressed, upPressed, downPressed;
	private double x,y;
	
	public Frog(){
		x = 0;
		y = 0;
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.green);
		//frog body
		g.fillOval(375+(int)x, 550+(int)y, 35,35);
		//feet
		g.fillOval(405+(int)x, 575+(int)y, 10, 10);
		g.fillOval(371+(int)x, 575+(int)y, 10, 10);
		g.setColor(Color.black);
		//eyes
		g.fillOval(395+(int)x, 557+(int)y, 10,10);
		g.fillOval(383+(int)x, 557+(int)y, 10,10);
		
		
	}
	public void update(){
		if(upPressed)y-=40;
		if(downPressed)y+=40;
		if(leftPressed)x-=10;
		if(rightPressed)x+=10;
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			upPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downPressed = true;
	}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			upPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
