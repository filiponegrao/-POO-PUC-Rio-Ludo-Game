package model;

import java.awt.List;
import java.util.Arrays;

public class LudoTableModel 
{
	private Square[] tab;
	private Square[] redPath;
	private Square[] greenPath;
	private Square[] yellowPath;
	private Square[] bluePath;
	
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

		this.tab = new Square[]{sq1,sq2,sq3,sq4,sq5,sq6,sq7,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq15,sq16,sq17,sq18,
				sq19,sq20,sq21,sq22,sq23,sq24,sq25,sq26,sq27,sq28,sq29,sq30,sq31,sq32,sq33,sq34,sq35,sq36,sq37,
				sq38,sq39,sq40,sq41,sq42,sq43,sq44,sq45,sq46,sq47,sq48,sq49,sq50,sq51,sq52,sq53,sq54,sq55,sq56,
				sq57,sq58,sq59,sq60,sq61,sq62,sq63,sq64,sq65,sq66,sq67,sq68,sq69,sq70,sq71,sq72,sq73,sq74,sq75,
				sq76,sq77,sq78,sq79,sq80,sq81};
		
		
		this.redPath = new Square[] {sq2,sq3,sq4,sq5,sq6,sq8,sq9,sq10,sq11,sq12,sq13,sq14,sq21,sq22,sq23,sq24,
				sq25,sq26,sq27,sq28,sq29,sq30,sq31,sq32,sq33,sq48,sq63,sq62,sq61,sq60,sq59,sq58,sq76,sq77,sq78,
				sq79,sq80,sq81,sq75,sq69,sq68,sq67,sq66,sq65,sq64,sq54,sq53,sq52,sq51,sq50,sq49,sq34,sq35,sq36,
				sq37,sq38,sq39,sq40};
		
	}
	
	public Square[] getModel() 
	{
		return this.tab;
	}

	public Square[] getRedPath()
	{
		return this.redPath;
	}
//	
//	public void getGreenPath()
//	{
//		return this.greenPath;
//	}
//	
//	public Square[] getYellowPath()
//	{
//		return this.yellowPath;
//	}
//	
//	public Square[] getBluePath()
//	{
//		return this.bluePath;
//	}
}
