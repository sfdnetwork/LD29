package net.game.states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.game.misc.MenuOption;
import res.ResourceLoader;

public class StoryState extends DefaultState{
	private MenuOption backButton;
	private ResourceLoader r = new ResourceLoader();
	private Image storyImg;
	
	public StoryState() {
		storyImg = r.getImage("storyScreen.png");
		backButton = new MenuOption("Back", 1100, 40, 110, 60);
	}
	
	public void draw(Graphics g){
		g.drawImage(storyImg, 0, 0, null);
		backButton.draw(g);
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
