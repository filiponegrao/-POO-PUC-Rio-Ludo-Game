package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.imageio.ImageIO;

import model.PinModel;
import model.Team;


public class PinView
{
	public static void drawPins(PinModel[] p, Graphics2D g, Dimension pinDimension)
	{
		for (int i = 0; i < p.length; i++)
		{
			int posx = 0;
			int posy = 0;
			
			int diam = pinDimension.width - 10;

			posx = pinDimension.width * p[i].getX() + 5;
			posy = pinDimension.height * p[i].getY() + 5;				
			
			drawPin(g,posx,posy,diam,diam, p[i].getTeam());

		}
	}
	
	public static void drawPin(Graphics2D g, int posx, int posy, int width, int height, Team t)
	{
		Ellipse2D e = new Ellipse2D.Double(posx, posy, width, width);
		
		g.setPaint(t.getColor());
		
		g.fill(e);
		
		g.setStroke(new BasicStroke(1.0f));

		try 
		{
			Image shadow = ImageIO.read(new File("pinSombra.png"));
			
			g.drawImage(shadow,posx,posy,width,height, null);
		}
		catch(Exception exc)
		{
			System.out.print(exc.getMessage());
		}
		
	}

}
