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
	
	public LudoTable(int width, int height)
	{
		//Propriedades da classe
		this.mainDimension = new Dimension(width, height);
		this.setSize(width,height);
		this.setBackground(Color.white);
		
		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);
		
//		System.out.println(this.mainDimension);
//		System.out.println("Largura da casa: " + Integer.toString(this.houseDimension.width));
//		System.out.println("Altura da casa: " + Integer.toString(this.houseDimension.height));	
//		System.out.println("Largura do tile: " + Integer.toString(this.squareDimension.width));
//		System.out.println("Altura do tile: " + Integer.toString(this.squareDimension.height));
	}
	
	public void paintComponent(Graphics g)
	{
		//Inicializa as variáveis necessárias
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//criei essas paradas aqui pra testar os tamanhos
		Double houseWidth = (double) this.houseDimension.width;
		Double houseHeight = (double) this.houseDimension.height;
		
		Double mainWidth = (double) this.mainDimension.width;
		Double mainHeight = (double) this.mainDimension.height;
		
		final BasicStroke stroke = new BasicStroke(3.0f);
		
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
		
		//contorno da casa vermelha
		Rectangle2D redOutline = new Rectangle2D.Double();
		redOutline.setFrame(this.redHouse);
		redOutline.setRect(redHouse.getCenterX() - redHouse.getWidth()/4,redHouse.getCenterY() - redHouse.getHeight()/4,redHouse.getWidth()/2,redHouse.getHeight()/2);
		g2d.setPaint(MyColors.myLightRed);
		g2d.fill(redOutline);
		g2d.setPaint(MyColors.myDarkRed);
		g2d.setStroke(stroke);
		g2d.draw(redOutline);

		//contorno da casa verde
		Rectangle2D greenOutline = new Rectangle2D.Double();
		greenOutline.setFrame(this.greenHouse);
		greenOutline.setRect(greenHouse.getCenterX() - greenHouse.getWidth()/4,greenHouse.getCenterY() - greenHouse.getHeight()/4,greenHouse.getWidth()/2,greenHouse.getHeight()/2);
		g2d.setPaint(MyColors.myLightGreen);
		g2d.fill(greenOutline);
		g2d.setPaint(MyColors.myDarkGreen);
		g2d.setStroke(stroke);
		g2d.draw(greenOutline);

		//contorno da casa amarela
		Rectangle2D yellowOutline = new Rectangle2D.Double();
		yellowOutline.setFrame(this.yellowHouse);
		yellowOutline.setRect(yellowHouse.getCenterX() - yellowHouse.getWidth()/4,yellowHouse.getCenterY() - yellowHouse.getHeight()/4,yellowHouse.getWidth()/2,yellowHouse.getHeight()/2);
		g2d.setPaint(MyColors.myLightYellow);
		g2d.fill(yellowOutline);
		g2d.setPaint(MyColors.myDarkYellow);
		g2d.setStroke(stroke);
		g2d.draw(yellowOutline);
		
		//contorno da casa azul
		Rectangle2D blueOutline = new Rectangle2D.Double();
		blueOutline.setFrame(this.blueHouse);
		blueOutline.setRect(blueHouse.getCenterX() - blueHouse.getWidth()/4,blueHouse.getCenterY() - blueHouse.getHeight()/4,blueHouse.getWidth()/2,blueHouse.getHeight()/2);
		g2d.setPaint(MyColors.myLightBlue);
		g2d.fill(blueOutline);
		g2d.setPaint(MyColors.myDarkBlue);
		g2d.setStroke(stroke);
		g2d.draw(blueOutline);
							
				
		Square[] squares = this.ludoTable.getModel();
		//para testar img
//		Image rockTile;
		
		//Desenha os quadrados
		for (int i = 0; i < squares.length; i++) 
		{
			//COLOQUEI DOUBLE EM TUDO TESTANDO, CONFERIR. TIRAR O + 5.0
			Double x =  (double) (((double)squares[i].xPosition() * (double)squareDimension.width) + 5.0);
			Double y =  (double) (((double)squares[i].yPosition() * (double)squareDimension.height) + 5.0);
			Rectangle2D square = new Rectangle2D.Double(x,y,(double)squareDimension.width, (double)squareDimension.height);
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
			this.g2d.draw(square);
			
			if(isStartSquare == true)
			{
				g2d.setPaint(startColor);
				g2d.fillPolygon(coordinateXs, coordinateYs, 3);
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
		
		//calculando coordenadas pro triangulo azul maior mas só funciona quando coloco valor exato =/ 
//		int x1, x2, x3, y1, y2, y3;
//		x1 = this.mainDimension.width - this.mainDimension.width/2;
//		x2 = (int) ((this.mainDimension.width/2) + (this.squareDimension.width * 1.5));
//		x3 = (int) ((this.mainDimension.width/2) - (this.squareDimension.width * 1.5));
//		
//		y1 = this.mainDimension.height - this.mainDimension.height/2;
//		y2 = (int) ((this.mainDimension.height/2) + (this.squareDimension.height * 1.5));
//		y3 = (int) ((this.mainDimension.height/2) - (this.squareDimension.height * 1.5));
//		
		//Triangulo central vermelho
		int redCoordX[] = new int[] {320, 257, 257};
		int redCoordY[] = new int[] {320, 383, 257};

		g2d.setPaint(MyColors.myRed);
		g2d.fillPolygon(redCoordX, redCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(redCoordX, redCoordY, 3);
		
		//Triangulo central verde
		int greenCoordX[] = new int[] {320, 257, 383};
		int greenCoordY[] = new int[] {320, 257, 257};

		g2d.setPaint(MyColors.myGreen);
		g2d.fillPolygon(greenCoordX, greenCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(greenCoordX, greenCoordY, 3);
		
		//Triangulo central amarelo
		int yellowCoordX[] = new int[] {320, 383, 383};
		int yellowCoordY[] = new int[] {320, 257, 383};

		g2d.setPaint(MyColors.myYellow);
		g2d.fillPolygon(yellowCoordX, yellowCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(yellowCoordX, yellowCoordY, 3);
		
		//Triangulo central azul
		int blueCoordX[] = new int[] {320, 383, 257};
		int blueCoordY[] = new int[] {320, 383, 383};
		
		g2d.setPaint(MyColors.myBlue);
		g2d.fillPolygon(blueCoordX, blueCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(blueCoordX, blueCoordY, 3);
	}
}
