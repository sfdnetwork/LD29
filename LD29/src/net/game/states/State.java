package net.game.states;

public class State {

	public static int state = 0;
	
	public static int SPLASH = 0, MENU = 1, GAME = 2, END = 3, STORY = 4;

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		State.state = state;
	}
	
}
