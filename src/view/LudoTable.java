package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.EventHandlers;
import controller.LudoController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class LudoTable extends JPanel 
{
	//Model
	private Square[] squares;
	
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension houseDimension;
	private Dimension squareDimension;

	private LudoTableModel ludoTable = new LudoTableModel();
	
	//VISUAL ELEMENTS
	private HouseView houses;
	
	private Triangles triangles;

	private Boolean repainting = true;
	
	public Dimension getSquareDimension()
	{
		return this.squareDimension;
	}
	
	public Graphics2D getGraphics2D()
	{
		return this.g2d;
	}
	
	public LudoTable(int width, int height, Square[] squares)
	{
		super();

		this.squares = squares;
		
		//Propriedades da classe
		this.mainDimension = new Dimension(width, height);
				
		this.setBackground(Color.white);
		
		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.width/15);
		
	}

	public void paintComponent(Graphics g)
	{
		//Inicializa as variáveis necessárias		
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		final BasicStroke stroke = new BasicStroke(3.0f);

		//Desenha os quadrados menores
		for (int i = 0; i < this.squares.length; i++) 
		{
			int x =  this.squares[i].xPosition() * this.squareDimension.width;
			int y = this.squares[i].yPosition() * this.squareDimension.height;
			
			Rectangle2D square = new Rectangle2D.Double(x,y, this.squareDimension.width, this.squareDimension.height);
			
			Boolean isStartSquare = false;
			Color startColor = Color.white;
			
			int coordinateXs[] = new int[] {0, 0, 0};
			int coordinateYs[] = new int[] {0, 0, 0};

			if(squares[i].type() == SquareType.RedRoad)
			{
				this.g2d.setPaint(MyColors.myRed);

			}
			else if(squares[i].type() == SquareType.RedStart)
			{
				this.g2d.setPaint(MyColors.myRed);
				isStartSquare = true;
				startColor = MyColors.myLightRed;
				//coordenandas para triangulo
				coordinateXs = new int[] { (int) ((int) square.getCenterX() + square.getWidth()/4), 
						(int) ((int) square.getMinX() + square.getWidth()/4), 
						(int) ((int) square.getMinX() + square.getWidth()/4)};

				coordinateYs = new int[] { (int) square.getCenterY(), 
						(int) ((int) square.getMinY() + square.getWidth()/4), 
						(int) ((int) square.getMaxY() - square.getWidth()/4)};
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
				//coordenandas para triangulo
				coordinateXs = new int[] { (int) ((int) square.getCenterX()), 
						(int) ((int) square.getMinX() + square.getWidth()/4), 
						(int) ((int) square.getMaxX() - square.getWidth()/4)};

				coordinateYs = new int[] { (int) ((int) square.getCenterY() + square.getWidth()/4), 
						(int) ((int) square.getMinY() + square.getWidth()/4), 
						(int) ((int) square.getMinY() + square.getWidth()/4)};
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
				//coordenandas para triangulo
				coordinateXs = new int[] { (int) ((int) square.getCenterX()), 
						(int) ((int) square.getMaxX() - square.getWidth()/4), 
						(int) ((int) square.getMinX() + square.getWidth()/4)};

				coordinateYs = new int[] { (int) ((int) square.getCenterY() - square.getWidth()/4), 
						(int) ((int) square.getMaxY() - square.getWidth()/4), 
						(int) ((int) square.getMaxY() - square.getWidth()/4)};
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
				//coordenandas para triangulo
				coordinateXs = new int[] { (int) ((int) square.getCenterX() - square.getWidth()/4), 
						(int) ((int) square.getMaxX() - square.getWidth()/4), 
						(int) ((int) square.getMaxX() - square.getWidth()/4)};

				coordinateYs = new int[] { (int) square.getCenterY(), 
						(int) ((int) square.getMinY() + square.getWidth()/4), 
						(int) ((int) square.getMaxY() - square.getWidth()/4)};
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
			this.g2d.setStroke(stroke);
			this.g2d.draw(square);

			if(isStartSquare == true)
			{
				g2d.setPaint(startColor);
				g2d.fillPolygon(coordinateXs, coordinateYs, 3);
			}
		}
		
		this.drawTriangles();
		this.drawHouses();		
		
		//DESENHA AS PEÇAS
		
		PinModel[] reds = LudoController.sharedInstance.getRedPins();
		PinModel[] blues = LudoController.sharedInstance.getBluePins();
		PinModel[] greens = LudoController.sharedInstance.getGreenPins();
		PinModel[] yellows = LudoController.sharedInstance.getYellowPins();


		PinView.drawPins(reds, g2d, this.squareDimension);
		PinView.drawPins(blues, g2d, this.squareDimension);
		PinView.drawPins(greens, g2d, this.squareDimension);
		PinView.drawPins(yellows, g2d, this.squareDimension);
		
		addMouseListener(EventHandlers.getMouseEvent());
		
	}

	public void drawHouses()
	{
		this.houses = new HouseView(this.mainDimension);
		this.houses.createHouses(this.g2d);
	}
	public void drawTriangles()
	{
		this.triangles = new Triangles(this.mainDimension);
		this.triangles.drawTriangles(this.g2d);
	}
	
	//Testing
	public void loopPainting()
	{
		while(this.repainting)
		{
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			    this.repaint();
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
	
	public void rePaint()
	{
		this.repaint();
	}
	
	
}
