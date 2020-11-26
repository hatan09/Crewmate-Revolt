package gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {
	
	/*
	 * This class is used to get img from files
	 * Give it a link and assign the img by a var of type BufferedImage because this class's method return static img as a BufferedImage
	 * Get gif ==> ImageIcon
	 * */

	public static BufferedImage loadStaticImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));		//(1) - right click project-> new-> source folder-> choose res ==> path = /img/<name>.<extension>
			
			//return ImageIO.read(new FileInputStream(path));				//(2) import java.io.FileInputStream-> path = res/img/<name>.<extension> or res\\img\\<name>.<extension>
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ImageIcon loadAnimatedImage(String path) {
		return new ImageIcon(path);		//import javax.imageio.ImageIO;
										//import javax.swing.ImageIcon;-> path = res/img/<name>.<extension> or res\\img\\<name>.<extension>
	}
}
