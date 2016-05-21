package view;

//import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.LayoutManager2;
import java.awt.Toolkit;
import javax.swing.*;

import model.Square;

public class MainWindow extends JFrame 
{
	private Toolkit tk;
	
	private int width;	
	private int height;
		
	private GamePanel gamePanel;
	
	//Tamanhos
	private Dimension tableDimension;
	
	private Dimension menuDimension;
	
	private Dimension playerPanelDimension;
		
	
	//Funcoes de acesso
	public GamePanel gamePanel()
	{
		return this.gamePanel;
	}
	
	public Dimension getTableDimension()
	{
		return this.tableDimension;
	}
	
	public Dimension getMenuDimension()
	{
		return this.menuDimension;
	}
	
	public Dimension getPlayerPanelDimension()
	{
		return this.playerPanelDimension;
	}
	
	public MainWindow(String title, Square[] squares) 
	{
		/******************************************/
		/*** RECUPERAR INFORMACOES DA TELA ********/
		/******************************************/
		
		this.tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		
		/******************************************/
		/*************** DIMENSOES ****************/
		/******************************************/

		//Dimensao de referencia
		Dimension reference = new Dimension(screenWidth/2, screenWidth/2);
				
		int rest = reference.width % 15;
		
		reference.width = reference.width- rest;
		reference.height = reference.height - rest;
		
		//Dimensao do tabuleiro
		this.tableDimension = new Dimension(reference.width, reference.height);
		
		//Dimensao do menu
		this.menuDimension = new Dimension(reference.width, 20);
		
		//Dimensao do painel do jogador
		this.playerPanelDimension = new Dimension(reference.width * 6/15, reference.height);
		
		//Define a dimensao para a tela principal
		Dimension mainDimension = new Dimension(this.tableDimension.width + this.playerPanelDimension.width, this.tableDimension.height + this.menuDimension.height);
		
		//Adiciono a status bar
		mainDimension.height += 20;
				
		this.setSize(mainDimension.width, mainDimension.height);
				

		/******************************************/
		/*********** TELA DA APLICACAO ************/
		/******************************************/
		this.gamePanel = new GamePanel(mainDimension ,this.tableDimension, this.menuDimension, this.playerPanelDimension, this, squares);
		this.getContentPane().add(this.gamePanel);
		
		
		/******************************************/
		/********* POSICAO DA TELA ***************/
		/******************************************/
		
		int xpos = screenWidth/2 - mainDimension.width/2;
		int ypos = screenHeight/2 - mainDimension.height/2 - 30;
		
		this.setLocation(xpos, ypos);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(title);
		
		this.setVisible(true);
	}
	
}
