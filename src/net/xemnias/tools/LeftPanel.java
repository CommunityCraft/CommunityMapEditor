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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Case[][] cases;
	private int mouseX, mouseY;
	private MainFrame parent;
	
	public int xSize, ySize;
	public int translate;
	private Button b1 = new Button(0, 580, 32, 32, "-");
	private Button b2 = new Button(32, 580, 32, 32, "+");
	
	public LeftPanel(LayoutManager borderLayout, MainFrame p) 
	{
		super(borderLayout);
		parent = p;
		addMouseMotionListener(new motionListener());
		addMouseListener(new clickedListener());
		cases = new Case[xSize][ySize];
		
		for(int x = 0; x < xSize; x++)
		{
			for(int y = 0; y < ySize; y++)
			{
				cases[x][y] = new Case(x*32, y*32);
			}
		}
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.translate(translate, 0);
		for(int x = 0; x < xSize; x++)
		{
			for(int y = 0; y < ySize; y++)
			{
				if(cases[x][y]!=null)
				{
					if(mouseX > cases[x][y].getX() && mouseX < cases[x][y].getX()+32 && mouseY > cases[x][y].getY() && mouseY < cases[x][y].getY()+32)
					{
						cases[x][y].drawPreview(g, this, parent);
					}
					cases[x][y].drawRect(g, this);
					cases[x][y].draw(g, this);
				}
			}
			b1.draw(g);
			b2.draw(g);
		}
		repaint();
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
		for(int x = 0; x < xSize; x++)
		{
			for(int y = 0; y < ySize; y++)
			{
				if(e.getX() > cases[x][y].getX() && e.getX() < cases[x][y].getX()+32 && e.getY() > cases[x][y].getY() && e.getY() < cases[x][y].getY()+32)
				{
					cases[x][y].setSprite(parent.rightPanel.getSelectedTab().selectedSprite);
					cases[x][y].id = parent.rightPanel.getSelectedTab().id;
					cases[x][y].setType(parent.rightPanel.getSelectedTab().type);
				}
			}
		}
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
			for(int x = 0; x < xSize; x++)
			{
				for(int y = 0; y < ySize; y++)
				{
					if(e.getX() > cases[x][y].getX() && e.getX() < cases[x][y].getX()+32 && e.getY() > cases[x][y].getY() && e.getY() < cases[x][y].getY()+32)
					{
						cases[x][y].setSprite(parent.rightPanel.getSelectedTab().selectedSprite);
						cases[x][y].id = parent.rightPanel.getSelectedTab().id;
						cases[x][y].setType(parent.rightPanel.getSelectedTab().type);
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
