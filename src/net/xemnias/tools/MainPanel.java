package net.xemnias.tools;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainPanel extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainPanel(BorderLayout borderLayout) 
	{
		super(borderLayout);
		 try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
		       e.printStackTrace();

	    }
	    catch (InstantiationException e) {
		       e.printStackTrace();

	    }
	    catch (IllegalAccessException e) {
		       e.printStackTrace();

	    }
	}
	
}
