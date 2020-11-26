package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gfx.ImageLoader;

public class GUI_Login extends JLayeredPane{
	
	static JButton btn_login, btn_reg, btn_lost, btn_hide, btn_show;
	static JLabel lbl_welcome, lbl_reg, lbl_background, lbl_acc, lbl_pass;
	static JTextField tf_acc;
	static JPasswordField pf_pass;
	static Font f_acc, f_lbl, f_welcome, f_word_btn;
	
	public GUI_Login (){
		f_acc = new Font("Arial Rounded MT Bold", Font.PLAIN, 20);
		f_welcome = new Font("Arial Rounded MT Bold", Font.PLAIN, 50);
		f_lbl = new Font("Arial Rounded MT Bold", Font.PLAIN, 16);
		f_word_btn = new Font("Arial", Font.PLAIN, 14);
		
		Map attributes = f_word_btn.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		f_word_btn = f_word_btn.deriveFont(attributes);
		
		
		//******************************************************

		
		btn_login = new JButton("Login");
		btn_login.setFont(f_acc);
		btn_login.setBackground(new Color(1.0f,1.0f,1.0f));
		btn_login.setBounds(120, 280, 400, 45);
		
		btn_reg = new JButton("Create one...");
		btn_reg.setForeground(Color.BLUE);
		btn_reg.setFont(f_word_btn);
		btn_reg.setBorderPainted(false);
		btn_reg.setFocusPainted(false);
		btn_reg.setContentAreaFilled(false);
		btn_reg.setBounds(330, 370, 120, 25);
		
		btn_lost = new JButton("Lost your password?");
		btn_lost.setForeground(Color.BLUE);
		btn_lost.setFont(f_word_btn);
		btn_lost.setBorderPainted(false);
		btn_lost.setFocusPainted(false);
		btn_lost.setContentAreaFilled(false);
		btn_lost.setBounds(103, 340, 165, 25);
		
		btn_hide = new JButton();
		//btn_hide.setIcon(new ImageIcon(ImageLoader.loadImage("res\\img\\tile.jpg")));
		
		btn_show = new JButton();
		btn_show.setIcon(ImageLoader.loadAnimatedImage("/img/login_back.gif"));
		
		
		//******************************************************
		
		
		lbl_welcome = new JLabel("Welcome");
		lbl_welcome.setFont(f_welcome);
		//lbl_welcome.setForeground(Color.orange);
		lbl_welcome.setBounds(212, 20, 225, 60);
		
		lbl_reg = new JLabel("Don't have an account yet?");
		lbl_reg.setFont(f_lbl);
		lbl_reg.setBounds(120, 370, 220, 25);
		
		lbl_background = new JLabel();
		lbl_background.setIcon(ImageLoader.loadAnimatedImage("res\\img\\login_back.gif"));
		lbl_background.setBounds(0, 0, 650, 500);
		
		lbl_acc = new JLabel("Username");
		lbl_acc.setFont(f_lbl);
		lbl_acc.setForeground(Color.gray);
		lbl_acc.setBounds(120, 100, 100, 20);
		
		lbl_pass = new JLabel("Password");
		lbl_pass.setFont(f_lbl);
		lbl_pass.setForeground(Color.gray);
		lbl_pass.setBounds(120, 180, 100, 20);
		
		
		//******************************************************
		
		
		tf_acc = new HintTextField("Type in your username");
		tf_acc.setBounds(120, 120, 400, 45);
		tf_acc.setFont(f_acc);
		
		
		//******************************************************
		
		
		pf_pass = new HintPasswordField("Type in your password");
		pf_pass.setBounds(120,200,400,45);
		pf_pass.setFont(f_acc);
		
		
		//******************************************************
		
		add(pf_pass, new Integer(1)); add(tf_acc, new Integer(1));
		add(lbl_welcome, new Integer(0)); add(lbl_acc, new Integer(0)); add(lbl_pass, new Integer(0)); add(lbl_reg, new Integer(0)); add(lbl_background, new Integer(0));
		add(btn_login, new Integer(2)); add(btn_lost, new Integer(2)); add(btn_reg, new Integer(2));
	}
}
