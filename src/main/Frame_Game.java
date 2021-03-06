package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gfx.ImageLoader;

public class Frame_Game extends JFrame{
	
	/*
	 * This class is the settings for the Game window (Frame)
	 * Game window is the main window where displays many important windows (see class Game for more details)
	 * Its instance belongs to class Game 
	 * */

	private String title;
	private Canvas canvas;
	public JLabel waiting;
	
	//toolkit
	private Toolkit toolkit;
	private Image cursorImage;
	private Cursor cursor;
	
	public Frame_Game(String title, Dimension d) {
		URL url;
		url = this.getClass().getResource("/img/crosshair2.png");
		toolkit = Toolkit.getDefaultToolkit();
		cursorImage = toolkit.getImage(url);
		cursor = toolkit.createCustomCursor(cursorImage , new Point(this.getX(), this.getY()), "img");
		
		waiting = new JLabel(ImageLoader.loadAnimatedImage("/img/loader.gif"), JLabel.CENTER);
		
		this.title = title;
		setSize(d);
		setTitle(this.title);
		setResizable(false);
		setLocationRelativeTo(null);
		setCursor(cursor);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        // call terminate
				int choice = JOptionPane.showConfirmDialog(null, "Quit?");
				if(choice == JOptionPane.OK_OPTION) {
					dispose();
					Main.fr_login.setVisible(true);
					Main.game.stopGame();
				}
				else {
					
				}
		    }
		});
		canvas = new Canvas();
		canvas.setBackground(Color.white);
		canvas.setPreferredSize(getSize());
		canvas.setMaximumSize(getSize());
		canvas.setMinimumSize(getSize());
		canvas.setFocusable(false);
		
		//add(canvas); 
		add(waiting);
		
		setVisible(true);
		
		fullScreen();
	}
	
	public void reFresh() {
		
	}
	
	public void reSize(Dimension d) {
		setSize(d);
		System.out.println(getSize());
		canvas.setPreferredSize(getSize());
		canvas.setMaximumSize(getSize());
		canvas.setMinimumSize(getSize());
		Main.game.setHeight(getHeight());
		Main.game.setWidth(getWidth());
		reFresh();
	}
	
	public void fullScreen() {
		dispose();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		System.out.println(getSize());
		canvas.setPreferredSize(getSize());
		canvas.setMaximumSize(getSize());
		canvas.setMinimumSize(getSize());
		Main.game.setHeight(getHeight());
		Main.game.setWidth(getWidth());
		//pack();
	}
	
	public Canvas getCanvas () {
		return canvas;
	}
}
