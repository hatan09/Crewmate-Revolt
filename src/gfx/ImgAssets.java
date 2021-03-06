package gfx;

import java.awt.image.BufferedImage;

public class ImgAssets {
	
	/*
	 * This class holds all the processed images of every single thing of the Game like player's image, impostor's image
	 * To get an img or an img array, just call ImgAssets.<name> as they are all public and static
	 * */
	
	private static final int WIDTH = 32, HEIGHT = 32; //the size of the tile's images when cropping out of the sprite sheet
	
	public static final int PLAYER_WIDTH = 59, PLAYER_HEIGHT = 89; //the size of the player's images when cropping out of the sprite sheet
	
	private static final int FAST_WIDTH = 75, FAST_HEIGHT = 89; //the size of the player's images when cropping out of the sprite sheet
	
	private static final int FAST_ATK_WIDTH = 95, FAST_ATK_HEIGHT = 89; //the size of the player's images when cropping out of the sprite sheet

	
	//background
	public static BufferedImage menu, shop;
	
	
	//tiles & statics
	//public static BufferedImage grass, water, dirt, sand, logo, tree1;
	public static BufferedImage dirt, dirt1, dirt2, grass, grass1, stone, stone1, water, water1, water2, logo, tree1;
	
	
	//bullet
	public static BufferedImage bullet;
	
	
	//weapons' images: 0 = left, 1 = right
	public static BufferedImage[] desert_eagle = new BufferedImage[2];	//1
	public static BufferedImage[] double_de = new BufferedImage[2];		//2
	public static BufferedImage[] uzi = new BufferedImage[2];			//3
	public static BufferedImage[] ump = new BufferedImage[2];			//4
	public static BufferedImage[] m4a1 = new BufferedImage[2];			//5
	public static BufferedImage[] m16 = new BufferedImage[2];			//6
	public static BufferedImage[] ak47 = new BufferedImage[2];			//7
	
	public static BufferedImage[] anaconda = new BufferedImage[2];		//0 (not in db)
	public static BufferedImage[] knife = new BufferedImage[2];
	
	
	//btn
	//menu
	public static BufferedImage[] start_btn = new BufferedImage[2], shop_btn = new BufferedImage[2], setting_btn = new BufferedImage[2];
	public static BufferedImage[] resume_btn = new BufferedImage[2];
	
	//game
	public static BufferedImage[] quit_btn = new BufferedImage[2]; 
	
	//shop
	public static BufferedImage[] back_btn = new BufferedImage[2], buy_btn = new BufferedImage[2], equip_btn = new BufferedImage[2];
	public static BufferedImage shop_de, shop_double, shop_uzi, shop_ump, shop_m4a1, shop_m16, shop_ak, shop_cs, shop_gp, shop_price, shop_circle, shop_own, shop_equiped;
	
	
	//characters
	public static BufferedImage[] player_running_left = new BufferedImage[6];
	public static BufferedImage[] player_running_right = new BufferedImage[6];
	public static BufferedImage player_static_left, player_static_right;
	
	public static BufferedImage[] black_running_left = new BufferedImage[6];
	public static BufferedImage[] black_running_right = new BufferedImage[6];
	public static BufferedImage black_static_left, black_static_right;
	
	public static BufferedImage[] blue_running_left = new BufferedImage[6];
	public static BufferedImage[] blue_running_right = new BufferedImage[6];
	public static BufferedImage blue_static_left, blue_static_right;
	
	public static BufferedImage[] fast_running_left = new BufferedImage[6];
	public static BufferedImage[] fast_running_right = new BufferedImage[6];
	public static BufferedImage fast_static_left, fast_static_right, fast_atk_left, fast_atk_right;
	
	
	
	public static void init() {
		//red (player)
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/red_ss.png"));
		player_static_left = sheet.crop(PLAYER_WIDTH *0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		player_static_right = sheet.crop(PLAYER_WIDTH *0, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		for(int i = 0; i < player_running_left.length; i++) {
			player_running_left[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		for(int i = 0; i < player_running_right.length; i++) {
			player_running_right[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 1, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		//black (range)
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/black_ss.png"));
		black_static_left = sheet.crop(PLAYER_WIDTH *0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		black_static_right = sheet.crop(PLAYER_WIDTH *0, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		for(int i = 0; i < blue_running_left.length; i++) {
			black_running_left[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		for(int i = 0; i < black_running_right.length; i++) {
			black_running_right[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 1, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		//blue (melee)
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/blue_ss.png"));
		blue_static_left = sheet.crop(PLAYER_WIDTH *0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		blue_static_right = sheet.crop(PLAYER_WIDTH *0, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		for(int i = 0; i < blue_running_left.length; i++) {
			blue_running_left[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 0, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		for(int i = 0; i < blue_running_right.length; i++) {
			blue_running_right[i] = sheet.crop(PLAYER_WIDTH * (i + 1), PLAYER_HEIGHT * 1, PLAYER_WIDTH, PLAYER_HEIGHT);
		}
		
		//green (fast)
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/green_ss.png"));
		fast_static_left = sheet.crop(FAST_WIDTH *0, 0, FAST_WIDTH, FAST_HEIGHT);
		fast_static_right = sheet.crop(FAST_WIDTH *0, FAST_HEIGHT, FAST_WIDTH, FAST_HEIGHT);
		
		for(int i = 0; i < fast_running_left.length; i++) {
			fast_running_left[i] = sheet.crop(FAST_WIDTH * (i + 1), FAST_HEIGHT * 0, FAST_WIDTH, FAST_HEIGHT);
		}
		
		for(int i = 0; i < fast_running_right.length; i++) {
			fast_running_right[i] = sheet.crop(FAST_WIDTH * (i + 1), FAST_HEIGHT * 1, FAST_WIDTH, FAST_HEIGHT);
		}
		
		fast_atk_left = sheet.crop(FAST_WIDTH * (fast_running_left.length + 1), FAST_HEIGHT * 0, FAST_ATK_WIDTH, FAST_ATK_HEIGHT);
		fast_atk_right = sheet.crop(FAST_WIDTH * (fast_running_right.length + 1), FAST_HEIGHT * 1, FAST_ATK_WIDTH, FAST_ATK_HEIGHT);
		
		
		//tiles
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/tiles.jpg"));
		
		dirt = sheet.crop (WIDTH, 0, WIDTH, HEIGHT);
		
		dirt1 = sheet.crop (WIDTH * 2, 0, WIDTH, HEIGHT);
		
		dirt2 = sheet.crop (WIDTH * 3, 0, WIDTH, HEIGHT);
		
		grass = sheet.crop (0, HEIGHT,  WIDTH, HEIGHT);
		
		grass1 = sheet.crop (WIDTH , HEIGHT,  WIDTH, HEIGHT);
		
		stone = sheet.crop (WIDTH * 2 , HEIGHT,  WIDTH, HEIGHT);
		
		stone1 = sheet.crop (WIDTH * 3, HEIGHT,  WIDTH, HEIGHT);
		
		water = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		
		water1 = sheet.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		
		water2 = sheet.crop(WIDTH * 2 , HEIGHT * 2, WIDTH, HEIGHT);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/logo.jpg"));
		logo = sheet.crop(0, 0, 632, 632);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/tree1.png"));
		tree1 = sheet.crop(0, 0, 127, 165);
		
		
		//background
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/menu_background.png"));
		menu = sheet.crop(0, 0, 1920, 1080);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/shop_background.png"));
		shop = sheet.crop(0, 0, 1920, 1080);
		
		
		//buttons
		//menu
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
		
		//shop
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/back_btn.png"));
		back_btn[0] = sheet.crop(0, 0, 206, 174);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/back_btn_hv.png"));
		back_btn[1] = sheet.crop(0, 0, 206, 174);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/buy_btn.png"));
		buy_btn[0] = sheet.crop(0, 0, 432, 189);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/buy_btn_hv.png"));
		buy_btn[1] = sheet.crop(0, 0, 432, 189);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/equip_btn.png"));
		equip_btn[0] = sheet.crop(0, 0, 282, 130);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/equip_btn_hv.png"));
		equip_btn[1] = sheet.crop(0, 0, 282, 130);
			//slots
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/desert_eagle.png"));
		shop_de = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/double_de.png"));
		shop_double = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/uzi.png"));
		shop_uzi = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ump.png"));
		shop_ump = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m4a1.png"));
		shop_m4a1 = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m16.png"));
		shop_m16 = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ak47.png"));
		shop_ak = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/coming_soon.png"));
		shop_cs = sheet.crop(0, 0, 382, 185);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/gp.png"));
		shop_gp = sheet.crop(0, 0, 452, 58);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/price.png"));
		shop_price = sheet.crop(0, 0, 452, 58);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/circle.png"));
		shop_circle = sheet.crop(0, 0, 323, 155);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/owned.png"));
		shop_own = sheet.crop(0, 0, 247, 95);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/equiped.png"));
		shop_equiped = sheet.crop(0, 0, 184, 67);
		
		
		//game
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/quit_btn.png"));
		quit_btn[0] = sheet.crop(0, 0, 421, 181);

		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/quit_btn_hv.png"));
		quit_btn[1] = sheet.crop(0, 0, 421, 181);
		
		
		//bullet
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/bullet_img.png"));
		bullet = sheet.crop(0, 0, 40, 40);
		
		
		//guns
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/desert_eagle_left.png"));
		desert_eagle[0] = sheet.crop(0, 0, 240, 240);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/desert_eagle_right.png"));
		desert_eagle[1] = sheet.crop(0, 0, 240, 240);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ump_left.png"));
		ump[0] = sheet.crop(0, 0, 254, 254);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ump_right.png"));
		ump[1] = sheet.crop(0, 0, 254, 254);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m4a1_left.png"));
		m4a1[0] = sheet.crop(0, 0, 340, 340);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m4a1_right.png"));
		m4a1[1] = sheet.crop(0, 0, 340, 340);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m16_left.png"));
		m16[0] = sheet.crop(0, 0, 310, 310);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/m16_right.png"));
		m16[1] = sheet.crop(0, 0, 310, 310);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ak47_left.png"));
		ak47[0] = sheet.crop(0, 0, 320, 320);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/ak47_right.png"));
		ak47[1] = sheet.crop(0, 0, 320, 320);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/double_de_left.png"));
		double_de[0] = sheet.crop(0, 0, 260, 260);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/double_de_right.png"));
		double_de[1] = sheet.crop(0, 0, 260, 260);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/uzi_left.png"));
		uzi[0] = sheet.crop(0, 0, 200, 200);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/uzi_right.png"));
		uzi[1] = sheet.crop(0, 0, 200, 200);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/anaconda_left.png"));
		anaconda[0] = sheet.crop(0, 0, 310, 310);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/anaconda_right.png"));
		anaconda[1] = sheet.crop(0, 0, 310, 310);
		
		//knife
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/knife_left.png"));
		knife[0] = sheet.crop(0, 0, 188, 188);
		
		sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/knife_right.png"));
		knife[1] = sheet.crop(0, 0, 188, 188);
		
		//sheet = new SpriteSheet(ImageLoader.loadStaticImage("/img/tile.jpg"));
		//grass = sheet.crop(0, 0, width, height);
	}
}
