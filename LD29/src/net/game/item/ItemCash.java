package net.game.item;

public class ItemCash extends Item{

	public ItemCash(int x, int y) {
		super(x, y);
		ItemType = "Cash";
		ItemName = "Cash";
		ItemDesc = " +10";
		itemImg = r.getImage("cash.png");
	}
	
	public void addEffect(){
		
	}

}
