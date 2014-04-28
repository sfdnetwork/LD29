package net.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import res.ResourceLoader;

public class Projectile {

	public double xOrigin, yOrigin;
	protected double angle;
	protected double nx, ny;
	protected double speed, range;
	protected double distance;
	public double size = 20;
	public boolean isDead = false;
	private Image bulletImg;
	
	private ResourceLoader r = new ResourceLoader();
	
	public Projectile(double x, double y, double direction) {
		xOrigin = x;
		yOrigin = y;
		angle = direction;
		
		bulletImg = r.getImage("bullet.png");
	}
	
	public void update(){
		
	}
	
	public boolean isOutside(){
		if(xOrigin < 0 || yOrigin < 0 || xOrigin > 1280 || yOrigin > 720){
			isDead = true;
			return true;
		}else{
			return false;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		//g.fillRect((int) xOrigin, (int) yOrigin, (int)size,(int) size);
		g.drawImage(bulletImg, (int) xOrigin, (int) yOrigin, (int)size,(int) size, null);
	}
	
}
