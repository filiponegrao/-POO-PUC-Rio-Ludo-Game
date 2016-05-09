package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LudoTable extends JPanel {
	
	private Graphics2D g2d;
	
	private Dimension mainDimension;
	
	private Dimension houseDimension;
	private Dimension squareDimension;
	
	//VISUAL ELEMENTS
	
	private Rectangle2D greenHouse;
	private Rectangle2D yellowHouse;
	private Rectangle2D redHouse;
	private Rectangle2D blueHouse;

	
	public LudoTable(Dimension d)
	{
		//Propriedades da classe
		this.mainDimension = d;
		this.setSize(d);
		this.setBackground(Color.white);
		
		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width * 7/17), (mainDimension.width * 7/17));
		this.squareDimension = new Dimension(mainDimension.width/17, mainDimension.height/17);
		
	}
	
	public void paintComponent(Graphics g)
	{
		//Inicia as variaveis necessarias
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//Pinta a casa verde
		this.g2d.setPaint(Color.green);
		this.greenHouse = new Rectangle.Double(mainDimension.getWidth() - houseDimension.getWidth(), 0, houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.greenHouse);
 		
		//Pinta a casa amarela
		this.g2d.setPaint(Color.yellow);
		this.yellowHouse = new Rectangle.Double(mainDimension.getWidth() - houseDimension.getWidth(), mainDimension.getHeight() - houseDimension.getHeight(), houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.yellowHouse);	
		
		//Pinta a casa Vermelha
		this.g2d.setPaint(Color.red);
		this.redHouse = new Rectangle.Double(0, 0,houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.redHouse);	
		
		//Pinta a casa Azul
		this.g2d.setPaint(Color.blue);
		this.blueHouse = new Rectangle.Double(0, mainDimension.getHeight() - houseDimension.getHeight(), houseDimension.getWidth(), houseDimension.getHeight());	
		this.g2d.fill(this.blueHouse);	
		
		Square[] squares = LudoTableModel.getModel();
		
		//Desenhas os quadrados
		for (int i = 0; i < squares.length; i++) {
			
			Double x =  squares[i].xPosition() * squareDimension.getWidth() ;
			Double y =  squares[i].yPosition() * squareDimension.getHeight();
			Rectangle2D square = new Rectangle2D.Double(x,y,squareDimension.getWidth(), squareDimension.getHeight());
			
			switch (squares[i].team())
			{
			case Red:
				this.g2d.setPaint(Color.red);
				
			case Blue:
				this.g2d.setPaint(Color.blue);
				
			case Yellow:
				this.g2d.setPaint(Color.yellow);
				
			case Green:
				this.g2d.setPaint(Color.green);
				
			case None:
				this.g2d.setPaint(Color.white);

			}
			
			this.g2d.fill(square);
			this.g2d.setPaint(Color.black);
			this.g2d.draw(square);


			
			
		}
		
		
		
	}

}
