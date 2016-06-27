package model;

import java.util.List;

import controller.LudoController;

import java.util.ArrayList;
import java.util.Arrays;

public class LudoTableModel 
{
	private final Square[] tab;
	private final Square[] redPath;
	private final Square[] greenPath;
	private final Square[] yellowPath;
	private final Square[] bluePath;
	
	//Pins
	
	private PinModel[] redPins;
	private PinModel[] bluePins;
	private PinModel[] yellowPins;
	private PinModel[] greenPins;

	
	public LudoTableModel()
	{
		Square sq1 = new Square(0, 6, Team.None, SquareType.Normal);
		Square sq2 = new Square(1, 6, Team.Red, SquareType.RedStart);
		Square sq3 = new Square(2, 6, Team.None, SquareType.Normal);
		Square sq4 = new Square(3, 6, Team.None, SquareType.Normal);
		Square sq5 = new Square(4, 6, Team.None, SquareType.Normal);
		Square sq6 = new Square(5, 6, Team.None, SquareType.Normal);
		
		Square sq7 = new Square(6, 6, Team.None, SquareType.Normal);
		Square sq8 = new Square(6, 5, Team.None, SquareType.Normal);
		Square sq9 = new Square(6, 4, Team.None, SquareType.Normal);
		Square sq10 = new Square(6, 3, Team.None, SquareType.Normal);
		Square sq11 = new Square(6, 2, Team.None, SquareType.Normal);
		Square sq12 = new Square(6, 1, Team.None, SquareType.SafePoint);
		Square sq13 = new Square(6, 0, Team.None, SquareType.Normal);

		Square sq14 = new Square(7, 0, Team.Green, SquareType.GreenEntry);
		Square sq15 = new Square(7, 1, Team.Green, SquareType.GreenRoad);
		Square sq16 = new Square(7, 2, Team.Green, SquareType.GreenRoad);
		Square sq17 = new Square(7, 3, Team.Green, SquareType.GreenRoad);
		Square sq18 = new Square(7, 4, Team.Green, SquareType.GreenRoad);
		Square sq19 = new Square(7, 5, Team.Green, SquareType.GreenRoad);
		Square sq20 = new Square(7, 6, Team.Green, SquareType.GreenRoad);
		
		Square sq21 = new Square(8, 0, Team.None, SquareType.Normal);
		Square sq22 = new Square(8, 1, Team.Green, SquareType.GreenStart);
		Square sq23 = new Square(8, 2, Team.None, SquareType.Normal);
		Square sq24 = new Square(8, 3, Team.None, SquareType.Normal);
		Square sq25 = new Square(8, 4, Team.None, SquareType.Normal);
		Square sq26 = new Square(8, 5, Team.None, SquareType.Normal);
		Square sq27 = new Square(8, 6, Team.None, SquareType.Normal);
		
		Square sq28 = new Square(9, 6, Team.None, SquareType.Normal);
		Square sq29 = new Square(10, 6, Team.None, SquareType.Normal);
		Square sq30 = new Square(11, 6, Team.None, SquareType.Normal);
		Square sq31 = new Square(12, 6, Team.None, SquareType.Normal);
		Square sq32 = new Square(13, 6, Team.None, SquareType.SafePoint);
		Square sq33 = new Square(14, 6, Team.None, SquareType.Normal);
		
		Square sq34 = new Square(0, 7, Team.Red, SquareType.RedEntry);
		Square sq35 = new Square(1, 7, Team.Red, SquareType.RedRoad);
		Square sq36 = new Square(2, 7, Team.Red, SquareType.RedRoad);
		Square sq37 = new Square(3, 7, Team.Red, SquareType.RedRoad);
		Square sq38 = new Square(4, 7, Team.Red, SquareType.RedRoad);
		Square sq39 = new Square(5, 7, Team.Red, SquareType.RedRoad);
		Square sq40 = new Square(6, 7, Team.Red, SquareType.RedRoad);
		Square sq41 = new Square(7, 7, Team.None, SquareType.Normal);
		
		Square sq42 = new Square(8, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq43 = new Square(9, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq44 = new Square(10, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq45 = new Square(11, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq46 = new Square(12, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq47 = new Square(13, 7, Team.Yellow, SquareType.YellowRoad);
		Square sq48 = new Square(14, 7, Team.Yellow, SquareType.YellowEntry);
		
		Square sq49 = new Square(0, 8, Team.None, SquareType.Normal);
		Square sq50 = new Square(1, 8, Team.None, SquareType.SafePoint);
		Square sq51 = new Square(2, 8, Team.None, SquareType.Normal);
		Square sq52 = new Square(3, 8, Team.None, SquareType.Normal);
		Square sq53 = new Square(4, 8, Team.None, SquareType.Normal);
		Square sq54 = new Square(5, 8, Team.None, SquareType.Normal);
		Square sq55 = new Square(6, 8, Team.None, SquareType.Normal);
		Square sq56 = new Square(7, 8, Team.Blue, SquareType.BlueRoad);
		Square sq57 = new Square(8, 8, Team.None, SquareType.Normal);
		Square sq58 = new Square(9, 8, Team.None, SquareType.Normal);
		Square sq59 = new Square(10, 8, Team.None, SquareType.Normal);
		Square sq60 = new Square(11, 8, Team.None, SquareType.Normal);
		Square sq61 = new Square(12, 8, Team.None, SquareType.Normal);
		Square sq62 = new Square(13, 8, Team.Yellow, SquareType.YellowStart);
		Square sq63 = new Square(14, 8, Team.None, SquareType.Normal);
		
		Square sq64 = new Square(6, 9, Team.None, SquareType.Normal);
		Square sq65 = new Square(6, 10, Team.None, SquareType.Normal);
		Square sq66 = new Square(6, 11, Team.None, SquareType.Normal);
		Square sq67 = new Square(6, 12, Team.None, SquareType.Normal);
		Square sq68 = new Square(6, 13, Team.Blue, SquareType.BlueStart);
		Square sq69 = new Square(6, 14, Team.None, SquareType.Normal);
		
		Square sq70 = new Square(7, 9, Team.Blue, SquareType.BlueRoad);
		Square sq71 = new Square(7, 10, Team.Blue, SquareType.BlueRoad);
		Square sq72 = new Square(7, 11, Team.Blue, SquareType.BlueRoad);
		Square sq73 = new Square(7, 12, Team.Blue, SquareType.BlueRoad);
		Square sq74 = new Square(7, 13, Team.Blue, SquareType.BlueRoad);
		Square sq75 = new Square(7, 14, Team.Blue, SquareType.BlueEntry);
		
		Square sq76 = new Square(8, 9, Team.None, SquareType.Normal);
		Square sq77 = new Square(8, 10, Team.None, SquareType.Normal);
		Square sq78 = new Square(8, 11, Team.None, SquareType.Normal);
		Square sq79 = new Square(8, 12, Team.None, SquareType.Normal);
		Square sq80 = new Square(8, 13, Team.Blue, SquareType.SafePoint);
		Square sq81 = new Square(8, 14, Team.None, SquareType.Normal);
		Square sq82 = new Square(7,8, Team.Blue, SquareType.BlueRoad);

		this.tab = new Square[]{sq1,sq2,sq3,sq4,sq5,sq6,sq7,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq15,sq16,sq17,sq18,
				sq19,sq20,sq21,sq22,sq23,sq24,sq25,sq26,sq27,sq28,sq29,sq30,sq31,sq32,sq33,sq34,sq35,sq36,sq37,
				sq38,sq39,sq40,sq41,sq42,sq43,sq44,sq45,sq46,sq47,sq48,sq49,sq50,sq51,sq52,sq53,sq54,sq55,sq56,
				sq57,sq58,sq59,sq60,sq61,sq62,sq63,sq64,sq65,sq66,sq67,sq68,sq69,sq70,sq71,sq72,sq73,sq74,sq75,
				sq76,sq77,sq78,sq79,sq80,sq81,sq82};
		
		this.redPath = new Square[] {sq2,sq3,sq4,sq5,sq6,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq21,sq22,sq23,sq24,
				sq25,sq26,sq28,sq29,sq30,sq31,sq32,sq33,sq48,sq63,sq62,sq61,sq60,sq59,sq58,sq76,sq77,sq78,
				sq79,sq80,sq81,sq75,sq69,sq68,sq67,sq66,sq65,sq64,sq54,sq53,sq52,sq51,sq50,sq49,sq34,sq35,sq36,
				sq37,sq38,sq39,sq40};
		
		this.greenPath = new Square[] {sq22,sq23,sq24,sq25,sq26,sq28,sq29,sq30,sq31,sq32,sq33,sq48,sq63,sq62,
				sq61,sq60,sq59,sq58,sq76,sq77,sq78,sq79,sq80,sq81,sq75,sq69,sq68,sq67,sq66,sq65,sq64,sq54,sq53,
				sq52,sq51,sq50,sq49,sq34,sq1,sq2,sq3,sq4,sq5,sq6,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq15,sq16,sq17,
				sq18,sq19,sq20};
		
		this.yellowPath = new Square[] {sq62,sq61,sq60,sq59,sq58,sq76,sq77,sq78,sq79,sq80,sq81,sq75,sq69,sq68,
				sq67,sq66,sq65,sq64,sq54,sq53,sq52,sq51,sq50,sq49,sq34,sq1,sq2,sq3,sq4,sq5,sq6,sq8,sq9,sq10,
				sq11,sq12,sq13,sq14, sq21,sq22,sq23,sq24,sq25,sq26,sq28,sq29,sq30,sq31,sq32,sq33,sq48,sq47,sq46,
				sq45,sq44,sq43,sq42};
		
		this.bluePath = new Square[] {sq68,sq67,sq66,sq65,sq64,sq54,sq53,sq52,sq51,sq50,sq49,sq34,sq1,sq2,sq3,
				sq4,sq5,sq6,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq21,sq22,sq23,sq24,sq25,sq26,sq28,sq29,sq30,sq31,
				sq32,sq33,sq48,sq63,sq62,sq61,sq60,sq59,sq58,sq76,sq77,sq78,sq79,sq80,sq81,sq75,sq74,sq73,
				sq72,sq71,sq70,sq82};
		
		
		//Red pins
		
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
		
		// BLUE PINS
		
		PinModel bp1 = new PinModel(2,11,Team.Blue);
		PinModel bp2 = new PinModel(2,12,Team.Blue);
		PinModel bp3 = new PinModel(3,11,Team.Blue);
		PinModel bp4 = new PinModel(3,12,Team.Blue);
		
		this.bluePins = new PinModel[]{bp1,bp2,bp3,bp4};
		
	}
	
	public Square[] getModel() 
	{
		return this.tab;
	}

	public Square[] getRedPath()
	{
		return this.redPath;
	}
	
	public Square[] getGreenPath()
	{
		return this.greenPath;
	}
	
	public Square[] getYellowPath()
	{
		return this.yellowPath;
	}
	
	public Square[] getBluePath()
	{
		return this.bluePath;
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
	
	public Square getNextSquareWithSteps(int x, int y, Team t, int steps)
	{
		if(t == Team.Blue)
		{
			for (int i = 0; i < bluePath.length; i++)
			{
				if(bluePath[i].xPosition() == x && bluePath[i].yPosition() == y)
				{
					if(i+steps >= bluePath.length)
					{
						return bluePath[bluePath.length-1];
					}
					else
					{
						return bluePath[(i+steps)];
					}
				}
			}
			
			return bluePath[0];
		}
		else if(t == Team.Red)
		{
			for (int i = 0; i < redPath.length; i++)
			{
				if(redPath[i].xPosition() == x && redPath[i].yPosition() == y)
				{
					if(i+steps >= redPath.length)
					{
						return redPath[redPath.length-1];
					}
					else
					{
						return redPath[(i+steps)];
					}
				}
			}
			
			return redPath[0];
		}
		else if(t == Team.Green)
		{
			for (int i = 0; i < greenPath.length; i++)
			{
				if(greenPath[i].xPosition() == x && greenPath[i].yPosition() == y)
				{
					if(i+steps >= greenPath.length)
					{
						return greenPath[greenPath.length-1];
					}
					else
					{
						return greenPath[(i+steps)];
					}
				}
			}
			
			return greenPath[0];
		}
		else if(t == Team.Yellow)
		{
			for (int i = 0; i < yellowPath.length; i++)
			{
				if(yellowPath[i].xPosition() == x && yellowPath[i].yPosition() == y)
				{
					if(i+steps >= yellowPath.length)
					{
						return yellowPath[yellowPath.length-1];
					}
					else
					{
						return yellowPath[(i+steps)];
					}
				}
			}
			
			return yellowPath[0];
		}
		
		return null;
	}
	
	/** 
	 *  função que verifica se o pino está na casa inicial checando se ao adicionar 1 passo 
	 *  pino deve mover-se para casa de saída
	 */

	public Boolean isInitialPin (PinModel p)
	{
		Square sq = this.getNextSquareWithSteps(p.getX(), p.getY(), p.getTeam(), 1);
		
		if(sq != null)
		{
			if(p.getTeam() == Team.Blue && sq == bluePath[0])
			{
				return true;
			}
			else if(p.getTeam() == Team.Red && sq == redPath[0])
			{
				return true;
			}
			else if(p.getTeam() == Team.Green && sq == greenPath[0])
			{
				return true;
			}
			else if(p.getTeam() == Team.Yellow && sq == yellowPath[0])
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		 
		return null;
	}
	
	/**
	 *  Funcao que verifica o caso da unica movimentacao
	 *  de todas as peças de um jogador, ser a casa inicial.
	 *  Nesse caso a funcao retorna false, pois nao ha possibilidades
	 *  além de tirar uma peça da casa inicial.
	 */
	public Boolean hasPossibilites(PinModel[] pins)
	{
		for (PinModel pin : pins)
		{
			Team team = pin.getTeam();
			
			Square teste = this.getNextSquareWithSteps(pin.getX(), pin.getY(), team, 1);
			
			if(team == Team.Blue)
			{
				if(!teste.equals(this.bluePath[0]))
				{
					return true;
				}
			}
			else if(team == Team.Red)
			{
				if(!teste.equals(this.redPath[0]))
				{
					return true;
				}
			}
			else if(team == Team.Green)
			{
				if(!teste.equals(this.greenPath[0]))
				{
					return true;
				}
			}
			else if(team == Team.Yellow)
			{
				if(!teste.equals(this.yellowPath[0]))
				{
					return true;
				}
			}			
		}
		
		return false;
	}
	
	public Boolean ableToMove(PinModel pins[], int diceValue)
	{
		if(!this.hasPossibilites(pins) && diceValue != 5)
		{
			return false;
		}
		
		for (PinModel pin : pins)
		{
			Square s = this.getNextSquareWithSteps(pin.getX(), pin.getY(), pin.getTeam(), diceValue);
			
			if(s == null) { break; }
			
			if(LudoController.sharedInstance.checkPathClear(pin))
			{
				if(!this.isPinOnFinal(s.xPosition(), s.yPosition(), pin.getTeam()))
				{
					System.out.println(s.xPosition());
					System.out.println(s.yPosition());

					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public Coord getHouseSquareAvaliable(PinModel[] pins)
	{
		List<Coord> squares = new ArrayList<Coord>();
		
		Team team = pins[0].getTeam();
		
		if(team == Team.Red)
		{
			Coord c1 = new Coord(2,2);
			Coord c2 = new Coord(2,3);
			Coord c3 = new Coord(3,2);
			Coord c4 = new Coord(3,3);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			if (squares.size() == 0) { return null; }
			
			return squares.get(0);
		}
		else if(team == Team.Blue)
		{			
			Coord c1 = new Coord(2,11);
			Coord c2 = new Coord(2,12);
			Coord c3 = new Coord(3,11);
			Coord c4 = new Coord(3,12);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.get(0);
		}
		else if(team == Team.Green)
		{
			Coord c1 = new Coord(11,2);
			Coord c2 = new Coord(11,3);
			Coord c3 = new Coord(12,2);
			Coord c4 = new Coord(12,3);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.get(0);
		}
		else if(team == Team.Yellow)
		{
			Coord c1 = new Coord(11,11);
			Coord c2 = new Coord(11,12);
			Coord c3 = new Coord(12,11);
			Coord c4 = new Coord(12,12);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			if(squares.size() == 0)
			{
				return null;
			}
			else
			{
				return squares.get(0);
			}			
		}
		
		return null;
	}
	
	public List<Square> getPathForSteps(PinModel pin, int steps)
	{		
		List<Square> squares = new ArrayList<Square>();
		
		if(this.isPinOnFinal(pin.getX(), pin.getY(), pin.getTeam())) { return squares; }

		for(int i = 1; i <= steps; i++)
		{
			squares.add(this.getNextSquareWithSteps(pin.getX(), pin.getY(), pin.getTeam(), i));
		}
		
		return squares;
	}
	
	public int numberOfHousesAvaliable(PinModel[] pins)
	{
		List<Coord> squares = new ArrayList<Coord>();

		Team team = pins[0].getTeam();

		if(team == Team.Red)
		{
			Coord c1 = new Coord(2,2);
			Coord c2 = new Coord(2,3);
			Coord c3 = new Coord(3,2);
			Coord c4 = new Coord(3,3);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.size();
		}
		else if(team == Team.Blue)
		{			
			Coord c1 = new Coord(2,11);
			Coord c2 = new Coord(2,12);
			Coord c3 = new Coord(3,11);
			Coord c4 = new Coord(3,12);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.size();
		}
		else if(team == Team.Green)
		{
			Coord c1 = new Coord(11,2);
			Coord c2 = new Coord(11,3);
			Coord c3 = new Coord(12,2);
			Coord c4 = new Coord(12,3);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.size();
		}
		else if(team == Team.Yellow)
		{
			Coord c1 = new Coord(11,11);
			Coord c2 = new Coord(11,12);
			Coord c3 = new Coord(12,11);
			Coord c4 = new Coord(12,12);

			squares.add(c1);
			squares.add(c2);
			squares.add(c3);
			squares.add(c4);
			
			for (PinModel pin : pins)
			{				
				if(pin.getX() == c1.x && pin.getY() == c1.y)
				{
					squares.remove(c1);
				}
				else if(pin.getX() == c2.x && pin.getY() == c2.y)
				{
					squares.remove(c2);
				}
				else if(pin.getX() == c3.x && pin.getY() == c3.y)
				{
					squares.remove(c3);
				}
				else if(pin.getX() == c4.x && pin.getY() == c4.y)
				{
					squares.remove(c4);
				}
			}
			
			return squares.size();			
		}
		
		return squares.size();			
	}
	
	public Boolean isOnVictoryRoad(PinModel pin)
	{
		Team team  = pin.getTeam();
		
		int x = pin.getX();
		
		int y = pin.getY();
		
		if(team == Team.Blue)
		{
			for (Square square : bluePath)
			{
				if(square.xPosition() == x && square.yPosition() == y)
				{
					if(square.type() == SquareType.BlueRoad)
					{
						return true;
					}
				}
			}
		}
		else if(team == Team.Red)
		{
			for (Square square : redPath)
			{
				if(square.xPosition() == x && square.yPosition() == y)
				{
					if(square.type() == SquareType.RedRoad)
					{
						return true;
					}
				}
			}
		}
		else if(team == Team.Green)
		{
			for (Square square : greenPath)
			{
				if(square.xPosition() == x && square.yPosition() == y)
				{
					if(square.type() == SquareType.GreenRoad)
					{
						return true;
					}
				}
			}
		}
		else if(team == Team.Yellow)
		{
			for (Square square : yellowPath)
			{
				if(square.xPosition() == x && square.yPosition() == y)
				{
					if(square.type() == SquareType.YellowRoad)
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
//	public Boolean isSafePoint(int x, int y)
//	{
//		
//	}
	
	public Boolean isPinOnFinal(int x, int y, Team team)
	{		
		if(team == Team.Red)
		{
			if(	x == this.redPath[this.redPath.length-1].xPosition() &&
				y == this.redPath[this.redPath.length-1].yPosition())
			{
				return true;
			}
			
			return false;
		}
		else if(team == Team.Blue)
		{
			if(	x == this.bluePath[this.bluePath.length-1].xPosition() &&
					y == this.bluePath[this.bluePath.length-1].yPosition())
				{
					return true;
				}
				
			return false;
		}
		else if(team == Team.Green)
		{
			if(	x == this.greenPath[this.greenPath.length-1].xPosition() &&
					y == this.greenPath[this.greenPath.length-1].yPosition())
				{
					return true;
				}
				
			return false;
			
		}
		else if(team == Team.Yellow)
		{
			if(	x == this.yellowPath[this.yellowPath.length-1].xPosition() &&
					y == this.yellowPath[this.yellowPath.length-1].yPosition())
				{
					return true;
				}
				
			return false;
		}
		
		return false;
	}
	
	public Square getSquare(int x, int y)
	{
		for (Square square : this.tab)
		{
			if(square.xPosition() == x && square.yPosition() == y)
			{
				return square;
			}
		}
		
		return null;
	}
	
	public Square getFinalSquare(Team team)
	{
		if(team == Team.Blue)
		{
			return this.bluePath[this.bluePath.length-1];
		}
		else if(team == Team.Red)
		{
			return this.redPath[this.redPath.length-1];
		}
		else if(team == Team.Green)
		{
			return this.greenPath[this.greenPath.length-1];
		}
		else if(team == Team.Yellow)
		{
			return this.yellowPath[this.yellowPath.length-1];
		}
		
		return null;
	}
	
	public int getStepsRemaining(PinModel pin)
	{
		Team team = pin.getTeam();
		
		if(this.isPinOnFinal(pin.getX(), pin.getY(), team)) { return 0; }
		
		if(team == Team.Blue)
		{
			List blues = Arrays.asList(this.bluePath);
			
			int index = blues.indexOf(pin);
			
			return blues.size() - index - 1;	
		}
		else if(team == Team.Red)
		{
			List reds = Arrays.asList(this.redPath);
			
			int index = reds.indexOf(pin);
			
			return reds.size() - index - 1;
		}
		else if(team == Team.Green)
		{
			List greens = Arrays.asList(this.greenPath);
			
			int index = greens.indexOf(pin);
			
			return greens.size() - index - 1;
		}
		else if(team == Team.Yellow)
		{
			List yellows = Arrays.asList(this.yellowPath);
			
			int index = yellows.indexOf(pin);
			
			return yellows.size() - index - 1;
		}
		
		return -1;
	}
}
