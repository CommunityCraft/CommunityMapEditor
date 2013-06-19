package net.xemnias.tools;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public CommunityMapEditor parent;
	
	public MainPanel mainPanel = new MainPanel(new BorderLayout());
	public LeftPanel leftPanel;
	public RightPanel rightPanel = new RightPanel(new FlowLayout(), this);
	public JScrollPane scroll;
	public JSplitPane splitPane; 
	
	public MainFrame(CommunityMapEditor communityMapEditor)
	{
		
		 leftPanel = new LeftPanel(new FlowLayout(), this);
		 scroll = new JScrollPane(leftPanel);
		 splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, rightPanel);
		parent = communityMapEditor;
		init();
		addCompoment();
		
		
	}
	

	private void addCompoment() 
	{
		add(mainPanel);
		splitPane.setDividerLocation(760);
		mainPanel.add(splitPane);
	}

	private void init() 
	{
		setSize(1080,720);
		setTitle("Map editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
