package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import bullets.Bullet;
import data.Gun;
import gfx.Animation;
import gfx.ImgAssets;
import input.KeyManager;
import main.Handler;
import sound.SoundEffect;

public class Player extends Creature{
	private static final int ANIM_SPD = 78;
	private double angle;
	long lastTime = 0;
	long timer = 0;
	long loading = 0;
	
	private Gun curr;
	private Gun next;
	
	private float acc = 0.3f;
	private int stamina;

	private boolean isShooting = false;
	
	private boolean isReloading = false;
	
	private int mouseX, mouseY;
	
	
	public Player(Handler handler, float x, float y, int width, int height/*, Gun curr, Gun next*/) {
		super(handler, x, y, width, height);
		this.speed = 5;
		
		box.setBounds(15, 60, 57, 57);
		
		anim_run_left = new Animation(ANIM_SPD, ImgAssets.player_running_left);
		anim_run_right = new Animation(ANIM_SPD, ImgAssets.player_running_right);
		
		curr = new Gun(Gun.M16);
		
		next = new Gun(Gun.DESERT_EAGLE);
	}
	
	public void getInput() {
		//get input from keyboard: w = up = yMove: -speed, d = right = xMove: +speed
		
		xMove = 0;
		yMove = 0;
		penetrating = false;
		
		
		if(handler.getKeyManager().up) {
			yMove -= speed;
		}
		if(handler.getKeyManager().down) {
			yMove += speed;
		}

		if(handler.getKeyManager().left) {
			xMove -= speed;
		}
		if(handler.getKeyManager().right) {
			xMove += speed;
		}
		
		isMoving = (xMove != 0 || yMove != 0);
		
		mouseX = handler.getMouseManager().getMouseX();
		mouseY = handler.getMouseManager().getMouseY();
		isShooting = handler.getMouseManager().isPressingLeft();
		
		isReloading = handler.getKeyManager().r;
	}
	
	private void rotateHands(double x, double y) {
		angle = Math.acos(y / Math.sqrt(x*x + y*y));
		if(headingRight) {
			double rotationRequired = angle;
			double locationX = curr.getRight().getWidth() / 2;
			double locationY = curr.getRight().getHeight() / 2;
			hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		}
		else {
			double rotationRequired = - angle;
			double locationX = curr.getLeft().getWidth() / 2;
			double locationY = curr.getLeft().getHeight() / 2;
			hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
		}
	}
	
	public void shoot() {
		
		curr.shoot(handler, angle, x ,y, width, height, headingRight, this);
		
	}

	@Override
	public void melee() {
		
	}
	
	
	public Gun getcurrentGun() {
		return curr;
	}
	
	public void reload() {
		curr.reload();
		handler.getKeyManager().r = false;
		isReloading = false;
	}
	
	public void swap(Gun gun) {
		if(next.equals(gun))
			next = curr;
		curr = gun;
		timer = curr.getDelay();
		loading = 0;
	}

	@Override
	public void update() {
		//get mouse & key input
		getInput();
		
		//swap 2 guns
		if(handler.getKeyManager().q) {
			swap(next);
			handler.getKeyManager().q = false;
		}
		
		if(isMoving) {
			SoundEffect.playStep();
			
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
			SoundEffect.stopStep();
		}
		
		//check direction
		headingRight = (mouseX >= x - handler.getCamera().getxOffset() + getWidth() / 2);
		
		rotateHands((double) (mouseX - (x - handler.getCamera().getxOffset() + getWidth() / 2)), 
				    (double) ((y - handler.getCamera().getyOffset() + getHeight() / 2) - mouseY));
		
		//atk speed
		if(timer < curr.getDelay()) {
			timer += System.currentTimeMillis() - lastTime;
		}
		else {
			timer = curr.getDelay();
		}
		
		//if there is no ammo left ==> force reload
		if (!curr.isShootable()) isReloading = true;
		
		//if reloading
		if(isReloading) {
			//if really need to reload
			if(curr.getCurrAmmo() < curr.getMaxAmmo()) {
				loading += System.currentTimeMillis() - lastTime;
				if(loading >= curr.getReloadTime()) {
					reload();
					loading = 0;
				}
			}
			//if full of ammo already ==> cancel reload
			else isReloading = handler.getKeyManager().r = false;
		}
		//if not reloading
		else {
			//if is shooting and able to shoot
			if(isShooting && timer == curr.getDelay()) {
				shoot();
				timer = 0;
			}
		}

		lastTime = System.currentTimeMillis();
		
		//set focus
		handler.getCamera().focusOnEntity(this);
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.player_static_right : ImgAssets.player_static_left), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);

		//g.fillRect((int) (x - handler.getCamera().getxOffset() + box.x), (int) (y - handler.getCamera().getyOffset() + box.y), box.width,  box.height);
		
		if(hand_op != null && !isReloading)
			if(headingRight) {
				g.drawImage(hand_op.filter(curr.getRight(), null), 
						(int) (x - handler.getCamera().getxOffset() - (curr.getRight().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (curr.getRight().getHeight() - getHeight()) / 2), 
							hand_op.filter(curr.getRight(), null).getWidth(),  hand_op.filter(curr.getRight(), null).getHeight(), null);
			}
			else {
				g.drawImage(hand_op.filter(curr.getLeft(), null), 
						(int) (x - handler.getCamera().getxOffset() - (curr.getRight().getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (curr.getRight().getHeight() - getHeight()) / 2), 
							hand_op.filter(curr.getRight(), null).getWidth(),  hand_op.filter(curr.getRight(), null).getHeight(), null);
			}
		
		g.setColor(Color.green);
		g.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 10), (int) (((double) health / (double) maxHealth) * 100), 10);
	}
	

}
