package net.xemnias.tools;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RightPanel extends JPanel {

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	private JPanel blockPanel = new JPanel();
	private JPanel entityPanel = new JPanel();
	private JPanel itemPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	
	public RightPanel(LayoutManager borderLayout) 
	{
		super(borderLayout);
		setLayout(new BorderLayout(0, 0));
		
		add(tabbedPane);
		tabbedPane.add(blockPanel, "Terrain et Block");
		tabbedPane.add(entityPanel, "Entités et monstres");
		tabbedPane.add(itemPanel, "Items");
		tabbedPane.add(otherPanel, "Autre");
	}
}
