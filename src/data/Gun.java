package data;

public class Gun {

	private int id;
	private String name;
	private int maxAmmo;
	private int currAmmo;
	private int speed;
	private int reloadTime;
	private int dmg;
	
	public Gun(int id, String name, int maxAmmo, int speed, int reloadTime, int dmg) {
		
	}

	
	//SETTER & GETTERS
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public int getCurrAmmo() {
		return currAmmo;
	}

	public int getSpeed() {
		return speed;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public int getDmg() {
		return dmg;
	}
}
