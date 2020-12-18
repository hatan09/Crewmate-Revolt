package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIString extends UIObject{
	private Font font;
	private String s = "";

	public UIString(float x, float y, int width, int height, Font font) {
		super(x, y, width, height);
		
		this.font = font;
		
	}
	
	public void setString(String s) {
		this.s = s;
	}

	@Override
	public void onClick() {
		
		
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s, (int) x, (int) y);
	}

}
