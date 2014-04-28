package net.game.states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import res.ResourceLoader;

public class Splash {

	private Image splashImg;
	private ResourceLoader r = new ResourceLoader();
	
	private Timer tick;
	
	public Splash() {
		tick = new Timer();
		
		splashImg = r.getImage("splashImg.png");
		
		tick.schedule(new TimerTask(){

			@Override
			public void run() {
				State.setState(State.MENU);
			}
			
		}, 3000);
		
		
	}
	
	public void update(){
		
	}
	
	public void keyPressed(KeyEvent e){
		State.setState(State.MENU);
	}
	
	public void draw(Graphics g){
		g.drawImage(splashImg, 0, 0, null);
	}
	
}
