package bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import entities.Entity;
import entities.creatures.Creature;
import entities.statics.StaticEntity;
import entities.statics.solid.SolidEntity;
import main.Handler;

public class Bullet {

	protected Handler handler;
	
	private double x;
	private double y;
	
	private double speed;
	private double xMove;
	private double yMove;
	
	private double range;
	
	private double traveled = 0;
	
	private Rectangle box;
	private BufferedImage img;
	private int dmg;
	private double deg;
	private int coef;
	
	Entity shooter;
	
	public Bullet(Handler handler, double x, double y, BufferedImage img, double speed, double range, int dmg, double deg, boolean headingRight, Entity shooter) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
		this.range = range;
		this.dmg = dmg;
		this.deg = deg;
		
		this.shooter = shooter;
		
		coef = (headingRight)? 1 : -1;
		
		box = new Rectangle(10, 10);
		
		calculateSpeed();
	}
	
	private void calculateSpeed() {
		xMove = Math.sin(deg) * speed * coef;
		yMove = - Math.cos(deg) * speed;
		
	}
	
	public Rectangle getCollisionBox(double dx, double dy) {
		return new Rectangle((int) (x + box.x + dx) , (int) (y + box.y + dy), box.width, box.height);
	}
	
	public boolean checkEntityCollision(double dx, double dy) {
		for(StaticEntity e : handler.getWorld().geteManager().getStaticEntities()) {
			if(e.getCollisionBox(0f, 0f).intersects(getCollisionBox(dx + img.getWidth()/2, dy + img.getHeight()/2))) {
				e.takeDmg(dmg);
				return true;
			}
		}
		for(SolidEntity e : handler.getWorld().geteManager().getSolidEntities()) {
			if(e.getCollisionBox(0f, 0f).intersects(getCollisionBox(dx + img.getWidth()/2, dy + img.getHeight()/2))) {
				e.takeDmg(dmg);
				return true;
			}
		}
		for(Creature c : handler.getWorld().geteManager().getCreatures()) {
			if(shooter.equals(c)) continue;
			if(c.getHitBox(0f, 0f).intersects(getCollisionBox(dx + img.getWidth()/2, dy + img.getHeight()/2))) {
				c.takeDmg(dmg);
				return true;
			}
		}
		return false;
	}
	
	private void move() {
		if(traveled + Math.sqrt(Math.pow(xMove, 2) + Math.pow(yMove, 2)) >= range) {
			xMove = (range - traveled) * Math.sin(deg) * coef;
			yMove = (range - traveled) * Math.cos(deg);
			checkEntityCollision(xMove, yMove);
			handler.getWorld().getbController().removeBullet(this);
		}
		else {
			if(checkEntityCollision(xMove, yMove)) {
				handler.getWorld().getbController().removeBullet(this);
			}
			else {
				moveX();
				moveY();
				traveled += Math.sqrt(Math.pow(xMove, 2) + Math.pow(yMove, 2));
			}
		}
	}
	
	private void moveX() {
		x += xMove;
	}
	
	private void moveY() {
		y += yMove;
	}
	
	public void update() {
		move();
	}
	
	public void render(Graphics g) {
		g.drawImage(img, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), img.getWidth(), img.getHeight(), null);
		//g.setColor(Color.red);
		//g.fillRect((int) (x - handler.getCamera().getxOffset() + img.getWidth()/2), (int) (y - handler.getCamera().getyOffset() + img.getHeight()/2), (int) box.getWidth(), (int) box.getHeight());
	}
}
