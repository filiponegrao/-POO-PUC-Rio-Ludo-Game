package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		this.initPins();
		
		this.dice = new DiceModel();
		
	}
	
	public void initPins()
	{	
		this.squares = this.model.getModel();
		
		this.redPins = this.model.getRedPins();
		this.bluePins = this.model.getBluePins();
		this.greenPins = this.model.getGreenPins();
		this.yellowPins = this.model.getYellowPins();
		
		this.setAllPins();
	}
	
	public void setAllPins()
	{
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
//		System.out.println("passa na funcao");
		Boolean isInitial = this.model.isInitialPin(p);
		
		if(this.model.isPinOnFinal(p.getX(), p.getY(), p.getTeam()))
		{
			JOptionPane.showMessageDialog(null,
					"Este pino ja esta na casa final!",
					"Ops!", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		Square destin = this.model.getNextSquareWithSteps(p.getX(), p.getY(), p.getTeam(), this.diceValue);
		
		if(destin == null) { return; }

		if(p.getTeam() == this.currentTeam)
		{
			if(isInitial && this.diceValue == 5)
			{
				this.executeMovement(destin, p);
			}
			else if(isInitial && this.diceValue != 5 && this.diceValue != 0)  //se tenta mover peça na casa inicial sem o valor 5
			{
				JOptionPane.showMessageDialog(null,
						"Com esse valor você só pode mover peças fora da casa de início.",
						"Ops!", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else if(this.diceValue == 6 && sequence != 3)
			{
				if(this.teamHasABarrier(p.getTeam()))
				{
					Team barrier = this.getBarrierOn(p.getX(), p.getY());

					if(barrier != null && barrier == p.getTeam())
					{
						this.executeMovement(destin, p);
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Voce precisa desfazer sua barreira!",
								"Ops!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					this.executeMovement(destin, p);
				}
			}
			else
			{
				this.executeMovement(destin, p);
			}
		}
	}
	
	public void executeMovement(Square destin, PinModel p)
	{
		if(this.checkPathClear(p))
		{
			this.animatingMove(p, destin.xPosition(), destin.yPosition());
			if (this.pinStrikes()) //se houve captura
			{
				JOptionPane.showMessageDialog(null,
						"Você pode andar 20 casas por ter capturado uma peça!",
						"Oba!", JOptionPane.INFORMATION_MESSAGE);
				
				this.setDiceValue(20); //permite andar mais 20 casas
			}
			else if(this.checkDonePath(p))
			{
				if(this.checkGameOver(p.getTeam()))
				{
					String message = "PARABENS! Vencedor: Time " + p.getTeam().getName();
					List<Team> ranking = this.getRanking();
					message += "\nSegundo lugar: " + ranking.get(1).getName();
					message += "\nTerceiro lugar: " + ranking.get(2).getName();
					
					JOptionPane.showMessageDialog(null, message, "PARABENS!", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Você alcançou a ultima casa! Ande 10 casas com qualquer outra peça!",
							"Oba!", JOptionPane.INFORMATION_MESSAGE);
					
					this.setDiceValue(10); //permite andar mais 10 casas
				}
			}
			else if(this.diceValue == 6)
			{
				this.setDiceValue(0);
				
				JOptionPane.showMessageDialog(null,
						"Você pode jogar novamente pois tirou um 6!", 
						"Oba!", JOptionPane.INFORMATION_MESSAGE);
				this.dice.buttonEnable();
			}
			else
			{
				this.setDiceValue(0);
				this.setCurrentTeam();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					"Você não pode atravessar nem parar em uma barreira!",
					"Ops!", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		return;
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
		if(!this.model.ableToMove(this.getCurrentPlayerPins(this.currentTeam), this.diceValue))
		{
			
			JOptionPane.showMessageDialog(null,
						"Ops! Você precisa tirar 5 para sair com uma peça e nao pode mover as outras!",
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
		
		//Verifica o caso de ja ser a casa final
		if(this.model.isPinOnFinal(x, y, Team.Blue))
		{
			return null;
		}
		if(this.model.isPinOnFinal(x, y, Team.Red))
		{
			return null;
		}
		if(this.model.isPinOnFinal(x, y, Team.Yellow))
		{
			return null;
		}
		if(this.model.isPinOnFinal(x, y, Team.Green))
		{
			return null;
		}
		
		//Verifica o caso de ser um safe point
		Square square = this.model.getSquare(x, y);
		
		if(square == null) { return null; }
		
		if(square.type() == SquareType.SafePoint) { return null; }
		
		
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
		
		if(squares.size() == 0) { return false; }
		
		for (Square square : squares)
		{
			Team barrier = this.getBarrierOn(square.xPosition(), square.yPosition());
			
			if(barrier != null) { return false; }
		}
		
		return true;	
	}
	
	public Boolean checkDonePath(PinModel pin)
	{
		int x = pin.getX();
		int y = pin.getY();
		Team team = pin.getTeam();
		
		if(this.model.isPinOnFinal(x, y, team))
		{
			return true;
		}
		
		return false;
	}
	
	public Boolean checkGameOver(Team team)
	{
		if(team == Team.Blue)
		{
			for (PinModel pin : this.bluePins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
			
			return true;
		}
		else if(team == Team.Red)
		{
			for (PinModel pin : this.redPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
			
			return true;
		}
		else if(team == Team.Green)
		{
			for (PinModel pin : this.greenPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
			
			return true;
		}
		else if(team == Team.Yellow)
		{
			for (PinModel pin : this.yellowPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

	public Boolean pinStrikes()	
	{		
		for (PinModel pin : allPins)
		{
			for (PinModel otherPin : allPins)
			{
				if(pin.getX() == otherPin.getX() && pin.getY() == otherPin.getY() && pin.getTeam() != otherPin.getTeam())
				{
					Square square = this.model.getSquare(pin.getX(), pin.getY());
					
					if(square == null) { break; }
					
					if(square.type() == SquareType.SafePoint) { break; }
					
					//Esse foi o pin movid, entao devolve o outro pin
					if(pin.equals(this.lastPinPlayed))
					{
						Coord coord = this.model.getHouseSquareAvaliable(this.getCurrentPlayerPins(otherPin.getTeam()));
						
						otherPin.setX(coord.x);
						otherPin.setY(coord.y);
						
						this.mainWindow.gamePanel().ludoTable().rePaint();
						
						return true;
					}
					//Se nao, devolve o proprio pin, pois ele foi capturado.
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
					"Voce tirou 6 e nao tem mais peças na sua casa de início. Ande 7 casas!",
					"Oba!", JOptionPane.INFORMATION_MESSAGE);
			
			this.setDiceValue(7);
			this.dice.buttonDisable();
			
			return true;
		}
		
		return false;
	}
	
	public List<PinModel> getPinsOnCoordinate(int x, int y)
	{
		List<PinModel> pins = new ArrayList<PinModel>();
		
		for (PinModel blue : this.bluePins)
		{
			if(blue.getX() == x && blue.getY() == y)
			{
				pins.add(blue);
			}
		}
	
		for (PinModel red : this.redPins)
		{
			if(red.getX() == x && red.getY() == y)
			{
				pins.add(red);
			}
		}

		for (PinModel green : this.greenPins)
		{
			if(green.getX() == x && green.getY() == y)
			{
				pins.add(green);
			}
		}

		for (PinModel yellow : this.yellowPins)
		{
			if(yellow.getX() == x && yellow.getY() == y)
			{
				pins.add(yellow);
			}
		}
		
		return pins;
	}
	
	public Boolean thirdTimeSix()
	{
//		System.out.println(this.diceValue);
//		System.out.println(this.sequence);
		
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
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
		}
		else if(team == Team.Red)
		{
			for (PinModel pin : this.redPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
		}
		else if(team == Team.Green)
		{
			for (PinModel pin : this.greenPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
				{
					return false;
				}
			}
		}
		else if(team == Team.Yellow)
		{
			for (PinModel pin : this.yellowPins)
			{
				if(!this.model.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam()))
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
	}

	//pegar informações da lista e distribuir 
	public void processGameData(ArrayList<String> data)
	{
		int i = 0;
		int j = 0;

		//interpreta time atual
		String team = data.get(i);
		
		int[] posBlue = new int[8];
		int[] posRed = new int[8];
		int[] posGreen = new int[8];
		int[] posYellow = new int[8];
	
		//posições atuais dos pinos azuis
		for(i = 1; i < 9; i++)
		{
			posBlue[j] = Integer.parseInt(data.get(i));
			j++;
		}

		//posições atuais dos pinos vermelhos
		j = 0;
		for(; i < 17; i++)
		{
			posRed[j] = Integer.parseInt(data.get(i));
			j++;
		}
		
		//posições atuais dos pinos verdes
		j = 0;
		for(; i < 25; i++)
		{
			posGreen[j] = Integer.parseInt(data.get(i));
			j++;
		}
		
		//posições atuais dos pinos amarelos
		j = 0;
		for(; i < 33; i++)
		{
			posYellow[j] = Integer.parseInt(data.get(i));
			j++;
		}
		
		//atualiza time da vez
		if(team.equals("Blue"))
		{
			this.currentTeam = Team.Blue;
		}
		else if(team.equals("Red"))
		{
			this.currentTeam = Team.Red;
		}
		else if(team.equals("Green"))
		{
			this.currentTeam = Team.Green;
		}
		else if(team.equals("Yellow"))
		{
			this.currentTeam = Team.Yellow;
		}
		this.teamObserved.setValue(this.currentTeam);
		
		//atualiza posições dos pinos azuis
		j = 0;
		for(i = 0; i < 4; i++)
		{
			this.bluePins[i].setX(posBlue[j]);
			this.bluePins[i].setY(posBlue[j+1]);
			j+=2;
		}
		
		//atualiza posições dos pinos vermelhos
		j = 0;
		for(i = 0; i < 4; i++)
		{
			this.redPins[i].setX(posRed[j]);
			this.redPins[i].setY(posRed[j+1]);
			j+=2;
		}
		
		//atualiza posições dos pinos verdes
		j = 0;
		for(i = 0; i < 4; i++)
		{
			this.greenPins[i].setX(posGreen[j]);
			this.greenPins[i].setY(posGreen[j+1]);
			j+=2;
		}
		
		//atualiza posições dos pinos amarelos
		j = 0;
		for(i = 0; i < 4; i++)
		{
			this.yellowPins[i].setX(posYellow[j]);
			this.yellowPins[i].setY(posYellow[j+1]);
			j+=2;
		}
		
		this.setAllPins();
		this.mainWindow.gamePanel().ludoTable().rePaint();
	}
	
	public void resetGame()
	{
		// BLUE PINS
		PinModel bp1 = new PinModel(2,11,Team.Blue);
		PinModel bp2 = new PinModel(2,12,Team.Blue);
		PinModel bp3 = new PinModel(3,11,Team.Blue);
		PinModel bp4 = new PinModel(3,12,Team.Blue);
		this.bluePins = new PinModel[]{bp1,bp2,bp3,bp4};
		
		//RED PINS
		PinModel rp1 = new PinModel(2,2,Team.Red);
		PinModel rp2 = new PinModel(2,3,Team.Red);
		PinModel rp3 = new PinModel(3,2,Team.Red);
		PinModel rp4 = new PinModel(3,3,Team.Red);
		this.redPins = new PinModel[]{rp1,rp2,rp3,rp4};

		// GREEN PINS
		PinModel gp1 = new PinModel(11,2,Team.Green);
		PinModel gp2 = new PinModel(11,3,Team.Green);
		PinModel gp3 = new PinModel(12,2,Team.Green);
		PinModel gp4 = new PinModel(12,3,Team.Green);
		this.greenPins = new PinModel[]{gp1,gp2,gp3,gp4};

		//YELLOW PINS
		PinModel yp1 = new PinModel(11,11,Team.Yellow);
		PinModel yp2 = new PinModel(11,12,Team.Yellow);
		PinModel yp3 = new PinModel(12,11,Team.Yellow);
		PinModel yp4 = new PinModel(12,12,Team.Yellow);
		this.yellowPins = new PinModel[]{yp1,yp2,yp3,yp4};

		this.currentTeam = Team.Blue;
		this.teamObserved.setValue(this.currentTeam);
		
		this.setDiceValue(0);	
		this.setAllPins();
		this.mainWindow.gamePanel().playerPanel().getDiceModel().buttonEnable();
		
		this.mainWindow.gamePanel().ludoTable().rePaint();
	}
	
	public List<Team> getRanking()
	{
		List ranking = new ArrayList<Team>();
		
		int bluePoints = 0;
		
		for (PinModel pin : this.bluePins)
		{
			bluePoints += this.model.getStepsRemaining(pin);
		}
		
		int redPoints = 0;
		
		for (PinModel pin : this.redPins)
		{
			redPoints += this.model.getStepsRemaining(pin);
		}
		
		int greenPoints = 0;
		
		for (PinModel pin : this.greenPins)
		{
			greenPoints += this.model.getStepsRemaining(pin);
		}
		
		int yellowPoints = 0;
		
		for (PinModel pin : this.yellowPins)
		{
			yellowPoints += this.model.getStepsRemaining(pin);
		}
		
		int points[] = new int[]{bluePoints, redPoints, greenPoints, yellowPoints};
		
		Arrays.sort(points);
		
		int i = 0;
		while(ranking.size() < 4)
		{
			if(points[i] == bluePoints)
			{
				ranking.add(Team.Blue);
				i++;
			}
			else if(points[i] == redPoints)
			{
				ranking.add(Team.Red);
				i++;
			}
			else if(points[i] == greenPoints)
			{
				ranking.add(Team.Green);
				i++;
			}
			else if(points[i] == yellowPoints)
			{
				ranking.add(Team.Yellow);
				i++;
			}
		}
		return ranking;
	}
}
