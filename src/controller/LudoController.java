package controller;


import javax.swing.JOptionPane;

import model.*;
import view.*;

public class LudoController 
{
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
		this.mainWindow.gamePanel().playerPanel().repaint();
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
		System.out.println("passa na funcao");
		Boolean isInitial = this.model.isInitialPin(p);
		
		Square destin = this.model.getNextSquareWithSteps(p.getX(), p.getY(), p.getTeam(), this.diceValue);

		if(p.getTeam() == this.currentTurn)
		{
			if(isInitial && this.diceValue == 5)
			{
				if(destin != null)
				{
					this.animatingMove(p, destin.xPosition(), destin.yPosition());
					this.setCurrentTeam();
				}
			}
			else if(isInitial && this.diceValue != 5 && this.diceValue != 0)
			{
				JOptionPane.showMessageDialog(null,
						"Com esse valor voce so pode mover peças fora da casa de inicio.",
						"Ops!", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(this.diceValue == 6)
			{
				
			}
			else if(this.diceValue != 5 && this.diceValue != 0 && !this.model.hasPossibilites(this.getCurrentPlayerPins()))
			{
				this.skipPlayer();
			}
			else
			{
				this.animatingMove(p, destin.xPosition(), destin.yPosition());
				this.setCurrentTeam();
			}
		}
	}
	
	public void setCurrentTeam()
	{
		if(this.currentTurn == Team.Blue)
		{
			this.currentTurn = Team.Red;
			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Red);
		}
		else if (this.currentTurn == Team.Red)
		{
			this.currentTurn = Team.Green;
			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Green);
		}
		else if (this.currentTurn == Team.Green)
		{
			this.currentTurn = Team.Yellow;
			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Yellow);
		}
		else if(this.currentTurn == Team.Yellow)
		{
			this.currentTurn = Team.Blue;
			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Blue);
		}
		
		this.setDiceValue(0);
		
	}
	
	public void animatingMove(PinModel p, int posx, int posy)
	{
		while(p.getX() != posx || p.getY() != posy)
		{
			if(posx > p.getX())
			{
				p.setX(p.getX()+1);
			}
			else if(posx < p.getX())
			{
				p.setX(p.getX()-1);
			}
			
			if(posy > p.getY())
			{
				p.setY(p.getY()+1);
			}
			else if(posy < p.getY())
			{
				p.setY(p.getY()-1);
			}
			
			this.mainWindow.gamePanel().ludoTable().rePaint();
		}
	}	
	
	public void skipPlayer()
	{
		if(this.diceValue != 5 && !this.model.hasPossibilites(this.getCurrentPlayerPins()))
		{
			
			JOptionPane.showMessageDialog(null,
						"Ops! Voce precisa tirar 5 para sair com uma peça!",
						"Não foi dessa vez!", JOptionPane.INFORMATION_MESSAGE);
			
			this.setCurrentTeam();
		}
	}
	
	public PinModel[] getCurrentPlayerPins()
	{
		if(this.currentTurn == Team.Red)
		{
			return this.redPins;
		}
		else if(this.currentTurn == Team.Blue)
		{
			return this.bluePins;
		}
		else if(this.currentTurn == Team.Green)
		{
			return this.greenPins;
		}
		else if(this.currentTurn == Team.Yellow)
		{
			return this.yellowPins;
		}
		
		return null;
	}
	
	public Team getBarrierOn(int x, int y)
	{
		Team onePin = null;
		
		for (PinModel pin : this.bluePins)
		{
			if(pin.getX() == x && pin.getY() == y)
			{
				if(onePin == null)
				{
					onePin = pin.getTeam();
				}
				else
				{
					return pin.getTeam();
				}
			}
		}
		
		for (PinModel pin : this.redPins)
		{
			if(pin.getX() == x && pin.getY() == y)
			{
				if(onePin == null)
				{
					onePin = pin.getTeam();
				}
				else
				{
					return pin.getTeam();
				}
			}
		}
		
		for (PinModel pin : this.greenPins)
		{
			if(pin.getX() == x && pin.getY() == y)
			{
				if(onePin == null)
				{
					onePin = pin.getTeam();
				}
				else
				{
					return pin.getTeam();
				}
			}
		}
		
		for (PinModel pin : this.yellowPins)
		{
			if(pin.getX() == x && pin.getY() == y)
			{
				if(onePin == null)
				{
					onePin = pin.getTeam();
				}
				else
				{
					return pin.getTeam();
				}
			}
		}
		
		return null;
	}
//	
//	public void checkEndMovement()
//	{
//		
//	}
//	
}
