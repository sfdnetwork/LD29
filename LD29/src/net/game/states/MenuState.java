package net.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import net.game.misc.MenuOption;
import res.ResourceLoader;

public class MenuState extends DefaultState{

	private Image menuImg;
	private ResourceLoader r = new ResourceLoader();
	private ArrayList<MenuOption> optionList = new ArrayList<MenuOption>();
	private MenuOption menuOption;
	private int initialY = 400;
	
	public MenuState() {
		menuImg = r.getImage("menu.png");
		
		optionList.add(new MenuOption("PLAY", 70, initialY, 300, 60));
		optionList.add(new MenuOption("STORY", 70, initialY + 70, 300, 60));
		optionList.add(new MenuOption("EXIT", 70, initialY + 140, 300, 60));
	}
	
	public void draw(Graphics g){
		g.drawImage(menuImg, 0, 0, null);
		
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < optionList.size(); i++){
			optionList.get(i).draw(g);
		}
		
	}
	
	public void mousePressed(MouseEvent e){
		if(e.getButton() == 1){
			for(int i = 0; i < optionList.size(); i++){
				if(optionList.get(i).MouseIntersects(e.getX(), e.getY())){
					optionList.get(i).isPressed = true;
				}else{
					optionList.get(i).isPressed = false;
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == 1){
			for(int i = 0; i < optionList.size(); i++){
				if(optionList.get(i).MouseIntersects(e.getX(), e.getY())){
					if(i == 0){
						State.setState(State.GAME);
					}
					if(i == 1){
						State.setState(State.STORY);
					}
					if(i == 2){
						System.exit(0);
					}
					optionList.get(i).isPressed = false;
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e){
		for(int i = 0; i < optionList.size(); i++){
			optionList.get(i).MouseIntersects(e.getX(), e.getY());
		}
	}
	
}
