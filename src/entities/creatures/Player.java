package entities.creatures;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;

public class Player extends Creature{
	private int anim_spd = 78;
	private double angle;
	long last_time = 0;
	long timer = 0;
	
	int v1x = 0, v1y = 1, v2x = 50, v2y = -50;
	double test_angle;
	
	private float acc = 0.3f;
	private int stamina;
	private boolean headingRight = true;
	private boolean isMoving = false;
	
	private int mouseX, mouseY;
	private AffineTransform tx;
	private AffineTransformOp op;
	
	private Animation anim_run_left, anim_run_right;
	
	public Player(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.speed = 5;
		
		box.setBounds(15, 60, 57, 57);
		
		anim_run_left = new Animation(anim_spd, ImgAssets.player_running_left);
		anim_run_right = new Animation(anim_spd, ImgAssets.player_running_right);
		
		test_angle = Math.acos((double) (v1y*v1y) / (v1y * Math.sqrt(v2x*v2x + v2y*v2y))); 
	}
	
	public void getInput() {
		//get input from keyboard: w = up = yMove: -speed, d = right = xMove: +speed
		
		xMove = 0;
		yMove = 0;
		penetrating = false;
		
		
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
			//if(Math.abs(xMove) < speed) xMove -= acc;
			//else xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove += speed;
			//if(Math.abs(xMove) < speed) xMove += acc;
			//else xMove = speed;
		}
		//else xMove = 0;
		if(xMove == 0 && yMove == 0) isMoving = false;
		else isMoving = true;
		
		mouseX = handler.getMouseManager().getMouseX();
		mouseY = handler.getMouseManager().getMouseY();
	}
	
	private void rotateHands(int x, int y) {
		if(headingRight) {
			hands = ImgAssets.gun_right;
			//double rotationRequired = Math.toRadians (angle);
			angle = Math.acos(y / Math.sqrt(x*x + y*y));
			double rotationRequired = angle;
			double locationX = hands.getWidth() / 2;
			double locationY = hands.getHeight() / 2;
			tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		}
		else {
			hands = ImgAssets.gun_left;
			//double rotationRequired = Math.toRadians (angle);
			angle = Math.acos(y / Math.sqrt(x*x + y*y));
			double rotationRequired = - angle;
			double locationX = hands.getWidth() / 2;
			double locationY = hands.getHeight() / 2;
			tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		}
	}

	@Override
	public void update() {
		//get key input
		getInput();
		

		/*
		 * timer += System.nanoTime() - last_time; last_time = System.nanoTime();
		 * if(timer >= 1000000000) { if(angle >= 90) angle = -90; else angle += 10;
		 * timer = 0; }
		 */
		
		if(isMoving) {
			anim_run_left.update();
			anim_run_right.update();
			if(!penetrating) move();
			else {
				x += xMove;
				y += yMove;
			}
		}
		else {
			anim_run_left.reset();
			anim_run_right.reset();
		}
		
		if(mouseX >= x - handler.getCamera().getxOffset() + getWidth() / 2) headingRight = true; else headingRight = false;
		
		rotateHands(mouseX - (int) (x - handler.getCamera().getxOffset() + getWidth() / 2), (int) (y - handler.getCamera().getyOffset() + getHeight() / 2 + 5) - mouseY);
		
		//set focus
		handler.getCamera().focusOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.player_static_right : ImgAssets.player_static_left), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);

		//g.fillRect((int) (x - handler.getCamera().getxOffset() + box.x), (int) (y - handler.getCamera().getyOffset() + box.y), box.width,  box.height);
		
		if(op != null)
			g.drawImage(op.filter(hands, null), (int) (x - handler.getCamera().getxOffset() - (hands.getWidth() - getWidth()) / 2), (int) (5 + y - handler.getCamera().getyOffset() - (hands.getHeight() - getHeight()) / 2), op.filter(hands, null).getWidth(),  op.filter(hands, null).getHeight(), null);
	}
	
	public BufferedImage getFrame() {
		return (headingRight)? anim_run_right.getFrame() : anim_run_left.getFrame();
	}

}
