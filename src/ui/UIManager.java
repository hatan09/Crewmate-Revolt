package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.Handler;

public class UIManager {

	private ArrayList<UIObject> objects;
	private Handler handler;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
		
	}
	
	public void addUIObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeUIObject(UIObject o) {
		objects.remove(o);
	}
	
	public void update() {
		for(UIObject o : objects) {
			o.update();
		}
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
}
