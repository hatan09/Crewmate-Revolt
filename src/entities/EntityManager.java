package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.statics.StaticEntity;
import entities.statics.solid.SolidEntity;
import main.Handler;

public class EntityManager {

	private Handler handler;
	
	private ArrayList<Entity> entities;
	private ArrayList<SolidEntity> solidEntities;
	private Player player;
	
	Comparator<Entity> renderOrder = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getHeight() + a.y < b.getHeight() + b.y ) {
				return -1;
			}
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player) {
		this.player = player;
		
		entities = new ArrayList<Entity>();
		solidEntities = new ArrayList<SolidEntity>();
		
		entities.add(player);
		entities.addAll(solidEntities);
		
	}
	
	public void update() {		
		for(Entity e : entities) {
			e.update();
		}
		entities.sort(renderOrder);
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	
	//GETTERS & SETTERS
	
	
	public void addSolidEntity(SolidEntity se) {
		solidEntities.add(se);
		entities.add(se);
	}
	
	public void addMultipleSolidEntities(ArrayList<SolidEntity> ses) {
		solidEntities.addAll(ses);
		entities.addAll(ses);
	}
	
	public void removeSolidEntity(SolidEntity se) {
		solidEntities.remove(se);
		entities.remove(se);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addMultipleEntities(ArrayList<Entity> es) {
		entities.addAll(es);
	}
	
	public void removeEnity(Entity e) {
		entities.remove(e);
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public ArrayList<SolidEntity> getSolidEntities() {
		return solidEntities;
	}

	public void setSolidEntities(ArrayList<SolidEntity> solidEntities) {
		this.solidEntities = solidEntities;
	}
	
}
