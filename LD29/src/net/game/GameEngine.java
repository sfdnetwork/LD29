package net.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.game.states.State;
import net.game.states.StateManager;
import res.Sound;

public class GameEngine extends JPanel implements Runnable{

	private static final long serialVersionUID = -6333661134494360666L;
	//Game Variables
	private Thread game;
	private volatile boolean running = false;
	//Double Buffer
	private Image dbImage;
	private Graphics dbg;
	//StateManager
	StateManager sManager;
	private Sound sound;
	private boolean soundLoaded = false;
	
	//Default Constructor
	public GameEngine() {
		
		sManager = new StateManager();
		sound = new Sound("soundtrackOneCompressed.mp3");
		
		
		State.setState(State.SPLASH);
		
		while(soundLoaded == false){
			if(sound.loaded == true){
				sound.loop();
				soundLoaded = true;
			}
			
			
		}
		
		//Panel Config
		setPreferredSize(new Dimension(1280, 720));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();
		
		//Key Listener
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				sManager.keyPressed(e);
			}
			public void keyReleased(KeyEvent e){
				sManager.keyReleased(e);
			}
		});
		//Mouse Listener
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				sManager.mousePressed(e);
			}
			public void mouseReleased(MouseEvent e){
				sManager.mouseReleased(e);
			}
		});
		//Mouse Motion Listener
		addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				sManager.mouseMoved(e);
			}
			public void mouseDragged(MouseEvent e){
				sManager.mouseDragged(e);
			}
		});
	}
	
	public void run() {
		final double gameHertz = 60.0;
		final double timeBetweenUpdates = 1000000000 / gameHertz;
		final int maxUpdatesBeforeRender = 1;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		final double targetFPS = 60.0;
		final double targetTimeBetweenRenders  = 1000000000 / targetFPS;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(running){
			double now = System.nanoTime();
			int updateCount = 0;
			
			while(now - lastUpdateTime > timeBetweenUpdates && updateCount < maxUpdatesBeforeRender){
				//Update and Render
				gameUpdate();
				
				
				lastUpdateTime += timeBetweenUpdates;
				updateCount++;
			}
			
			if(now - lastUpdateTime > timeBetweenUpdates){
				lastUpdateTime = now - timeBetweenUpdates;
			}
			
			//Draw
			gameRender();
			paintScreen();
			lastRenderTime = now;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime){
				lastSecondTime = thisSecond;
			}
			
			while(now - lastRenderTime < targetTimeBetweenRenders && now - lastUpdateTime < timeBetweenUpdates){
				Thread.yield();
				now = System.nanoTime();
			}
			
			
		}
	}
	
	private void gameUpdate(){
		if(running && game != null){
			sManager.update();
		}
	}
	
	private void draw(Graphics g){
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		sManager.draw(g);
	}
	
	private void paintScreen(){
		Graphics g;
		try{
			g = this.getGraphics();
			if(dbImage != null && g != null){
				g.drawImage(dbImage, 0, 0, 1280, 720, null);
				Toolkit.getDefaultToolkit();
			}
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	private void gameRender(){
		if(dbImage == null){
			dbImage =  createImage(1280, 720);
			if(dbImage == null){
				return;
			}else{
				dbg = dbImage.getGraphics();
			}
		}
		
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, 1280, 720);
		draw(dbg);
	}
	
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	
	private void startGame(){
		if(game == null || !running){
			game = new Thread(this);
			game.start();
			running = true;
		}
	}
	
	private void stopGame(){
		if(running){
			running = false;
			try{
				game.join();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
