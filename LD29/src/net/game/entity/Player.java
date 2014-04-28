package net.game.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import net.game.config.PlayerConfig;
import net.game.item.Item;
import net.game.item.ItemCash;
import net.game.item.ItemHealthSerum;
import net.game.objects.CannonProjectile;
import net.game.objects.Projectile;
import net.game.world.World;
import res.ResourceLoader;
import res.Sound;

public class Player extends Entity{

	private Rectangle cannonRect;
	private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	private World world;
	private ResourceLoader r = new ResourceLoader();
	public int health = 1000;
	public boolean isDead;
	public double score = 0;
	public double cash = 0;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
	private Image cannonImg;
	private int cannonDX, cannonDY;
	private Font lFont = new Font("Arial", Font.BOLD, 20);
	
	private Timer tick = new Timer();
	private double displayHealth;
	private Sound sound;
	private Sound hurt;
	private Sound playerHurt;
	private Sound sCash;
	private Image shadowImg;
	
	public Player(World world) {
		super();
		this.world = world;
		cannonRect = new Rectangle(540, 550, 100, 100);

		PlayerConfig.setPlayerWidth(cannonRect.width);
		PlayerConfig.setPlayerHeight(cannonRect.height);
		
		cannonImg =  r.getImage("cannon.png");
		shadowImg = r.getImage("shadow.png");
		sound = new Sound("shoot.wav");
		hurt = new Sound("hurt.wav");
		playerHurt = new Sound("PlayerHurt.wav");
		sCash = new Sound("cash.wav");
	}
	
	public void update(){
		super.update();
		
		cannonRect.x += cannonDX;
		
		PlayerConfig.setPlayerX(cannonRect.x);
		PlayerConfig.setPlayerY(cannonRect.y);
		
		for(int i = 0; i < projectileList.size(); i++){
			projectileList.get(i).update();
			
			//Hit an Enemy
			for(int j = 0; j < world.soldierList.size(); j++){
				if(world.soldierList != null){
					if(world.soldierList.get(j).isColliding(projectileList.get(i).xOrigin, projectileList.get(i).yOrigin, projectileList.get(i).size, projectileList.get(i).size)){
						score += 0.5; //Award Score
						world.soldierList.get(j).health-=50; //Remove enemy Health
						world.soldierList.get(j).y -= 10;
						if(world.soldierList.get(j).health <= 0){
							
							Random random = new Random();
							int randomInt = random.nextInt(10);
							if(randomInt == 0){
								itemList.add(new ItemCash(world.soldierList.get(j).x, world.soldierList.get(j).y));
							}else if(randomInt == 1){
								itemList.add(new ItemHealthSerum(world.soldierList.get(j).x, world.soldierList.get(j).y));
							}
							
							
						}
						
						hurt.play();
						if(projectileList.get(i) != null)
						projectileList.get(i).isDead = true; //Remove players bullet
					}
				}
				
				
			}
			
		}
		
		for(int i = 0; i < projectileList.size(); i++){
			if(projectileList.get(i).isOutside() == true){
				projectileList.get(i).isDead = true;
			}
			if(projectileList.get(i).isDead == true){
				projectileList.remove(i);
			}
		}
		
		for(int i = 0; i < world.soldierList.size(); i++){
			for(int j = 0; j < world.soldierList.get(i).projectileList.size(); j++){
				if(isColliding(world.soldierList.get(i).projectileList.get(j).xOrigin, world.soldierList.get(i).projectileList.get(j).yOrigin, world.soldierList.get(i).projectileList.get(j).size, world.soldierList.get(i).projectileList.get(j).size)){
					world.soldierList.get(i).projectileList.get(j).isDead = true;
					health -= 10; //Remove Personal Health
					
					playerHurt.play();
				}
			}
			
		}
		
		if(cannonRect.x < 10){
			cannonRect.x = 10;
		}
		
		if(cannonRect.x + cannonRect.width > 1270){
			cannonRect.x = 1270 - cannonRect.width;
		}
		
		if(health <= 0){
			isDead = true;
		}
		
		displayHealth = ((double)health / 1000.0) * 100.0;
		
		System.out.println(" Projectile: " + projectileList.size() + " Soldiers: " + world.soldierList.size());
		//System.out.println("Health: " + health + " IsDead: " + isDead + " DisplayHealth: " + displayHealth);
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		for(int i = 0; i < itemList.size(); i++){
			itemList.get(i).draw(g);
		}
		
		for(int i = 0; i < projectileList.size(); i++){
			projectileList.get(i).draw(g);
		}
		
		
		
		g.setColor(Color.WHITE);
		g.setFont(lFont);
		g.drawImage(shadowImg, cannonRect.x, cannonRect.y + 10, cannonRect.width, cannonRect.height, null);
		g.drawImage(cannonImg, cannonRect.x, cannonRect.y, cannonRect.width, cannonRect.height, null);
		
		
		g.setColor(Color.BLACK);
		
		g.drawString("Score: " + score, 20 + 2,  600 + 2);
		g.drawString("Health: " + (int) displayHealth + "%", 20 + 2, 620 + 2);
		g.drawString("Cash: " + cash + " Credits", 20 + 2, 640 + 2);
		
		g.setColor(Color.white);
		
		g.drawString("Score: " + score, 20,  600);
		g.drawString("Health: " + (int) displayHealth + "%", 20, 620);
		g.drawString("Cash: " + cash + " Credits", 20, 640);
		
		
		
		
	}
	
	private void shoot(final int x, final int y, final double direction){
		projectileList.add(new CannonProjectile(x, y, direction));
	}
	
	public boolean isColliding(double x, double y, double width, double height){
		if(x + width > cannonRect.x && y + height > cannonRect.y && x < cannonRect.x + cannonRect.width && y < cannonRect.y + cannonRect.height){
			return true;
		}else{
			return false;
		}
	}
	
	public void mousePressed(MouseEvent e){
		super.mousePressed(e);
		
		if(e.getButton() == 1){
			double dx = e.getX() - (cannonRect.x + 50);
			double dy = e.getY() - cannonRect.y;
			
			double direction = Math.atan2(dy, dx);
			
			shoot((int)cannonRect.x + 50, (int)cannonRect.y, direction);
			sound.play();
		}
		
		if(e.getButton() == 3){
			for(int i = 0; i < itemList.size(); i++){
				if(itemList.get(i).MouseIntersects(e.getX(), e.getY())){
					if(itemList.get(i).ItemType == "Cash"){
						cash += 10;
						sCash.play();
					}else if(itemList.get(i).ItemType == "Serum"){
						health += 10;
						sCash.play();
					}
					
					itemList.remove(i);
				}
			}
		}
		
	}
	
	public void mouseMoved(MouseEvent e){
		for(int i = 0; i < itemList.size(); i++){
			if(itemList.get(i).MouseIntersects(e.getX(), e.getY())){
				
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_A){
			if(leftBoundaryCheck() == false){
				cannonDX = -5;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			if(rightBoundaryCheck() == false){
				cannonDX = 5;
			}
		}
		
		
	}
	
	public boolean leftBoundaryCheck(){
		if(cannonRect.x < 10){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean rightBoundaryCheck(){
		if(cannonRect.x + cannonRect.width > 1270){
			return true;
		}else{
			return false;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_A){
			cannonDX = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			cannonDX = 0;
		}
	}
	
	
	
}
