package model;

import java.util.Random;

import controller.LudoController;

public class DiceModel 
{
	private int value;
	
	private Boolean enable = true;
	
	public DiceModel()
	{
		
	}
	
	public void playDice()
	{
		if(!this.enable) { return; }
		
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
				this.enable = true;
				return;
			}
		}
		
		this.enable = false;
	}
	
	public void setValue()
	{
		LudoController.sharedInstance.setDiceValue(this.value);
	}
	
	public void buttonDisable()
	{
		this.enable = false;
	}
	
	public void buttonEnable()
	{
		this.enable = true;
	}
}
