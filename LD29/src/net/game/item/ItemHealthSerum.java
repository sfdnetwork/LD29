package net.game.item;

public class ItemHealthSerum extends Item{

	public ItemHealthSerum(int x, int y) {
		super(x, y);
		ItemType = "Serum";
		ItemName = "Health";
		ItemDesc = " +10";
		itemImg = r.getImage("serum.png");
	}

	
}
