package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	/*
	 * This class is used to crop an img which is a sprite sheet
	 * Give it an img of type BufferedImage (of course static img) and get the cropped img by assigning it to a var of type BufferedImage
	 * Also, to crop, give it occordinates and sizes of the img wanted to get
	 * */

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
