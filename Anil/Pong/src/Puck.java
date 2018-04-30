import java.awt.Color;
import java.awt.Graphics;


public class Puck {
	public static final int Height = 10;
	public static final int Width = 100;
	public static final int Speed = 4;
	public static final int SIZE = 50;
	
	
	private double x,y; //puck position and velocity
	private double vx;
	private double vy;
	public int myScore;
	public int xScore;
	public boolean myScored;
	public boolean xScored;
	
	public Puck(){
		myScored = xScored = false;
		myScore=xScore=0;
		x = MainClass.WIDTH/2;
		y = MainClass.HEIGHT/2;
		vx = 3;
		vy = 3;
	}
	
	public void draw(Graphics g) {
		// Head
		g.setColor(Color.black);
		g.fillOval((int)x,(int) y, 50, 50);
		// Mouth
		g.setColor(Color.white);
		g.fillOval((int)x+8, (int)y+10, 35, 35);
		g.setColor(Color.black);
		g.fillOval((int)x+8,(int)y+4, 35, 35);
		// Eyes (Left)
		g.setColor(Color.white);
		g.fillOval((int)x+8,(int)y+10, 15, 15);
		g.setColor(Color.black);
		g.fillOval((int)x+10,(int)y+12, 8, 8);
		// Eyes (Right)
		g.setColor(Color.white);
		g.fillOval((int)x+28,(int)y+10, 15, 15);
		g.setColor(Color.black);
		g.fillOval((int)x+30,(int)y+12, 8, 8);
		// Nose
		g.setColor(Color.white);
		int[] b = {(int)x+20,(int)x+25,(int)x+30};
		int[] s = {(int)y+35,(int)y+22,(int)y+35};
		g.fillPolygon(b, s, 3);
		g.setColor(Color.red);
			g.drawString("PLAYER 1", MainClass.WIDTH/4-30, MainClass.HEIGHT/2);
		if(x<MainClass.WIDTH/2-25)g.setColor(Color.green);
			g.drawString("PLAYER 1", MainClass.WIDTH/4-30, MainClass.HEIGHT/2);
		g.setColor(Color.magenta);
			g.drawString("PLAYER 2", MainClass.WIDTH/2 +250, MainClass.HEIGHT/2);
		if(x>MainClass.WIDTH/2-25)g.setColor(Color.green);
			g.drawString("PLAYER 2", MainClass.WIDTH/2 +250, MainClass.HEIGHT/2);
	}
	
	public void update(){
		x += vx;
		y += vy;
		//if(x>(MainClass.WIDTH-285)|| x<(MainClass.WIDTH/10+25))vx*=-1;
		if(y>(MainClass.HEIGHT-175) || y<(MainClass.HEIGHT/10+25))vy*=-1;
		
		//**Score (Below)**
		if(x>(MainClass.WIDTH-285)){
			myScore++;
				x = MainClass.WIDTH/2;
				y = MainClass.HEIGHT/2;
				vx*=-1;
				myScored = true;
				xScored = false;
		}
		if(x<(MainClass.WIDTH/10+25)){
			xScore++;
				x = MainClass.WIDTH/2;
				y = MainClass.HEIGHT/2;
				vx*=-1;
				xScored = true;
				myScored = false;
		}
		
	}
	
	public boolean hitPaddle_P1(Paddle_P1 p){
		if(x-p.WIDTH>p.getX())return false;
		if(y+SIZE<p.getY())return false;
		if(y>p.getY()+p.HEIGHT)return false;
		return true;
		}
	
	
	public boolean hitPaddle_P2(Paddle_P2 p){
		if(x+SIZE<p.getX())return false;
		if(y+SIZE<p.getY())return false;
		if(y>p.getY()+p.HEIGHT)return false;
		return true;
	}

	public void reverse() {
		vx*=-1;
	}
	
	public void Stop() {
		vx=vy=0;
		if(myScore>xScore){
			x=MainClass.WIDTH/2-300;
			y=MainClass.HEIGHT/2-100;
		} 
		if(myScore<xScore){
			x=MainClass.WIDTH/2+250;
			y=MainClass.HEIGHT/2-100;
		}
	}


	
}
