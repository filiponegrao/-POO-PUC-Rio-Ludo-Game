package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class HouseView 
{
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension houseDimension;
//	private Dimension squareDimension;
	
	private Rectangle2D greenHouse;
	private Rectangle2D yellowHouse;
	private Rectangle2D redHouse;
	private Rectangle2D blueHouse;

	public HouseView(Dimension d, Graphics2D g)
	{
		this.mainDimension = new Dimension(d.width, d.height);	
		this.g2d = (Graphics2D)g;
		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));

		this.createHouses();	
	}
	
	public void createHouses()
	{
		//Pinta a casa Vermelha
		this.g2d.setPaint(MyColors.myRed);
		this.redHouse = new Rectangle2D.Double(0, 0,this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.redHouse);

		//Pinta a casa Verde
		this.g2d.setPaint(MyColors.myGreen);
		this.greenHouse = new Rectangle2D.Double(this.mainDimension.width - this.houseDimension.width, 0, this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.greenHouse);

		//Pinta a casa Amarela
		this.g2d.setPaint(MyColors.myYellow);
		this.yellowHouse = new Rectangle2D.Double(this.mainDimension.width - this.houseDimension.width, this.mainDimension.height - this.houseDimension.height, this.houseDimension.width, this.houseDimension.height);		
		this.g2d.fill(this.yellowHouse);

		//Pinta a casa Azul
		this.g2d.setPaint(MyColors.myBlue);
		this.blueHouse = new Rectangle2D.Double(0, this.mainDimension.height - this.houseDimension.height, this.houseDimension.width, this.houseDimension.height);	
		this.g2d.fill(this.blueHouse);
	}
}
