package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Triangles 
{
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension houseDimension;
	private Dimension squareDimension;

	public Triangles(Dimension d)
	{
		this.mainDimension = new Dimension(d.width, d.height);	

		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);		
	}

	public void drawTriangles(Graphics2D g)
	{
		this.g2d = g;

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
	}
}
