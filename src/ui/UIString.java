package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIString extends UIObject{
	private Font font;
	private String s = "";
	private Color color;

	public UIString(float x, float y, int width, int height, Font font, Color color) {
		super(x, y, width, height);
		
		this.font = font;
		this.color = color;
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
		g.setColor(color);
		g.drawString(s, (int) x, (int) y);
	}

}
