package data;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import bullets.Bullet;
import database.SQL_Communication;
import entities.Entity;
import gfx.ImgAssets;
import main.Handler;
import sfx.SoundEffect;

public class Gun {

	private int id;
	private String name;
	private int price;
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
	
	public Gun(int id, String name, int price, boolean isAR, int maxAmmo, long delay, long reloadTime, int dmg, double speed, double range, BufferedImage[] img) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.isAR = isAR;
		this.delay = delay;
		this.reloadTime = reloadTime;
		this.maxAmmo = maxAmmo;
		this.currAmmo = maxAmmo;
		this.dmg = dmg;
		this.range = range;
		this.speed = speed;
		
		if(img == null) return;
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
	
	public Gun(Gun gun, BufferedImage[] img) {
		this.id = gun.id;
		this.name = gun.name;
		this.price = gun.price;
		this.isAR = gun.isAR;
		this.delay = gun.delay;
		this.reloadTime = gun.reloadTime;
		this.maxAmmo = gun.maxAmmo;
		this.currAmmo = gun.maxAmmo;
		this.dmg = gun.dmg;
		this.range = gun.range;
		this.speed = gun.speed;
		
		setImg(img);
	}

	public static void init() {
		IMPOSTOR = new Gun(0, "ANACONDA", 10, false, 30, 300, 2000, 20, 20, 1000, ImgAssets.anaconda);
		
		DESERT_EAGLE = new Gun(SQL_Communication.getGuns(1), ImgAssets.desert_eagle);
		DOUBLE_DE = new Gun(SQL_Communication.getGuns(2), ImgAssets.double_de);
		UZI = new Gun(SQL_Communication.getGuns(3), ImgAssets.uzi);
		UMP = new Gun(SQL_Communication.getGuns(4), ImgAssets.ump);
		M4A1 = new Gun(SQL_Communication.getGuns(5), ImgAssets.m4a1);
		M16 = new Gun(SQL_Communication.getGuns(6), ImgAssets.m16);
		AK47 = new Gun(SQL_Communication.getGuns(7), ImgAssets.ak47);
		
//		DESERT_EAGLE = new Gun(1, "DESERT EAGLE", 10, false, 30, 300, 2000, 20, 20, 1000, ImgAssets.desert_eagle);
//		DOUBLE_DE = new Gun(2, "DOUBLE DE", 10, false, 30, 300, 2000, 20, 20, 1000, ImgAssets.double_de);
//		UZI = new Gun(3, "UZI", 10, true, 30, 300, 2000, 20, 20, 1000, ImgAssets.uzi);
//		UMP = new Gun(4, "UMP", 10, true, 30, 100, 2000, 20, 20, 1000, ImgAssets.ump);
//		M4A1 = new Gun(5, "M4A1", 10, true, 30, 300, 2000, 20, 20, 1000, ImgAssets.m4a1);
//		M16 = new Gun(6, "M16", 10, true, 30, 100, 2000, 20, 20, 1000, ImgAssets.m16);
//		AK47 = new Gun(7, "AK47", 10, true, 30, 300, 2000, 20, 20, 1000, ImgAssets.ak47);
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
			ex.printStackTrace();
		}
		
		currAmmo--;
	}
	
	public boolean isShootable() {
		return currAmmo > 0;
	}
	
	public void playReloadSound() {
		try {
			SoundEffect.playReload();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
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
	
	public int getPrice() {
		return price;
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
	
	public void setImg(BufferedImage[] img) {
		left = img[0];
		right = img[1];
	}
	
	public static Gun getGunAsID(int id) {
		switch(id) {
		case 1: {return DESERT_EAGLE;}
		case 2: {return DOUBLE_DE;}
		case 3: {return UZI;}
		case 4: {return UMP;}
		case 5: {return M4A1;}
		case 6: {return M16;}
		case 7: {return AK47;}
		}
		return null;
	}
}
