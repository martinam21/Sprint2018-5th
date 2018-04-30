import java.awt.Color;
import java.awt.Graphics;

public class Walls {
	
	public static final int WIDTH = MainClass.WIDTH;
	public static final int HEIGHT = MainClass.HEIGHT;
	public static final int SPACING = (int) 27.5;
	int x = WIDTH/2;
	int y = HEIGHT/2;
	
	public void draw(Graphics bg){
			//LINE 1
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2-180, (int) (HEIGHT/2-174.5), (int)(WIDTH/2+180), (int) (HEIGHT/2-174.5));
		//LINE 2
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2+180), (int) (HEIGHT/2-174.5), (int)(WIDTH/2+180), (int) (HEIGHT/2-107.5));
		//LINE 3
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2+180), (int) (HEIGHT/2-107.5), WIDTH/2+170, (int) (HEIGHT/2-107.5));
		//LINE 4
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2+170, (int) (HEIGHT/2-107.5), WIDTH/2+170, (int) (HEIGHT/2-13.75));
		//LINE 5
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2+170, (int) (HEIGHT/2-13.75), (int)(WIDTH/2+198.5), (int) (HEIGHT/2-13.75));
		//LINE 6
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2+170, (int) (HEIGHT/2+13.75), (int)(WIDTH/2+198.5), (int) (HEIGHT/2+13.75));
		//LINE 7
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2+170, (int) (HEIGHT/2+13.75), WIDTH/2+170, (int) (HEIGHT/2+107.5));
		//LINE 8
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2+170, (int) (HEIGHT/2+107.5), (int)(WIDTH/2+197.5), (int) (HEIGHT/2+107.5));
		//LINE 9
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2+197.5), (int) (HEIGHT/2+107.5), (int)(WIDTH/2+197.5), (int) (HEIGHT/2+209.5));
		//LINE 10
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2+197.5), (int) (HEIGHT/2+209.5), (int) (WIDTH/2-197.5), (int) (HEIGHT/2+209.5));
		//LINE 12
		bg.setColor(Color.orange);
		bg.drawLine((int) (WIDTH/2-197.5), (int) (HEIGHT/2+209.5), (int)(WIDTH/2-197.5), (int) (HEIGHT/2+107.5));
		//LINE 13
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2-197.5), (int) (HEIGHT/2+107.5), WIDTH/2-170, (int) (HEIGHT/2+107.5));
		//LINE 14
		bg.setColor(Color.orange);
		bg.drawLine(WIDTH/2-170, (int) (HEIGHT/2+107.5), WIDTH/2-170, (int) (HEIGHT/2+13.75));
		//LINE 15
		bg.setColor(Color.orange);
		bg.drawLine((int) (WIDTH/2-198.5), (int) (HEIGHT/2+13.75), WIDTH/2-170, (int) (HEIGHT/2+13.75));
		//LINE 16
		bg.setColor(Color.orange);
		bg.drawLine((int) (WIDTH/2-198.5), (int) (HEIGHT/2-13.75), WIDTH/2-170, (int) (HEIGHT/2-13.75));
		//LINE 17
		bg.setColor(Color.orange);
		bg.drawLine((int) (WIDTH/2-170), (int) (HEIGHT/2-13.75), WIDTH/2-170, (int) (HEIGHT/2-107.5));
		//LINE 18
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2-180), (int) (HEIGHT/2-107.5), WIDTH/2-170, (int) (HEIGHT/2-107.5));
		//LINE 19
		bg.setColor(Color.orange);
		bg.drawLine((int)(WIDTH/2-180), (int) (HEIGHT/2-174.5), (int)(WIDTH/2-180), (int) (HEIGHT/2-107.5));
		//CENTER BOX
		bg.setColor(Color.orange);
		bg.drawRect(WIDTH/2-50, (int) (HEIGHT/2-22.5), 100, 45);
		bg.setColor(Color.black);
		bg.drawLine(WIDTH/2-15, (int)(HEIGHT/2-22.5), WIDTH/2+12, (int)(HEIGHT/2-22.5));;
		//WALL 2
		bg.setColor(Color.red);
		bg.fillRect(WIDTH/2-50, HEIGHT/2-65, 100, 15);
		//WALL 3
		bg.setColor(Color.yellow);
		bg.fillRect((int) (WIDTH/2-7.5), (int) (HEIGHT/2-107.5), 16, (int) 43);
		//WALL 4
		bg.setColor(Color.green);
		bg.fillRect(WIDTH/2+35, (int) (HEIGHT/2-108), 65, (int) 16);
		//WALL 5
		bg.setColor(Color.GREEN);
		bg.fillRect(WIDTH/2-100, (int) (HEIGHT/2-108), 65, 16);
			//WALL 6
		bg.setColor(Color.PINK);
		bg.fillRect((int) (WIDTH/2-52.5), (int) (HEIGHT/2-147), 106, 12);
		//WALL 7
		bg.setColor(Color.cyan);
		bg.fillRect((int)(WIDTH/2+80),(int) (HEIGHT/2-175), 15, (int) 40);
		//WALL 8
		bg.setColor(Color.blue);
		bg.fillRect((int)(WIDTH/2-95), (int) (HEIGHT/2-174.5), 15, (int) 42.5);
		//WALL 9
		bg.setColor(Color.magenta);
		bg.fillRect((int) (WIDTH/2+124.5), HEIGHT/2-147, 29, 12);
		//WALL 10
		bg.setColor(Color.DARK_GRAY);
		bg.fillRect((int) (WIDTH/2+127.5), (int) (HEIGHT/2-107.5), 16, (int) 58);
		//WALL 11
		bg.setColor(Color.LIGHT_GRAY);
		bg.fillRect((int) (WIDTH/2+77.5), (int) (HEIGHT/2-65), 50, 15);
		//WALL 12
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2+78.5), (int) (HEIGHT/2-22.5), 57, 15);
		//WALL 13
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2+120), (int) (HEIGHT/2-7.5), 15, (int) 29.5);
		//WALL 14
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2+77.5), HEIGHT/2+15, 15, 35);
		//WALL 15
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2+35, HEIGHT/2+50, (int) 107.5, 15);
		//WALL 16
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2+77.5), (int) (HEIGHT/2+92.5), 65, 15);
		//WALL 17
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2+122.5), HEIGHT/2+135, (int) 47.5, (int) 47.5);
		//WALL 18
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-7.5), HEIGHT/2+50, 15, (int) 42.5);
		//WALL 19
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2-50, (int) (HEIGHT/2+92.5), 100, 15);
		//WALL 20
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2+35, HEIGHT/2+135, (int) 60, 15);
		//WALL 21
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2+80, HEIGHT/2+150, 15, (int) 32.5);
		//WALL 22
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-7.5), (int) (HEIGHT/2+135), 15, (int) 42.5);
		//WALL 23
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2-50, (int) (HEIGHT/2+177.5), 100, 5);
		//WALL 24
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2-95, HEIGHT/2+135, (int) 60, 15);
		//WALL 25
		bg.setColor(Color.orange);
		bg.fillRect(WIDTH/2-95, HEIGHT/2+150, 15, (int) 32.5);
		//WALL 26
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-170), HEIGHT/2+135, (int) 47.5, (int) 47.5);
		//WALL 27
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-142.5), (int) (HEIGHT/2+92.5), 65, 15);
		//WALL 28
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-142.5), HEIGHT/2+50, (int) 107.5, 15);
		//WALL 29
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-92.5), HEIGHT/2+15, 15, 35);
		//WALL 30
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-135), (int) (HEIGHT/2-7.5), 15, (int) 29.5);
		//WALL 31
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-135), (int) (HEIGHT/2-22.5), 58, 15);
		//WALL 32
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-127.5), (int) (HEIGHT/2-65), 50, 15);
		//WALL 33
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-142), (int) (HEIGHT/2-107.5), 15, (int) 58); //(int) (WIDTH/2-170)
		//WALL 34
		bg.setColor(Color.orange);
		bg.fillRect((int) (WIDTH/2-155.5), HEIGHT/2-150, 30, 18);
	}

	}
