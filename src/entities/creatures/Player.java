package entities.creatures;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;

public class Player extends Creature{
	private long timer;
	private long last_time;
	private long now;
	private int i = 0;
	private int anim_spd = 78;
	
	private float acc = 0.3f;
	private int stamina;
	private boolean headingRight = true;
	private boolean isMoving = false;
	
	AffineTransform tx;
	AffineTransformOp op;
	
	private Animation anim_run_left, anim_run_right;
	
	public Player(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.speed = 5;
		
		box.setBounds(15, 60, 57, 57);
		
		hands = ImgAssets.player_static_left;
		
		double rotationRequired = Math.toRadians (10);
		double locationX = hands.getWidth() / 2;
		double locationY = hands.getHeight() / 2;
		tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		last_time = System.nanoTime();
		
		anim_run_left = new Animation(anim_spd, ImgAssets.player_running_left);
		anim_run_right = new Animation(anim_spd, ImgAssets.player_running_right);
	}
	
	public void getInput() {
		//get input from keyboard: w = up = yMove: -speed, d = right = xMove: +speed
		
		xMove = 0;
		yMove = 0;
		
		
		if(handler.getKeyManager().up) {
			yMove -= speed;
			//if(Math.abs(yMove) < speed) yMove -= acc;
			//else yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove += speed;
			//if(Math.abs(yMove) < speed) yMove += acc;
			//else yMove = speed;
		}
		//else yMove = 0;

		if(handler.getKeyManager().left) {
			xMove -= speed;
			headingRight = false;
			//if(Math.abs(xMove) < speed) xMove -= acc;
			//else xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove += speed;
			headingRight = true;
			//if(Math.abs(xMove) < speed) xMove += acc;
			//else xMove = speed;
		}
		//else xMove = 0;
		if(xMove == 0 && yMove ==0) isMoving = false;
		else isMoving = true;
	}

	@Override
	public void update() {
		getInput();
		
		
		if(isMoving) {
			anim_run_left.update();
			anim_run_right.update();
			move();
		}
		else {
			i = 0;
			anim_run_left.reset();
			anim_run_right.reset();
		}
		
		handler.getCamera().focusOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		
		//g.drawImage(ImgAssets.player_static_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.player_static_right : ImgAssets.player_static_left), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);

		//g.fillRect((int) (x - handler.getCamera().getxOffset() + box.x), (int) (y - handler.getCamera().getyOffset() + box.y), box.width,  box.height);
		
		//g.drawImage(op.filter(hands, null), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), 75, 75, null);
	}
	
	public BufferedImage getFrame() {
		return (headingRight)? anim_run_right.getFrame() : anim_run_left.getFrame();
	}

}
