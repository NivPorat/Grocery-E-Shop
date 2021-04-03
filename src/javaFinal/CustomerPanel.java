package javaFinal;

import java.awt.Color;

import javax.swing.JPanel;

public class CustomerPanel extends JPanel
{

	JPanel panel = new JPanel();
	
	
	public CustomerPanel() 
	{
		CreatePanel();
	}
	
	public void CreatePanel() 
	{
		panel.setBounds(400, 200, 1200, 1000);
		panel.setBackground(Color.GREEN);
		panel.setVisible(true);
	}
	
}
