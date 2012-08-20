package net.xemnias.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class LeftPanel extends JPanel 
{
	public Case[][] cases;
	private int mouseX, mouseY;
	private MainFrame parent;
	
	public LeftPanel(LayoutManager borderLayout, MainFrame p) 
	{
		super(borderLayout);
		parent = p;
		addMouseMotionListener(new motionListener());
		addMouseListener(new clickedListener());
		
		cases = new Case[64][64];
		
		for(int x = 0; x < 64; x++)
		{
			for(int y = 0; y < 64; y++)
			{
				cases[x][y] = new Case(x*32, y*32);
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(int x = 0; x < 64; x++)
		{
			for(int y = 0; y < 64; y++)
			{
				if(mouseX > cases[x][y].getX() && mouseX < cases[x][y].getX()+32 && mouseY > cases[x][y].getY() && mouseY < cases[x][y].getY()+32)
				{
					cases[x][y].drawRect(g, this);
					cases[x][y].drawPreview(g, this, parent);
				}
				cases[x][y].draw(g, this);
			}
		}
	}
	
	class clickedListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
		for(int x = 0; x < 64; x++)
		{
			for(int y = 0; y < 64; y++)
			{
				if(e.getX() > cases[x][y].getX() && e.getX() < cases[x][y].getX()+32 && e.getY() > cases[x][y].getY() && e.getY() < cases[x][y].getY()+32)
				{
					cases[x][y].setSprite(parent.rightPanel.blockPanel.selectedSprite);
				}
			}
		}
		repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class motionListener implements MouseMotionListener
	{

		public void mouseDragged(MouseEvent e)
		{

			mouseX = e.getX();
			mouseY = e.getY();
			for(int x = 0; x < 64; x++)
			{
				for(int y = 0; y < 64; y++)
				{
					if(e.getX() > cases[x][y].getX() && e.getX() < cases[x][y].getX()+32 && e.getY() > cases[x][y].getY() && e.getY() < cases[x][y].getY()+32)
					{
						cases[x][y].setSprite(parent.rightPanel.blockPanel.selectedSprite);
					}
				}
			}
			repaint();
		}

		public void mouseMoved(MouseEvent e)
		{
			mouseX = e.getX();
			mouseY = e.getY();
			repaint();
		}
		
	}

}
