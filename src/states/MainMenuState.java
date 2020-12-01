package states;

import java.awt.Graphics;
import java.util.Random;

import ui.UIImageButton;
import ui.UIManager;

import gfx.ImgAssets;
import main.Handler;

public class MainMenuState extends State{
	private int mouseX, mouseY;
	private int windowW, windowH;
	private int backgoundW = 1920, backgroundH = 1080;
	private Random r;
	private long lastTime = 0;
	private long timer;
	private int ix;
	private int iy;
	
	private UIManager uiManager;

	public MainMenuState(Handler handler) {
		super(handler);
		
		r = new Random();
		
		//idk why but this method must be called 2 times in order to render the picture
		handler.getGame().renderLogo();
		handler.getGame().renderLogo();
		
		uiManager = new UIManager(handler);
		
		uiManager.addUIObject(new UIImageButton(200, 200, 150, 50, ImgAssets.start_btn) {
			public void onClick() {
				State.setState(handler.getGame().gameState);
			}
		});
		
		handler.getMouseManager().setUIManager(uiManager);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		windowW = handler.getGameWidth();
		windowH = handler.getGameHeight();
	}

	@Override
	public void update() {
		//mouse input
		mouseX = handler.getMouseManager().getMouseX();
		mouseY = handler.getMouseManager().getMouseY();
		
		uiManager.update();
		
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(timer >= 400) {
			ix = r.nextInt(10);
			iy = r.nextInt(10);
			timer = 0;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(ImgAssets.menu, - ix - (backgoundW - windowW) / 2, - iy - (backgroundH - windowH) / 2, backgoundW + 10, backgroundH + 10, null);
		
		uiManager.render(g);
	}
}
