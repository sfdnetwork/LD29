package net.game.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import res.ResourceLoader;

public class Item {

	public String ItemType = "Item";
	public String ItemName = "Item";
	public String ItemDesc = "ItemDesc";
	protected int x, y, width, height;
	public boolean isHovered;
	protected Image itemImg;
	
	public ResourceLoader r = new ResourceLoader();
	
	public Item(int x, int y) {
		this.x = x;
		this.y = y;
		width = 50;
		height =  50;
		
		
	}
	
	public void draw(Graphics g){
		g.drawImage(itemImg, x, y, width, height, null);
		
		if(isHovered == true){
			g.setColor(Color.BLACK);
			g.fillRect(x, y - 30, 150, 30);
			g.setColor(Color.WHITE);
			g.drawString(ItemName + " " + ItemDesc, x + 10, y - 5);
		}
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
	
	public void addEffect(){
		
	}
	
}
