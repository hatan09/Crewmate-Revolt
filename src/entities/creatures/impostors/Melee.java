package entities.creatures.impostors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;
import wave.WaveManager;

public class Melee extends Impostor{
	Color outterAtkRect_color = new Color(210, 115, 115);
	Color innerAtkRect_color = Color.red;
	
	private BufferedImage hands_left, hands_right;

	public Melee(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		atkRange = 100;
		chargeTime = 2000;
		delayTime = 3000;
		maxDistance = 700;
		atkAnimTime = 500;
		
		dmg = 10;
		
		anim_run_left = new Animation(ANIM_SPD, ImgAssets.player_running_left);
		anim_run_right = new Animation(ANIM_SPD, ImgAssets.player_running_right);
		
		atkRect = new Rectangle((int) (width / 2 - atkRange), (int) (height / 2 - atkRange), (int) atkRange * 2, (int) atkRange * 2);
		
		hands_left = ImgAssets.knife[0];
		hands_right = ImgAssets.knife[1];
	}

	@Override
	public void melee() {
		hit(0, 0);
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
		
		if(health <= 0) WaveManager.removeMelee();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		
		if(isAtking) {
			if(headingRight) {
				double rotationRequired = (double) timer / (double) atkAnimTime * Math.PI * 2;
				double locationX = hands_right.getWidth() / 2;
				double locationY = hands_right.getHeight() / 2;
				hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
			}
			else {
				double rotationRequired = - (double) timer / (double) atkAnimTime * Math.PI * 2;
				double locationX = hands_left.getWidth() / 2;
				double locationY = hands_left.getHeight() / 2;
				hand_tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				hand_op = new AffineTransformOp(hand_tx, AffineTransformOp.TYPE_BILINEAR);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isCharging) {
			g.setColor(outterAtkRect_color);
			g.fillRect((int) (x + atkRect.x - handler.getCamera().getxOffset()), (int) (y + atkRect.y - handler.getCamera().getyOffset()), 
					atkRect.width, atkRect.height);
			
			g.setColor(innerAtkRect_color);
			g.fillRect((int) (x + (double) atkRect.x * proportion - handler.getCamera().getxOffset()), (int) (y + (double) atkRect.y * proportion - handler.getCamera().getyOffset()), 
					(int) (double) (atkRect.width * proportion), (int) (double) (atkRect.height * proportion));
		}
		
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.player_static_right : ImgAssets.player_static_left), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		
		super.render(g);
		
		if(!isAtking) {
			if(headingRight) {
				g.drawImage(hands_right, 
						(int) (x - handler.getCamera().getxOffset() - (hands_right.getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (hands_right.getHeight() - getHeight()) / 2), 
							hands_right.getWidth(),  hands_right.getHeight(), null);
			}
			else {
				g.drawImage(hands_left, 
						(int) (x - handler.getCamera().getxOffset() - (hands_left.getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (hands_left.getHeight() - getHeight()) / 2), 
							hands_left.getWidth(),  hands_left.getHeight(), null);
			}
		}
		else if(hand_op != null) {
			if(headingRight) {
				g.drawImage(hand_op.filter(hands_right, null), 
						(int) (x - handler.getCamera().getxOffset() - (hands_right.getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (hands_right.getHeight() - getHeight()) / 2), 
							hand_op.filter(hands_right, null).getWidth(),  hand_op.filter(hands_right, null).getHeight(), null);
			}
			else {
				g.drawImage(hand_op.filter(hands_left, null), 
						(int) (x - handler.getCamera().getxOffset() - (hands_left.getWidth() - getWidth()) / 2), 
						(int) (y - handler.getCamera().getyOffset() - (hands_left.getHeight() - getHeight()) / 2), 
							hand_op.filter(hands_left, null).getWidth(),  hand_op.filter(hands_left, null).getHeight(), null);
			}
		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
}
