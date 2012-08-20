package net.xemnias.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CaseSelecter extends Rectangle
{
	public CaseSelecter(int x, int y, int w, int h)
	{
		super(x,y,w,h);
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
	}
}
