package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel
{
	public PlayerPanel(Dimension dimension)
	{
		super();
		
		this.setBackground(MyColors.myDarkGray);

		System.out.print(dimension);
	}
	
}
