package entities.creatures;


import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entities.Entity;
import gfx.Animation;
import main.Handler;
import tiles.Tile;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 10, DEFAULT_CREATURE_HEIGHT = 10;
	
	protected int maxHealth;
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	protected boolean headingRight = true;
	protected boolean isMoving = false;
	
	protected Rectangle hitbox;
	
	protected static final int ANIM_SPD = 78;
	
	protected Animation anim_run_left, anim_run_right;
	
	protected AffineTransform hand_tx;
	protected AffineTransformOp hand_op;
	
	protected boolean penetrating = false;

	
	public Creature(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
		maxHealth = DEFAULT_HEALTH;
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		
		hitbox = new Rectangle(0, 0, width, height);
	}
	
	public void move() {
		if(xMove != 0 && yMove != 0) {
			xMove = (float) (xMove/Math.sqrt(2));
			yMove = (float) (yMove/Math.sqrt(2));
		}
		
		if(!checkEntityCollision(xMove, 0f)) moveX();
		
		if(!checkEntityCollision(0f, yMove)) moveY();
		
		//get input: get first xMove, yMove
		//check move on x and y: try to move: into solid ==> not move, else: move
		//if move ok, move: x += xMove, y += yMove
	}
	
	public void moveX() {
		if(xMove > 0) {	//right
			int tx = (int) (x + xMove + box.x + box.width) / Tile.TILE_WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + box.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int) (y + box.y + box.height) / Tile.TILE_HEIGHT)){
				x += xMove;
			}
			else {
				x = tx * Tile.TILE_WIDTH - box.x - box.width - 1;
			}
		}
		else if(xMove < 0) {	//left
			int tx = (int) (x + xMove + box.x) / Tile.TILE_WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + box.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int) (y + box.y + box.height) / Tile.TILE_HEIGHT)){
				x += xMove;
			}
			else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - box.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove > 0) {	//down
			int ty = (int) (y + yMove + box.y + box.height) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + box.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTile((int) (x + box.x + box.width) / Tile.TILE_WIDTH, ty)){
				y += yMove;
			}
			else {
				y = ty * Tile.TILE_HEIGHT - box.y - box.height - 1;
			}
		}
		else if(yMove < 0) {	//up
			int ty = (int) (y + yMove + box.y) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + box.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTile((int) (x + box.x + box.width) / Tile.TILE_WIDTH, ty)){
				y += yMove;
			}
			else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - box.y;
			}
		}
	}
	
	public boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	public boolean checkEntityCollision(float dx, float dy) {
		for(Entity e : handler.getWorld().geteManager().getSolidEntities()) {
			if(e.equals(this)) continue;
			
			if(e.getCollisionBox(0f, 0f).intersects(getCollisionBox(dx, dy))) return true;
		}
		return false;
	}
	
	public Rectangle getHitBox(float dx, float dy) {
		return new Rectangle((int) (x + hitbox.x + dx) , (int) (y + hitbox.y + dy), hitbox.width, hitbox.height);
	}
	
	public void takeDmg(int dmg) {
		health -= dmg;
	}
	
	public void shoot() {
		
	}
	
	public void melee() {
		System.out.println("Melee");
	}
	
	public BufferedImage getFrame() {
		return (headingRight)? anim_run_right.getFrame() : anim_run_left.getFrame();
	}
	
	//setter & getter
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
}
