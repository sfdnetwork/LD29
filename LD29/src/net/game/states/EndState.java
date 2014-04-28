package net.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.game.misc.ClassDefault;
import net.game.misc.MenuOption;
import res.ResourceLoader;

public class EndState implements ClassDefault{

	public Image endImg;
	
	private ResourceLoader r = new ResourceLoader();
	
	private int finalScore;
	private double finalCash;
	private MenuOption backButton;
	private Font lFont = new Font("Arial", Font.BOLD, 40);
	
	public EndState(int finalScore, double finalCash) {
		this.finalScore = finalScore;
		this.finalCash = finalCash;
		
		endImg = r.getImage("endScreen.png");
		
		backButton = new MenuOption("Back", 20, 550, 110, 60);
	}

	public void update() {
		
	}

	private Font smallFont = new Font("Arial", Font.BOLD, 14);
	Font bFont = new Font("Arial", Font.BOLD, 60);;
	
	public void draw(Graphics g) {
		g.drawImage(endImg, 0, 0, null);
		
		g.setFont(lFont);
		g.setColor(Color.BLACK);
		
		g.drawString("Core Lost", 500, 200);
		
		g.setFont(bFont);
		g.drawString("Score: " + finalScore, 400, 260);
		g.drawString("Cash: " + finalCash, 400, 320);
		
		g.setFont(lFont);
		
		backButton.draw(g);
		
		g.setFont(smallFont);
		g.setColor(Color.WHITE);
		g.drawString("Developer's Highscore: 391", 20, 650);
		g.drawString("Tweet Me Your Hightscore! @SFDNetwork", 20, 665);
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1){
				if(backButton.MouseIntersects(e.getX(), e.getY())){
					backButton.isPressed = true;
				}else{
					backButton.isPressed = false;
				}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1){
				if(backButton.MouseIntersects(e.getX(), e.getY())){
					State.setState(State.MENU);
					backButton.isPressed = false;
				}
		}
	}

	public void mouseMoved(MouseEvent e) {
		backButton.MouseIntersects(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			State.setState(State.MENU);
		}
	}
	
}
