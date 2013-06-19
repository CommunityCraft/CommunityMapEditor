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
	private static final long serialVersionUID = 1L;
	Image[] selecter;
	public Image selectedSprite = null;
	private CaseSelecter[][] cases;
	public int id=-1;
	
	private int mouseX, mouseY;
	private CaseSelecter actualCase;
	private CaseSelecter selectedCase;
	public String type;
	
	public SelecterPanel(final String[] string, String t) 
	{
		type = t;
		selecter = new Image[string.length];
		try {
			for(int x = 0; x < selecter.length; x++)
			{
				System.out.println(System.getProperty("user.dir")+File.separator+string[x]);
				selecter[x] = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+string[x]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cases = new CaseSelecter[8][16];
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 16; y++)
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

								id = getIdByCoord(actualCase.x/32, actualCase.y/32);
								System.out.println(id);
						BufferedImage buff = null;
						if(actualCase.y < 8*32)
						{
							buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+string[0]));
							
							selectedSprite = buff.getSubimage(actualCase.x, actualCase.y, actualCase.width, actualCase.height);
						
						}
						else if(actualCase.y >= 8*32)
						{
							buff = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+string[1]));	
							selectedSprite = buff.getSubimage(actualCase.x, actualCase.y-256, actualCase.width, actualCase.height);
							
						}
						selectedCase = actualCase;
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(id);
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
		if(arg0.getX()> 0 && arg0.getX() < 512 && arg0.getY()> 0 && arg0.getY() < 512)
			return true;
		return false;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < selecter.length; i++)
		{

			g.drawImage(selecter[i], 0, 256*i, this);
		}
		
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 16; y++)
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
			
			g.drawImage(selectedSprite, 0, 520, this);
		}
	}
	
	public int getIdByCoord(int x, int y)
	{
		return x+(8*y);
	}

}
