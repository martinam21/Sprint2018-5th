import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Pac1 implements KeyListener {

	static int x, oldX;
	static int y, oldY;
	static double vX;
	static double vY;
	private double direction;
	private double health;
	public static final int SIZE = 20;
	public static final int BUFFER = (int) 3.75;
	private boolean leftPressed, rightPressed, upPressed, downPressed;
	
	public Pac1() {
		oldX = x = MainClass.WIDTH / 2 - SIZE/2;
		oldY = y = (int) (MainClass.HEIGHT / 2 - 47);
		vX = 0;
		vY = 0;
		direction = Math.PI * 2;
	}
	
	public void update() {
		if (leftPressed) {
			vX = -1;
			vY = 0;
		}
		if (rightPressed) {
			vX = 1;
			vY = 0;
		}
		if (upPressed) {
			vY = -1;
			vX = 0;
		}
		if (downPressed) {
			vY = 1;
			vX = 0;
		}
		if(x == 201.5 && vX<0){
			x = (int) 577.5;
			vX = -1;
		}
		if(x == 578.5 && getvX()>0){
			x = (int) 202.5;
			vX = 1;
		}
		oldX = x;
		oldY = y;
		x += vX;
		y += vY;
		System.out.println(y);
	}
	
	public boolean hitWall(BufferedImage map){
		int w = x;
		int z = y;
		Graphics g = map.getGraphics();
		g.setColor(Color.red);
		if(vX<0){
			//top left corner
			if(!new Color(map.getRGB(w - BUFFER, z - BUFFER)).equals(Color.black))return true;
			// left center
			if(!new Color(map.getRGB(w - BUFFER, z + SIZE/2)).equals(Color.black))return true;
			//bottom left corner
			if(!new Color(map.getRGB(w - BUFFER, z + SIZE + BUFFER)).equals(Color.black))return true;
		}
		if(vX>0){
			//top right corner
			if(!new Color(map.getRGB(w + SIZE + BUFFER, z - BUFFER)).equals(Color.black))return true; // no wall at x y
			// right center
			if(!new Color(map.getRGB(w + SIZE + BUFFER, z + SIZE/2)).equals(Color.black))return true;
			//bottom right corner
			if(!new Color(map.getRGB(w + SIZE + BUFFER, z + SIZE + BUFFER)).equals(Color.black))return true; // no wall at x y
		}
		if(vY<0){			
			//top left corner
			if(!new Color(map.getRGB(w - BUFFER, z - BUFFER)).equals(Color.black))return true;
			// top center
			if(!new Color(map.getRGB(w + SIZE/2, z - BUFFER)).equals(Color.black))return true;
			//top right corner
			if(!new Color(map.getRGB(w + SIZE + BUFFER, z - BUFFER)).equals(Color.black))return true;
		}
		if(vY>0){
			//bottom left corner
			if(!new Color(map.getRGB(w - BUFFER, z + SIZE + BUFFER)).equals(Color.black))return true;
			// bottom center
			if(!new Color(map.getRGB(w + SIZE/2, z + SIZE + BUFFER)).equals(Color.black))return true;
			//bottom right corner
			if(!new Color(map.getRGB(w + SIZE, z + SIZE + BUFFER)).equals(Color.black))return true;
		}
		return false;
	}

	public void draw1(Graphics g) {
		g.setColor(Color.PINK);
		g.drawOval(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.setColor(Color.red);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	public double getvX() {
		return vX;
	}

	public double getvY() {
		return vY;
	}

	public void backUp() {
		x = oldX;
		y = oldY;
		
	}
}
