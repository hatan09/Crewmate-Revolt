package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	/*
	 * This is used to hold every tile's img for every tile on a map
	 * Each tile has an id and its own image, of course, it has a name which is also its variable name.
	 * */
	
	public static Tile[] tiles = new Tile[100];		/* 
														This array is used to hold all instances of any type of tile (grass, dirt...) belong with its id. Yes, as long as their types are just Tile
														
													 */ 
	public static Tile grassTile = new GrassTile(0);	//now, tiles[] added grassTile - id = 0
	public static Tile dirtTile = new DirtTile(1);		//now, tiles[] added dirtTile  - id = 1
	public static Tile waterTile = new WaterTile(2);	//now, tiles[] added waterTile - id = 2
	
	//public static Tile errorTile = new Tile(new );
	
	
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;	//the actual size of a tile which will be rendered
	
	protected BufferedImage tile_img;
	protected final int id;
	
	
	public Tile(BufferedImage tile_img, int id) {
		this.tile_img = tile_img;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(tile_img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;	//is NOT SOLID by defauly ==> to make it SOLID : Override
	}
	
	public int getId() {
		return id;
	}

}
