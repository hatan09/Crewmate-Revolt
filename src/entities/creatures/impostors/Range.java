package entities.creatures.impostors;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import data.Gun;
import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;
import wave.WaveManager;

public class Range extends Impostor{
	

	private Gun gun;
	
	private BufferedImage hands_left;
	private BufferedImage hands_right;

	public Range(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		gun = new Gun(Gun.IMPOSTOR);

		atkRange = 500;
		chargeTime = 2000;
		delayTime = 5000;
		maxDistance = 1000;
		atkAnimTime = 200;
		
		anim_run_left = new Animation(ANIM_SPD, ImgAssets.black_running_left);
		anim_run_right = new Animation(ANIM_SPD, ImgAssets.black_running_right);
		
		rotateHands();
	}
	
	private void turnAndRotateHands(double x, double y) {
		headingRight = this.x <= handler.getWorld().getPlayer().getX();
		angle = Math.acos(y / Math.sqrt(x*x + y*y));
		if(headingRight) {
			double rotationRequired = angle;
			double locationX = gun.getRight().getWidth() / 2;
			double locationY = gun.getRight().getHeight() / 2;
			hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		}
		else {
			double rotationRequired = - angle;
			double locationX = gun.getLeft().getWidth() / 2;
			double locationY = gun.getLeft().getHeight() / 2;
			hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		}
	}
	
	private void rotateHands() {
		angle = Math.PI / 2;
		
		double rotationRequired = - angle;
		double locationX = gun.getLeft().getWidth() / 2;
		double locationY = gun.getLeft().getHeight() / 2;
		hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		hands_left = hand_op.filter(gun.getLeft(), null);
		
		rotationRequired = angle;
		locationX = gun.getRight().getWidth() / 2;
		locationY = gun.getRight().getHeight() / 2;
		hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		hands_right = hand_op.filter(gun.getRight(), null);
	}
	
	@Override
	public void shoot() {
		gun.shoot(handler, angle, x, y, width, height, headingRight, this);
	}
	
	@Override
	public void atk() {
		super.atk();
		shoot();
	}
	
	@Override
	public void takeDmg(int dmg) {
		super.takeDmg(dmg);
		
		if(health <= 0) WaveManager.removeRange();
	}
	
	@Override
	public void update() {
		super.update();
		
		if(isCharging) {
			turnAndRotateHands(	(double) (handler.getWorld().getPlayer().getX() + handler.getWorld().getPlayer().getWidth() / 2 - (x + getWidth() / 2)), 
								(double) (y + getWidth() / 2 - (handler.getWorld().getPlayer().getY() + handler.getWorld().getPlayer().getHeight() / 2)));
		}
		else if(isAtking) {
			if(headingRight) {
				double rotationRequired = angle - (double) timer / (double) atkAnimTime * (Math.PI / 3);
				double locationX = gun.getRight().getWidth() / 2;
				double locationY = gun.getRight().getHeight() / 2;
				hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
			}
			else {
				double rotationRequired = - angle + (double) timer / (double) atkAnimTime * (Math.PI / 3);
				double locationX = gun.getRight().getWidth() / 2;
				double locationY = gun.getRight().getHeight() / 2;
				hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.black_static_right : ImgAssets.black_static_left), 
				(int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()),
				width, height, null);

		if(!isCharging && !isAtking && !isDelaying) {
			if(headingRight) {
				g.drawImage(hands_right, 
						(int) (x - handler.getCamera().getxOffset() - (gun.getRight().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (gun.getRight().getHeight() - getHeight()) / 2), 
							hands_right.getWidth(),  hands_right.getHeight(), null);
			}
			else {
				g.drawImage(hands_left, 
						(int) (x - handler.getCamera().getxOffset() - (gun.getRight().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (gun.getRight().getHeight() - getHeight()) / 2), 
							hands_left.getWidth(),  hands_left.getHeight(), null);
			}
		}
		else if(hand_op != null) {
			if(headingRight) {
				g.drawImage(hand_op.filter(gun.getRight(), null), 
						(int) (x - handler.getCamera().getxOffset() - (gun.getRight().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (gun.getRight().getHeight() - getHeight()) / 2), 
							hand_op.filter(gun.getRight(), null).getWidth(),  hand_op.filter(gun.getRight(), null).getHeight(), null);
			}
			else {
				g.drawImage(hand_op.filter(gun.getLeft(), null), 
						(int) (x - handler.getCamera().getxOffset() - (gun.getLeft().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (gun.getLeft().getHeight() - getHeight()) / 2), 
							hand_op.filter(gun.getLeft(), null).getWidth(),  hand_op.filter(gun.getLeft(), null).getHeight(), null);
			}
		}
		
		super.render(g);
	}

	@Override
	public void melee() {
		// TODO Auto-generated method stub
		
	}

}
