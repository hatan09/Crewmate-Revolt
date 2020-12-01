package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle box;
	boolean hovered = false;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new Rectangle((int) x, (int) y, width, height);
	}
	
	public abstract void onClick();
	
	public abstract void update();

	public abstract void render(Graphics g);
	
	public void onMouseMove(MouseEvent e) {
		if(box.contains(e.getX(), e.getY())) hovered = true;
		else hovered = false;
	}
	
	public void onMouseRelease(MouseEvent e) {
		if(hovered) onClick();
	}
	
	//GETTERS & SETTERS
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	
}
