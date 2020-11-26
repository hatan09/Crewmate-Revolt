package JDBC_Connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServer_Connector {
	
	//private static String connectionUrl = "jdbc:sqlserver://localhost:50631;databaseName=OOP;user=admin@oop_db;password=Oop123";
	private static String ip = "localhost";
	private static String port = "50631";
	private static String url = "jdbc:sqlserver://";
	private static String user = "admin@oop_db";
	private static String pass = "Oop123";

	
	//#1
    public static Connection getJDBCConnection() {
        try {
            // Load SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to SQL Server ... ");
            try (Connection connection = DriverManager.getConnection(url + ip + ":" + port,user,pass)) {
                	System.out.println("Done.");
            }
        }
        catch (Exception e) {
        	System.out.println();
        	e.printStackTrace();
        }
        return null;
    }
    
    //#2
    public static Connection getJDBCConnection(String user, String pass, String ip, String port) {
    	setUser(user);
    	setPass(pass);
    	setIP(ip);
    	setPort(port);
    	return SQLServer_Connector.getJDBCConnection();
    }
    
    //set ip
    public static void setIP(String ip) {
    	if(ip.isEmpty()) {
    		SQLServer_Connector.ip = "localhost";
    	}
    	else {
    		SQLServer_Connector.ip = ip;
    	}
    }
    
    //set username
    public static void setUser(String user) {
    	if(ip.isEmpty()) {
    		SQLServer_Connector.user = "admin@oop_db";
    	}
    	else {
    		SQLServer_Connector.user = user;
    	}
    }
    
    //set password
    public static void setPass(String pass) {
    	if(ip.isEmpty()) {
    		SQLServer_Connector.pass = "Oop123";
    	}
    	else {
    		SQLServer_Connector.pass = pass;
    	}
    }
    
    //set port
    public static void setPort(String port) {
    	if(port.isEmpty()) {
    		SQLServer_Connector.port = "50631";
    	}
    	else {
    		SQLServer_Connector.port = port;
    	}
    }
}