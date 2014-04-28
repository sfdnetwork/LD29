package net.game;

import javax.swing.JFrame;

public class Main extends JFrame{

	private GameEngine gEngine;
	
	public Main() {
		gEngine = new GameEngine();
		
		setResizable(false);
		setTitle("Underground Siege | Beneath The Surface | LD29");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		add(gEngine);
		
	}
	
	public static void main(String args[]){
		new Main();
	}
	
}
