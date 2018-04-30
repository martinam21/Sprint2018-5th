import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class MainClass extends JFrame implements Runnable{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	List<Log> allLogs;
	Graphics bg;
	BufferedImage offscreen;
	Frog myFrog;
	
	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}
	
	public MainClass(){
		myFrog = new Frog();
		allLogs = new ArrayList<Log>();
		for(int i=0 ; i<20 ; i++){
			allLogs.add(new Log(i*100,475));
			allLogs.add(new Log(i*100,375));
			allLogs.add(new Log(i*100,275));
			allLogs.add(new Log(i*100,175));
			allLogs.add(new Log(i*100,75));

		}
		this.addKeyListener(myFrog);
		offscreen = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		bg = offscreen.getGraphics();
		new Thread(this).start();
		
	}
	public void paint (Graphics g){
		bg.setColor(Color.blue);
		bg.fillRect(0, 0, WIDTH, HEIGHT);
		myFrog.draw(bg);
		for(Log l : allLogs)l.draw(bg);
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(30);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			myFrog.update();
			repaint();
		}
	}
		

}
