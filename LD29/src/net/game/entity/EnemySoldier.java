package net.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.game.config.PlayerConfig;
import net.game.objects.CannonProjectile;
import net.game.objects.Projectile;
import res.ResourceLoader;

public class EnemySoldier extends Entity{

	private int enemyState = 0;
	public int x, y, width, height;
	public boolean isDead = false;
	public int health;
	public final int WALKING = 0, SHOOTING = 1;
	private int proposedX, proposedY;
	private Random random = new Random();
	private boolean shouldShoot = false;
	private Timer tick;
	private Image soldierImg, shadowImg;
	private ResourceLoader r = new ResourceLoader();
	
	public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	
	
	public EnemySoldier() {
		x = (1 + random.nextInt(1000));
		y = -80 + (random.nextInt(10));
		
		width = 80;
		height = 64;
		health = 100;
		
		proposedX = (PlayerConfig.getPlayerX() + (200 - random.nextInt(562)));
		proposedY = ((PlayerConfig.getPlayerY() - 100) - random.nextInt(400));
		
		//System.out.println("X: " + x + " Y: " + y  + " ProposedX: " + proposedX + " ProposedY: " + proposedY);
		
		tick = new Timer();
		
		soldierImg = r.getImage("robot.png");
		shadowImg = r.getImage("robotShadow.png");
		shouldShoot = true;
		tick.schedule(new TimerTask(){
			
			@Override
			public void run() {
				if(shouldShoot == true){
					double dx = (PlayerConfig.getPlayerX() + (PlayerConfig.PlayerWidth / 2)) - x;
					double dy = PlayerConfig.getPlayerY() - y;
					
					double direction = Math.atan2(dy, dx);
					shoot(x, y, direction);
				}
			}
			
		}, (200 +  random.nextInt(1000)), (500 +  random.nextInt(1000)));
	}
	
	public void update(){
		for(int i = 0; i < projectileList.size(); i++){
			projectileList.get(i).update();
			
			if(projectileList.get(i).isOutside()){
				projectileList.get(i).isDead = true;
			}
		}
		
		
		
		for(int i = 0; i < projectileList.size(); i++){
			if(projectileList.get(i).isDead == true){
				projectileList.remove(i);
			}
		}
		
		if(health < 1){
			isDead = true;
		}
		
		if(enemyState == WALKING){
			y += 1;
			if(x < proposedX ){
				x++;
			}
			if( y < proposedY){
				y++;
			}else{
				y = proposedY;
			}
			
			if(x > proposedX ){
				x--;
			}
			
			if(x == proposedX && y == proposedY){
				enemyState = SHOOTING;
			}
			
		}
		if(enemyState == SHOOTING){
			
			shouldShoot = true;
		}
		//System.out.println("X: " + x + " Y: " + y  + " ProposedX: " + proposedX + " ProposedY: " + proposedY + " IsDead " + isDead + " Health " + health);
		//System.out.println("Updating" + enemyState);
		
	}
	
	public boolean isColliding(double x, double y, double width, double height){
		if(x + width > this.x && y + height > this.y && x < this.x + this.width && y < this.y + this.height){
			return true;
		}else{
			return false;
		}
	}
	
	private void shoot(final int x, final int y, final double direction){
		projectileList.add(new CannonProjectile(x, y, direction));
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < projectileList.size(); i++){
			
			projectileList.get(i).draw(g);
		}
		
		g.setColor(Color.BLUE);
		if(isDead == false){
			//g.fillRect(x, y, width, height);
			g.drawImage(shadowImg, x - 10, y - 10,  null);
			g.drawImage(soldierImg, x, y, width, height, null);
		}
		
		
		
	}
	
	
}
