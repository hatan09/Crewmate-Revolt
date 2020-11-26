package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class GUI_Working_Login extends GUI_Login implements ActionListener, MouseListener, ItemListener, KeyListener{
	
	/*
	 * This class is used to setup all logic, behaviors and listener for the login panel (BE of the login)
	 * */
	
	private static boolean showReg = false;
	
	Game _game;
	
	public GUI_Working_Login() {
		btn_login.addActionListener(this);
		btn_lost.addActionListener(this);
		btn_reg.addActionListener(this);
		//add update listener to textfields
		pf_pass.addActionListener(new ActionListener() {		
		@Override
			public void actionPerformed(ActionEvent e) {	//catch enter
				btn_login.doClick();
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == btn_login) {
			if(checkLogin()) {
				Main.fr_login.setVisible(false);
				if(startGame());	//check wether it can start our Game window (main window)
			}
		}
		
		if(btn == btn_reg) {
			if(showReg) {
				Main.fr_login.setSizeLog();
				showReg = false;
			}
			else {
				Main.fr_login.setSizeReg();
				showReg = true;
			}
		}
		
		if(btn == btn_lost) {
			String s = JOptionPane.showInputDialog(null, "Your email", "Input your email to recover it!", JOptionPane.INFORMATION_MESSAGE);
			if(!(s == null)) {
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Canceled!");
			}
		}
		
	}
	
	private boolean checkLogin() {
		if(checkLEmail()) {
			if(checkLPass()) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	private boolean checkLEmail() {
		return true;
	}
	
	private boolean checkLPass() {
		return true;
	}
	
	private boolean startGame() {
		Main.game.start();
		return true;
	}

}
