package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

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
					
					map = this.stringToHashMap(content);
					
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

}
