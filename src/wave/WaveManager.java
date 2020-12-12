package wave;

public abstract class WaveManager {

	private static int wave;
	private static int noRange;
	private static int noMelee;
	private static int noFast;
	private static boolean boss = false;
	
	public static void nextWave() {
		wave++;
		
		boss = (wave%10 == 0);
		
		noFast = 4*wave/25;
		noRange = wave/25;
		noMelee = wave - noFast - noRange;
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
	
	
}
