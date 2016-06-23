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

	Team currentTeam = null;
	static ArrayList<String> pins = null;


	public static void readFile() throws IOException
	{
		try
		{
			inputStream = new BufferedReader(new FileReader("src/informacoesPartida.txt"));

			String reading;
			while((reading = inputStream.readLine()) != null)
			{
				System.out.println(reading);
			}
		}
		finally
		{
			if (inputStream != null) 
			{
				inputStream.close();
			}
		}
		
//		for(String pin : pins)
//		{
//			System.out.println(pin);
//		}
	}
}
