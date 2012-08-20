package net.xemnias.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SelecterPanel extends JPanel 
{
	Image selecter;
	private String path;
	public Image selectedSprite = null;
	private CaseSelecter[][] cases;
	
	private int mouseX, mouseY;
	private CaseSelecter actualCase;
	private CaseSelecter selectedCase;
	
	public SelecterPanel(String string) 
	{
		try {
			selecter = ImageIO.read(new File("data/tiles/"+string));
			path = "data/tiles/"+string;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cases = new CaseSelecter[8][8];
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				cases[x][y] = new CaseSelecter(x*32, y*32, 32, 32);
			}
		}
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent arg0) 
			{
				if(isClickOnImage(arg0))
				{
					
					try {
						BufferedImage buff = ImageIO.read(new File(path));
						selectedCase = actualCase;
						selectedSprite = buff.getSubimage(actualCase.x, actualCase.y, actualCase.width, actualCase.height);
					} catch (IOException e) {
						e.printStackTrace();
					}

					repaint();
				}
			}
			
			public void mouseExited(MouseEvent arg0) {	}
			public void mouseEntered(MouseEvent arg0) {	}
			public void mouseClicked(MouseEvent arg0) {	}
		});
	
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

				repaint();
			}
			
			public void mouseDragged(MouseEvent e) {}
		});
	}
	
	private boolean isClickOnImage(MouseEvent arg0)
	{
		if(arg0.getX()> 0 && arg0.getX() < 256 && arg0.getY()> 0 && arg0.getY() < 256)
			return true;
		return false;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(selecter, 0, 0, this);
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				if(mouseX> cases[x][y].x && mouseX < cases[x][y].x+32)
				{
					if(mouseY > cases[x][y].y && mouseY < cases[x][y].y+32)
					{
						actualCase = cases[x][y];
					}
				}
			}
		}
		
		if(selectedSprite != null)
		{
			selectedCase.render(g);
			
			g.drawImage(selectedSprite, 0, 300, this);
		}
	}
	
	

}
