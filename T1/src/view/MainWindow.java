package view;

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
	private TeamView teams;
	
	public JPanel panel;
	
	public MainWindow(String title) 
	{
		this.tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		
		this.width = 640;
		this.height = 640;		
				
		int xpos = screenWidth/2 - this.width/2;
		int ypos = screenHeight/2 - this.height/2;
	
//		this.setSize(this.width,this.height);
//		this.setLocation(xpos, ypos);
		
		this.setBounds(xpos, ypos, this.width, this.height);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle(title);
		
		this.ludotable = new LudoTable(this.width, this.height);
		this.teams = new TeamView(new Dimension(this.width, this.height));
		
		//Verificar como adicionar dois JPanel em um JFrame usando LayoutManager
//		this.panel = new JPanel(new GridLayout(1,1));	
//		this.panel.add(this.ludotable);
//		this.panel.add(this.teams);
//		this.getContentPane().add(this.panel);
		
		this.getContentPane().add(this.ludotable);
		
		this.setVisible(true);
	}
	
}
