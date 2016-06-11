package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Square;

public class GamePanel extends JPanel 
{

	private JFrame jframe;
	
	private LudoTable ludoTable;
	private PlayerPanel playerPanel;
	private MenuPanel menuPanel;
	
		
	//Funcoes de acesso
	public LudoTable ludoTable()
	{
		return this.ludoTable;
	}
	
	public PlayerPanel playerPanel()
	{
		return this.playerPanel;
	}
	
	public GamePanel(Dimension mainDimension, Dimension tableDimension, Dimension menuDimension, Dimension panelDimension, JFrame jframe, Square[] squares)
	{
		this.jframe = jframe;
		
	    setLayout(null);
				
		this.ludoTable = new LudoTable(tableDimension.width, tableDimension.height, squares);
		this.ludoTable.setLocation(0, 0);
		this.ludoTable.setSize(tableDimension);
		this.add(this.ludoTable);
	
		this.menuPanel = new MenuPanel();
		this.jframe.setJMenuBar(this.menuPanel);
					
		this.playerPanel = new PlayerPanel(panelDimension);
		this.playerPanel.setLocation(tableDimension.width, 0);
		this.playerPanel.setSize(panelDimension);
		this.add(this.playerPanel);

	}
}
