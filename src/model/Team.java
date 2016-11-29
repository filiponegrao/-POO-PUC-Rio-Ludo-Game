package model;

import java.awt.Color;

import view.MyColors;

public enum Team 
{
	None(Color.WHITE,"Nada"),
	Red(MyColors.myDarkRed,"Vermelho"),
	Green(MyColors.myDarkGreen,"Verde"),
	Yellow(MyColors.myDarkYellow, "Amarelo"),
	Blue(MyColors.myDarkBlue,"Azul");	
	
	Color color;
	
	String name;
	
	Team(Color c, String name)
	{
		this.color = c;
		this.name = name;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public static Team newTeam(String name)
	{
		if(name.equals(Team.Blue.getName()))
		{
			return Team.Blue;
		}
		else if(name.equals(Team.Red.getName()))
		{
			return Team.Red;
		}
		else if(name.equals(Team.Green.getName()))
		{
			return Team.Green;
		}
		else if(name.equals(Team.Yellow.getName()))
		{
			return Team.Yellow;
		}
		
		System.out.println("Nao foi possivel conveter a stirng para um Team");
		System.out.println(name);
		return Team.Blue;
	}
	
}
