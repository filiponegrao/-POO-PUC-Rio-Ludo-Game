package view;
import model.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.LudoController;

import java.util.Random;

public class PlayerPanel extends JPanel implements ActionListener
{
	private JButton playButton;
	
	private Graphics2D g2d;

	private int diceValue;

	private JLabel teamLabel;
	
	private String labelText = "Turno do jogador: ";
	
	/** Propriedades do dado */
	
	private BufferedImage diceImage;
	
	private TexturePaint diceTexture;
	
	private Dimension diceDimension;
	
	private Point dicePosition;
	
	private DiceModel dice = new DiceModel();
	
	public PlayerPanel(Dimension dimension)
	{
		super();

		this.setBackground(MyColors.myDarkGray);

		this.setLayout(null);

		this.playButton = new JButton("Jogar Dado");
		this.playButton.setContentAreaFilled(true);
		this.playButton.setOpaque(false);
		this.playButton.setForeground(MyColors.myDarkGray);
		this.playButton.setBorderPainted(true);
		this.playButton.setLocation(dimension.width/4, dimension.height/5);
		this.playButton.setSize(dimension.width/2, 40);
		this.playButton.addActionListener(this);
		this.add(this.playButton);	
		
		int x = this.getWidth()/4;
		int y = this.getHeight()/3;
		int w = this.getWidth()/2;
		int h = w;
		
		this.dicePosition = new Point(x,y);
		this.diceDimension = new Dimension(w,h);
		
		this.teamLabel = new JLabel(this.labelText + "Azul");		
		this.teamLabel.setOpaque(true);
		this.teamLabel.setLocation(20, dimension.height - 200);
		this.teamLabel.setSize(dimension.width - 40, 60);
		this.teamLabel.setForeground(MyColors.myBlue);
		this.teamLabel.setBackground(Color.white);
		this.teamLabel.setFont(new Font("Helvetica", 0, 20));
		this.add(this.teamLabel);
			
	}

	public void setLabelTeam(Team team)
	{
		this.teamLabel.setText(this.labelText + team.getName());
	}
	
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		this.diceValue = LudoController.sharedInstance.getDiceValue();
		try
		{
			int x = this.getWidth()/4;
			int y = this.getHeight()/3;
			int w = this.getWidth()/2;
			int h = w;
			
			this.dicePosition = new Point(x,y);
			this.diceDimension = new Dimension(w,h);
			
			String ind = "dice" + Integer.toString(this.diceValue) + ".png";
			this.diceImage = ImageIO.read(new File(ind));
			this.diceTexture =	new TexturePaint(this.diceImage, new Rectangle(this.dicePosition.x,
								this.dicePosition.y, this.diceDimension.width, this.diceDimension.height));
			
			this.g2d.setPaint(this.diceTexture);
			this.g2d.fillRect(this.dicePosition.x, this.dicePosition.y, this.diceDimension.width, this.diceDimension.height);
		}	
		catch(IOException e)
		{
//			System.out.println(e.getMessage());
//			System.exit(1);
		}
	}
	

	//IMPLEMENTA MÉTODO DA INTERFACE
	public void actionPerformed(ActionEvent e) 
	{		
		this.dice.playDice();
		this.diceValue = LudoController.sharedInstance.getDiceValue();
		this.repaint();
	}	
}
