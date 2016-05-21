package model;

import java.awt.Color;

import view.MyColors;

public enum Team 
{
	None(Color.WHITE), Red(MyColors.myDarkRed), Green(MyColors.myDarkGreen), Yellow(MyColors.myDarkYellow), Blue(MyColors.myDarkBlue);	
	
	Color color;
	
	Team(Color c)
	{
		this.color = c;
	}
	
	public Color getColor()
	{
		return this.color;
	}
}
