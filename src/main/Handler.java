package main;

import java.awt.MouseInfo;
import java.awt.Point;

import gfx.Camera;
import input.KeyManager;
import world.World;

public class Handler {
	private static Handler handler;

	private Game game;
	
	private World world;
	
	private Handler(Game game) {
		this.game = game;
	}
	
	public static Handler createHandler(Game game) {
		if(handler == null) {
			handler = new Handler(game);
		}
		return handler;
	}
	
	public int getGameWidth() {
		return game.getWidth();
	}
	
	public int getGameHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public Point getCurorPosition() {
		return MouseInfo.getPointerInfo().getLocation();
	}
	
	//SETTERS, GETTERS
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	
}

