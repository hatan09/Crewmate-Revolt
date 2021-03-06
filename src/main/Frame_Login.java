package main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import database.SQL_Communication;

public class Frame_Login extends JFrame{
	
	Dimension d_size;
	Dimension r_size;
	
	GUI_Working_Login pn_login;
	
	public Frame_Login() {
		d_size = new Dimension(650, 500);
		r_size = new Dimension(1000, 500);
		
		setSizeLog();
		setTitle("Crewmate Revolt - Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		pn_login = new GUI_Working_Login();
		add(pn_login);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        // call terminate
				SQL_Communication.closeConnection();
		    }
		});
	}
	
	public void setSizeReg() {
		setSize(r_size);
	}
	
	public void setSizeLog() {
		setSize(d_size);
	}
}

