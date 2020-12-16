package states;

import java.awt.Graphics;

import ui.UIImageButton;
import ui.UIManager;

import gfx.ImgAssets;
import main.Handler;

public class MainMenuState extends State{
	private int mouseX, mouseY;
	private int windowW, windowH;
	private int backgoundW = ImgAssets.menu.getWidth(), backgroundH = ImgAssets.menu.getHeight();
	private long lastTime = 0;
	private long timer;
	
	private UIImageButton start, shop, setting;
	
	private UIManager uiManager;

	public MainMenuState(Handler handler) {
		super(handler);
		
		windowW = handler.getGameWidth();
		windowH = handler.getGameHeight();
		
		uiManager = new UIManager(handler);
		
		start = new UIImageButton(windowW / 2 - ImgAssets.start_btn[0].getWidth() / 2, windowH / 2, ImgAssets.start_btn[0].getWidth(), ImgAssets.start_btn[0].getHeight(), ImgAssets.start_btn) {
			public void onClick() {
				handler.getGame().setGameState();
			}};
			
		shop = new UIImageButton(windowW / 2 - ImgAssets.start_btn[0].getWidth() / 2, windowH / 2 + ImgAssets.start_btn[0].getHeight() + 20, ImgAssets.shop_btn[0].getWidth(), ImgAssets.shop_btn[0].getHeight(), ImgAssets.shop_btn) {
			public void onClick() {
				handler.getGame().setShopState();
			}};
		
		setting = new UIImageButton(windowW / 2 - 306 + 417 + 20, windowH / 2 + ImgAssets.start_btn[0].getHeight() + 20, ImgAssets.setting_btn[0].getWidth(), ImgAssets.setting_btn[0].getHeight(), ImgAssets.setting_btn) {
			public void onClick() {
				handler.getGame().setShopState();
			}};
				
		uiManager.addUIObject(start);
		uiManager.addUIObject(shop);
		uiManager.addUIObject(setting);
		
		setUI();
		
		//idk why but this method must be called 2 times in order to render the logo picture
//		handler.getGame().renderLogo();
//		handler.getGame().renderLogo();
//		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public void setUI() {
		handler.getMouseManager().setUIManager(uiManager);
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(ImgAssets.menu, (backgoundW - windowW) / 2, (backgroundH - windowH) / 2, backgoundW, backgroundH, null);
		
		uiManager.render(g);
	}
}
