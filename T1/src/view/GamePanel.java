package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private JFrame jframe;
	
	private LudoTable ludoTable;
	private PlayerPanel playerPanel;
	private MenuPanel menuPanel;
		
	public GamePanel(Dimension mainDimension, Dimension tableDimension, Dimension menuDimension, Dimension panelDimension, JFrame jframe)
	{
		this.jframe = jframe;
		
		this.setLayout(new BorderLayout());
		
		this.ludoTable = new LudoTable(tableDimension.width, tableDimension.height);
		this.add(this.ludoTable, BorderLayout.CENTER);
		this.ludoTable.setLocation(0, 20);
	
		
		this.menuPanel = new MenuPanel();
		this.jframe.setJMenuBar(this.menuPanel);
		this.menuPanel.setLocation(100, 100);
				
		
	}
}
