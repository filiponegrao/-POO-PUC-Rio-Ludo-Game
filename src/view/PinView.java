package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.LudoController;
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
			
			Team barrier = LudoController.sharedInstance.getBarrierOn(p[i].getX(), p[i].getY());
			
			List<PinModel> pins = LudoController.sharedInstance.getPinsOnCoordinate(p[i].getX(), p[i].getY());
			
			if(barrier != null && pins.size() == 2)
			{
				posx = pinDimension.width * p[i].getX() + 5;
				posy = pinDimension.height * p[i].getY() + 5;
				
				drawBarrier(g,posx,posy,diam,diam, p[i].getTeam());
			}
			else if(pins.size() > 1)
			{
				drawMultiplePins(g, pins, diam);
			}
			else
			{
				posx = pinDimension.width * p[i].getX() + 5;
				posy = pinDimension.height * p[i].getY() + 5;
				
				drawPin(g, posx, posy, diam, diam, pins.get(0).getTeam());
			}
	
		}
	}
	
	public static void drawPin(Graphics2D g, int posx, int posy, int width, int height, Team t)
	{
		Ellipse2D e = new Ellipse2D.Double(posx, posy, width, width);
		
		g.setStroke(new BasicStroke(3.0f));

		g.setPaint(t.getColor());
		
		g.fill(e);
		
		if(t.getName().equals("Azul"))
		{
			g.setPaint(MyColors.blueBoader);
			g.draw(e);
		}
		else if(t.getName().equals("Vermelho"))
		{
			g.setPaint(MyColors.redBoader);
			g.draw(e);
		}
		else if(t.getName().equals("Verde"))
		{
			g.setPaint(MyColors.greenBoader);
			g.draw(e);
		}
		else if(t.getName().equals("Amarelo"))
		{
			g.setPaint(MyColors.yellowBoader);
			g.draw(e);
		}		
	}

	public static void drawBarrier(Graphics2D g, int posx, int posy, int width, int height, Team t)
	{
		Ellipse2D e1 = new Ellipse2D.Double(posx - width/3, posy - width/3, width, width);
		Ellipse2D e2 = new Ellipse2D.Double(posx + width/3, posy + width/3, width, width);

		g.setStroke(new BasicStroke(3.0f));

		g.setPaint(t.getColor());
		g.fill(e1);
		
		if(t.getName().equals("Azul"))
		{
			g.setPaint(MyColors.blueBoader);
			g.draw(e1);
		}
		else if(t.getName().equals("Vermelho"))
		{
			g.setPaint(MyColors.redBoader);
			g.draw(e1);
		}
		else if(t.getName().equals("Verde"))
		{
			g.setPaint(MyColors.greenBoader);
			g.draw(e1);
		}
		else if(t.getName().equals("Amarelo"))
		{
			g.setPaint(MyColors.yellowBoader);
			g.draw(e1);
		}
		
		g.setPaint(t.getColor());
		g.fill(e2);
		
		if(t.getName().equals("Azul"))
		{
			g.setPaint(MyColors.blueBoader);
			g.draw(e2);
		}
		else if(t.getName().equals("Vermelho"))
		{
			g.setPaint(MyColors.redBoader);
			g.draw(e2);
		}
		else if(t.getName().equals("Verde"))
		{
			g.setPaint(MyColors.greenBoader);
			g.draw(e2);
		}
		else if(t.getName().equals("Amarelo"))
		{
			g.setPaint(MyColors.yellowBoader);
			g.draw(e2);
		}
	}
	
	public static void drawMultiplePins(Graphics2D g, List<PinModel> pins, int width)
	{
		//Primeiramente divide os pinos por time
		
		List<PinModel> blues = new ArrayList<PinModel>();
		List<PinModel> reds = new ArrayList<PinModel>();
		List<PinModel> greens = new ArrayList<PinModel>();
		List<PinModel> yellows = new ArrayList<PinModel>();

		int i = 0;
		
		for (PinModel pin : pins)
		{
			if(pin.getTeam() == Team.Blue)
			{
				blues.add(pin);
			}
			else if(pin.getTeam() == Team.Red)
			{
				reds.add(pin);
			}
			else if(pin.getTeam() == Team.Green)
			{
				greens.add(pin);
			}
			else if(pin.getTeam() == Team.Yellow)
			{
				yellows.add(pin);
			}
		}
		
		//Define as margens
		int horizontalPadding = 0;
		int verticalPadding = 0;
		
		
		if(blues.size() > 0 && yellows.size() > 0 || reds.size() > 0 && greens.size() > 0)
		{
			horizontalPadding = width/3;
			verticalPadding = width/3;
		}
		
		if(blues.size() > 0 && reds.size() > 0 || greens.size() > 0 && yellows.size() > 0)
		{
			horizontalPadding = width/3;
			verticalPadding = width/3;
		}

		//Agora desenha os vermelhos
		for (PinModel red : reds)
		{
			int posx = (red.getX() * (width+10)) + 5;
			int posy = (red.getY() * (width+10)) + 5;

			Ellipse2D e = new Ellipse2D.Double(posx - horizontalPadding, posy - verticalPadding - (i*8), width, width);

			//Define o contorno
			g.setStroke(new BasicStroke(3.0f));

			g.setPaint(red.getTeam().getColor());
			g.fill(e);

			g.setPaint(MyColors.redBoader);
			g.draw(e);

			i++;
		}
		
		i = 0;
		//Agora desenha os azuis
		for (PinModel blue : blues)
		{
			
			int posx = (blue.getX() * (width+10)) + 5;
			int posy = (blue.getY() * (width+10)) + 5;
			
			if(reds.size() > 0)
			{
				posx += (width/3)*2;
			}
			
			System.out.println(posx);
			
			Ellipse2D e = new Ellipse2D.Double(posx - horizontalPadding, posy + verticalPadding - (i*8), width, width);
			
			//Define o contorno
			g.setStroke(new BasicStroke(3.0f));

			g.setPaint(blue.getTeam().getColor());
			g.fill(e);
			
			g.setPaint(MyColors.blueBoader);
			g.draw(e);
			
			i++;
		}
		
		i = 0;

		
		
		i = 0;
		//Agora desenha os verdes
		for (PinModel green : greens)
		{
			int posx = (green.getX() * (width+10)) + 5;
			int posy = (green.getY() * (width+10)) + 5;
			
			Ellipse2D e = new Ellipse2D.Double(posx - horizontalPadding, posy + verticalPadding - (i*8), width, width);
			
			//Define o contorno
			g.setStroke(new BasicStroke(3.0f));

			g.setPaint(green.getTeam().getColor());
			g.fill(e);
			
			g.setPaint(MyColors.greenBoader);
			g.draw(e);
			
			i++;
		}

		i = 0;
		//Agora desenha os amarelos
		for (PinModel yellow : yellows)
		{
			int posx = (yellow.getX() * (width+10)) + 5;
			int posy = (yellow.getY() * (width+10)) + 5;
			
			if(greens.size() > 0)
			{
				posx += (width/3)*2;
			}
			
			Ellipse2D e = new Ellipse2D.Double(posx - horizontalPadding, posy + verticalPadding - (i*8), width, width);
			
			//Define o contorno
			g.setStroke(new BasicStroke(3.0f));

			g.setPaint(yellow.getTeam().getColor());
			g.fill(e);
			
			g.setPaint(MyColors.yellowBoader);
			g.draw(e);
			
			i++;
		}
	}
}
