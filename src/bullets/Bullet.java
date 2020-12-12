package bullets;

import main.Handler;

public abstract class Bullet {

	protected Handler handler;
	
	public Bullet(Handler handler) {
		this.handler = handler;
	}
}
