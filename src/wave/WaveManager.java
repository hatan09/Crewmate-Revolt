package wave;

import java.util.Random;

import entities.creatures.impostors.Fast;
import entities.creatures.impostors.Impostor;
import entities.creatures.impostors.Melee;
import entities.creatures.impostors.Range;
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
	private static int gpEarned = 0;
	private static boolean boss = false;
	
	private static Random r;
	
	public static void init(int wave, Handler handler) {
		WaveManager.wave = 10 * ((wave - 10) / 10);
		if(WaveManager.wave < 0) WaveManager.wave = 0;
		WaveManager.handler = handler;
		
		System.out.println(wave);
		System.out.println(WaveManager.wave);
		
		noRange = 0;
		noMelee = 0;
		noFast = 0;
		noBoss = 0;
		
		r = new Random();
	}
	
	public static void nextWave() {
		System.out.println("Next wave");
		wave++;
		
		//boss = (wave%25 == 0);
		
		noFast = wave / 5;
		noRange = wave / 20;
		noMelee = wave / 2 - noFast - noRange;
		noMelee = (noMelee < 1)? 1 : noMelee;
		
		for(int i = 0; i < noMelee; i++) {
			handler.getWorld().geteManager().addCreature(new Melee(handler, randomX(), randomY(), World.PLAYER_WIDTH, World.PLAYER_HEIGHT));
		}
		
		for(int i = 0; i < noRange; i++) {
			handler.getWorld().geteManager().addCreature(new Range(handler, randomX(), randomY(), World.PLAYER_WIDTH, World.PLAYER_HEIGHT));
		}
		
		for(int i = 0; i < noFast; i++) {
			handler.getWorld().geteManager().addCreature(new Fast(handler, randomX(), randomY(), World.FAST_WIDTH, World.FAST_HEIGHT));
		}
		
	}
	
	public static void update() {
		if(noMelee == 0 && noRange == 0 && noFast == 0 && noBoss == 0) nextWave();
	}
	
	public static void removeMelee() {
		noMelee--;
		meleeKill++;
		gpEarned = gpEarned + 3;
	}
	
	public static void removeRange() {
		noRange--;
		rangeKill++;
		gpEarned = gpEarned + 5;
	}
	
	public static void removeFast() {
		noFast--;
		fastKill++;
		gpEarned = gpEarned + 8;
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

	public static int getMeleeKill() {
		return meleeKill;
	}

	public static int getFastKill() {
		return fastKill;
	}

	public static int getRangeKill() {
		return rangeKill;
	}

	public static int getGpEarned() {
		return gpEarned;
	}

	public static boolean isBoss() {
		return boss;
	}
	
	public static void setHandler(Handler handler) {
		WaveManager.handler = handler;
	}
	
}
