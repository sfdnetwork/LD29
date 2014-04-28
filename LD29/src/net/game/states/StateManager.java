package net.game.states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.game.Game;
import net.game.misc.ClassDefault;
import res.ResourceLoader;

public class StateManager implements ClassDefault{
	
	private boolean gameLoaded;
	private boolean menuLoaded;
	private boolean splashLoaded;
	private boolean endLoaded;
	private boolean storyLoaded;
	
	private int lastScore;
	private double lastCash;
	
	private Game game;
	private Splash splash;
	private EndState end;
	private MenuState menu;
	private StoryState story;
	
	public StateManager() {
		
	}
	
	public void init(){
		if(State.getState() == State.SPLASH){
			if(splashLoaded == false){
				splash = new Splash();
				splashLoaded = true;
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == false){
				menu = new MenuState();
				menuLoaded = true;
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == false){
				lastScore = 0;
				lastCash = 0;
				game = new Game();
				gameLoaded = true;
			}
		}else if(State.getState() == State.END){
				end = new EndState(lastScore, lastCash);
				endLoaded = true;
		}else if(State.getState() == State.STORY){
			if(storyLoaded == false){
				story = new StoryState();
				storyLoaded = true;
			}
		}
	}

	public void update() {
		init();
		
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				splash.update();
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				menu.update();
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.update();
				if(game.gameEnded == true){
					gameLoaded = false;
					lastScore = game.finalScore;
					lastCash = game.finalCash;
					State.setState(State.END);
				}
			}
		}else if(State.getState() == State.END){
			if(endLoaded){
				end.update();
			}
		}
		
		
	}

	public void draw(Graphics g) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				splash.draw(g);
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				menu.draw(g);
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.draw(g);
			}
		}else if(State.getState() == State.END){
			if(endLoaded){
				end.draw(g);
			}
		}else if(State.getState() == State.STORY){
			if(storyLoaded){
				story.draw(g);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				menu.mousePressed(e);
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.mousePressed(e);
			}
		}else if(State.getState() == State.END){
			if(endLoaded){
				end.mousePressed(e);
			}
		}else if(State.getState() == State.STORY){
			if(storyLoaded){
				story.mousePressed(e);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				menu.mouseReleased(e);
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				menu.mouseReleased(e);
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.mouseReleased(e);
			}
		}else if(State.getState() == State.END){
			if(endLoaded == true){
				end.mouseReleased(e);
			}
		}else if(State.getState() == State.STORY){
			if(storyLoaded){
				story.mouseReleased(e);
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				menu.mouseMoved(e);
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.mouseMoved(e);
			}
		}else if(State.getState() == State.END){
			if(endLoaded == true){
				end.mouseMoved(e);
			}
		}else if(State.getState() == State.STORY){
			if(storyLoaded){
				story.mouseMoved(e);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				splash.keyPressed(e);
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.keyPressed(e);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.keyReleased(e);
			}
		}else if(State.getState() == State.END){
			if(endLoaded == true){
				game.keyReleased(e);
			}
		}
		else if(State.getState() == State.STORY){
			if(storyLoaded == true){
				story.keyReleased(e);
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(State.getState() == State.SPLASH){
			if(splashLoaded == true){
				
			}
		}else if(State.getState() == State.MENU){
			if(menuLoaded == true){
				
			}
		}else if(State.getState() == State.GAME){
			if(gameLoaded == true){
				game.mouseDragged(e);
			}
		}
	}
	
	
}
