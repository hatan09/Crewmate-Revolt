package bullets;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;

public class BulletController {

	private Handler handler;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public BulletController(Handler handler) {
		this.handler = handler;
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public void removeBullet(Bullet b) {
		bullets.remove(b);
	}
	
	public void update() {
		for(Bullet b : bullets) {
			b.update();
		}
	}
	
	public void render(Graphics g) {
		for(Bullet b : bullets) {
			b.render(g);
		}
	}
}
