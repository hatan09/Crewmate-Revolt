package states;

import java.awt.Graphics;


import main.Handler;
import ui.UIManager;

public abstract class State {
	//this class is an abstract class acting like an interface for sub-states inherit
	//also, it acts like a state manager as it can holds a sub-state as its data, and we can call the getState method to retrieve the current state anywhere.
	
	protected Handler handler;
	
	protected UIManager uiManager;
	
	protected State(Handler handler) {
		this.handler = handler;
		
		//this means, every sub-state must take a handler when create a new instance
	}

	//State Manager: static var and static method used to set and get current state of the whole game
	private static State _state = null;
	
	
	//Manager methods
	public static void setState(State state) {
		_state = state;
		state.setUI();
	}
	
	public static State getState() {
		return _state;
	}
	
	//methods run when changing state
	public void setUI() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void reset() {
		
	}
	
	
	//methods that all States must have
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}
