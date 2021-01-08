package entities.statics.solid;

import java.awt.Graphics;

import gfx.ImgAssets;
import main.Handler;

public class Rock extends SolidEntity{

	public Rock(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImgAssets.tree1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}

}
