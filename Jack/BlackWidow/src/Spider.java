import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spider implements KeyListener {
	double vx, vy;
	int lifes, gainCount, slowtime, deathint;
	long score;
	double myX, myY;
	static int Wx, Wy;
	private boolean isdead, GainLife, LeftPressed, RightPressed, UpPressed, DownPressed;
	public boolean justdied;

	public Spider() {
		gainCount = 0;
		GainLife = false;
		isdead = false;
		lifes = 5;
		vx = 0.0;
		vy = 0.0;
		myX = MainClass.WIDTH / 2 - 10;
		myY = MainClass.HEIGHT / 2 - 10;

	}
	public void slow(){
		slowtime = 150;
	}

	public void takedamage() {
		if (lifes >= 2) {
			lifes--;
			vx = 0.0;
			vy = 0.0;
			myX = MainClass.WIDTH / 2 - 10;
			myY = MainClass.HEIGHT / 2 - 10;
			slowtime = 0;
			deathint = 100;
		} else {
			isdead = true;
		}
	}

	public void update() {
		if (UpPressed)
			vy -= 0.25;
		if (DownPressed)
			vy += 0.25;
		if (LeftPressed)
			vx -= 0.25;
		if (RightPressed)
			vx += 0.25;
		vy *= .95;
		vx *= .95;

		if (isdead()) {
			vy = 0;
			vx = 0;
		}
		myX += vx;
		myY += vy;
		Wx = (int) myX;
		Wy = (int) myY;
		if (myX > MainClass.WIDTH || myX < 0)
			vx *= -1;
		if (myY > MainClass.HEIGHT + 10 || myY < 0)
			vy *= -1;
		if(slowtime >= 1){
			vx = vx * .5;
			vy = vy * .5;
			slowtime--;
		}
		if(deathint >= 1){
			deathint--;
			Exploders.explodeable = false;
		}else{
			Exploders.explodeable = true;
		}
		
		if (MainClass.score >= 20000 && gainCount == 0) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 40000 && gainCount == 1) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 60000 && gainCount == 2) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 80000 && gainCount == 3) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 100000 && gainCount == 4) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 120000 && gainCount == 5) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 140000 && gainCount == 6) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 160000 && gainCount == 7) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 180000 && gainCount == 8) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 200000 && gainCount == 9) {
			lifes++;
			gainCount++;
		}
		if (MainClass.score >= 220000 && gainCount == 10) {
			lifes++;
			gainCount++;
		}
	}

	public void draw(Graphics g) {
		int x = (int) myX;
		int y = (int) myY;
		g.setColor(Color.red);
		if(deathint >= 20){
			g.setColor(Color.white);
		}
		if(deathint < 20 && deathint >= 1){
			g.setColor(Color.blue);
		}
		g.drawOval(x - 10, y + 5, 10, 10);
		g.drawOval(x, y, 20, 20);
		int[] px1 = { x + 29, x + 5, x + 29 };
		int[] py1 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px1, py1, 3);
		int[] px2 = { x + 24, x, x + 24 };
		int[] py2 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px2, py2, 3);
		int[] px3 = { x + 19, x, x + 19 };
		int[] py3 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px3, py3, 3);
		int[] px4 = { x - 4, x + 20, x - 4 };
		int[] py4 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px4, py4, 3);
		int[] px5 = { x - 9, x + 15, x - 9 };
		int[] py5 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px5, py5, 3);
		int[] px6 = { x - 14, x + 10, x - 14 };
		int[] py6 = { y - 10, y + 10, y + 30 };
		g.drawPolyline(px6, py6, 3);
		if(slowtime >= 1){
			g.setColor(Color.green);
			g.fillOval(x-8, y + 7, 6, 6);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D && !isdead) {
			RightPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A && !isdead) {
			LeftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W && !isdead) {
			UpPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && !isdead) {
			DownPressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			RightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			LeftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			UpPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			DownPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public boolean isdead() {
		return isdead;
	}

	public double getX() {
		return myX;
	}

	public double getY() {
		return myY;
	}

}
