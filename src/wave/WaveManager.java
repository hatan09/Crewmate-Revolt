package wave;

import java.util.Random;

import entities.creatures.Impostor;
import main.Handler;
import tiles.Tile;
import world.World;

public abstract class WaveManager {
	
	private static Handler handler;

	private static int wave = 0;
	private static int noRange = 0;
	private static int noMelee = 0;
	private static int noFast = 0;
	private static int noBoss = 0;
	private static int bossKill = 0, meleeKill = 0, fastKill = 0, rangeKill = 0;
	private static boolean boss = false;
	
	private static Random r;
	
	public static void init(int wave, Handler handler) {
		WaveManager.wave = wave;
		WaveManager.handler = handler;
		
		r = new Random();
	}
	
	public static void nextWave() {
		wave++;
		
		boss = (wave%10 == 0);
		
		noFast = 4*(wave/25);
		noRange = wave/25;
		noMelee = wave - noFast - noRange;
		
		for(int i = 0; i < noMelee; i++) {
			handler.getWorld().geteManager().addCreature(new Impostor(handler, randomX(), randomY(), World.PLAYER_WIDTH, World.PLAYER_HEIGHT));
		}
		
	}
	
	public static void update() {
		if(noMelee == 0 && noRange == 0 && noFast == 0 && noBoss == 0) nextWave();
	}
	
	public static void removeMelee() {
		noMelee--;
		meleeKill++;
	}
	
	public static void removeRange() {
		noRange--;
		rangeKill++;
	}
	
	public static void removeRunner() {
		noFast--;
		fastKill++;
	}
	
	public static void removeBoss() {
		noBoss--;
		bossKill++;
	}

	public static int randomX() {
		return r.nextInt((handler.getWorld().getWidth() - 4 ) * Tile.TILE_WIDTH);
	}
	
	public static int randomY() {
		return r.nextInt((handler.getWorld().getHeight() - 4 ) * Tile.TILE_HEIGHT);
	}
	
	//GETTERS
	
	public static int getNoMelee() {
		return noMelee;
	}


	public static int getNoFast() {
		return noFast;
	}


	public static int getWave() {
		return wave;
	}

	public static int getNoRange() {
		return noRange;
	}

	public static boolean isBoss() {
		return boss;
	}
	
	public static void setHandler(Handler handler) {
		WaveManager.handler = handler;
	}
	
}
