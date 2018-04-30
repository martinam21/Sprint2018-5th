import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle_P2 implements KeyListener {
	public static final int HEIGHT = 100;
	public static final int WIDTH = 25;
	public static final int MINY = 100;
	public static final int MAXY = 700;

	
	
	private double py,pv;//paddle position and velocity
	private boolean upPressed;
	private boolean downPressed;
	
	
	
	public Paddle_P2(){
		py = HEIGHT/2;
		pv = 4;
		upPressed = downPressed = false;	
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(1630,(int)py, WIDTH, HEIGHT);
	}
	
	
	public void update(){
		if(upPressed)py-=5;
		if(downPressed)py+=5;
		py += pv;
		pv *= 0.95;
		if((int)py<130)py=130;
		if((int)py>765)py=765;
	}
	
	public double getX() {return 1630;}
	public double getY() {return py;}
	public double getSize() {return getSize();}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}