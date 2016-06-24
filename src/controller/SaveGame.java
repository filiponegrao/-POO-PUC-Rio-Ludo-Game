package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.PinModel;
import model.Team;

public class SaveGame 
{
	static PrintWriter outputStream = null;
	
	public static void writeFile (Team currentTeam, PinModel[] bluePins, PinModel[] redPins, PinModel[] greenPins, PinModel[] yellowPins) throws IOException
	{
		try
		{
			outputStream = new PrintWriter(new FileWriter("src/informacoesPartida.txt"));
			
			outputStream.println(currentTeam);
			for(PinModel pin : bluePins)
			{
//				outputStream.println(pin.getTeam());
				outputStream.println(pin.getX());
				outputStream.println(pin.getY());
			}
			for(PinModel pin : redPins)
			{
//				outputStream.println(pin.getTeam());
				outputStream.println(pin.getX());
				outputStream.println(pin.getY());
			}
			for(PinModel pin : greenPins)
			{
//				outputStream.println(pin.getTeam());
				outputStream.println(pin.getX());
				outputStream.println(pin.getY());
			}
			for(PinModel pin : yellowPins)
			{
//				outputStream.println(pin.getTeam());
				outputStream.println(pin.getX());
				outputStream.println(pin.getY());
			}

		}
		finally
		{
			if (outputStream != null) 
			{
				outputStream.close();
			}
		}
	}

}
