package net.game.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import res.ResourceLoader;

public class MenuOption {

	private int x, y, width, height;
	public boolean isHovered;
	public boolean isPressed;
	private String name;
	private Font lFont = new Font("Arial", Font.BOLD, 20);
	private Image optionImg, upImg, hoverImg, downImg;
	private ResourceLoader r = new ResourceLoader();
	
	public MenuOption(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		upImg = r.getImage("menuOption.png");
		hoverImg = r.getImage("menuOption_Hover.png");
		downImg = r.getImage("menuOption_Pressed.png");
		optionImg = upImg;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		lFont = new Font("Arial", Font.BOLD, 40);
		g.setColor(Color.GREEN);
		if(isHovered){
			g.drawImage(hoverImg, x, y, width, height, null);
		}else{
			g.drawImage(optionImg, x, y, width, height, null);
		}
		
		if(isPressed){
			g.drawImage(downImg, x, y, width, height, null);
		}
		
		
		g.setFont(lFont);
		
		g.setColor(Color.BLACK);
		g.drawString(name, x + 10, y + 40);
	}
	
	public boolean MouseIntersects(int x, int y){
		if(x > this.x && y > this.y && x < this.x + this.width && y < this.y + this.height){
			isHovered = true;
			return true;
		}else{
			isHovered = false;
			return false;
		}
	}
	
}
