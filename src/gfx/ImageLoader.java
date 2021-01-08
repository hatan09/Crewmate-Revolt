package gfx;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

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
			System.out.println(ImageLoader.class.getResource(path));
			return ImageIO.read(ImageLoader.class.getResource(path));		//(1) - right click project-> new-> source folder-> choose res ==> path = /img/<name>.<extension>
			
			//return ImageIO.read(new FileInputStream(path));				//(2) import java.io.FileInputStream-> path = res/img/<name>.<extension> or res\\img\\<name>.<extension>
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ImageIcon loadAnimatedImage(String path) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = ImageLoader.class.getResource(path);
		ImageIcon icon = new ImageIcon(tk.getImage(url));
		return icon;					//import javax.imageio.ImageIO;
										//import javax.swing.ImageIcon;-> path = res/img/<name>.<extension> or res\\img\\<name>.<extension>
	}
}
