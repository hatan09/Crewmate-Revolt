package data;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


import bullets.Bullet;
import entities.Entity;
import gfx.ImgAssets;
import main.Handler;

public class Gun {

	private int id;
	private String name;
	private int maxAmmo;
	private int currAmmo;
	private long delay;
	private long reloadTime;
	private int dmg;
	private double speed;
	private double range;
	
	private AffineTransform bullet_tx;
	private AffineTransformOp bullet_op;
	
	public static Gun m4a1;
	
	public Gun(int id, String name, int maxAmmo, long delay, long reloadTime, int dmg, double speed, double range) {
		this.delay = delay;
		this.reloadTime = reloadTime;
		this.maxAmmo = maxAmmo;
		this.currAmmo = maxAmmo;
		this.dmg = dmg;
		this.range = range;
		this.speed = speed;
	}

	public static void init() {
		m4a1 = new Gun(1, "M4A1", 30, 100, 2000, 20, 20, 1000);
	}
	
	public void shoot(Handler handler, double angle, float x, float y, int width, int height, boolean headingRight, Entity e) {
		
		double rotationRequired = angle;
		if(!headingRight) rotationRequired = -1 * rotationRequired;
		double locationX = ImgAssets.bullet.getWidth() / 2;
		double locationY = ImgAssets.bullet.getHeight() / 2;
		bullet_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		bullet_op = new AffineTransformOp(bullet_tx, AffineTransformOp.TYPE_BILINEAR);
		handler.getWorld().getbController().addBullet(new Bullet(handler, x + width/2, y + height/2, bullet_op.filter(ImgAssets.bullet, null), 
				getSpeed(), getRange(), getDmg(), angle, headingRight, e));
		currAmmo--;
	}
	
	public boolean isShootable() {
		return currAmmo > 0;
	}
	
	public void reload() {
		currAmmo = maxAmmo;
	}
	
	//SETTER & GETTERS
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public int getCurrAmmo() {
		return currAmmo;
	}

	public long getDelay() {
		return delay;
	}

	public long getReloadTime() {
		return reloadTime;
	}

	public int getDmg() {
		return dmg;
	}

	public double getSpeed() {
		return speed;
	}

	public double getRange() {
		return range;
	}
}
