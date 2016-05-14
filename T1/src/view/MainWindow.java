package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager2;
import java.awt.Toolkit;
import javax.swing.*;

public class MainWindow extends JFrame 
{

	private Toolkit tk;
	
	private int width;
	
	private int height;
	
	private LudoTable ludotable;
		
	public MainWindow(String title) 
	{
		this.tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		
		/** DEFINE UMA TELA COM LARGURA X, SENDO RAZAO DE 15*/
		
		//Encontra a maior largura antes da razao de 15
		int div = (screenWidth/2) / 15;
		
		//Encontra o quanto passa
		int rest = (screenWidth/2) % 15;
		
		if (rest > 0)
		{
			this.width = (div+1)*15;
			this.height = this.width;		
		}
		else
		{
			this.width = screenWidth/2;
			this.height = this.width;
		}
		
				
		int xpos = screenWidth/2 - this.width/2;
		int ypos = screenHeight/2 - this.height/2;
	
		this.setSize(this.width,this.height+20);
		this.setLocation(xpos, ypos);
		
//		this.setBounds(xpos, ypos, this.width, this.height);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(title);
		
		this.ludotable = new LudoTable(this.width, this.height);	
		
		this.getContentPane().add(this.ludotable);
		
		this.setVisible(true);
	}
	
}
