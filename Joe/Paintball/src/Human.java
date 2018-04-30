import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class Human implements KeyListener {

	double direction, speed, health, dV;
	private boolean isDead, upPressed, downPressed, spacePressed, enterPressed;
	private boolean leftPressed,rightPressed;
	private int x, y;
	private boolean flip;
	private boolean canShoot;
	
	
	
	
	
	public Human(int x, int y, boolean flip) {
		this.flip = flip;
		this.x = x;
		this.y = y;
		dV = 0;
		health = 1;
		direction = Math.PI/4;
		canShoot = true;
		
 
	}

	public void takeDamage() {
		health -= 0.1;
	}

	public boolean justDied() {
		if (health < 0 && !isDead) {
			return isDead = true;
		}
		return false;
		
	}
	
	public boolean hit(Paintball b){
		if(this == b.shooter)return false;
		double dX = this.x - b.getX(); 
		double dY = this.y - b.getY();
		return Math.sqrt(dX*dX+dY*dY)< 55;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		if (flip) {
			AffineTransform tx = new AffineTransform();
			tx.translate(x, 0);
			tx.scale(-1, 1);
			tx.translate(-x, 0);
			g2.setTransform(tx);
		}
		if (health < 0)
			return;
		if (health > .5)
			g.setColor(Color.green);
		if (health <= .5 && health > .3)
			g.setColor(Color.yellow);
		if (health <= .3)
			g.setColor(Color.red);
		g.fillRect(x-15, MainClass.HEIGHT/2+195, 5, (int)(health*40)); 
		

		
		
	
		
		

		g.fillOval(MainClass.WIDTH / 2 + 20, MainClass.HEIGHT / 2, 4, 0);
		int centerX = MainClass.WIDTH / 2;
		int centerY = MainClass.HEIGHT / 2;
		
		g.setColor(Color.black);
		g.drawOval(x, y, 30, 30);
		g.drawLine(x + 15, y + 30, x + 15, y + 75);
		g.drawLine(x + 15, y + 75, x, y + 100);
		g.drawLine(x + 15, y + 75, x + 30, y + 100);
		g.drawLine(x, y + 50, x + 30, y + 50);
	

		g.fillRect(x + 8, y + 60, 20, 7);
		g.drawLine(x + 40, y + 50, x + 40, y + 65);
		g.drawLine(x + 20, y + 65, x + 40, y + 65);
		g.fillRect(x + 35, y + 45, 35, 7);
		g.fillRect(x + 55, y + 50, 5, 10);
		g.fillRect(x + 45, y + 40, 4, 7);
		g.fillOval(x + 37, y + 30, 20, 10);
		g2.setTransform(old); 


	}

	public void update() {
		if (upPressed)
			dV -= 0.02;
		
		if (downPressed)
			dV += 0.02;
	
		
		if (leftPressed)
			x -= 5;
	
		if (rightPressed)
			x += 5; 
		
	
		
		direction += dV;
		dV *= 0.7;
		
		
		
	}
	

	public Paintball shoot() {
		if (canShoot && spacePressed) {
			canShoot = false;
			double dir = flip ? -Math.PI/4-direction : direction;
			return new Paintball(dir, x, y+50, this); 
		} else {
			return null; 
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("pressing key");
		if (e.getKeyCode() == KeyEvent.VK_UP)
			upPressed = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = true;
		if (flip && e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = true;
		if (flip && e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = true;
		if (flip && e.getKeyCode() == KeyEvent.VK_SPACE)
			spacePressed = true;
		if (!flip && e.getKeyCode() == KeyEvent.VK_ENTER)
			spacePressed = true;
		if (!flip && e.getKeyCode() == KeyEvent.VK_W)
			upPressed = true; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_S)
			downPressed = true; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_A)
			leftPressed = true; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_D)
			rightPressed = true; 
	} 

	@Override
	public void keyReleased(KeyEvent e) {
		canShoot = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			upPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = false;
		if (flip && e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		if (flip && e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if (flip && e.getKeyCode() == KeyEvent.VK_SPACE)
			spacePressed = false;
		if (!flip && e.getKeyCode() == KeyEvent.VK_ENTER)
			spacePressed = false;
		if (!flip && e.getKeyCode() == KeyEvent.VK_W)
			upPressed = false; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_S)
			downPressed = false; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_A)
			leftPressed = false; 
		if(!flip && e.getKeyCode() == KeyEvent.VK_D)
			rightPressed = false; 


	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isDead() {
		
		return isDead;
	}

	public double getDirection() {
	
		return direction;
	}

}
