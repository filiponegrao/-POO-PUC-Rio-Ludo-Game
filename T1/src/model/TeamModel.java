package model;

public class TeamModel 
{
	private Team team;
	private int piecesIn = 0;
	private int piecesOut = 4;
	
	public TeamModel(Team team)
	{
		this.team = team;
	}
	
	public void setPiecesIn()
	{
		this.piecesIn += 1;
		this.piecesOut -= 1;
	}
	
	public int getPiecesIn()
	{
		return this.piecesIn;
	}
	
	public int getPiecesOut()
	{
		return this.piecesOut;
	}
}
