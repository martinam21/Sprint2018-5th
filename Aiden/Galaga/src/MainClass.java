import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class MainClass extends JFrame implements Runnable, KeyListener {
	public static final int HEIGHT = 800;
	public static final int WIDTH  = 600;
	public static final int STAR_COUNT = 50;
	public static final int ALIEN_COUNT = 10;
	public static final int BULLET_COUNT = 10;
	
	BufferedImage offScreen;
	Graphics bg;
	
	Ship myShip;
	Aliens myAliens;
	List<Aliens>allAliens;
	List<Stars>allStars;
	List<Bullet>allBullets;
	List<AlienBullet>alienBullets;
	int canShoot;
	int aliensAlive = 10;
	int kills = 0;
	double difficulty = .01;
	
	public MainClass(){
		allStars = new ArrayList<Stars>();
		myShip = new Ship();
		allAliens = new ArrayList<Aliens>();
		allBullets = new ArrayList<Bullet>();
		alienBullets = new ArrayList<AlienBullet>();
		offScreen = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(30f);
		bg.setFont(f);
		int spacing = HEIGHT/STAR_COUNT;
		for(int i=0 ; i<STAR_COUNT ; i++){
			allStars.add(new Stars(i*spacing));
		}
		int alienSpacing = HEIGHT/ALIEN_COUNT;
		for(int i=0 ; i<ALIEN_COUNT ; i++)allAliens.add(new Aliens(i*alienSpacing-300));
		
		new Thread(this).start();
		this.addKeyListener(myShip);
		this.addKeyListener(this);
	}
	public static void main(String[] args){
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}
	
	public void paint(Graphics g){
		bg.setColor(Color.BLACK);
		bg.fillRect(0,0,WIDTH,HEIGHT);
		for(Stars s : allStars)s.draw(bg);
		myShip.draw(bg);
		for(Aliens a : allAliens)a.draw(bg);
		for(Bullet b : allBullets)b.draw(bg);
		for(AlienBullet z : alienBullets)z.shoot(bg);
		bg.setColor(Color.green);
		bg.drawString("Kills:"+kills, 50, 100);
		if(myShip.isDead){
			bg.setColor(Color.red);
			bg.drawString("Game Over",WIDTH/2-75, HEIGHT/2);
		}
		g.drawImage(offScreen, 0, 0, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE && canShoot==0){
			if(myShip.isDead)return;
			canShoot = 12; //to change rate of fire, change number
			allBullets.add(new Bullet(myShip.getY(),myShip.getX()));
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while(true){
		try {
			Thread.sleep(30);
			myShip.update();
			for(Stars s : allStars)s.update();
			if(canShoot>0)canShoot--;
			for(Bullet b : allBullets)b.update(10);
			for(int i=0 ; i<allAliens.size() ; i++){
				Aliens a = allAliens.get(i);
			for(int k=0 ; k<alienBullets.size() ; k++){
				AlienBullet z = alienBullets.get(k);
				if(myShip.hit(z)){
					alienBullets.remove(z);
					myShip.takeDamage();
					}
				if(myShip.justDied()){
					allAliens.clear();
					}
			}
				if(Math.random()<difficulty)alienBullets.add(new AlienBullet(a.getX(),a.getY()));
				for(int j=0 ; j<allBullets.size() ; j++){
					Bullet b = allBullets.get(j);
					if(b.getAge()>45)allBullets.remove(b);
							if(a.hit(b)){
								allAliens.remove(a);
								allBullets.remove(b);
								aliensAlive--;
								kills++;
								}
							}
				if(aliensAlive==0){
					for(int c=0 ; c<ALIEN_COUNT ; c++)allAliens.add(new Aliens(c*(HEIGHT/ALIEN_COUNT)-300));
					aliensAlive = 10;
					difficulty += .005;
					}
						}
					for(AlienBullet z : alienBullets)z.updateBullet(10);
				repaint();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
		}
	}
}

