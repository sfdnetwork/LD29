package net.game.world;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import net.game.entity.EnemySoldier;
import net.game.misc.ClassDefault;
import res.ResourceLoader;

public class World implements ClassDefault{

	public ArrayList<EnemySoldier> soldierList = new ArrayList<EnemySoldier>();
	private Timer tick;
	private Image backImg;
	private ResourceLoader r = new ResourceLoader();
	
	
	
	public World() {
		tick = new Timer();
		
		backImg = r.getImage("soilback.png");
		
		tick.schedule(new TimerTask(){

			@Override
			public void run() {
				if(soldierList.size() < 30)
				soldierList.add(new EnemySoldier());
			}
			
		}, 10, 1000);
		
	}

	public void update() {
		for(int i = 0; i < soldierList.size(); i++){
			soldierList.get(i).update();
		}
		
		for(int i = 0; i < soldierList.size(); i++){
			if(soldierList.get(i).isDead == true){
				soldierList.remove(i);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(backImg, 0, 0, null);
		
		for(int i = 0; i < soldierList.size(); i++){
			soldierList.get(i).draw(g);
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	
}
