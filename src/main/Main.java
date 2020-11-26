package main;

import utils.Utils;

public class Main {
	static Frame_Login fr_login;
	static Game game;
	public static void main(String[] args) {
		Utils.setPath();
		fr_login = new Frame_Login();
		fr_login.setVisible(true);
		
		game = new Game();
	}
}
