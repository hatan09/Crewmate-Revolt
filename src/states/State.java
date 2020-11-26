package states;

import java.awt.Graphics;

import main.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}

	//State Manager
	private static State _state = null;
	
	public static void setState(State state) {
		_state = state;
	}
	
	public static State getState() {
		return _state;
	}
	
	//methods that all States have
	public abstract void render(Graphics g);
	
	public abstract void update();
	
}
