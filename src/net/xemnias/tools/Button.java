package net.xemnias.tools;

import java.awt.Graphics;

public class Button 
{
	public int x, y, w, h;
	public String s;
	
	public Button(int i, int j, int k, int l, String str)
	{
		x = i;
		y = j;
		w = k;
		h = l;
		s = str;
	}
	
	public void draw(Graphics g)
	{
		g.drawRect(x, y, w, h);
		g.drawString(s, x+14, y+14);
	}
	
	public boolean isOnIt(int mouseX, int mouseY)
	{ 
		return (mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h);
	}
}
