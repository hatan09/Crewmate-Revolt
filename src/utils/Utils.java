package utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class Utils {

	private static String filePath;		//current location (folder) of the game

	//load file as string
	public static String loadFileAsString(String path) {
		StringBuilder strBuilder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				strBuilder.append(line + "\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return strBuilder.toString();
	}
	//end load file as string
	
	
	//parse int
	public static int parseInt(String n) {
		try {
			return Integer.parseInt(n);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//end parse int

	
	//Pathway
	public static void setPath() {
		filePath = System.getProperty("user.dir");
		System.out.println(filePath);
	}
	
	public static String getPath() {
		return filePath;
	}
	//end Pathway
	
	
	//encode int
	public static String encodeInt(int num) {
		return Integer.toHexString(num * 2);
	}
	
	public static int decodeInt(String s) {
		return (int) Long.parseLong(s, 16) / 2;
	}
	//end encode int
}
