package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

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
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);
		
		System.out.println(this.mainDimension);
		System.out.println("Largura da casa: " + Integer.toString(this.houseDimension.width));
		System.out.println("Altura da casa: " + Integer.toString(this.houseDimension.height));	
		System.out.println("Largura do tile: " + Integer.toString(this.squareDimension.width));
		System.out.println("Altura do tile: " + Integer.toString(this.squareDimension.height));
	}
	
	public void paintComponent(Graphics g)
	{
		//Inicializa as variáveis necessárias
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//criei essas paradas aqui pra testar os tamanhos
		int houseWidth = this.houseDimension.width;
		int houseHeight = this.houseDimension.height;
		
		int mainWidth = this.mainDimension.width;
		int mainHeight = this.mainDimension.height;
		
		//Pinta a casa Vermelha
		this.g2d.setPaint(MyColors.myRed);
		this.redHouse = new Rectangle2D.Double(0, 0,houseWidth, houseHeight);		
		this.g2d.fill(this.redHouse);
		
		//Pinta a casa Verde
		this.g2d.setPaint(MyColors.myGreen);
		this.greenHouse = new Rectangle2D.Double(mainWidth - houseWidth, 0, houseWidth, houseHeight);		
		this.g2d.fill(this.greenHouse); 		
		//Pinta a casa Amarela
		this.g2d.setPaint(MyColors.myYellow);
		this.yellowHouse = new Rectangle2D.Double(mainWidth - houseWidth, mainHeight - houseHeight, houseWidth, houseHeight);		
		this.g2d.fill(this.yellowHouse);	
			
		//Pinta a casa Azul
		this.g2d.setPaint(MyColors.myBlue);
		this.blueHouse = new Rectangle2D.Double(0, mainHeight - houseHeight, houseWidth, houseHeight);	
		this.g2d.fill(this.blueHouse);	
		
		Square[] squares = this.ludoTable.getModel();
		
		Image rockTile;
		
		//Desenhas os quadrados
		for (int i = 0; i < squares.length; i++) 
		{
			//mudei aqui pra int
			int x =  squares[i].xPosition() * squareDimension.width;
			int y =  squares[i].yPosition() * squareDimension.height;
			Rectangle2D square = new Rectangle2D.Double(x,y,squareDimension.width, squareDimension.height);
			Ellipse2D startPoint;
			Boolean isStartSquare = false;
			Color startColor = Color.white;
						
			if(squares[i].type() == SquareType.RedRoad)
			{
				this.g2d.setPaint(MyColors.myRed);
			}
			else if(squares[i].type() == SquareType.RedStart)
			{
				this.g2d.setPaint(MyColors.myRed);
				isStartSquare = true;
				startColor = MyColors.myLightRed;
			}
			else if(squares[i].type() == SquareType.GreenRoad)
			{
				this.g2d.setPaint(MyColors.myGreen);
			}
			else if(squares[i].type() == SquareType.GreenStart)
			{
				this.g2d.setPaint(MyColors.myGreen);
				isStartSquare = true;
				startColor = MyColors.myLightGreen;
			}
			else if(squares[i].type() == SquareType.BlueRoad)
			{
				this.g2d.setPaint(MyColors.myBlue);
			}
			else if(squares[i].type() == SquareType.BlueStart)
			{
				this.g2d.setPaint(MyColors.myBlue);
				isStartSquare = true;
				startColor = MyColors.myLightBlue;
			}
			else if(squares[i].type() == SquareType.YellowRoad)
			{
				this.g2d.setPaint(MyColors.myYellow);
			}
			else if(squares[i].type() == SquareType.YellowStart)
			{
				this.g2d.setPaint(MyColors.myYellow);
				isStartSquare = true;
				startColor = MyColors.myLightYellow;
			}
			else if(squares[i].type() == SquareType.SafePoint)
			{
				this.g2d.setPaint(Color.black);
			}
			else
			{
				this.g2d.setPaint(Color.white);
			}
			
			this.g2d.fill(square);
			this.g2d.setPaint(Color.black);
			this.g2d.draw(square);
			
			if(isStartSquare == true)
			{
				startPoint = new Ellipse2D.Double((square.getWidth())/4,(square.getWidth())/4,(square.getWidth())/2, (square.getHeight())/2);
				startPoint.setFrameFromCenter(square.getCenterX(), square.getCenterY(), square.getX() + square.getWidth()/4, square.getY() + square.getWidth()/4);
				g2d.setPaint(startColor);
				g2d.fill(startPoint);		
			}
			
			//para carregar uma imagem
//			try
//			{
//				rockTile = ImageIO.read(new File("rockTile.jpg"));
//				this.g2d.drawImage(rockTile, 0, 0, null);
//			}
//			catch(IOException e)
//			{
//				System.out.println(e.getMessage());
//			    System.exit(1);
//			}
		}
			
	}

}
