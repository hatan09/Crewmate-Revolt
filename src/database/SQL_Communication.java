package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC_Connector.SQLServer_Connector;
import data.Gun;
import data.User;

public abstract class SQL_Communication {

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

    //close
    public static void closeConnection() {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static boolean checkUsername(String username) {
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
	
	public static boolean Register(String userName, String name, String pass) {
		String query1 = "INSERT INTO players (username, name, password) VALUES (?, ?, ?)";
		String query2 = "INSERT INTO own_guns (p_id, g_id) VALUES (?, 1)";
		String get_id = "SELECT p_id FROM players WHERE username = ?";
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query1);
			pst.setString(1, userName);
			pst.setString(2, name);
			pst.setString(3, pass);
			int rs = pst.executeUpdate();
			PreparedStatement get_id_query = conn.prepareStatement(get_id);
			get_id_query.setString(1, userName);
			ResultSet set = get_id_query.executeQuery();
			if(rs == 1) {	//check if the creation of new player successful, if yes
				if(set.next()) {	//check if able to get P_ID of new player, if yes
					PreparedStatement create_1st_ownGun = conn.prepareStatement(query2);
					create_1st_ownGun.setString(1, Integer.toString(set.getInt("p_id")));
					create_1st_ownGun.executeUpdate();
					success = true;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}

	public static boolean verifyAuthentication(String username, String password) {
		String query = "SELECT p_id FROM players WHERE username = ? AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
		boolean exist = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
			if(exist) setID(rs.getInt("p_id"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}
	
	private static void setID(int id) {
		User.setID(id);
	}

	public static ArrayList<String> getPlayerInfo(int p_id) {
		String query = "SELECT * FROM players WHERE p_id = ?";
		ArrayList<String> data = new ArrayList<String>();
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, p_id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				data.add(Integer.toString(rs.getInt("highest_wave")));
				data.add(Integer.toString(rs.getInt("gp")));
				data.add(Integer.toString(rs.getInt("melee_kills")));
				data.add(Integer.toString(rs.getInt("fast_kills")));
				data.add(Integer.toString(rs.getInt("range_kills")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return data;
	}
	
	public static ArrayList<Integer> getOwnGuns(int p_id) {
		String query = "SELECT g_id FROM own_guns WHERE p_id = ?";
		ArrayList<Integer> owns = new ArrayList<Integer>();
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, p_id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				owns.add(rs.getInt("g_id"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return owns;
	}
	
	public static int[] getEquipGuns(int p_id) {
		String query = "SELECT pri, sec FROM players WHERE p_id = ?";
		int[] equip = new int[2];
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, p_id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				equip[0] = rs.getInt("pri");
				equip[1] = rs.getInt("sec");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return equip;
	}
	
	public static Gun getGuns(int g_id) {
		String query = "SELECT * FROM guns WHERE g_id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, g_id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Gun gun = new Gun(
									g_id,
									rs.getString("name"),
									rs.getInt("price"),
									(rs.getString("type").equals("AR"))? true: false,
									rs.getInt("max_ammo"),
									rs.getLong("delay"),
									rs.getLong("reload_time"),
									rs.getInt("dmg"),
									rs.getDouble("speed"),
									rs.getDouble("range"),
									null
						);
				return gun;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return null;
	}

	public static boolean updateGP(int p_id, int newGP) {
		String query = "UPDATE players SET gp = ? WHERE p_id = ?";
		
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, newGP);
			pst.setInt(2, p_id);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return success;
	}

	public static boolean updateHighestWave(int p_id, int newWave) {
		String query = "UPDATE players SET highest_wave = ? WHERE p_id = ?";
		
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, newWave);
			pst.setInt(2, p_id);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return success;
	}

	public static boolean updateStatistics(int p_id, int newRange, int newFast, int newMelee) {
		String query = "UPDATE players SET range_kills = ?, fast_kills = ?, melee_kills = ? WHERE p_id = ?";
		
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, newRange);
			pst.setInt(2, newFast);
			pst.setInt(3, newMelee);
			pst.setInt(4, p_id);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return success;
	}
	
	public static boolean updateEquipGuns(int p_id, int pri, int sec) {
		String query = "UPDATE players SET pri = ?, sec = ? WHERE p_id = ?";
		
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, pri);
			pst.setInt(2, sec);
			pst.setInt(3, p_id);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return success;
	}
	
	public static boolean createOwnGun(int p_id, int g_id) {
		String query = "INSERT INTO own_guns (p_id, g_id) VALUES (?, ?)";
		
		boolean success = false;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, p_id);
			pst.setInt(2, g_id);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		
		return success;
	}
	
	public static boolean updatePlayerSingleInfo(int p_id, String attribute, String input) {
		String query = "UPDATE players SET " + attribute + " = ? WHERE p_id = ?";
		boolean success = false;
		boolean cont = true;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			switch(attribute) {
				case("Name"):{pst.setString(1, input); break;}
				case("Email"):{pst.setString(1, input); break;}
				case("Password"):{pst.setString(1, input); break;}
				default:{cont = false; break;}
			}
			pst.setInt(2, p_id);
			int rs = 0;
			if(cont) rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
}
