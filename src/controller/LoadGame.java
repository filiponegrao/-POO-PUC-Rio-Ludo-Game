package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.PinModel;
import model.Team;

public class LoadGame 
{
	static BufferedReader inputStream = null;

	static ArrayList<String> gameData = new ArrayList<String>();


	public static ArrayList<String> readFile() throws IOException
	{
		try
		{
			inputStream = new BufferedReader(new FileReader("src/informacoesPartida.txt"));

			String reading;
			while((reading = inputStream.readLine()) != null)
			{
				gameData.add(reading);
			}
		}
		finally
		{
			if (inputStream != null) 
			{
				inputStream.close();
			}
		}

		return gameData;

	}
}
