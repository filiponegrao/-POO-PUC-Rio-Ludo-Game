package model;

public class TeamModel 
{
	private Team team;
	private int pawnIn = 0;
	private int pawnOut = 4;
	
	public TeamModel(Team team)
	{
		this.team = team;
	}
	
	public void setPiecesIn()
	{
		this.pawnIn += 1;
		this.pawnOut -= 1;
	}
	
	public int getPiecesIn()
	{
		return this.pawnIn;
	}
	
	public int getPiecesOut()
	{
		return this.pawnOut;
	}
}
