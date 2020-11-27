package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	/*
	 * This class is for getting and managing (logics) the input from keyboard
	 * Add an instance of this to the main Frame
	 * Frame.addKeyListener (or any listener) means we allow java to capture key (mouse) actions while on that Frame
	 * To add Listener, we must input a Listener instance (object) of that Listener type ==> the instance has logics for actions
	 * ==> for each action, the instance will manage the outcome for that action
	 * 
	 * Like this class, it captures and sets the value (bool) for the keys[] that pressed or released
	 * Then any Frame that take this instance in a Listener, it can use this instance's keys[] to verify which key is pressed/released
	 * 		and perform the approriate controls
	 * */
	
	private int upNum = KeyEvent.VK_W, downNum = KeyEvent.VK_S, leftNum = KeyEvent.VK_A, rightNum = KeyEvent.VK_D;
	private boolean[] keys;
	public boolean up, down, left, right;
	
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		up = keys[upNum];
		down = keys[downNum];
		left = keys[leftNum];
		right = keys[rightNum];
	}
	
	public void setUp(int num) {
		upNum = num;
	}
	
	public void setDown(int num) {
		downNum = num;
	}

	public void setLeft(int num) {
		leftNum = num;
	}

	public void setRight(int num) {
		rightNum = num;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
