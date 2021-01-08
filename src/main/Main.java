package main;

import database.SQL_Communication;
import utils.Utils;

public class Main {
	static Frame_Login fr_login;
	static Game game;
	public static void main(String[] args) {
		System.out.println(SQL_Communication.checkConnection());
		
		Utils.setPath();
		fr_login = new Frame_Login();
		fr_login.setVisible(true);
		
		game = new Game();
	}
}
