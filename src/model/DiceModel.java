package model;

import java.util.Random;

public class DiceModel 
{
	private int value;
	
	public DiceModel()
	{
		
	}
	
	public void playDice()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(7);
		
		if(randomNum == 0)
		{
			while(randomNum == 0)
			{
				randomNum = rand.nextInt(7);
			}
		}
		
		this.value = randomNum;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
