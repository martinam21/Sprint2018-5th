import java.awt.Color;
import java.awt.Graphics;

public class Ghost {

	private double x, y, vX, vY, oldX, oldY, dX, dY;
	public boolean eatable;
	private Color myColor;
	private static final int SIZE = 30;
	private double speed;
	public int age;
	

	public Ghost(Color c) {
		myColor = c;
		reset();
	}
	
	public void reset(){
		age = 0;
		eatable = false;
		oldX = x = MainClass.WIDTH / 2;
		oldY = y = MainClass.HEIGHT / 2;
		vX = 0;
		vY = -2;
	}

	public void draw(Graphics g) {
		if(eatable)g.setColor(Color.BLUE);
		else g.setColor(myColor);
		g.fillOval((int) x, (int) y, SIZE, SIZE);
	}

	public void update(Pacman p) {
		oldX = x;
		oldY = y;
		if(MainClass.level==1)speed = 2;
		if(MainClass.level==2)speed = 2.5;
		if(MainClass.level>=3 && MainClass.level<=4)speed = 3;
		if(MainClass.level>=5 && MainClass.level<=9)speed = 3.5;
		if(MainClass.level>=10 && MainClass.level<=24)speed = 4;
		if(MainClass.level>=25 && MainClass.level<=49)speed = 5;
		if(MainClass.level>=50 && MainClass.level<=99)speed = 6;
		if(MainClass.level>=100 && MainClass.level<=149)speed = 7;
		if(MainClass.level>=150 && MainClass.level<=199)speed = 8;
		if(MainClass.level>=200 && MainClass.level<=249)speed = 9;
		if(MainClass.level>=250)speed = 10;
		x += vX;
		y += vY;
		if (Math.random() < speed*.01) {
			int dir = (int) (Math.random() * 4);
			switch (dir) {
			case 0:
				vX = 0;
				vY = speed;
				break;
			case 1:
				vX = 0; 
				vY = -speed;
				break;
			case 2:
				vX = speed;
				vY = 0;
				break;
			case 3:
				vX = -speed;
				vY = 0;
				break;
			}
//			if(Math.random()<1){
//				double dX = this.x - p.getX();
//				double dY = this.y - p.getY();
//				if(dX<0){
//					vX = speed;
//					vY = 0;
//				}
//				if(dX>0){
//					vX = -speed;
//					vY = 0;
//				}
//				if(dY<0){
//					vX = 0;
//					vY = speed;
//				}
//				if(dY>0){
//					vX = 0;
//					vY = -speed;
//				}
//			}
		}
	}
	
	public void stopGhost() {
		x = oldX;
		y = oldY;
	}
	
	public double getX(){ return x; }
	public double getY(){ return y; }
	public double getSize(){ return SIZE;}

	public void changeDir() {
		int dir = (int) (Math.random() * 4);
		switch (dir) {
		case 0:
			vX = 0;
			vY = speed;
			break;
		case 1:
			vX = 0;
			vY = -speed;
			break;
		case 2:
			vX = speed;
			vY = 0;
			break;
		case 3:
			vX = -speed;
			vY = 0;
			break;
		}
	}
}	
