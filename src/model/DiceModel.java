package model;

import java.util.Random;

import controller.LudoController;

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
		this.setValue();
		
		if(LudoController.sharedInstance.skipPlayer())
		{
			return;
		}
		else if(this.value == 6)
		{
			if(!LudoController.sharedInstance.thirdTimeSix())
			{
				LudoController.sharedInstance.checkSevenSteps();
			}
		}
	}
	
	public void setValue()
	{
		LudoController.sharedInstance.setDiceValue(this.value);
	}
}
