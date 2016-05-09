package model;

import java.awt.List;

public class LudoTableModel 
{
	private static Square[] tab;
	private Square[] redPath;
	
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
		Square sq12 = new Square(6, 1, Team.None, SquareType.Normal);
		Square sq13 = new Square(6, 0, Team.None, SquareType.Normal);

		Square sq14 = new Square(7, 0, Team.None, SquareType.Normal);
		Square sq15 = new Square(7, 1, Team.Green, SquareType.GreenRoad);
		Square sq16 = new Square(7, 2, Team.Green, SquareType.GreenRoad);
		Square sq17 = new Square(7, 3, Team.Green, SquareType.GreenRoad);
		Square sq18 = new Square(7, 4, Team.Green, SquareType.GreenRoad);
		Square sq19 = new Square(7, 5, Team.Green, SquareType.GreenRoad);
		Square sq20 = new Square(7, 6, Team.Green, SquareType.GreenRoad);
		
		
		this.tab = new Square[]{sq1,sq2,sq3,sq4,sq5,sq6,sq7,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq15,sq16,sq17,sq18,sq19,sq20};
	}
	
	public static Square[] getModel() 
	{
		return tab;
	}
	
}
