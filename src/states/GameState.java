package states;

import java.awt.Font;
import java.awt.Graphics;

import gfx.ImgAssets;
import main.Handler;
import ui.UIImageButton;
import ui.UIManager;
import ui.UIString;
import wave.WaveManager;
import world.World;

public class GameState extends State{

	/*
	 * Update and render every component belonging to this State: GameState
	 * */
	
	//components belong to this State
	private World world;			//when game is not paused

	
	//components belong to UI, which are showed when game is paused
	private UIImageButton resume, quit;
	
	private UIManager gameUIManager;
	
	private UIString ammo, amount;
	
	private boolean isPaused = false;
	
	public GameState(Handler handler) {
		super(handler);
		
		//UI when game is paused
		uiManager = new UIManager(handler);

		quit = new UIImageButton(handler.getGameWidth() / 2 - ImgAssets.quit_btn[0].getWidth() / 2, handler.getGameHeight() / 2 - ImgAssets.quit_btn[0].getHeight(), 
				ImgAssets.quit_btn[0].getWidth(), ImgAssets.quit_btn[0].getHeight(), ImgAssets.quit_btn) {
			@Override
			public void onClick() {
				handler.getWorld().gameOver();
			}
		};
		
		uiManager.addUIObject(quit);

		//UI while playing
		gameUIManager = new UIManager(handler);
		
		ammo = new UIString(1300, 1000, 0, 0, new Font("Arial", Font.BOLD, 50)) {
			@Override
			public void update() {
				super.update();
				setString(handler.getWorld().getPlayer().getcurrentGun().getName() + " : " + handler.getWorld().getPlayer().getcurrentGun().getCurrAmmo() + " / " + handler.getWorld().getPlayer().getcurrentGun().getMaxAmmo());
			}
		};
		
		amount = new UIString(800, 40, 0, 0, new Font("Arial", Font.BOLD, 50)) {
			@Override
			public void update() {
				super.update();
				setString(Integer.toString(WaveManager.getNoFast() + WaveManager.getNoMelee() + WaveManager.getNoRange()));
			}
		};
		
		gameUIManager.addUIObject(ammo);
		gameUIManager.addUIObject(amount);
		
		//new world
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	public void reset() {
		handler.getKeyManager().esc = false;
		world.reset("res/worlds/world1.txt");
	}


	@Override
	public void update() {
		isPaused = handler.getKeyManager().esc;
		
		if(!isPaused) {
			handler.getMouseManager().setUIManager(gameUIManager);
			world.update();
			gameUIManager.update();
		}
		else {
			handler.getMouseManager().setUIManager(uiManager);
			uiManager.update();
		}
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
		gameUIManager.render(g);
		if(isPaused)
			uiManager.render(g);
		
	}
	

}
