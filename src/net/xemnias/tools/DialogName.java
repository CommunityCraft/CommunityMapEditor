package net.xemnias.tools;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogName extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public String name;
	public DialogName(final MapWriter w, final String p)
	{
		getContentPane().setLayout(null);
		
		JLabel lblNomDeVotre = new JLabel("Nom de votre map :");
		lblNomDeVotre.setBounds(10, 11, 125, 14);
		getContentPane().add(lblNomDeVotre);
		
		textField = new JTextField();
		textField.setBounds(118, 8, 158, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				name = textField.getText()+".map";
				w.writeAs(p+"/"+name);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(128, 36, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblmap = new JLabel(".map");
		lblmap.setBounds(282, 11, 46, 14);
		getContentPane().add(lblmap);
		setAlwaysOnTop(true);
		setSize(300, 100);
		this.setVisible(true);
	}
}
