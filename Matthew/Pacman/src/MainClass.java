import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class MainClass extends JFrame implements Runnable, KeyListener{
	
	public static final int WIDTH = 1050;
	public static final int HEIGHT = 900;
	
	private BufferedImage offScreen;
	private Graphics bg;
	private boolean gameOver,levelOver;
	private int score, lives;
	private int age;
	public static int level;
	Pacman myPacman;
	Wall myWalls;
	List<Wall> allWalls;
	Dot myDots;
	List<Dot> allDots;
	Ghost[] myGhost;
	
	
	
	public MainClass(){
		level = 1;
		restart();
		offScreen = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(40f);
		bg.setFont(f);
		allWalls = new ArrayList<Wall>();
		allDots = new ArrayList<Dot>();
		score = 0;
		lives = 3;
		age = 0;
		
		// dot locations
		for(int i=0 ; i<19 ; i++){
			allDots.add(new Dot(i*50+70,120,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+70,795,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<8 ; i++){
			allDots.add(new Dot(i*50+120,370,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+120,595,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,370,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,595,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<12 ; i++){
			allDots.add(new Dot(70,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(970,i*50+170,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<4 ; i++){
			allDots.add(new Dot(270,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(770,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+320,245,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,245,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<3 ; i++){
			allDots.add(new Dot(220,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(320,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(420,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(645,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(745,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(845,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(270,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(535,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(770,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+320,695,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<5 ; i++){
			allDots.add(new Dot(520,i*50+170,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<1 ; i++){
			allDots.add(new Dot(120,i*50+220,new Color(0xe5e5b8)));
			allDots.add(new Dot(120,i*50+695,new Color(0xe5e5b8)));
			allDots.add(new Dot(920,i*50+220,new Color(0xe5e5b8)));
			allDots.add(new Dot(920,i*50+695,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+645,745,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+420,745,new Color(0xe5e5b8)));
			allDots.add(new Dot(870,i*50+220,Color.blue));
			allDots.add(new Dot(870,i*50+695,Color.blue));
			allDots.add(new Dot(170,i*50+220,Color.blue));
			allDots.add(new Dot(170,i*50+695,Color.blue));
		}
		for(int i=0 ; i<2 ; i++){
			allDots.add(new Dot(i*50+645,695,new Color(0xe5e5b8)));
		}
		
		// border 
		for(int i=0 ; i<40 ; i++){
			allWalls.add(new Wall(i*25+25,75));
			allWalls.add(new Wall(i*25+25,825));
		}
		for(int i=0 ; i<31 ; i++){
			allWalls.add(new Wall(25,i*25+75));
			allWalls.add(new Wall(1000,i*25+75));
		}
		// starting area for ghosts
		for(int i=0 ; i<5 ; i++){
			allWalls.add(new Wall(i*25+475,550));
		}
		for(int i=0 ; i<6 ; i++){
			allWalls.add(new Wall(450,i*25+425));
			allWalls.add(new Wall(600,i*25+425));
		}
		// blocks in corners
		for(int i=0 ; i<6 ; i++){
			// top left
			allWalls.add(new Wall(i*25+100,150));
			allWalls.add(new Wall(i*25+100,175));
			allWalls.add(new Wall(i*25+100,250));
			allWalls.add(new Wall(i*25+100,275));
			allWalls.add(new Wall(i*25+100,300));
			allWalls.add(new Wall(i*25+100,325));
			// top right
			allWalls.add(new Wall(i*25+800,150));
			allWalls.add(new Wall(i*25+800,175));
			allWalls.add(new Wall(i*25+800,250));
			allWalls.add(new Wall(i*25+800,275));
			allWalls.add(new Wall(i*25+800,300));
			allWalls.add(new Wall(i*25+800,325));
			// bottom left
			allWalls.add(new Wall(i*25+100,625));
			allWalls.add(new Wall(i*25+100,650));
			allWalls.add(new Wall(i*25+100,725));
			allWalls.add(new Wall(i*25+100,750));
			// bottom right
			allWalls.add(new Wall(i*25+800,625));
			allWalls.add(new Wall(i*25+800,650));
			allWalls.add(new Wall(i*25+800,725));
			allWalls.add(new Wall(i*25+800,750));
		}
		// holes in corner blocks
		for(int i=0 ; i<2 ; i++){
			// top left
			allWalls.add(new Wall(i*25+200,200));
			allWalls.add(new Wall(i*25+200,225));
			// top right
			allWalls.add(new Wall(i*25+800,200));
			allWalls.add(new Wall(i*25+800,225));
			// bottom left
			allWalls.add(new Wall(i*25+200,675));
			allWalls.add(new Wall(i*25+200,700));
			// bottom right
			allWalls.add(new Wall(i*25+800,675));
			allWalls.add(new Wall(i*25+800,700));
		}
		// top middle walls
		for(int i=0 ; i<8 ; i++){
			allWalls.add(new Wall(i*25+300,150));
			allWalls.add(new Wall(i*25+300,175));
			allWalls.add(new Wall(i*25+300,200));
			allWalls.add(new Wall(i*25+300,275));
			allWalls.add(new Wall(i*25+300,300));
			allWalls.add(new Wall(i*25+300,325));
		}
		for(int i=0 ; i<7 ; i++){
			allWalls.add(new Wall(i*25+575,150));
			allWalls.add(new Wall(i*25+575,175));
			allWalls.add(new Wall(i*25+575,200));
			allWalls.add(new Wall(i*25+575,275));
			allWalls.add(new Wall(i*25+575,300));
			allWalls.add(new Wall(i*25+575,325));
		}
		// middle walls next to start
		for(int i=0 ; i<7 ; i++){
			//right
			allWalls.add(new Wall(675,i*25+400));
			allWalls.add(new Wall(700,i*25+400));
			allWalls.add(new Wall(775,i*25+400));
			allWalls.add(new Wall(800,i*25+400));
			allWalls.add(new Wall(875,i*25+400));
			allWalls.add(new Wall(900,i*25+400));
			allWalls.add(new Wall(925,i*25+400));
			//left
			allWalls.add(new Wall(100,i*25+400));
			allWalls.add(new Wall(125,i*25+400));
			allWalls.add(new Wall(150,i*25+400));
			allWalls.add(new Wall(175,i*25+400));
			allWalls.add(new Wall(250,i*25+400));
			allWalls.add(new Wall(275,i*25+400));
			allWalls.add(new Wall(350,i*25+400));
			allWalls.add(new Wall(375,i*25+400));
		}
		// bottom walls
		//right
		for(int i=0 ; i<7 ; i++){
			allWalls.add(new Wall(i*25+575,625));
			allWalls.add(new Wall(i*25+575,650));
		}
		for(int i=0 ; i<3 ; i++){
			allWalls.add(new Wall(i*25+675,725));
			allWalls.add(new Wall(i*25+675,750));
		}
		for(int i=0 ; i<4 ; i++){
			allWalls.add(new Wall(575,i*25+675));
			allWalls.add(new Wall(600,i*25+675));
		}
		//left
		for(int i=0 ; i<8 ; i++){
			allWalls.add(new Wall(i*25+300,625));
			allWalls.add(new Wall(i*25+300,650));
		}
		for(int i=0 ; i<4 ; i++){
			allWalls.add(new Wall(i*25+300,725));
			allWalls.add(new Wall(i*25+300,750));
		}
		for(int i=0 ; i<4 ; i++){
			allWalls.add(new Wall(450,i*25+675));
			allWalls.add(new Wall(475,i*25+675));
		}
		this.addKeyListener(myPacman);
		this.addKeyListener(this);
	}
	
	private void restart() {
		gameOver = false;
		levelOver = false;
		myPacman = new Pacman();
		myGhost = new Ghost[4];
		myGhost[0] = new Ghost(Color.red);
		myGhost[1] = new Ghost(Color.pink);
		myGhost[2] = new Ghost(Color.cyan);
		myGhost[3] = new Ghost(Color.orange);
		this.addKeyListener(myPacman);
		this.addKeyListener(this);
		new Thread(this).start();
	}
	
	private void newLevel() {
		gameOver = false;
		levelOver = false;
		myPacman = new Pacman();
		myGhost = new Ghost[4];
		myGhost[0] = new Ghost(Color.red);
		myGhost[1] = new Ghost(Color.pink);
		myGhost[2] = new Ghost(Color.cyan);
		myGhost[3] = new Ghost(Color.orange);
		this.addKeyListener(myPacman);
		this.addKeyListener(this);
		
		for(int i=0 ; i<19 ; i++){
			allDots.add(new Dot(i*50+70,120,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+70,795,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<8 ; i++){
			allDots.add(new Dot(i*50+120,370,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+120,595,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,370,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,595,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<12 ; i++){
			allDots.add(new Dot(70,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(970,i*50+170,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<4 ; i++){
			allDots.add(new Dot(270,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(770,i*50+170,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+320,245,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+570,245,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<3 ; i++){
			allDots.add(new Dot(220,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(320,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(420,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(645,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(745,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(845,i*50+425,new Color(0xe5e5b8)));
			allDots.add(new Dot(270,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(535,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(770,i*50+645,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+320,695,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<5 ; i++){
			allDots.add(new Dot(520,i*50+170,new Color(0xe5e5b8)));
		}
		for(int i=0 ; i<1 ; i++){
			allDots.add(new Dot(120,i*50+220,new Color(0xe5e5b8)));
			allDots.add(new Dot(120,i*50+695,new Color(0xe5e5b8)));
			allDots.add(new Dot(920,i*50+220,new Color(0xe5e5b8)));
			allDots.add(new Dot(920,i*50+695,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+645,745,new Color(0xe5e5b8)));
			allDots.add(new Dot(i*50+420,745,new Color(0xe5e5b8)));
			allDots.add(new Dot(870,i*50+220,Color.blue));
			allDots.add(new Dot(870,i*50+695,Color.blue));
			allDots.add(new Dot(170,i*50+220,Color.blue));
			allDots.add(new Dot(170,i*50+695,Color.blue));
		}
		for(int i=0 ; i<2 ; i++){
			allDots.add(new Dot(i*50+645,695,new Color(0xe5e5b8)));
		}
	}
	
	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}
	
	public void paint(Graphics g){
		bg.setColor(Color.black);
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		myPacman.draw(bg);
		for(Wall w : allWalls){
			w.draw(bg);
		}
		for(Dot d : allDots){
			d.draw(bg);
		}
		for(Ghost gh : myGhost)gh.draw(bg);
		
		bg.setColor(Color.white);
		bg.drawString("Lives = "+lives, 50, 60);
		
		bg.setColor(Color.white);
		bg.drawString("Level = "+level, 450, 60);
		
		bg.setColor(Color.white);
		bg.drawString("Score = "+score, 800, 60);
		
		
		if(gameOver){
			bg.setColor(Color.white);
			bg.drawString("Game Over", WIDTH/2-100, HEIGHT/2);
			bg.drawString("Score = "+score, WIDTH/2-100, HEIGHT/2+100);
		}
		
		g.drawImage(offScreen, 0, 0, null);
	}

	@Override
	public void run() {
		while(!gameOver && !levelOver){
			try {
				Thread.sleep(30);
				// update pacman
				myPacman.update();
				for(Wall w : allWalls){
					if(w.hit(myPacman)){
						myPacman.stop();
					}
				}
				for(int i = 0 ; i<allDots.size() ; i++){
					Dot d = allDots.get(i);
					if(d.eat(myPacman)){
						allDots.remove(d);
						score+=10;
					}
					if(d.eat(myPacman) && d.getColor()==Color.blue){
						for(Ghost gh : myGhost){
							gh.eatable = true;
							gh.age = 0;
						}
					}
					if(allDots.size()<1){
						newLevel();
						level+=1;
						lives+=1;
					}
				}
				
				
				if(lives==0)gameOver = true;
				for(Ghost gh : myGhost){
					if(myPacman.hit(gh) && gh.eatable==false)lives -= 1; 
					if(myPacman.hit(gh) && gh.eatable==false)levelOver = true;
					if(myPacman.hit(gh) && gh.eatable==true) score += 100;
					gh.update(myPacman);
				

					if(myPacman.hit(gh) && gh.eatable==true)gh.reset();
					if(gh.eatable==true){
						gh.age++;
					}
					if(level<=10 && gh.age/30>20){
						gh.eatable=false;
						gh.age = 0;
					}
					if(level<=25 && level>=11 && gh.age/30>15){
						gh.eatable=false;
						gh.age = 0;
					}
					if(level<=100 && level>=26 && gh.age/30>10){
						gh.eatable=false;
						gh.age = 0;
					}
					if(level>=101 && gh.age/30>8){
						gh.eatable=false;
						gh.age = 0;
					}
				
					for(Wall w : allWalls){
						if(w.hitGhost(gh)){
							gh.stopGhost();
							gh.changeDir();
						}
					}
				}
								
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver && e.getKeyCode()== KeyEvent.VK_SPACE) restart();
		if(levelOver && e.getKeyCode()== KeyEvent.VK_SPACE) restart();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
