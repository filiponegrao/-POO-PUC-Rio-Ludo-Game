package controller;


import java.io.IOException;
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
		this.mainWindow = new MainWindow("1221846-1411287", squares);
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
	
	public PinModel getPinOnPosition(int posx, int posy, Team team)
	{
		int square = this.mainWindow.gamePanel().ludoTable().getSquareDimension().width;
		
		if(team == Team.Blue)
		{

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
		}
		else if(team == Team.Red)
		{
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
		}
		else if(team == Team.Green)
		{
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
		}
		else if(team == Team.Yellow)
		{
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
		}
		
		return null;
	}
	
	public Team getCurrentTeam()
	{
		return this.currentTeam;
	}
	
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
				else
				{
					JOptionPane.showMessageDialog(null,
							"Voce nao pode atravessar nem parar em uma barreira!",
							"Ops!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	public void setCurrentTeam()
	{
		this.lastDiceValue = this.diceValue;
		this.lastTeam = this.currentTeam;
		
		
		if(this.currentTeam == Team.Blue && !this.checkWin(Team.Red))
		{
			this.currentTeam = Team.Red;
		}
		else if (this.currentTeam == Team.Red && !this.checkWin(Team.Green))
		{
			this.currentTeam = Team.Green;
		}
		else if (this.currentTeam == Team.Green && !this.checkWin(Team.Yellow))
		{
			this.currentTeam = Team.Yellow;
		}
		else if(this.currentTeam == Team.Yellow && !this.checkWin(Team.Blue))
		{
			this.currentTeam = Team.Blue;
		}
		else if(this.currentTeam == Team.Blue && this.checkWin(Team.Red) && !this.checkWin(Team.Green))
		{
			this.currentTeam = Team.Green;
		}
		else if (this.currentTeam == Team.Red && this.checkWin(Team.Green) && !this.checkWin(Team.Yellow))
		{
			this.currentTeam = Team.Yellow;
		}
		else if (this.currentTeam == Team.Green && this.checkWin(Team.Yellow) && !this.checkWin(Team.Blue))
		{
			this.currentTeam = Team.Blue;
		}
		else if(this.currentTeam == Team.Yellow && this.checkWin(Team.Blue) && !this.checkWin(Team.Red))
		{
			this.currentTeam = Team.Red;
		}
		else if(this.currentTeam == Team.Blue && this.checkWin(Team.Red) && this.checkWin(Team.Green))
		{
			this.currentTeam = Team.Yellow;
		}
		else if (this.currentTeam == Team.Red && this.checkWin(Team.Green) && this.checkWin(Team.Yellow))
		{
			this.currentTeam = Team.Blue;
		}
		else if (this.currentTeam == Team.Green && this.checkWin(Team.Yellow) && this.checkWin(Team.Blue))
		{
			this.currentTeam = Team.Red;
		}
		else if(this.currentTeam == Team.Yellow && this.checkWin(Team.Blue) && this.checkWin(Team.Red))
		{
			this.currentTeam = Team.Green;
		}
		else if(this.checkWin(Team.Blue) && this.checkWin(Team.Red) && this.checkWin(Team.Green) && this.checkWin(Team.Yellow))
		{
			JOptionPane.showMessageDialog(null,
					"Ordem dos vencedores:\n1 Lugar - \n2 Lugar \n3 Lugar - \n4Lugar -",
					"Fim de jogo!", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		this.setDiceValue(0);
		this.teamObserved.setValue(this.currentTeam);
		
		this.mainWindow.gamePanel().playerPanel().getDiceModel().buttonEnable();
	}
	
	public void animatingMove(PinModel p, int posx, int posy)
	{
		//Loop que serveria para animar.
		//Enquanto a situacao nao foi verificada,
		//mantei-vos o loop aqui.
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
			
			this.mainWindow.gamePanel().ludoTable().rePaint();
		}
		
		//Checa a possibilidade de um jogador ter vencido!
		if(this.checkWin(this.currentTeam))
		{
			JOptionPane.showMessageDialog(null,
					"Voce completou o jogo!",
					"Parabens!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(this.diceValue != 6)
		{
			this.sequence = 0;
		}
		else
		{
			this.sequence += 1;
		}
	}	
	
	public Boolean skipPlayer()
	{
		if(this.diceValue != 5 && !this.model.hasPossibilites(this.getCurrentPlayerPins(this.currentTeam)))
		{
			
			JOptionPane.showMessageDialog(null,
						"Ops! Voce precisa tirar 5 para sair com uma peça!",
						"Não foi dessa vez!", JOptionPane.INFORMATION_MESSAGE);
			
			this.setCurrentTeam();
			
			return true;
		}
		
		return false;
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
				else if(onePin == Team.Blue)
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
				else if(onePin == Team.Red)
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
				else if(onePin == Team.Green)
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
				else if(onePin == Team.Yellow)
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
	
	public PinModel getPinOnCoordinate(int x, int y, Team team)
	{
		if(team == Team.Blue)
		{
			for (PinModel pin : this.bluePins)
			{
				if(pin.getX() == x && pin.getY() == y)
				{
					return pin;
				}
			}
		}
		else if(team == Team.Red)
		{
			for (PinModel pin : this.redPins)
			{
				if(pin.getX() == x && pin.getY() == y)
				{
					return pin;
				}
			}
		}
		else if(team == Team.Green)
		{
			for (PinModel pin : this.greenPins)
			{
				if(pin.getX() == x && pin.getY() == y)
				{
					return pin;
				}
			}
		}
		else if(team == Team.Yellow)
		{
			for (PinModel pin : this.yellowPins)
			{
				if(pin.getX() == x && pin.getY() == y)
				{
					return pin;
				}
			}
		}
		
		return null;
	}
	
	public Team[] getTwoPins(int x, int y)
	{		
		List<Team> teams = new ArrayList<Team>();
		
		PinModel blue = this.getPinOnCoordinate(x, y, Team.Blue);
		
		if(blue != null)
		{
			teams.add(Team.Blue);
		}
		
		PinModel red = this.getPinOnCoordinate(x, y, Team.Red);
				
		if(red != null)
		{
			teams.add(Team.Red);
		}
		
		if(teams.size() == 2) { return new Team[]{teams.get(0),teams.get(1)}; }
		
		PinModel green = this.getPinOnCoordinate(x, y, Team.Green);
		
		if(green != null)
		{
			teams.add(Team.Green);
		}
		
		if(teams.size() == 2) { return new Team[]{teams.get(0),teams.get(1)}; }

		PinModel yellow = this.getPinOnCoordinate(x, y, Team.Yellow);
		
		if(yellow != null)
		{
			teams.add(Team.Yellow);
		}
	
		if(teams.size() == 2) { return new Team[]{teams.get(0),teams.get(1)}; }
		
		return null;
	}
	
	public Boolean thirdTimeSix()
	{
		System.out.println(this.diceValue);
		System.out.println(this.sequence);
		
		if(this.lastPinPlayed == null) { return false; }
		
		//Ja esta na reta final
		if(this.model.isOnVictoryRoad(this.lastPinPlayed))
		{
			return false;
		}
		
		if(this.diceValue != 6 && this.sequence != 3)
		{
			return false;
		}
		else if(this.diceValue == 6 && this.sequence == 3)
		{
			Coord coord = this.model.getHouseSquareAvaliable(this.getCurrentPlayerPins(this.currentTeam));
			
			if(coord == null) { return false; }
			
			JOptionPane.showMessageDialog(null,
					"Voce tirou 6 pela terceira vez e não está na reta final! Volte para casa de inicio!",
					"A não!!", JOptionPane.INFORMATION_MESSAGE);
			
			this.lastPinPlayed.setX(coord.x);
			this.lastPinPlayed.setY(coord.y);
			
			this.setCurrentTeam();
			
			this.mainWindow.gamePanel().ludoTable().repaint();
			
			return true;
		}	
		return false;
	}
	
	public Boolean checkWin(Team team)
	{
		Boolean azul = false;
		Boolean vermelho = false;
		Boolean verde = false;
		Boolean amarelo = false;
		
		
		if(team == Team.Blue)
		{
			for (PinModel pin : this.bluePins)
			{
				if(!this.model.isPinOnFinal(pin))
				{
					return false;
				}
			}
		}
		else if(team == Team.Red)
		{
			for (PinModel pin : this.redPins)
			{
				if(!this.model.isPinOnFinal(pin))
				{
					return false;
				}
			}
		}
		else if(team == Team.Green)
		{
			for (PinModel pin : this.greenPins)
			{
				if(!this.model.isPinOnFinal(pin))
				{
					return false;
				}
			}
		}
		else if(team == Team.Yellow)
		{
			for (PinModel pin : this.yellowPins)
			{
				if(!this.model.isPinOnFinal(pin))
				{
					return false;
				}
			}
		}
		
		if(azul && verde && amarelo && vermelho)
		{
			JOptionPane.showMessageDialog(null,
					"Ordem dos vencedores:\n1 Lugar - \n2 Lugar \n3 Lugar - \n4Lugar -",
					"Fim de jogo!", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		return true;
	}
	
	public void saveCurrentGame() throws IOException
	{
		SaveGame.writeFile(this.getCurrentTeam(), this.getBluePins(), this.getRedPins(), this.getGreenPins(), this.getYellowPins());
	}
	
	public void loadGame() throws IOException
	{
		ArrayList<String> gameData = LoadGame.readFile();
		
		processGameData(gameData);

//		for(String data : gameData)
//		{
//			System.out.println(data);
//		}	
	}
	
	//pegar informações da lista e distribuir 
	public void processGameData(ArrayList<String> data)
	{
		int i = 0;
		//interpreta time atual
		String team = data.get(i);
		System.out.println(team); //time da vez
		
//		System.out.println(data.get(i+1));	time
//		System.out.println(Integer.parseInt(data.get(i+2)));  posX
//		System.out.println(Integer.parseInt(data.get(i+3)));   posY

//		for(i = 1; i < data.size();)
//		{
//			
//		}
	}
}
