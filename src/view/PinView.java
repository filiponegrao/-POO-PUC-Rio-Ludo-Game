package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class PinView {
	
	public static void printPin(Graphics2D g, int posx, int posy, int width, int height)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File("pin.png"));

			TexturePaint pin = new TexturePaint(image, new Rectangle(posx, posy, width, height));
			
			g.setPaint(pin);
			g.fillRect(posx, posy, width, height);
			
		}
		catch (IOException ex)
		{
			System.out.println("ta dando merda");
			System.out.println(ex);
		}
		
		
	}

}
