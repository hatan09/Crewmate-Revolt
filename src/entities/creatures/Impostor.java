package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Animation;
import gfx.ImgAssets;
import main.Handler;
import wave.WaveManager;

public class Impostor extends Creature{
	
	private float distance;
	private final static float MAX_DISTANCE = 500;
	private float atkRange;

	public Impostor(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		this.speed = 3;
		this.atkRange = 100;
		
		box.setBounds(15, 60, 57, 57);
		
		anim_run_left = new Animation(ANIM_SPD, ImgAssets.player_running_left);
		anim_run_right = new Animation(ANIM_SPD, ImgAssets.player_running_right);
	}
	
	public void approach() {
		xMove = 0;
		yMove = 0;
		
		if(distance < MAX_DISTANCE && distance > atkRange) {
			if(x - handler.getWorld().getPlayer().getX() > 0) {
				headingRight = false;
				if(Math.abs(x - handler.getWorld().getPlayer().getX()) < speed) {
					xMove = -(x - handler.getWorld().getPlayer().getX());
				}
				else xMove = -speed;
			}
			else if(x - handler.getWorld().getPlayer().getX() < 0){
				headingRight = true;
				if(Math.abs(x - handler.getWorld().getPlayer().getX()) < speed) {
					xMove = x - handler.getWorld().getPlayer().getX();
				}
				else xMove = speed;
			}
			
			
		
			if(y - handler.getWorld().getPlayer().getY() > 0) {
				if(Math.abs(y - handler.getWorld().getPlayer().getY()) < speed) {
					yMove = -(y - handler.getWorld().getPlayer().getY());
				}
				else yMove = -speed;
			}
			else if(y - handler.getWorld().getPlayer().getY() < 0){
				if(Math.abs(y - handler.getWorld().getPlayer().getY()) < speed) {
					yMove = y - handler.getWorld().getPlayer().getY();
				}
				else yMove = speed;
			}
		}
		else if(distance <= atkRange) {
			melee();
		}
		
		isMoving = (xMove != 0 || yMove != 0);
	}
	
	@Override
	public void takeDmg(int dmg) {
		super.takeDmg(dmg);
		if(health <= 0) {
			WaveManager.removeMelee();
			handler.getWorld().geteManager().removeCreature(this);
		}
	}

	@Override
	public void update() {
		distance = (float) Math.sqrt(Math.pow(x - handler.getWorld().getPlayer().getX(), 2) + (Math.pow(y - handler.getWorld().getPlayer().getY(), 2)));
		
		approach();
		
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
		g.drawImage((isMoving)? getFrame() : ((headingRight)? ImgAssets.player_static_right : ImgAssets.player_static_left), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);

		//g.fillRect((int) (x - handler.getCamera().getxOffset() + box.x), (int) (y - handler.getCamera().getyOffset() + box.y), box.width,  box.height);
		
		if(hand_op != null)
			g.drawImage(hand_op.filter(hands, null), (int) (x - handler.getCamera().getxOffset() - (hands.getWidth() - getWidth()) / 2), (int) (5 + y - handler.getCamera().getyOffset() - (hands.getHeight() - getHeight()) / 2), hand_op.filter(hands, null).getWidth(),  hand_op.filter(hands, null).getHeight(), null);
	
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 10), (int) (((double) health / (double) maxHealth) * 100), 10);
	}

}
