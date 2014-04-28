package net.game.objects;

import java.awt.Graphics;

public class CannonProjectile extends Projectile{

	public CannonProjectile(double x, double y, double direction) {
		super(x, y, direction);
		
		speed = 10;
		range = 200;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update(){
		xOrigin += nx;
		yOrigin += ny;
	}
	
	public void draw(Graphics g){
		super.draw(g);
	}
	
}
