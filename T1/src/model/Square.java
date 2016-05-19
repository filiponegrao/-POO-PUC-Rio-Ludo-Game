package model;

public class Square
{

	private int posX;
	private int posY;
	private Team team;
	private SquareType type;
	
	public Square(int posX, int posY, Team team, SquareType type)
	{
		this.posX = posX;
		this.posY = posY;
		this.team = team;
		this.type = type;
	}
	
	public int xPosition()
	{
		return this.posX;
	}
	
	public int yPosition()
	{
		return this.posY;
	}
	
	public Team team()
	{
		return this.team;
	}
	
	public SquareType type()
	{
		return this.type;
	}
}
