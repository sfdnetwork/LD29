package net.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.game.entity.EnemySoldier;
import net.game.entity.Player;
import net.game.states.DefaultState;
import net.game.states.State;
import net.game.world.World;

public class Game extends DefaultState{

	private Player player;
	private World world;
	public boolean gameEnded = false;
	public int finalScore;
	public double finalCash;
	
	public Game() {
		
		world = new World();
		player = new Player(world);
		gameEnded = false;
	}
	
	@Override
	public void update() {
		super.update();
		
		player.update();
		world.update();
		
		if(player.isDead){
			finalScore = (int) player.score;
			finalCash = player.cash;
			gameEnded = true;
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		
		world.draw(g);
		player.draw(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		
		player.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		
		player.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		
		player.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		
		player.keyReleased(e);
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			State.setState(State.MENU);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		
		player.mouseDragged(e);
	}

	
	
}
