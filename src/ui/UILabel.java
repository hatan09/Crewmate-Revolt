package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UILabel extends UIObject{
	
	private BufferedImage img;

	public UILabel(float x, float y, int width, int height, BufferedImage img) {
		super(x, y, width, height);
		
		this.img = img;
	}

	@Override
	public void onClick() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, width, height, null);
	}

}
