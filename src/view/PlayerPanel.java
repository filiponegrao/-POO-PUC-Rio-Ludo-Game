package view;
import model.*;
import socket.SocketController;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LudoController;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class PlayerPanel extends JPanel implements ActionListener, Observer
{
	private JButton playButton;
	
	private Graphics2D g2d;

	private Dimension mainDimension;

	private JLabel teamLabel;
	
	private String labelText = "Turno do jogador: ";
	
	private Team observedTeam;
	
	/** Propriedades do dado */
	
	private BufferedImage diceImage;
	
	private TexturePaint diceTexture;
	
	private Dimension diceDimension;
	
	private Point dicePosition;
	
	private int diceValue;
	
	private DiceModel dice = new DiceModel();
	
	/** Dados do jogador **/
	private JLabel playerName;
	private JLabel playerTeam;
	
	public PlayerPanel(Dimension dimension)
	{
		super();
		
		Team myTeam = SocketController.sharedInstance().myTeam;
		this.customByTeam(myTeam);

		this.setLayout(null);

		this.playButton = new JButton("Jogar Dado");
		this.playButton.setContentAreaFilled(false);
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
		
		this.mainDimension = new Dimension(dimension);
				
		this.teamLabel = new JLabel(this.labelText + "Azul");		
		this.teamLabel.setLocation(20, dimension.height - dimension.height/5 * 2);
		this.teamLabel.setSize(dimension.width - 40, 40);
		this.teamLabel.setForeground(Color.darkGray);
		this.teamLabel.setBackground(MyColors.myLightBlue);
		this.teamLabel.setOpaque(true);
		this.teamLabel.setFont(new Font("Helvetica", 0, 16));
		this.teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		this.teamLabel.setBorder(new RoundedBorder(15));
		this.add(this.teamLabel);	
		
		String nickname = SocketController.sharedInstance().myNickname;
		Team team = SocketController.sharedInstance().myTeam;
		
		this.playerName = new JLabel("Olá " + nickname + ",");
		this.playerName.setLocation(20, dimension.height/12);
		this.playerName.setSize(dimension.width - 40, 40);
		this.playerName.setForeground(Color.WHITE);
		this.playerName.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.playerName);
		
		this.playerTeam = new JLabel("Seu time é: " + team.getName());
		this.playerTeam.setLocation(20, dimension.height/10 + 10);
		this.playerTeam.setSize(dimension.width - 40, 40);
		this.playerTeam.setForeground(Color.WHITE);
		this.playerTeam.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.playerTeam);
	}
	
	public DiceModel getDiceModel()
	{
		return this.dice;
	}

//	public void setLabelTeam(Team team)
//	{
//		this.teamLabel.setText(this.labelText + team.getName());
//	}
	
	public void customByTeam(Team team)
	{
		 if (team == Team.Blue)
	        {
	    		this.setBackground(MyColors.myBlue);
	        }
	        else if (team == Team.Red)
	        {
	    		this.setBackground(MyColors.myRed);
	        }
	        else if (team == Team.Green)
	        {
	    		this.setBackground(MyColors.myGreen);
	        }
	        else if (team == Team.Yellow)
	        {
	    		this.setBackground(MyColors.myYellow);
	        }
	}
	
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//background do label
		this.g2d.setColor(Color.white);
        this.g2d.fillRoundRect(20, this.mainDimension.height - this.mainDimension.height/5 * 2, this.mainDimension.width - 40, 40, 15, 15);
		
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
	

	public void actionPerformed(ActionEvent e) 
	{		
		Team currentTeam = LudoController.sharedInstance.getCurrentTeam();
		Team myTeam = SocketController.sharedInstance().myTeam;
		
		if (myTeam != currentTeam)
		{
			JOptionPane.showMessageDialog(null, "Não é a sua vez!");
			return;
		}
		
		this.dice.playDice();
		this.diceValue = LudoController.sharedInstance.getDiceValue();
				
		this.repaint();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		this.observedTeam = (Team) arg;
		this.teamLabel.setText(this.labelText + this.observedTeam.getName());
		
		 if (this.observedTeam == Team.Blue)
	        {
	    		this.teamLabel.setBackground(MyColors.myLightBlue);
	    		//this.setBackground(MyColors.myBlue);
	        }
	        else if (this.observedTeam == Team.Red)
	        {
	    		this.teamLabel.setBackground(MyColors.myLightRed);
	    		//this.setBackground(MyColors.myRed);
	        }
	        else if (this.observedTeam == Team.Green)
	        {
	    		this.teamLabel.setBackground(MyColors.myLightGreen);
	    		//this.setBackground(MyColors.myGreen);
	        }
	        else if (this.observedTeam == Team.Yellow)
	        {
	    		this.teamLabel.setBackground(MyColors.myLightYellow);
	    		//this.setBackground(MyColors.myYellow);
	        }
		 
		 this.repaint();
	}	
}
