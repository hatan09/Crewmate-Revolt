package bullets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.creatures.Creature;
import main.Handler;

public abstract class Bullet {

	protected Handler handler;
	
	private double x;
	private double y;
	private double a = 0, b = 0;
	private double speed;
	private double range;
	private double traveled;
	private Rectangle box;
	private BufferedImage img;
	private int dmg;
	private double deg;
	private int coef;
	
	public Bullet(Handler handler, double x, double y, BufferedImage img, double speed, double range, int dmg, double deg, boolean headingRight) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
		this.range = range;
		this.dmg = dmg;
		this.deg = deg;
		
		coef = (headingRight)? 1 : -1;
		
		calculate();
	}
	
	private void calculate() {
		if (deg == Math.PI || deg == 0) {
			if(deg != 0) speed = - speed;
		}
		else {
			speed = speed * Math.sin(deg);
			
			if(deg > Math.PI / 2)
				a = - Math.tan(deg - Math.PI / 2) * coef;
			else a = Math.tan(Math.PI / 2 - deg) * coef;
			b = y - a * x;
		}
		
	}
	
	public Rectangle getCollisionBox(float dx, float dy) {
		return new Rectangle((int) (x + box.x + dx) , (int) (y + box.y + dy), box.width, box.height);
	}
	
	public boolean checkEntityCollision(float dx, float dy) {
		for(Creature cr : handler.getWorld().geteManager().getCreatures()) {
			if(cr.getCollisionBox(0f, 0f).intersects(getCollisionBox(dx, dy))) {
				cr.takeDmg(dmg);
				return true;
			}
		}
		return false;
	}
	
	public void update() {
		if(a == 0) {
			y += speed;
		}
		else {
			x += speed * coef;
			y = a * x + b;
		}
		traveled += Math.abs(speed);		
		if(traveled >= range) {
			
		}
	}
	
	public void render(Graphics g) {
		g.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) box.getWidth(), (int) box.getHeight());
		//g.drawImage(img, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), img.getWidth(), img.getHeight(), null);
	}
}
