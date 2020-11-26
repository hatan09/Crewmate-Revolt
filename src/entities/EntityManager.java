package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.statics.StaticEntity;
import entities.statics.solid.SolidEntity;
import main.Handler;

public class EntityManager {

	private Handler handler;
	private ArrayList<Creature> creatures;
	private ArrayList<StaticEntity> staticEntities;
	private ArrayList<SolidEntity> solidEntities;
	private Player player;
	
	public EntityManager(Handler handler, Player player) {
		this.player = player;
		
		creatures = new ArrayList<Creature>();
		staticEntities = new ArrayList<StaticEntity>();
		solidEntities = new ArrayList<SolidEntity>();
		
		addCreature(player);
	}
	
	public void update() {
		for(Entity e : creatures) {
			e.update();
		}
		for(Entity e: staticEntities) {
			e.update();
		}
		for(Entity e : solidEntities) {
			e.update();
		}
	}
	
	public void render(Graphics g) {
		for(Entity e : creatures) {
			e.render(g);
		}
		for(Entity e: staticEntities) {
			e.render(g);
		}
		for(Entity e : solidEntities) {
			e.render(g);
		}
	}
	
	
	//GETTERS & SETTERS
	
	
	public void addCreature(Creature c) {
		creatures.add(c);
	}
	
	public void addMultipleCreatures(Creature[] cs) {
		for(Creature c : cs) creatures.add(c);
	}
	
	public void addStaticEntity(StaticEntity se) {
		staticEntities.add(se);
	}
	
	public void addMultipleStaticEntities(StaticEntity[] ses) {
		for(StaticEntity se : ses) staticEntities.add(se);
	}
	
	public void addSolidEntity(SolidEntity se) {
		solidEntities.add(se);
	}
	
	public void addMultipleSolidEntities(SolidEntity[] ses) {
		for(SolidEntity se : ses) solidEntities.add(se);
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

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}

	public ArrayList<StaticEntity> getStaticEntities() {
		return staticEntities;
	}

	public void setStaticEntities(ArrayList<StaticEntity> staticEntities) {
		this.staticEntities = staticEntities;
	}

	public ArrayList<SolidEntity> getSolidEntities() {
		return solidEntities;
	}

	public void setSolidEntities(ArrayList<SolidEntity> solidEntities) {
		this.solidEntities = solidEntities;
	}
	
}
