package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gfx.ImgAssets;

public class LogoPane extends JPanel{

	private BufferedImage img;
	public LogoPane() {
		img = ImgAssets.logo;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
}
