/**
 * 
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import model.Square;
import model.SquareType;

/**
 * @author fernandacarvalho
 *
 *
 */
public class SquareView 
{
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension squareDimension;
	private Square[] squares;
	
	public SquareView (Dimension d)
	{
		this.mainDimension = new Dimension(d.width, d.height);	
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);	
	}
	
	public void paintSquares (Graphics2D g, Square[] squares)
	{
		this.g2d = g;
		this.squares = squares;
		
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
	}
}
