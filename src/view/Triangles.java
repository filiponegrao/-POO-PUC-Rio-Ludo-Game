package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Triangles 
{
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension squareDimension;

	public Triangles(Dimension d)
	{
		this.mainDimension = new Dimension(d.width, d.height);	
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);		
	}

	public void drawTriangles(Graphics2D g)
	{
		this.g2d = g;

		//coordenadas x e y necessárias para renderizar triangulos
		int x1, x2, x3, y1, y2, y3;
		x1 = (int) (this.mainDimension.width - this.mainDimension.width/2.0); 
		x2 = (int) ((this.mainDimension.width/2) + (this.squareDimension.width * 1.5)); 
		x3 = (int) ((this.mainDimension.width/2) - (this.squareDimension.width * 1.5)); 

		y1 = (int) (this.mainDimension.height - this.mainDimension.height/2.0); 
		y2 = (int) ((this.mainDimension.height/2) + (this.squareDimension.height * 1.5)); 
		y3 = (int) ((this.mainDimension.height/2) - (this.squareDimension.height * 1.5));
				
		//Triangulo central vermelho
		int redCoordX[] = new int[] {x1, x3, x3};
		int redCoordY[] = new int[] {y1, y2, y3};
		g2d.setPaint(MyColors.myRed);
		g2d.fillPolygon(redCoordX, redCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(redCoordX, redCoordY, 3);

		//Triangulo central verde
		int greenCoordX[] = new int[] {x1, x3, x2};
		int greenCoordY[] = new int[] {y1, y3, y3};
		g2d.setPaint(MyColors.myGreen);
		g2d.fillPolygon(greenCoordX, greenCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(greenCoordX, greenCoordY, 3);

		//Triangulo central amarelo
		int yellowCoordX[] = new int[] {x1, x2, x2};
		int yellowCoordY[] = new int[] {y1, y3, y2};
		g2d.setPaint(MyColors.myYellow);
		g2d.fillPolygon(yellowCoordX, yellowCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(yellowCoordX, yellowCoordY, 3);

		//Triangulo central azul		
		int blueCoordX[] = new int[] {x1,x2,x3};
		int blueCoordY[] = new int[] {y1,y2,y2};
		g2d.setPaint(MyColors.myBlue);
		g2d.fillPolygon(blueCoordX, blueCoordY, 3);
		g2d.setPaint(Color.black);
		g2d.drawPolygon(blueCoordX, blueCoordY, 3);		
	}
}
