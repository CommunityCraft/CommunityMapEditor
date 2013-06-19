 package net.xemnias.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public SelecterPanel blockPanel = new SelecterPanel(new String[]{"spritesheet.png"}, "block");
	private JPanel entityPanel = new SelecterPanel(new String[]{"entity.png"}, "entity");
	private JPanel itemPanel = new SelecterPanel(new String[]{"item.png"}, "item");
	private JPanel otherPanel = new SelecterPanel(new String[]{}, "other");
	private final JPanel panel = new JPanel();
	private final JButton btnGomme = new JButton("Gomme");
	private final JButton btnGnrer = new JButton("G\u00E9n\u00E9rer");
	MainFrame parent;
	public final JButton btnNouvelleMap = new JButton("Nouvelle Map");
	private final JButton btnOuvrir = new JButton("Ouvrir");
	
	public File openedMap = null;
	
	public RightPanel(LayoutManager borderLayout, MainFrame m)
	{
		super(borderLayout);
		setLayout(new BorderLayout(0, 0));
		parent = m;
		add(tabbedPane);
		tabbedPane.add(blockPanel, "Terrain et Block");
		tabbedPane.add(entityPanel, "Entités et monstres");
		tabbedPane.add(itemPanel, "Items");
		tabbedPane.add(otherPanel, "Autre");
		
		add(panel, BorderLayout.SOUTH);
		btnGomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getSelectedTab().selectedSprite = null;
			}
		});
		btnOuvrir.addActionListener(new loaderListener(this));
		
		panel.add(btnOuvrir);
		
		panel.add(btnGomme);
		btnGnrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MapWriter writer = new MapWriter(parent.leftPanel.cases, new Dimension(parent.leftPanel.xSize*32, parent.leftPanel.ySize*32));
				writer.buildXMLData();
				if(openedMap != null)
				{
					writer.writeAs(openedMap.getAbsolutePath());
				}
				else
				{
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setVisible(true);
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.setCurrentDirectory(new File("/C:/Work Space Eclipse/CommunityGame/data/map"));
					int returnVal =  fileChooser.showOpenDialog(parent);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						new DialogName(writer, fileChooser.getSelectedFile().getAbsolutePath());
					}
				}

				
			}
		});
		
		panel.add(btnGnrer);
		btnNouvelleMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						new DialogMap(parent);
			}
		});
		
		panel.add(btnNouvelleMap);
		
	}
	
	public SelecterPanel getSelectedTab()
	{
		return (SelecterPanel) tabbedPane.getSelectedComponent();
	}
}

class loaderListener implements ActionListener
{
	private RightPanel parent;
	
	public loaderListener(RightPanel a)
	{
		parent = a;
	}
	
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setVisible(true);
		fileChooser.setCurrentDirectory(new File("/C:/Work Space Eclipse/CommunityGame/data/map"));
		int returnVal =  fileChooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			
			parent.openedMap = fileChooser.getSelectedFile();
			MapLoader loader = new MapLoader(parent.parent.leftPanel.cases, parent, parent.openedMap.getAbsolutePath());

			loader.readMap();
			loader.buildMap(loader.getCases());
		}
	}
}
