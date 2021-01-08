package entities.creatures.impostors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;
import wave.WaveManager;

public class Fast extends Impostor{
	Color outterAtkRect_color = new Color(210, 115, 115);
	Color innerAtkRect_color = Color.red;
	
	private final int FAST_ATK_WIDTH = 142;
	private final int FAST_ATK_HEIGHT = 142;

	public Fast(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		speed = 6;
		
		dmg = 15;
		
		atkRange = 150;
		chargeTime = 750;
		delayTime = 1000;
		maxDistance = 700;
		atkAnimTime = 200;
		
		anim_run_left = new Animation(ANIM_SPD, ImgAssets.fast_running_left);
		anim_run_right = new Animation(ANIM_SPD, ImgAssets.fast_running_right);
		
		atkRect = new Rectangle((int) atkRange, (int) atkRange / 2);
		atkRect.x = width/2;
		atkRect.y = (height - atkRect.height) / 2;
	}
	
	@Override
	public void melee() {
		if(headingRight) hit(0,0);
		else 			 hit(-atkRect.width,0);
	}
	
	public Rectangle getAtkRect(int dx, int dy) {
		return new Rectangle((int) (x + atkRect.x + dx) , (int) (y + atkRect.y + dy), atkRect.width, atkRect.height);
	}
	
	public boolean hit(int dx, int dy) {
		if(handler.getWorld().getPlayer().getHitBox(0f, 0f).intersects(getAtkRect(dx, dy))) {
			handler.getWorld().getPlayer().takeDmg(dmg);
			return true;
		}
		return false;
	}
	
	@Override
	public void atk() {
		super.atk();
		
		melee();
	}
	
	@Override
	public void takeDmg(int dmg) {
		super.takeDmg(dmg);
		
		if(health <= 0) WaveManager.removeFast();
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Graphics g) {
		if(isCharging) {
			g.setColor(outterAtkRect_color);
			g.fillRect((int) (x + atkRect.x - handler.getCamera().getxOffset()) + ((headingRight)? 0 : - atkRect.width), (int) (y + atkRect.y - handler.getCamera().getyOffset()), 
					atkRect.width, atkRect.height);
			
			g.setColor(innerAtkRect_color);
			g.fillRect((int) (x + atkRect.x - handler.getCamera().getxOffset() + ((headingRight)? 0 : - (double) atkRect.width * proportion)), (int) (y + atkRect.y - handler.getCamera().getyOffset()), 
					(int) ((double) atkRect.width * proportion), atkRect.height);
		}
		
		if(isMoving) g.drawImage(getFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		else if(isAtking) g.drawImage((headingRight)? ImgAssets.fast_atk_right : ImgAssets.fast_atk_left, 
				(int) (x - (FAST_ATK_WIDTH - width) / 2 - handler.getCamera().getxOffset()), 
				(int) (y - handler.getCamera().getyOffset()), 
					FAST_ATK_WIDTH, FAST_ATK_HEIGHT, null);
		else g.drawImage((headingRight)? ImgAssets.fast_static_right : ImgAssets.fast_static_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);

		super.render(g);
	}

	@Override
	public void shoot() {
		
	}
	

}
