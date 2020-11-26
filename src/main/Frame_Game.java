package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame_Game extends JFrame{
	
	/*
	 * This class is the settings for the Game window (Frame)
	 * Game window is the main window where displays many important windows (see class Game for more details)
	 * Its instance belongs to class Game 
	 * */

	private String title;
	private Canvas canvas;
	
	//default windows size for game
	private final Dimension DEFAULT_SIZE = new Dimension(500, 500);
	
	public Frame_Game(String title, Dimension d) {
		this.title = title;
		setSize(d);
		setTitle(this.title);
		setResizable(false);
		setLocationRelativeTo(null);
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
		canvas.setPreferredSize(getSize());
		canvas.setMaximumSize(getSize());
		canvas.setMinimumSize(getSize());
		canvas.setFocusable(false);
		
		add(canvas);
		pack();
		
		setVisible(true);
		
		//fullScreen();
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
		pack();
	}
	
	public Canvas getCanvas () {
		return canvas;
	}
}
