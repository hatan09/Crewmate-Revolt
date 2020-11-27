package world;

import java.awt.Graphics;

import entities.EntityManager;
import entities.creatures.Player;
import entities.statics.Tree;
import entities.statics.solid.Rock;
import main.Game;
import main.Handler;
import tiles.Tile;
import utils.Utils;

public class World {
	
	/*
	 * This class is used to get information of the world, then make it into arrays, then to the id of each tile on the map. The spawning point of player is also loaded
	 * It can handle with different worlds according to the map being played
	 * It main purpose is to render every tile of the map onto the screeen ==> we can adjust the display to make it approriate with the camera/the view of a player
	 * */
	
	private Handler handler;
	
	private int width, height;	//width = number of tiles horizontally, height = number of tiles vertically
	private int spawnX, spawnY;	//location of player spawning
	private int[][] map;		//holds id for tile at a single location like map[0][0] = 1, and id of 1 is a grass tile (for ex)
	
	private int player_width = 88, player_height = 133;
	//private int player_width = 59, player_height = 86; //this is the size of player's image
	
	private Tree tree1;
	
	private Rock rock1;
	
	private EntityManager eManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
		
		eManager = new EntityManager(handler, new Player(handler, spawnX * Tile.TILE_HEIGHT, spawnY * Tile.TILE_HEIGHT, player_width, player_height));
		
		//eManager.addStaticEntity(tree1);
		
		tree1 = new Tree(handler, 500, 500, 127, 165);
		
		rock1 = new Rock(handler, 1000, 200, 127, 165);
		
		eManager.addSolidEntity(rock1);
		eManager.addSolidEntity(tree1);
	}
	
	public void update() {
		eManager.update();
	}
	
	public void render(Graphics g) {		
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getGameWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getGameHeight()) / Tile.TILE_HEIGHT + 1);
		
				//What's rendering effectiveness?
		
		for(int y = yStart; y < yEnd;y++) {
			for(int x = xStart; x < xEnd; x++) {
				//PRINCIPLES OF DISPLAYING TILES & HOW CAMERA AFFECTS TILES' RENDER:
				
				//How to display? ==> Tile.render(g, x, y)
				
				//***everything works in a 2d maxtrix with x (vertical) and y (horizontal) with the point (0,0) is the most up-left point
				
					//every tile will be rendered in the way that the up-left point (the point 0,0) of the tile will be at the location (x, y), then fill the area of the tile's wdith and height
					//then move to the next tile by adding some amount of tile's width and height according to the order of the tile being rendered next.
				//How to move to the next tile to render it?
					//use for loop (like this one) to render:
						//row by row: increase x by tile's width in each step, then reset x and add tile's height to y (this loop's method)
						//column by column: increase y by tile's height in each step, then reset y and add tile's width to x (not recommend)
					//this is like moving the milestone of each tile to the location chosen to render the tile, then fill the area with the tile
						//==> every single tile will be continuously and one by one rendered on the screen from the starting point with the starting tile
				//What is the map's (0,0) point? What is map's coord?
					//map's (0,0) point is the most up-left point of the map where is the location of the very first tile (with respect to the map)
					//there is no more tile rendered above and on the left of the starting point
					//this is the real (0,0) point with the player and all entities of the game
					//also, the map's occordinates are the place where every action occurs (real coords)
				//What is the window's (frame's) occordinates?
					//the view that is displayed on the screen also has Oxy coordiantes (like working with sizes and location of components on a JPanel)
					//this is sticked to the JFrame
					//(0,0) to (screen_width, screen_height) is the full window's coords system where game uses to render things (in full-screen mode, unit: pixels)
					//the method g.draw uses window's coords system to locate the milestone and area of tiles
				
				//***2 coords systems: map's and window's***
				
				//What is the starting point?
					//starting point is the point in the window's coords system where the map's (0,0) located
						//==> the map's (0,0) can be places on any locations with respect to the window's coords system
						//==> the window's (0,0) point can be any point of the map's coords
						//***renders thing from (0,0) to (screen_width, screen_height) in the window's coords system***
						//==>things which will be rendered are starting rendering from that point of map's coords until fill up the screen
				//Are the map's occordinates similar to a window's (frame's) occordinates?
					//sometimes they are, sometimes they aren't
					//if the starting point is sticked with the window's (0,0) - by default, the map's occordinates are similar to a window's (frame's) occordinates
						//==> only a proportion of the map from (0,0) to (screen_width, screen_height) (as the size of window) will be rendered throughout the game
					//unless, we can use camera to move around and make the relative location: window's (0,0) and a map's point change so that to render different parts of the map
				//How does camera work?
					//if the starting point is sticked with the window's (0,0) by default
						//the map starts from the point (0,0) of the window
						//a proportion of the map from (0,0) to (screen_width, screen_height) (as the size of window) will be rendered
					//if the starting point is sticked with different window's point
						//the map will start from that window's point, so the very first tile (map[0][0]) will be rendered at the that point
						//the window's (0,0) is now at the different location on the map
						//==> a proportion of the map from (- that point x, - that point y) to (screen_width - that point x, screen_height - that point y) will be rendered
					//saying, the camera is similar to that: we take the point (0,0) of the map away, which makes the whole map to move the same, while the view is still static, so we can see a different part of the map
				//==> to display the map at (x0, y0) we need to set the starting point at (-x0, -y0), then loop to render all the map by adding some tile's width and height
				//==> x0 = xOffset, y0 = yOffset
				
				
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));	//getTile: method below
			}
		}
		eManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.grassTile;
		
		Tile t =  Tile.tiles[map[y][x]];
		if(t == null) {
			System.out.println("Did not found tile at map[" + y + "][" + x + "]");
			return Tile.tiles[10];
		}
		return t;
	}
	
	private void loadWorld(String path) {	//this will read the text file world.txt and get necessary info
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");	//separated by space or newline
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		
		map = new int[height][width];
		
		for(int y = 0;y < height; y++) {
			for(int x = 0; x < width; x++) {
				map[y][x] = Utils.parseInt(tokens[4 + x + y * width]);
			}
		}
	}
	
	
	//	SETTERS, GETTERS

	public EntityManager geteManager() {
		return eManager;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
