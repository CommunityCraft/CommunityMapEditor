package net.xemnias.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Case 
{
	private int x, y;
	private Image sprite;
	public int id = -1;
	public String type;
	
	public Case(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g, ImageObserver parent)
	{
		g.drawImage(sprite, x, y, parent);
	}
	
	public String getXString() {
		return Integer.toString(x);
	}
	public String getYString() {
		return Integer.toString(y);
	}
	
	public int getX() {
		return (x);
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

		((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(
                java.awt.AlphaComposite.SRC_OVER,Math.min(
                    0.3f,
                    1.0f ) )) ;
		((Graphics2D) g).setColor(Color.black);
		((Graphics2D) g).drawRect(x, y, 32, 32);
 
  ((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,1.0f));
 

	}

	public void drawPreview(Graphics g, LeftPanel leftPanel, MainFrame parent) 
	{
		   ((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(
		                java.awt.AlphaComposite.SRC_OVER,Math.min(
		                    0.3f,
		                    1.0f ) )) ;
		  
		  g.drawImage(parent.rightPanel.getSelectedTab().selectedSprite, x, y, leftPanel);
		 
		 
		  // Restaurer la transparence par défaut.
		 
		  ((Graphics2D) g).setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,1.0f));
		 

	}

	public void setType(String t) {
		type = t;
	}

	public Image getTexture(int id2) 
	{
		int y = id2/8;
		int x = id2-(y*8);
		BufferedImage buff = null;
		try {
			if(type.equalsIgnoreCase("block"))
			{
				if(id < 56)
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"spritesheet.png"));
				else
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"spritesheet2.png"));
			}

			if(type.equalsIgnoreCase("item"))
			{
				if(id < 56)
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"item.png"));
				else
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"item2.png"));
			}

			if(type.equalsIgnoreCase("entity"))
			{
				if(id < 56)
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"entity.png"));
				else
					buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"entity2.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buff.getSubimage(x*32, y*32, 32, 32);
	}
}
