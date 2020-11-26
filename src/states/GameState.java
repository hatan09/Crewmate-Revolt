package states;

import java.awt.Graphics;




import main.Handler;
import world.World;

public class GameState extends State{

	/*
	 * Update and render every component belonging to this State: GameState
	 * */
	
	//components belong to this State
	private World world;

	
	public GameState(Handler handler) {
		super(handler);
		
		//new world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}

	@Override
	public void render(Graphics g) {
		//map
		world.render(g);
	}

	@Override
	public void update() {
		//map
		world.update();
	}
	

}
