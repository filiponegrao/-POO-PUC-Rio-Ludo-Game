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

		
		this.tab = new Square[]{sq1,sq2,sq3,sq4,sq5,sq6,sq7,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq15,sq16,sq17,sq18,sq19,sq20,
				sq21,sq22,sq23,sq24,sq25,sq26,sq27,sq28,sq29,sq30,sq31,sq32,sq33,sq34,sq35,sq36,sq37,sq38,sq39,sq40};
	}
	
	public static Square[] getModel() 
	{
		return tab;
	}
	
}
