package controller;


import java.util.ArrayList;
import java.util.List;

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
	
	private List<PinModel> allPins = new ArrayList<PinModel>();
	
	private DiceModel dice;
	
	/*******************************/
	/****** VISUAL COMPONENTS*******/
	/*******************************/

	private MainWindow mainWindow;
	
	/*******************************/
	/*** CONTROLLERS COMPONENTS*****/
	/*******************************/
		
	private Team currentTeam = Team.Blue;
	
	private ObservableTeam teamObserved = new ObservableTeam();
	
	private PlayerPanel panelObserver;
	
	private Team lastTeam = Team.None;
	
	private int diceValue = 0;
	
	private int lastDiceValue = 0;
	
	private PinModel lastPinPlayed;
	
	private int sequence = 0;
	
	private LudoController()
	{
		this.squares = this.model.getModel();
		
		this.redPins = this.model.getRedPins();
		this.bluePins = this.model.getBluePins();
		this.greenPins = this.model.getGreenPins();
		this.yellowPins = this.model.getYellowPins();
		
		for(PinModel pin : this.redPins)
		{
			this.allPins.add(pin);
		}
		
		for(PinModel pin : this.bluePins)
		{
			this.allPins.add(pin);
		}
		
		for(PinModel pin : this.greenPins)
		{
			this.allPins.add(pin);
		}
		
		for(PinModel pin : this.yellowPins)
		{
			this.allPins.add(pin);
		}
		
		this.dice = new DiceModel();
		
	}
	
	public void loadScreen()
	{
		this.mainWindow = new MainWindow("LudoGame", squares);
		this.panelObserver = this.mainWindow.gamePanel().playerPanel();
		this.teamObserved.addObserver(this.panelObserver);
		
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
	
//	public Team getCurrentTeam()
//	{
//		return this.currentTeam;
//	}
	
	/**
	 * MÉTODO RESPONSAVEL PELO CONTROLE DOS MOVIMENTOS
	 * Checa-se regras de movimentacao e possiveis impedimentos.
	 */
	public void movePinToSquare(PinModel p)
	{
		System.out.println("passa na funcao");
		Boolean isInitial = this.model.isInitialPin(p);
		
		Square destin = this.model.getNextSquareWithSteps(p.getX(), p.getY(), p.getTeam(), this.diceValue);

		if(p.getTeam() == this.currentTeam)
		{
			if(isInitial && this.diceValue == 5)
			{
				if(destin != null)
				{
					this.animatingMove(p, destin.xPosition(), destin.yPosition());
					
					if (this.pinStrikes())
					{
						JOptionPane.showMessageDialog(null,
								"Voce pode andar 20 casas por ter capturado uma peça!",
								"Oba!", JOptionPane.INFORMATION_MESSAGE);
						
						this.setDiceValue(20);
					}
					else
					{
						this.setCurrentTeam();
					}
				}
			}
			else if(isInitial && this.diceValue != 5 && this.diceValue != 0)
			{
				JOptionPane.showMessageDialog(null,
						"Com esse valor voce so pode mover peças fora da casa de inicio.",
						"Ops!", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(this.diceValue == 6 && sequence != 3)
			{
				//Verifica a presenca de barreiras
				if(this.checkPathClear(p))
				{
					if(this.teamHasABarrier(p.getTeam()))
					{
						Team barrier = this.getBarrierOn(p.getX(), p.getY());
						
						if(barrier != null && barrier == p.getTeam())
						{
							this.animatingMove(p, destin.xPosition(), destin.yPosition());
							this.pinStrikes();
							
							this.setDiceValue(0);
							
							JOptionPane.showMessageDialog(null,
									"Voce pode jogar novamente pois tirou um 6!",
									"Oba!", JOptionPane.INFORMATION_MESSAGE);
							
							sequence += 1;
						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"Voce precisa desfazer sua barreira!",
									"Ops!", JOptionPane.INFORMATION_MESSAGE);
						}
						return;
					}
					else
					{
						
						this.animatingMove(p, destin.xPosition(), destin.yPosition());
						this.pinStrikes();
						
						this.setDiceValue(0);
						
						JOptionPane.showMessageDialog(null,
								"Voce pode jogar novamente pois tirou um 6!",
								"Oba!", JOptionPane.INFORMATION_MESSAGE);
						
						sequence += 1;
						
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Não é possivel atravessar uma barreira",
							"Ops!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if(this.diceValue == 6 && sequence == 3)
			{
				JOptionPane.showMessageDialog(null,
						"Voce tirou 6 tres vezes! Volte uma casa",
						"Ops!", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(this.diceValue != 5 && this.diceValue != 0 && !this.model.hasPossibilites(this.getCurrentPlayerPins(this.currentTeam)))
			{
				this.skipPlayer();
			}
			else if(this.diceValue == 7)
			{
				if(this.checkPathClear(p))
				{
					this.animatingMove(p, destin.xPosition(), destin.yPosition());

					this.setCurrentTeam();
					this.pinStrikes();
				}
			}
			else
			{
				//Verifica a presenca de barreiras
				if(this.checkPathClear(p))
				{
					this.animatingMove(p, destin.xPosition(), destin.yPosition());
					
					this.setCurrentTeam();
					this.pinStrikes();
				}
			}
		}
	}
	
	public void setCurrentTeam()
	{
		this.lastDiceValue = this.diceValue;
		this.lastTeam = this.currentTeam;
		
		
		if(this.currentTeam == Team.Blue)
		{
			this.currentTeam = Team.Red;
//			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Red);
		}
		else if (this.currentTeam == Team.Red)
		{
			this.currentTeam = Team.Green;
//			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Green);
		}
		else if (this.currentTeam == Team.Green)
		{
			this.currentTeam = Team.Yellow;
//			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Yellow);
		}
		else if(this.currentTeam == Team.Yellow)
		{
			this.currentTeam = Team.Blue;
//			this.mainWindow.gamePanel().playerPanel().setLabelTeam(Team.Blue);
		}
		
		this.setDiceValue(0);
		this.teamObserved.setValue(this.currentTeam);
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
			
			this.lastPinPlayed = p;
			
			if(this.diceValue != 6)
			{
				this.sequence = 0;
			}
			
			this.mainWindow.gamePanel().ludoTable().rePaint();
		}
	}	
	
	public void skipPlayer()
	{
		if(this.diceValue != 5 && !this.model.hasPossibilites(this.getCurrentPlayerPins(this.currentTeam)))
		{
			
			JOptionPane.showMessageDialog(null,
						"Ops! Voce precisa tirar 5 para sair com uma peça!",
						"Não foi dessa vez!", JOptionPane.INFORMATION_MESSAGE);
			
			this.setCurrentTeam();
		}
	}
	
	public PinModel[] getCurrentPlayerPins(Team team)
	{
		Team currentTeam = team;
		
		if(currentTeam == Team.Red)
		{
			return this.redPins;
		}
		else if(currentTeam == Team.Blue)
		{
			return this.bluePins;
		}
		else if(currentTeam == Team.Green)
		{
			return this.greenPins;
		}
		else if(currentTeam == Team.Yellow)
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
	
	public Boolean checkPathClear(PinModel pin)
	{
		List<Square> squares = this.model.getPathForSteps(pin, this.diceValue);
		
		for (Square square : squares)
		{
			Team barrier = this.getBarrierOn(square.xPosition(), square.yPosition());
			
			if(barrier != null) { return false; }
		}
		
		return true;	
	}

	public Boolean pinStrikes()	
	{
		for (PinModel pin : allPins)
		{
			for (PinModel otherPin : allPins)
			{
				if(	pin.getX() == otherPin.getX() &&
					pin.getY() == otherPin.getY() &&
					pin.getTeam() != otherPin.getTeam())
				{
					if(pin.equals(this.lastPinPlayed))
					{
						Coord coord = this.model.getHouseSquareAvaliable(this.getCurrentPlayerPins(otherPin.getTeam()));
						
						otherPin.setX(coord.x);
						otherPin.setY(coord.y);
						
						this.mainWindow.gamePanel().ludoTable().rePaint();
						
						return true;
					}
					else
					{
						Coord coord = this.model.getHouseSquareAvaliable(this.getCurrentPlayerPins(pin.getTeam()));

						pin.setX(coord.x);
						pin.setY(coord.y);
						
						this.mainWindow.gamePanel().ludoTable().rePaint();
						
						return true;
						
					}
				}
			}
		}
		
		return false;
	}
	
	public Boolean teamHasABarrier(Team team)
	{
		for (PinModel pin : allPins)
		{
			Team barrier = this.getBarrierOn(pin.getX(), pin.getY());
			
			if(barrier != null && barrier == team)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean checkSevenSteps()
	{
		PinModel[] pins = this.getCurrentPlayerPins(this.currentTeam);
		
		int n = this.model.numberOfHousesAvaliable(pins);
		
		if(this.diceValue == 6 && n == 4)
		{
			JOptionPane.showMessageDialog(null,
					"Voce tirou 6 e nao tem mais peças na sua casa de inicio. Ande 7 casas!",
					"Oba!", JOptionPane.INFORMATION_MESSAGE);
			
			this.setDiceValue(7);
			
			return true;
		}
		
		return false;
	}
	
	
	
}
