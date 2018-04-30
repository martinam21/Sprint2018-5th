import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Arrow implements KeyListener {

	public static final int SIZE = 20;
	double shadow;
	double direction;
	double speed;
	double dV;
	double gravity;
	private boolean leftPressed, rightPressed, spacePressed, enterPressed;
	public boolean didWin;
	public double arrowX;
	public double arrowY;

	public Arrow() {
		didWin = false;
		enterPressed = false;
		arrowX = MainClass.WIDTH / 10;
		arrowY = MainClass.HEIGHT / 2;
		leftPressed = rightPressed = false;
		shadow = 30;
		dV = 0;
		speed = 0;
		direction = 0;
		gravity = 0.0058;
	}

	public void update() {
		if (leftPressed && speed == 0)
			dV -= 0.05;
			shadow = -0.05;
		if (rightPressed && speed == 0)
			dV += 0.05;
			shadow +=0.05;
		direction += dV;
		dV *= 0.01;
		if (spacePressed)
			speed = 10;
		if (speed != 0)
			dV += gravity;
		arrowX += speed * Math.sin(direction);
		arrowY += -speed * Math.cos(direction);
		if (enterPressed && !didWin) {
			restart();
			MainClass.score = 0;
		}
		if (spacePressed && didWin) {
			didWin = false;
		}
		if (spacePressed && MainClass.gameover){
			MainClass.gameover = false;
		}
	}

	public void restart() {
		speed = 0;
		arrowX = MainClass.WIDTH / 10;
		arrowY = MainClass.HEIGHT / 2;
		MainClass.gameover = false;

	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old, at;
		old = g2.getTransform();
		at = new AffineTransform();
		at.translate(arrowX, arrowY);
		at.rotate(direction);
		at.translate(-arrowX, -arrowY);
		g2.setTransform(at);
		int[] x = { (int) arrowX - 10, (int) arrowX, (int) arrowX + 10 };
		int[] y = { (int) arrowY + 12, (int) arrowY - 15, (int) arrowY + 12 };
		g.setColor(Color.black);
		g.fillPolygon(x, y, 3);
		g.setColor(new Color(0x733E3E));
		g.fillRect((int) arrowX - 3, (int) arrowY + 11, 7, 40);
		int[] k = { (int) arrowX - 10, (int) arrowX - 2, (int) arrowX - 2 };
		int[] l = { (int) arrowY + 50, (int) arrowY + 18, (int) arrowY + 61 };
		g.setColor(new Color(0x858585));
		g.fillPolygon(k, l, 3);
		int[] n = { (int) arrowX + 11, (int) arrowX + 3, (int) arrowX + 3 };
		int[] m = { (int) arrowY + 50, (int) arrowY + 20, (int) arrowY + 60 };
		g.setColor(new Color(0x858585));
		g.fillPolygon(n, m, 3);
		g2.setTransform(old);
		//shadow
		g.setColor(Color.black);
		g.fillRect((int) arrowX, 590, (int)shadow, 5);

	}
	
	public boolean hitTarget(Target a) {
		if (arrowX + SIZE < a.getX())
			return false;
		if (arrowX > a.getX() + Target.W)
			return false;
		if (arrowY + SIZE < a.getY())
			return false;
		if (arrowY > a.getY() + Target.H)
			return false;
		didWin = true;
		return true;
	}

	public boolean hitGround(BufferedImage im) {
		int checkX = (int)(arrowX + speed * Math.sin(direction));
		int checkY = (int)(arrowY - speed * Math.cos(direction));
		if(checkX<0 || checkY<0 ||checkX>=im.getWidth() || checkY>= im.getHeight()){
			return false;
		}
		Color c = new Color(im.getRGB(checkX, checkY));
		if(c.equals(new Color(0x13BD4E)))return true;
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			didWin = false;
			enterPressed = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public double getDirection() {
		return direction;
	}

	public double getSpeed() {
		return speed;
	}

	public double getGravity() {
		return gravity;

	}

	public double getY() {
		return arrowY;
	}

	public double getX() {
		return arrowX;
	}

	public boolean didWin() {
		return didWin;
	}

}
