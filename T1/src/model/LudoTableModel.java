package model;

import java.awt.List;

public class LudoTableModel {
	
	
	public static Square[] getModel() 
	{
		Square sq1 = new Square(0, 7, Team.None, SquareType.Normal);
		Square sq2 = new Square(1, 7, Team.Red, SquareType.RedStart);
		Square sq3 = new Square(2, 7, Team.None, SquareType.Normal);
		Square sq4 = new Square(3, 7, Team.None, SquareType.Normal);
		Square sq5 = new Square(4, 7, Team.None, SquareType.Normal);
		Square sq6 = new Square(5, 7, Team.None, SquareType.Normal);
		Square sq7 = new Square(6, 7, Team.None, SquareType.Normal);

	
		return new Square[]{sq1, sq2,sq3,sq4,sq5,sq6, sq7};
	}
	
}
