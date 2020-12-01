package states;

import java.awt.Graphics;

import main.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}

	//State Manager: static var and static method used to set and get current state of the whole game
	private static State _state = null;
	
	public static void setState(State state) {
		_state = state;
	}
	
	public static State getState() {
		return _state;
	}
	
	//methods that all States must have
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}
