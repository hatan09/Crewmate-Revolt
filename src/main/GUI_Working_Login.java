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
		btn_go_reg.addActionListener(this);
		btn_log_hide.addActionListener(this);
		btn_log_show.addActionListener(this);
		btn_reg_hide.addActionListener(this);
		btn_reg_show.addActionListener(this);
		
		//add update listener to textfields
		pf_log_pass.addActionListener(new ActionListener() {		
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
		
		if(btn == btn_log_hide) {
			add(btn_log_show, Integer.valueOf(3));
			remove(btn_log_hide);
			String tmp = tf_log_pass.getText();
			pf_log_pass.setText(tmp);
			add(pf_log_pass, Integer.valueOf(2));
			remove(tf_log_pass);
		}
		
		if(btn == btn_log_show) {
			add(btn_log_hide, Integer.valueOf(3));
			remove(btn_log_show);
			String tmp = pf_log_pass.getText();
			tf_log_pass.setText(tmp);
			add(tf_log_pass, Integer.valueOf(2));
			remove(pf_log_pass);
		}
		
		if(btn == btn_reg_hide) {
			add(btn_reg_show, Integer.valueOf(3));
			remove(btn_reg_hide);
			String tmp = tf_reg_pass.getText();
			pf_reg_pass.setText(tmp);
			add(pf_reg_pass, Integer.valueOf(2));
			remove(tf_reg_pass);
		}
		
		if(btn == btn_reg_show) {
			add(btn_reg_hide, Integer.valueOf(3));
			remove(btn_reg_show);
			String tmp = pf_reg_pass.getText();
			tf_reg_pass.setText(tmp);
			add(tf_reg_pass, Integer.valueOf(2));
			remove(pf_reg_pass);
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
