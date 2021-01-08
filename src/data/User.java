package data;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.SQL_Communication;
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
	private static ArrayList<Integer> ownGuns = new ArrayList<Integer>();
	
	private static int priGun, secGun;
	
	public static void init() {
		retrieveData();
		retrieveOwnGuns();
		retrieveEquipGuns();
	}
	
	public static void update() {
		updateStatistics();
		updateGP();
		updateHighestWave();
	}
	
	public static void updateStatistics() {
		if(!SQL_Communication.updateStatistics(getID(), rangeKill, fastKill, meleeKill)) {
			JOptionPane.showMessageDialog(null, "Failed to update", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateGP() {
		if(!SQL_Communication.updateGP(getID(), gp)) {
			JOptionPane.showMessageDialog(null, "Failed to update", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateHighestWave() {
		if(!SQL_Communication.updateHighestWave(getID(), highestWave)) {
			JOptionPane.showMessageDialog(null, "Failed to update", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateEquipGuns() {
		if(!SQL_Communication.updateEquipGuns(getID(), priGun, secGun)) {
			JOptionPane.showMessageDialog(null, "Failed to update", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void newOwnGun(int g_id) {
		if(!SQL_Communication.createOwnGun(getID(), g_id)) {
			JOptionPane.showMessageDialog(null, "Failed to update", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		retrieveOwnGuns();
	}
	
	public static void retrieveData() {
		ArrayList<String> data = SQL_Communication.getPlayerInfo(getID());
		setHighestWave(Utils.parseInt(data.get(0)));
		gp = Utils.parseInt(data.get(1));
		setMeleeKill(Utils.parseInt(data.get(2)));
		setFastKill(Utils.parseInt(data.get(3)));
		setRangeKill(Utils.parseInt(data.get(4)));
	}
	
	public static void retrieveOwnGuns() {
		ownGuns.clear();
		ownGuns.addAll(SQL_Communication.getOwnGuns(getID()));
	}
	
	public static void retrieveEquipGuns() {
		priGun = SQL_Communication.getEquipGuns(getID())[0];
		secGun = SQL_Communication.getEquipGuns(getID())[1];
	}
	
	//GETTERS & SETTERS
	public static int getID() {
		return Utils.decodeInt(encodedID);
	}
	
	public static void setID(int num) {
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
	
	public static ArrayList<Integer> getOwnGuns() {
		return ownGuns;
	}
	
	public static void addOwnGuns(int g_id) {
		newOwnGun(g_id);
	}
	
	
	public static void setPriGun(int g_id) {
		priGun = g_id;
		updateEquipGuns();
	}
	
	public static int getPriGun() {
		return priGun;
	}
	
	public static void setSecGun(int g_id) {
		secGun = g_id;
		updateEquipGuns();
	}
	
	public static int getSecGun() {
		return secGun;
	}
	
	public static void clearData() {
		encodedID = "";
		username = "";
		highestWave = 0;
		gp = 0;
		bossKill = 0;
		rangeKill = 0;
		fastKill = 0;
		meleeKill = 0;
		ownGuns.clear();
	}
	
}
