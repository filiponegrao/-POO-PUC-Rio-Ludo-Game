package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Random;

public class PlayerPanel extends JPanel implements ActionListener
{
	private JButton playButton;
	final BasicStroke stroke = new BasicStroke(3.0f);
	private Graphics2D g2d;
	private BufferedImage diceImage;
	private TexturePaint diceTexture;
	private int diceId = 1;

	public PlayerPanel(Dimension dimension)
	{
		super();

		this.setBackground(MyColors.myDarkGray);

		this.setLayout(null);

		this.playButton = new JButton("Jogar Dado");
		//		this.playButton.setBackground(Color.white);
		this.playButton.setContentAreaFilled(true);
		this.playButton.setOpaque(false);
		this.playButton.setForeground(MyColors.myDarkGray);
		this.playButton.setBorderPainted(true);
		this.playButton.setLocation(dimension.width/4, dimension.height/5);
		this.playButton.setSize(dimension.width/2, 40);
		this.playButton.addActionListener(this);
		this.add(this.playButton);	
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;

		try
		{
			String ind = "diece" + Integer.toString(this.diceId) + ".png";
			this.diceImage = ImageIO.read(new File(ind));
			
			int x = this.getWidth()/4;
			int y = this.getHeight()/3;
			int w = this.getWidth()/2;
			int h = w;

			this.diceTexture = new TexturePaint(this.diceImage, new Rectangle(x, y, w, h));
			this.g2d.setPaint(this.diceTexture);
			this.g2d.fillRect(x, y, w, h);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	//IMPLEMENTA MÉTODO DA INTERFACE
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Clicked");
		this.repaint();
		
//		int value = this.randInt(1, 6);
//		System.out.println(value);
	}
	
	public int randInt(int min, int max)
	{
		Random rand = null;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
