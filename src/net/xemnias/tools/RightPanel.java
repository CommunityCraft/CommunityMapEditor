package net.xemnias.tools;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RightPanel extends JPanel {

	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public SelecterPanel blockPanel = new SelecterPanel("spritesheet.png");
	private JPanel entityPanel = new JPanel();
	private JPanel itemPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	private final JPanel panel = new JPanel();
	private final JButton btnGomme = new JButton("Gomme");
	
	public RightPanel(LayoutManager borderLayout)
	{
		super(borderLayout);
		setLayout(new BorderLayout(0, 0));
		
		add(tabbedPane);
		tabbedPane.add(blockPanel, "Terrain et Block");
		tabbedPane.add(entityPanel, "Entités et monstres");
		tabbedPane.add(itemPanel, "Items");
		tabbedPane.add(otherPanel, "Autre");
		
		add(panel, BorderLayout.SOUTH);
		btnGomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				blockPanel.selectedSprite = null;
			}
		});
		
		panel.add(btnGomme);
		
	}
}
