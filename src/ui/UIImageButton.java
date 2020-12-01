package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{

	private BufferedImage[] frames;
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] frames) {
		super(x, y, width, height);
		this.frames = frames;
	}

	@Override
	public void onClick() {
		//override this when creating new button in order to fit to the button's purpose
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(hovered)
			g.drawImage(frames[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(frames[0], (int) x, (int) y, width, height, null);
		
	}

}
