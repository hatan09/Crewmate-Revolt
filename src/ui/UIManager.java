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
		if(!objects.contains(o)) objects.add(o);
	}
	
	public void removeUIObject(UIObject o) {
		objects.remove(o);
	}
	
	
	public boolean contains(UIObject o) {
		return objects.contains(o);
	}
	
	public void update() {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).onMouseRelease(e);
		}
	}
}
