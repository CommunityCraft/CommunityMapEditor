package net.xemnias.tools;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public CommunityMapEditor parent;
	
	public MainPanel mainPanel = new MainPanel(new BorderLayout());
	public LeftPanel leftPanel = new LeftPanel(new FlowLayout());
	public RightPanel rightPanel = new RightPanel(new FlowLayout());
	
	public JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
	
	public MainFrame(CommunityMapEditor communityMapEditor)
	{
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
