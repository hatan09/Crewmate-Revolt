package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public abstract class Entity {

	protected Handler handler;	//this is used to get access to game's KeyListener, Camera
	protected BufferedImage hands;
	protected float x, y;
	protected int width, height;
	
	protected Rectangle box;
	protected Rectangle hitbox;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		box = new Rectangle(0, 0, width, height);
		hitbox = new Rectangle(0, 0, width, height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public Rectangle getCollisionBox(float dx, float dy) {
		return new Rectangle((int) (x + box.x + dx) , (int) (y + box.y + dy), box.width, box.height);
	}
	
	public abstract void takeDmg(int dmg);

	
	//setter & getter
	
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
	
}
