package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_Connector.SQLServer_Connector;
import database.services.Initialize;
import database.services.LoginServices;
import database.services.RegServices;

public class SQL_Communication implements RegServices, LoginServices, Initialize{

	static Connection conn = SQLServer_Connector.getJDBCConnection();
	
	public static boolean checkConnection() {
		String query = "SELECT * FROM guns";
		boolean exist = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}

	@Override
	public boolean checkUsername(String username) {
		String query = "SELECT username FROM players WHERE username = ?";
		boolean exist = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}

	@Override
	public boolean verifyAuthentication(String username, String password) {
		String query = "SELECT p_id FROM players WHERE username = ? and password = ?";
		boolean exist = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}

	@Override
	public ArrayList<String> getGuns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getPlayerInfo() {
		
	}

	@Override
	public int[] getOwnGuns() {
		// TODO Auto-generated method stub
		return null;
	}
}
