package gfx;

import java.awt.image.BufferedImage;

public class ImgAssets {
	
	/*
	 * This class holds all the processed images of every single thing of the Game like player's image, zombie's image
	 * To get an img, just call ImgAssets.<name>
	 * */
	
	private static final int WIDTH = 32, HEIGHT = 32; //the size of the tile's images when cropping out of the sprite sheet
	private static final int PLAYER_WIDTH = 59, PLAYER_HEIGHT = 89; //the size of the player's images when cropping out of the sprite sheet

	
	public static BufferedImage menu;
	
	public static BufferedImage player_static_left, player_static_right, grass, water, dirt, sand, logo, tree1, gun_right, gun_left, bullet;
	
	
	public static BufferedImage[] start_btn = new BufferedImage[2], shop_btn = new BufferedImage[2], setting_btn = new BufferedImage[2];
	public static BufferedImage[] resume_btn = new BufferedImage[2], quit_btn = new BufferedImage[2];
	
	public static BufferedImage[] player_running_left = new BufferedImage[6];
	public static BufferedImage[] player_running_right = new BufferedImage[6];
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/red_ss.png"));
		player_static_left = sheet.crop(PLAYER_WIDTH *0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		player_static_right = sheet.crop(PLAYER_WIDTH *0, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		
		for(int i = 0; i < player_running_left.length; i++) {
			player_running_left[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		for(int i = 0; i < player_running_right.length; i++) {
			player_running_right[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 1, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/grass_tile.jpg"));
		grass = sheet.crop(0, 0, WIDTH, HEIGHT);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/water_tile.jpg"));
		water = sheet.crop(0, 0, WIDTH, HEIGHT);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/dirt_tile.png"));
		dirt = sheet.crop(0, 0, 512, 512);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/sand_tile.jpg"));
		sand = sheet.crop(0, 0, WIDTH, HEIGHT);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/logo.jpg"));
		logo = sheet.crop(0, 0, 632, 632);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/tree1.png"));
		tree1 = sheet.crop(0, 0, 127, 165);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/gun_right.png"));
		gun_right = sheet.crop(0, 0, 240, 240);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/gun_left.png"));
		gun_left = sheet.crop(0, 0, 240, 240);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/menu_background.png"));
		menu = sheet.crop(0, 0, 1920, 1080);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/start_btn.png"));
		start_btn[0] = sheet.crop(0, 0, 612, 237);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/start_btn_hv.png"));
		start_btn[1] = sheet.crop(0, 0, 612, 237);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/shop_btn.png"));
		shop_btn[0] = sheet.crop(0, 0, 417, 191);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/shop_btn_hv.png"));
		shop_btn[1] = sheet.crop(0, 0, 417, 191);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn.png"));
		setting_btn[0] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn_hv.png"));
		setting_btn[1] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn.png"));
		resume_btn[0] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn_hv.png"));
		resume_btn[1] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn.png"));
		quit_btn[0] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/setting_btn_hv.png"));
		quit_btn[1] = sheet.crop(0, 0, 171, 150);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/bullet_img.png"));
		bullet = sheet.crop(0, 0, 40, 40);
		
		//sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/tile.jpg"));
		//grass = sheet.crop(0, 0, width, height);
	}
}
