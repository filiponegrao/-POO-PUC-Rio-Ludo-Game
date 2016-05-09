package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LudoTable extends JPanel 
{
	
	private Graphics2D g2d;
	
	private Dimension mainDimension;
	
	private Dimension houseDimension;
	private Dimension squareDimension;
	
	//VISUAL ELEMENTS
	
	private Rectangle2D greenHouse;
	private Rectangle2D yellowHouse;
	private Rectangle2D redHouse;
	private Rectangle2D blueHouse;

	private LudoTableModel ludoTable = new LudoTableModel();
	
	public LudoTable(Dimension d)
	{
		//Propriedades da classe
		this.mainDimension = d;
		this.setSize(d);
		this.setBackground(Color.white);
		
		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width * 6/15), (mainDimension.width * 6/15));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);
		
	}
	
	public void paintComponent(Graphics g)
	{
		//Inicia as variaveis necessarias
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//Pinta a casa verde
		this.g2d.setPaint(MyColors.myGreen);
		this.greenHouse = new Rectangle2D.Double(mainDimension.getWidth() - houseDimension.getWidth(), 0, houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.greenHouse);
 		
		//Pinta a casa amarela
		this.g2d.setPaint(MyColors.myYellow);
		this.yellowHouse = new Rectangle2D.Double(mainDimension.getWidth() - houseDimension.getWidth(), mainDimension.getHeight() - houseDimension.getHeight(), houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.yellowHouse);	
		
		//Pinta a casa Vermelha
		this.g2d.setPaint(MyColors.myRed);
		this.redHouse = new Rectangle2D.Double(0, 0,houseDimension.getWidth(), houseDimension.getHeight());		
		this.g2d.fill(this.redHouse);	
		
		//Pinta a casa Azul
		this.g2d.setPaint(MyColors.myBlue);
		this.blueHouse = new Rectangle2D.Double(0, mainDimension.getHeight() - houseDimension.getHeight(), houseDimension.getWidth(), houseDimension.getHeight());	
		this.g2d.fill(this.blueHouse);	
		
		Square[] squares = this.ludoTable.getModel();
		
		//Desenhas os quadrados
		for (int i = 0; i < squares.length; i++) {
			
			Double x =  squares[i].xPosition() * squareDimension.getWidth() ;
			Double y =  squares[i].yPosition() * squareDimension.getHeight();
			Rectangle2D square = new Rectangle2D.Double(x,y,squareDimension.getWidth(), squareDimension.getHeight());
			
			System.out.println(squares[i].type());
			
//			switch (squares[i].type())
//			{
//				case RedRoad:
//					this.g2d.setPaint(MyColors.myRed);
//
//				case BlueRoad:
//					this.g2d.setPaint(MyColors.myBlue);
//
//				case YellowRoad:
//					this.g2d.setPaint(MyColors.myYellow);
//
//				case GreenRoad:
//					this.g2d.setPaint(MyColors.myGreen);
//
//				case Normal:
//					this.g2d.setPaint(Color.white);
//				
//				default:
//					this.g2d.setPaint(Color.white);
//			}
			
			if(squares[i].type() == SquareType.RedRoad)
			{
				this.g2d.setPaint(MyColors.myRed);
			}
			else if(squares[i].type() == SquareType.GreenRoad)
			{
				this.g2d.setPaint(MyColors.myGreen);
			}
			else if(squares[i].type() == SquareType.BlueRoad)
			{
				this.g2d.setPaint(MyColors.myBlue);
			}
			else if(squares[i].type() == SquareType.YellowRoad)
			{
				this.g2d.setPaint(MyColors.myYellow);
			}
			else
			{
				this.g2d.setPaint(Color.white);
			}
			
			this.g2d.fill(square);
			this.g2d.setPaint(Color.black);
			this.g2d.draw(square);
		
		}
			
	}

}
