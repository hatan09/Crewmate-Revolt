package entities.statics;

import java.awt.Graphics;

import gfx.ImgAssets;
import main.Handler;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		box.setBounds(20, 82, 87, 83);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImgAssets.tree1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		
		g.fillRect((int) (x + box.x - handler.getCamera().getxOffset()), (int) (y + box.y - handler.getCamera().getyOffset()), box.width, box.height);
	}

}
