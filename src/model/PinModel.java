package model;

public class PinModel 
{	
	private Team team;
	
	private int x;
	
	private int y;
	
	public PinModel(int x, int y, Team team)
	{
		this.x = x;
		this.y = y;
		this.team = team;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Team getTeam()
	{
		return this.team;
	}
	
}



