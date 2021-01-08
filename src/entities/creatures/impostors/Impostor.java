package entities.creatures.impostors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import entities.creatures.Creature;
import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;
import wave.WaveManager;

public abstract class Impostor extends Creature{
	
	protected double angle;
	
	protected float distance;
	protected float maxDistance;
	protected float atkRange;
	protected double proportion = 0;
	
	//charge before atking after getting in range
	protected boolean isCharging = false;
	protected long chargeTime;
	
	//atk and display atk animation
	protected boolean isAtking = false;
	protected long atkAnimTime;
	
	//delay after atking
	protected boolean isDelaying = false;
	protected long delayTime;
	
	protected long timer = 0;
	protected long lastTime = 0;

	public Impostor(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	public void approach() {
		xMove = 0;
		yMove = 0;
		
		isMoving = false;
		
		distance = (float) Math.sqrt(Math.pow(x - handler.getWorld().getPlayer().getX(), 2) + (Math.pow(y - handler.getWorld().getPlayer().getY(), 2)));
		
		if(x - handler.getWorld().getPlayer().getX() > 0) {
			headingRight = false;
		}
		else {
			headingRight = true;
		}
		
		if(distance < maxDistance && distance > atkRange) {

			if(Math.abs(x - handler.getWorld().getPlayer().getX()) < speed) {
				xMove = Math.abs(x - handler.getWorld().getPlayer().getX()) * ((headingRight)? 1 : -1);
			}
			else xMove = speed * ((headingRight)? 1 : -1);
			
			
			if(y - handler.getWorld().getPlayer().getY() > 0) {
				if(Math.abs(y - handler.getWorld().getPlayer().getY()) < speed) {
					yMove = -Math.abs(y - handler.getWorld().getPlayer().getY());
				}
				else yMove = -speed;
			}
			else if(y - handler.getWorld().getPlayer().getY() < 0){
				if(Math.abs(y - handler.getWorld().getPlayer().getY()) < speed) {
					yMove = Math.abs(y - handler.getWorld().getPlayer().getY());
				}
				else yMove = speed;
			}
			isMoving = (xMove != 0 || yMove != 0);
		}
		else if(distance <= atkRange) {
			isCharging = true;
			isMoving = false;
		}	
	}
	
	public void atk() {
		isAtking = true;
		isCharging = false;
	}

	@Override
	public void update() {
		if(isDelaying) {
			if(timer < delayTime) {
				timer += System.currentTimeMillis() - lastTime;
			}
			else {
				isDelaying = false;
				timer = 0;
			}
		}
		else if(isCharging) {
			if(timer < chargeTime) {
				timer += System.currentTimeMillis() - lastTime;
				proportion = (double) timer / (double) chargeTime;
			}
			else {
				atk();
				timer = 0;
			}
		}
		else if (isAtking) {
			if(timer < atkAnimTime) {
				timer += System.currentTimeMillis() - lastTime;
			}
			else {
				isAtking = false;
				isDelaying = true;
			}
		}
		else {
			approach();
		}
		
		lastTime = System.currentTimeMillis();
		
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
		
	}

	@Override
	public void render(Graphics g) {
		//g.fillRect((int) (x - handler.getCamera().getxOffset() + box.x), (int) (y - handler.getCamera().getyOffset() + box.y), box.width,  box.height);

		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 10), (int) (((double) health / (double) maxHealth) * 100), 10);
	}

	

}
