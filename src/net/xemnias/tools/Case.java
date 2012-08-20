package net.xemnias.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Case 
{
	private int x, y;
	private Image sprite;
	
	public Case(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g, ImageObserver parent)
	{
		g.drawImage(sprite, x, y, parent);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public void drawRect(Graphics g, LeftPanel leftPanel) 
	{

		g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);
	}

	public void drawPreview(Graphics g, LeftPanel leftPanel, MainFrame parent) 
	{
		Graphics2D g2 = (Graphics2D) g ;
		   ((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(
		                java.awt.AlphaComposite.SRC_OVER,Math.min(
		                    0.3f,
		                    1.0f ) )) ;
		 
		  g.drawImage(parent.rightPanel.blockPanel.selectedSprite, x, y, leftPanel);
		 
		 
		  // Restaurer la transparence par défaut.
		 
		  ((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,1.0f));
		 

	}
}
