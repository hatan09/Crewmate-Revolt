package data;

import utils.Utils;

public class User {

	private static String encodedID;
	private static String username;
	private static int highestWave;
	private static int gp;
	private static int bossKill;
	private static int rangeKill;
	private static int fastKill;
	private static int meleeKill;
	private static int[] ownGuns;
	
	
	//GETTERS & SETTERS
	public static int ID() {
		return Utils.decodeInt(encodedID);
	}
	public static void setEncodedID(int num) {
		encodedID = Utils.encodeInt(num);
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		User.username = username;
	}
	public static int getHighestWave() {
		return highestWave;
	}
	public static void setHighestWave(int highestWave) {
		User.highestWave = highestWave;
	}
	public static int getGp() {
		return gp;
	}
	public static void setGp(int gp) {
		User.gp = gp;
	}
	public static int getBossKill() {
		return bossKill;
	}
	public static void setBossKill(int bossKill) {
		User.bossKill = bossKill;
	}
	public static int getRangeKill() {
		return rangeKill;
	}
	public static void setRangeKill(int rangeKill) {
		User.rangeKill = rangeKill;
	}
	public static int getFastKill() {
		return fastKill;
	}
	public static void setFastKill(int fastKill) {
		User.fastKill = fastKill;
	}
	public static int getMeleeKill() {
		return meleeKill;
	}
	public static void setMeleeKill(int meleeKill) {
		User.meleeKill = meleeKill;
	}
	public static int[] getOwnGuns() {
		return ownGuns;
	}
	public static void setOwnGuns(int[] ownGuns) {
		User.ownGuns = ownGuns;
	}
	
	
}
