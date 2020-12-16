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
	private ArrayList<StaticEntity> staticEntities;
	private ArrayList<Creature> creatures;
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
		staticEntities = new ArrayList<StaticEntity>();
		creatures = new ArrayList<Creature>();
		
		addCreature(player);
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
	
	
	public void addCreature(Creature cr) {
		creatures.add(cr);
		entities.add(cr);
	}
	
	public void addMultipleCreature(ArrayList<Creature> crs) {
		creatures.addAll(crs);
		entities.addAll(crs);
	}
	
	public void removeCreature(Creature cr) {
		creatures.remove(cr);
		entities.remove(cr);
	}
	
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
	
	public void addStaticEntity(StaticEntity se) {
		staticEntities.add(se);
		entities.add(se);
	}
	
	public void addMultipleStaticEntities(ArrayList<StaticEntity> ses) {
		staticEntities.addAll(ses);
		entities.addAll(ses);
	}
	
	public void removeStaticEntity(StaticEntity se) {
		staticEntities.remove(se);
		entities.remove(se);
	}
	
	public ArrayList<Creature> getCreatures() {
		return creatures;
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

	public ArrayList<SolidEntity> getSolidEntities() {
		return solidEntities;
	}
	
}
