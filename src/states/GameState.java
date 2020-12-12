package states;

import java.awt.Graphics;

import gfx.ImgAssets;
import main.Handler;
import ui.UIImageButton;
import ui.UIManager;
import world.World;

public class GameState extends State{

	/*
	 * Update and render every component belonging to this State: GameState
	 * */
	
	//components belong to this State
	private World world;			//when game is not paused

	
	//components belong to UI, which are showed when game is paused
	private UIImageButton resume, quit;
	
	private boolean isPaused = false;
	
	public GameState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		
		quit = new UIImageButton(handler.getGameWidth() / 2 - ImgAssets.quit_btn[0].getWidth() / 2, handler.getGameHeight() / 2 - ImgAssets.quit_btn[0].getHeight(), 
				ImgAssets.quit_btn[0].getWidth(), ImgAssets.quit_btn[0].getHeight(), ImgAssets.quit_btn) {
			@Override
			public void onClick() {
				handler.getGame().setMenuState();
			}
		};
		
		uiManager.addUIObject(quit);

		
		//new world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	public void reset() {
		handler.getKeyManager().esc = false;
		world.reset("res/worlds/world1.txt");
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		if(isPaused)
			uiManager.render(g);
	}

	@Override
	public void update() {
		if(handler.getKeyManager().esc) isPaused = true;
		else isPaused = false;
		
		if(!isPaused)
			world.update();
		//else {
			//uiManager.update();
		//}
	}
	

}
