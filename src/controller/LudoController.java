package controller;


import model.*;
import view.*;

public class LudoController {

	public static LudoController sharedInstance = new LudoController();
	
	/*******************************/
	/******* MODEL COMPONENTS*******/
	/*******************************/
	
	private LudoTableModel model = new LudoTableModel();
	
	private Square[] squares;
	
	private PinModel[] redPins ;
	
	private PinModel[] greenPins;
	
	private PinModel[] bluePins;
	
	private PinModel[] yellowPins;
	
	private DiceModel dice;
	
	
	/*******************************/
	/****** VISUAL COMPONENTS*******/
	/*******************************/

	private MainWindow mainWindow;
	
	/*******************************/
	/*** CONTROLLERS COMPONENTS*****/
	/*******************************/
	
	private Team currentTurn = Team.Blue;
	
	private int diceValue = 0;
	
	private LudoController()
	{
		this.squares = this.model.getModel();
		this.redPins = this.model.getRedPins();
		this.bluePins = this.model.getBluePins();
		this.greenPins = this.model.getGreenPins();
		this.yellowPins = this.model.getYellowPins();
		
		this.dice = new DiceModel();
	}
	
	public void loadScreen()
	{
		this.mainWindow = new MainWindow("LudoGame", squares);
		
//		Graphics2D g = this.mainWindow.gamePanel().ludoTable().graphics();
		
//		PinView.drawPins(this.redPins, g, this.mainWindow.getTableDimension());
	}
	
	public void setDiceValue(int value)
	{
		this.diceValue = value;
	}
	
	public int getDiceValue()
	{
		return this.diceValue;
	}
	
	public PinModel[] getRedPins()
	{
		return this.redPins;
	}
	
	public PinModel[] getBluePins()
	{
		return this.bluePins;
	} 
	
	public PinModel[] getYellowPins()
	{
		return this.yellowPins;
	}
	
	public PinModel[] getGreenPins()
	{
		return this.greenPins;
	}
	
	public PinModel getPinOnPosition(int posx, int posy)
	{
		int square = this.mainWindow.gamePanel().ludoTable().getSquareDimension().width;
		
		for (int i = 0; i < bluePins.length; i++)
		{
			int originx = bluePins[i].getX()*square;
			int limitx = bluePins[i].getX()*square + square;
			
			int originy = bluePins[i].getY()*square;
			int limity = bluePins[i].getY()*square + square;
			
			if( (posx > originx && posx < limitx) && (posy > originy && posy < limity))
			{
				return bluePins[i];
			}
		}
		
		for (int i = 0; i < redPins.length; i++)
		{
			int originx = redPins[i].getX()*square;
			int limitx = redPins[i].getX()*square + square;
			
			int originy = redPins[i].getY()*square;
			int limity = redPins[i].getY()*square + square;
			
			if( (posx > originx && posx < limitx) && (posy > originy && posy < limity))
			{
				return redPins[i];
			}
		}
		
		for (int i = 0; i < greenPins.length; i++)
		{
			int originx = greenPins[i].getX()*square;
			int limitx = greenPins[i].getX()*square + square;
			
			int originy = greenPins[i].getY()*square;
			int limity = greenPins[i].getY()*square + square;
			
			if( (posx > originx && posx < limitx) && (posy > originy && posy < limity))
			{
				return greenPins[i];
			}
		}
		
		for (int i = 0; i < yellowPins.length; i++)
		{
			int originx = yellowPins[i].getX()*square;
			int limitx = yellowPins[i].getX()*square + square;
			
			int originy = yellowPins[i].getY()*square;
			int limity = yellowPins[i].getY()*square + square;
			
			if( (posx > originx && posx < limitx) && (posy > originy && posy < limity))
			{
				return yellowPins[i];
			}
		}
		
		return null;
		
	}
	
	public Team getCurrentTeam()
	{
		return this.currentTurn;
	}
	
	public void movePinToSquare(PinModel p)
	{
		Square destin = this.model.getNextSquareWithSteps(p.getX(), p.getY(), p.getTeam(), this.diceValue);
		
		if(destin != null)
		{
			p.setX(destin.xPosition());
			p.setY(destin.yPosition());
			
			System.out.println("Redesenhando..");
			this.mainWindow.gamePanel().ludoTable().rePaint();
		}
	}
}
