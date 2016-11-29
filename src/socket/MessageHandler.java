package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MessageHandler extends Observable implements Runnable
{
	public Socket client;
	
	public MessageHandler(Socket client)
	{
		this.client = client;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			this.addObserver(SocketController.sharedInstance());
			
			InputStream input = client.getInputStream();
			
			Scanner scanner = new Scanner(input);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			//enquanto o evento que ele receber for
			//diferente de "gameover"
			while(!map.containsKey("gameover"))
			{	
				//enquanto houver linhas para ler
				while(scanner.hasNextLine())
				{
					String content = scanner.nextLine();
					
					if (content.contains("game"))
					{
						map = this.stringToHashMapGame(content);
					}
					else
					{
						map = this.stringToHashMap(content);
					}
					
					//da o update
					this.setChanged();
					this.notifyObservers(map);
				}
			}
			
			scanner.close();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return;
		}
	}
	
	/**
	 * Funcao que transforma uma string com um formato de map
	 * em um map e o retorna.  */
	public HashMap<String, Object> stringToHashMap(String s)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String[] elements = s.split(",");
		
		for (String element : elements) {
			
			element = element.replace("{", "");
			element = element.replace("}", "");
			
			String key = element.split("=")[0];
			key = key.replace(" ", "");
			String value = element.split("=")[1];
			value = value.replace(" ", "");
			
			map.put(key, value);
		}
		
		return map;
	}

	
	public HashMap<String, Object> stringToHashMapGame(String s)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> gameData = new HashMap<String, Object>();
		
		String[] gamestring = s.split("game=");
		String mapGame = gamestring[1];
		String tokenFinal = Pattern.quote("]]}}");
		mapGame = mapGame.split(tokenFinal)[0];
		
		//Acha o time
		String teamString = mapGame.split("currentTeam=")[1];
		int comaIndex = teamString.indexOf(',');
		String currentTeam = teamString.substring(0, comaIndex);
						
		//Valor do dado
		String diceValue = mapGame.split("diceValue=")[1].substring(0, 1);
		
		String tokenCoords = Pattern.quote("],[");
		String tokenTeams = Pattern.quote("]],");
		
		ArrayList<ArrayList<String>> greenPins = new ArrayList<ArrayList<String>>();
		String greens = mapGame.split("greens=")[1];
		greens = greens.split(tokenTeams)[0];
		greens = greens.replace(" ", "");
		greens = greens.substring(2);
		String[] greenTuples = greens.split(tokenCoords);
		for (String tuple : greenTuples)
		{
			String x = tuple.split(",")[0];
			String y = tuple.split(",")[1];
			ArrayList<String> coords = new ArrayList<String>();
			coords.add(x);
			coords.add(y);
			greenPins.add(coords);
		}
		map.put("greens", greenPins);
		
		ArrayList<ArrayList<String>> redPins = new ArrayList<ArrayList<String>>();
		String reds = mapGame.split("reds=")[1];
		reds = reds.split(tokenTeams)[0];		
		reds = reds.replace(" ", "");
		reds = reds.substring(2);
		String[] redTuples = reds.split(tokenCoords);
		for (String tuple : redTuples)
		{
			String x = tuple.split(",")[0];
			String y = tuple.split(",")[1];
			ArrayList<String> coords = new ArrayList<String>();
			coords.add(x);
			coords.add(y);
			redPins.add(coords);
		}
		map.put("reds", redPins);
		
		ArrayList<ArrayList<String>> yellowPins = new ArrayList<ArrayList<String>>();
		String yellows = mapGame.split("yellows=")[1];
		yellows = yellows.split(tokenTeams)[0];
		yellows = yellows.replace(" ", "");
		yellows = yellows.substring(2);
		String[] yellowsTuples = yellows.split(tokenCoords);
		for (String tuple : yellowsTuples)
		{
			String x = tuple.split(",")[0];
			String y = tuple.split(",")[1];
			ArrayList<String> coords = new ArrayList<String>();
			coords.add(x);
			coords.add(y);
			yellowPins.add(coords);
		}
		map.put("yellows", yellowPins);
		
		ArrayList<ArrayList<String>> bluePins = new ArrayList<ArrayList<String>>();
		String blues = mapGame.split("blues=")[1];
		blues = blues.split(tokenTeams)[0];
		blues = blues.replace(" ", "");
		blues = blues.substring(2);
		String[] blueTuples = blues.split(tokenCoords);
		for (String tuple : blueTuples)
		{
			String x = tuple.split(",")[0];
			String y = tuple.split(",")[1];
			ArrayList<String> coords = new ArrayList<String>();
			coords.add(x);
			coords.add(y);
			bluePins.add(coords);
		}
		map.put("blues", bluePins);
		
		map.put("currentTeam", currentTeam);
		map.put("diceValue", diceValue);
		gameData.put("game", map);
		
		System.out.println(gameData.toString());
		
		return gameData;
	}
	
}
