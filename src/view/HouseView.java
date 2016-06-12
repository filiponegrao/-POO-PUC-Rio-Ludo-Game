package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class HouseView 
{
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension houseDimension;

	private Rectangle2D greenHouse;
	private Rectangle2D yellowHouse;
	private Rectangle2D redHouse;
	private Rectangle2D blueHouse;

	public HouseView(Dimension d)
	{
		this.mainDimension = new Dimension(d.width, d.height);	
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
	}

	public void createHouses(Graphics2D g)
	{
		this.g2d = g;
		final BasicStroke stroke = new BasicStroke(4.0f);

		//Pinta a casa Vermelha
		this.g2d.setPaint(MyColors.myRed);
		this.redHouse = new Rectangle2D.Double(0, 0,this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.redHouse);
		this.g2d.setPaint(Color.black);
		this.g2d.draw(this.redHouse);

		//Pinta a casa Verde
		this.g2d.setPaint(MyColors.myGreen);
		this.greenHouse = new Rectangle2D.Double(this.mainDimension.width - this.houseDimension.width, 0, this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.greenHouse);
		this.g2d.setPaint(Color.black);
		this.g2d.draw(this.greenHouse);

		//Pinta a casa Amarela
		this.g2d.setPaint(MyColors.myYellow);
		this.yellowHouse = new Rectangle2D.Double(this.mainDimension.width - this.houseDimension.width, this.mainDimension.height - this.houseDimension.height, this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.yellowHouse);
		this.g2d.setPaint(Color.black);
		this.g2d.draw(this.yellowHouse);

		//Pinta a casa Azul
		this.g2d.setPaint(MyColors.myBlue);
		this.blueHouse = new Rectangle2D.Double(0, this.mainDimension.height - this.houseDimension.height, this.houseDimension.width, this.houseDimension.height);	
		this.g2d.fill(this.blueHouse);
		this.g2d.setPaint(Color.black);
		this.g2d.draw(this.blueHouse);

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
	}
}
