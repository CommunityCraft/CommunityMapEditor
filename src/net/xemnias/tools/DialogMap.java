package net.xemnias.tools;

import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogMap extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	
	public DialogMap(MainFrame f)
	{
		getContentPane().setLayout(null);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(66, 37, 70, 20);
		getContentPane().add(spinner);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(66, 74, 70, 20);
		getContentPane().add(spinner_1);
		
		JLabel lblXs = new JLabel("X Size :");
		lblXs.setBounds(10, 40, 46, 14);
		getContentPane().add(lblXs);
		
		JLabel lblYSize = new JLabel("Y Size");
		lblYSize.setBounds(10, 77, 46, 14);
		getContentPane().add(lblYSize);
		
		JLabel lblTiles = new JLabel("Tiles");
		lblTiles.setBounds(146, 77, 46, 14);
		getContentPane().add(lblTiles);
		
		JLabel label = new JLabel("Tiles");
		label.setBounds(146, 40, 46, 14);
		getContentPane().add(label);
		
		JLabel lblNouvelleMap = new JLabel("Nouvelle Map");
		lblNouvelleMap.setBounds(66, 11, 126, 14);
		getContentPane().add(lblNouvelleMap);
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.leftPanel.cases = new Case[(Integer)spinner.getValue()][(Integer)spinner_1.getValue()];
				parent.leftPanel.xSize = (Integer)spinner.getValue();
				parent.leftPanel.ySize = (Integer)spinner_1.getValue();
				
				for(int x = 0; x < parent.leftPanel.xSize; x++)
				{
					for(int y = 0; y < parent.leftPanel.ySize; y++)
					{
						parent.leftPanel.cases[x][y] = new Case(x*32, y*32);
					}
				}
				setVisible(false);
				parent.leftPanel.setPreferredSize(new Dimension(parent.leftPanel.xSize*32, parent.leftPanel.ySize*32));

				parent.leftPanel.updateUI();
			}
		});
		btnCrer.setBounds(200, 11, 89, 83);
		getContentPane().add(btnCrer);
		parent = f;
		setAlwaysOnTop(true);
		setSize(300, 100);
		setVisible(true);
	}
}
