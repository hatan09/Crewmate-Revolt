package gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index = 0;
	private long lastTime, timer = 0;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] f) {
		this.speed = speed;
		this.frames = f;
	}
	
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer >= speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public void reset() {
		timer = 0;
		index = 0;
	}
	
	public BufferedImage getFrame() {
		return frames[index];
	}
}
