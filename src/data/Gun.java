package data;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import bullets.Bullet;
import entities.Entity;
import gfx.ImgAssets;
import main.Handler;
import sound.SoundEffect;

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
	private boolean isAR;
	
	private BufferedImage right, left;
	
	private AffineTransform bullet_tx;
	private AffineTransformOp bullet_op;
	
	public static Gun M4A1, M16, DESERT_EAGLE, UMP, AK47, DOUBLE_DE, UZI, IMPOSTOR;
	
	public Gun(int id, String name, boolean isAR, int maxAmmo, long delay, long reloadTime, int dmg, double speed, double range, BufferedImage[] img) {
		this.id = id;
		this.name = name;
		this.isAR = isAR;
		this.delay = delay;
		this.reloadTime = reloadTime;
		this.maxAmmo = maxAmmo;
		this.currAmmo = maxAmmo;
		this.dmg = dmg;
		this.range = range;
		this.speed = speed;
		
		left = img[0];
		right = img[1];
	}
	
	public Gun(Gun gun) {
		this.id = gun.id;
		this.name = gun.name;
		this.isAR = gun.isAR;
		this.delay = gun.delay;
		this.reloadTime = gun.reloadTime;
		this.maxAmmo = gun.maxAmmo;
		this.currAmmo = gun.maxAmmo;
		this.dmg = gun.dmg;
		this.range = gun.range;
		this.speed = gun.speed;
		
		left = gun.left;
		right = gun.right;
	}

	public static void init() {
		IMPOSTOR = new Gun(0, "ANACONDA", false, 30, 300, 2000, 20, 20, 1000, ImgAssets.anaconda);
		DESERT_EAGLE = new Gun(1, "DESERT EAGLE", false, 30, 300, 2000, 20, 20, 1000, ImgAssets.desert_eagle);
		UMP = new Gun(2, "UMP", true, 30, 100, 2000, 20, 20, 1000, ImgAssets.ump);
		M4A1 = new Gun(3, "M4A1", true, 30, 300, 2000, 20, 20, 1000, ImgAssets.m4a1);
		M16 = new Gun(4, "M16", true, 30, 100, 2000, 20, 20, 1000, ImgAssets.m16);
		AK47 = new Gun(5, "AK47", true, 30, 300, 2000, 20, 20, 1000, ImgAssets.ak47);
		DOUBLE_DE = new Gun(6, "DOUBLE DE", false, 30, 300, 2000, 20, 20, 1000, ImgAssets.double_de);
		UZI = new Gun(7, "UZI", true, 30, 300, 2000, 20, 20, 1000, ImgAssets.uzi);
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
		
		try {
			if(isAR) SoundEffect.playAR();
			else SoundEffect.playDE();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
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

	public BufferedImage getRight() {
		return right;
	}

	public BufferedImage getLeft() {
		return left;
	}
	
	
}
