package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gfx.ImageLoader;

public class GUI_Login extends JLayeredPane{
	protected static boolean isLogPassHiden = true, isRegPassHiden = true;
	
	protected static JButton btn_login, btn_reg, btn_lost, btn_log_hide, btn_log_show, btn_reg_hide, btn_reg_show, btn_go_reg;
	protected static JLabel lbl_welcome, lbl_reg, lbl_background, lbl_acc, lbl_pass, lbl_reg_title, lbl_reg_acc, lbl_reg_name, lbl_reg_pass;
	protected static JTextField tf_log_acc, tf_log_pass, tf_reg_acc, tf_reg_name, tf_reg_pass;
	protected static JPasswordField pf_log_pass, pf_reg_pass;
	public static Font f_acc, f_lbl, f_welcome, f_word_btn;
	
	public GUI_Login (){
		f_acc = new Font("Arial Rounded MT Bold", Font.PLAIN, 20);
		f_welcome = new Font("Arial Rounded MT Bold", Font.PLAIN, 50);
		f_lbl = new Font("Arial Rounded MT Bold", Font.PLAIN, 16);
		f_word_btn = new Font("Arial", Font.PLAIN, 14);
		
		Map attributes = f_word_btn.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		f_word_btn = f_word_btn.deriveFont(attributes);
		
		
		//******************************************************

		
		//log
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
		
		btn_log_hide = new JButton();
		btn_log_hide.setIcon(new ImageIcon(ImageLoader.loadStaticImage("/img/hide_img.png")));
		btn_log_hide.setBorderPainted(false);
		btn_log_hide.setFocusPainted(false);
		btn_log_hide.setContentAreaFilled(false);
		btn_log_hide.setBounds(480,215,33,20);
		
		btn_log_show = new JButton();
		btn_log_show.setIcon(new ImageIcon(ImageLoader.loadStaticImage("/img/show_img.png")));
		btn_log_show.setBorderPainted(false);
		btn_log_show.setFocusPainted(false);
		btn_log_show.setContentAreaFilled(false);
		btn_log_show.setBounds(480,215,33,20);
		
		
		//reg
		btn_go_reg = new JButton("Register");
		btn_go_reg.setFont(f_acc);
		btn_go_reg.setBackground(new Color(1.0f,1.0f,1.0f));
		btn_go_reg.setBounds(660, 350, 300, 45);
		
		btn_reg_hide = new JButton();
		btn_reg_hide.setIcon(new ImageIcon(ImageLoader.loadStaticImage("/img/hide_img.png")));
		btn_reg_hide.setBorderPainted(false);
		btn_reg_hide.setFocusPainted(false);
		btn_reg_hide.setContentAreaFilled(false);
		btn_reg_hide.setBounds(920,295,33,20);
		
		btn_reg_show = new JButton();
		btn_reg_show.setIcon(new ImageIcon(ImageLoader.loadStaticImage("/img/show_img.png")));
		btn_reg_show.setBorderPainted(false);
		btn_reg_show.setFocusPainted(false);
		btn_reg_show.setContentAreaFilled(false);
		btn_reg_show.setBounds(920,295,33,20);
		
		
		//******************************************************
		
		
		//log
		lbl_welcome = new JLabel("Welcome");
		lbl_welcome.setFont(f_welcome);
		//lbl_welcome.setForeground(Color.orange);
		lbl_welcome.setBounds(212, 20, 225, 60);
		
		lbl_reg = new JLabel("Don't have an account yet?");
		lbl_reg.setFont(f_lbl);
		lbl_reg.setBounds(120, 370, 220, 25);
		
		lbl_background = new JLabel();
		lbl_background.setIcon(ImageLoader.loadAnimatedImage("/img/login_back.gif"));
		lbl_background.setBounds(0, 0, 650, 500);
		
		lbl_acc = new JLabel("Username");
		lbl_acc.setFont(f_lbl);
		lbl_acc.setForeground(Color.gray);
		lbl_acc.setBounds(120, 100, 100, 20);
		
		lbl_pass = new JLabel("Password");
		lbl_pass.setFont(f_lbl);
		lbl_pass.setForeground(Color.gray);
		lbl_pass.setBounds(120, 180, 100, 20);
		
		
		//reg
		lbl_reg_title = new JLabel("Register");
		lbl_reg_title.setFont(f_welcome);
		lbl_reg_title.setBounds(720, 20, 250, 60);
		
		lbl_reg_name = new JLabel("Name");
		lbl_reg_name.setFont(f_lbl);
		lbl_reg_name.setForeground(Color.gray);
		lbl_reg_name.setBounds(660, 100, 100, 20);
		
		lbl_reg_acc = new JLabel("Username");
		lbl_reg_acc.setFont(f_lbl);
		lbl_reg_acc.setForeground(Color.gray);
		lbl_reg_acc.setBounds(660, 180, 100, 20);
		
		lbl_reg_pass = new JLabel("Password");
		lbl_reg_pass.setFont(f_lbl);
		lbl_reg_pass.setForeground(Color.gray);
		lbl_reg_pass.setBounds(660, 260, 100, 20);
		
		
		//******************************************************
		
		
		//log
		tf_log_acc = new HintTextField("Type in your username");
		tf_log_acc.setBounds(120, 120, 400, 45);
		tf_log_acc.setFont(f_acc);
		
		
		//reg
		tf_reg_acc = new JTextField();
		tf_reg_acc.setBounds(660, 120, 300, 45);
		tf_reg_acc.setFont(f_acc);
		
		tf_reg_name = new JTextField();
		tf_reg_name.setBounds(660, 200, 300, 45);
		tf_reg_name.setFont(f_acc);
		
		
		//******************************************************
		
		
		//log
		pf_log_pass = new HintPasswordField("Type in your password");
		pf_log_pass.setBounds(120,200,400,45);
		pf_log_pass.setFont(f_acc);
		//++++++++++++++++++++++++
		tf_log_pass = new JTextField();
		tf_log_pass.setBounds(120,200,400,45);
		tf_log_pass.setFont(f_acc);
		
		
		//reg
		pf_reg_pass = new JPasswordField();
		pf_reg_pass.setBounds(660,280,300,45);
		pf_reg_pass.setFont(f_acc);
		//++++++++++++++++++++++++
		tf_reg_pass = new JTextField();
		tf_reg_pass.setBounds(660,280,300,45);
		tf_reg_pass.setFont(f_acc);
		
		
		//******************************************************
		
		add(tf_log_acc,Integer.valueOf(2)); add(pf_log_pass, Integer.valueOf(2));
		add(lbl_welcome, Integer.valueOf(1)); add(lbl_acc, Integer.valueOf(1)); add(lbl_pass, Integer.valueOf(1)); add(lbl_reg, Integer.valueOf(1)); add(lbl_background, Integer.valueOf(0));
		add(btn_login, Integer.valueOf(3)); add(btn_lost, Integer.valueOf(3)); add(btn_reg, Integer.valueOf(3)); add(btn_log_show, Integer.valueOf(3));
		
		add(tf_reg_acc, Integer.valueOf(2)); add(tf_reg_name, Integer.valueOf(2)); add(pf_reg_pass, Integer.valueOf(2));
		add(lbl_reg_acc, Integer.valueOf(1)); add(lbl_reg_name, Integer.valueOf(1)); add(lbl_reg_pass, Integer.valueOf(1)); add(lbl_reg_title, Integer.valueOf(1));
		add(btn_go_reg, Integer.valueOf(3)); add(btn_reg_show, Integer.valueOf(3));
	}
}
