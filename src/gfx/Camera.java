package gfx;

import entities.Entity;
import main.Game;
import main.Handler;
import tiles.Tile;

public class Camera {
	
	private Handler handler;
	
	private float xOffset, yOffset;
	
	public Camera(Handler handler, float xOffset, float yOffset) {
		this.xOffset = xOffset;		//DISPLAY everything by this amount to the right (view move to left) from the current position
		this.yOffset = yOffset;		//DISPLAY everything by this amount downward (view move upward) from the actual position
										//the point (0,0) of the window is now at (xOffset, yOffset) point of the map
											//==> render a part of map from (xOffset, yOffset)
										//the point (0,0) of the map is now at (-xOffset, -yOffset) point of the windows
											//==> the part of the map starting from (0,0) is rendered outside of the screen (up and left of the screen) which is showed to the screen (but it's still working)
										//==> the logic still works on the exact coordinates but we can see everything in the different view
		this.handler = handler;
	}
	
	//center an entity: by moving everything so that the entity in the center of screen
	public void focusOnEntity(Entity e) {
		if(e != null) {
			xOffset = e.getX() - handler.getGameWidth() / 2 + e.getWidth() / 2;		//get the true location of an entity, render everything to the right by this amount
			yOffset = e.getY() - handler.getGameHeight() / 2 + e.getHeight() / 2;		//get the true location of an entity, render everything downward by this amount
			
			checkBlank();
		}																			//==> update offset after this entity moves so that center the entity in the center of the screen
																				//or the game will render everything starting from the point (0,0) (with offsets) until it fills the screen, and it will only stick to that point
		//focus on an entity at (100, 100) of the map ==> entity now always appears in the center of the screen, not at any other locations when moving
		//but it still always remains at exact location on the map !? How?
		//that's because the map is now not static but moving, actually, the map is moving and the entity is static
																				
	}
	
	//change the view (of camera) by a little amount from the current displaying position (offset)
	//public void move(float dx, float dy) {
		//xOffset += dx;
		//yOffset += dy;
	//}
	
	public void checkBlank() {
		//check the outter boundaries of the map: not render what's outside of the map
		if(xOffset < 0) xOffset = 0;
		else if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getGameWidth()) xOffset = handler.getWorld().getWidth()  * Tile.TILE_WIDTH - handler.getGameWidth();
		
		if(yOffset < 0) yOffset = 0;
		else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGameHeight()) yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGameHeight();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	
}
