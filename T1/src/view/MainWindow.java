package view;

import java.awt.Dimension;
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
		
		this.width = screenWidth/2;
		this.height = screenWidth/2;		
				
		int xpos = screenWidth/2 - this.width/2;
		int ypos = screenHeight/2 - this.height/2;
	
//		this.setSize(this.width,this.height);
//		this.setLocation(xpos, ypos);
		
		this.setBounds(xpos, ypos, this.width, this.height);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle(title);
		
		this.ludotable = new LudoTable(new Dimension(this.width, this.height));
		
		this.getContentPane().add(this.ludotable);
		
		this.setVisible(true);
	}
	
}
