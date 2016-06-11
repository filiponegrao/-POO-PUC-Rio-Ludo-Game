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
	
	private SquareView squaresView;

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
		
		addMouseListener(EventHandlers.getMouseEvent());		

	}

	public void paintComponent(Graphics g)
	{
		//Inicializa as variáveis necessárias		
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		this.drawSquares();
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
	}

	public void drawSquares()
	{
		this.squaresView = new SquareView(this.mainDimension);
		this.squaresView.paintSquares(this.g2d, this.squares);
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
