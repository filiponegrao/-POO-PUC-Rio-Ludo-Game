package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.LudoController;
import model.*;
import view.PreGame;

public class SocketController extends Observable implements Observer 
{

	private static SocketController data = new SocketController();
	
	/** Server information */
	
	private String server;
	
	private int port;
	
	private Socket socket;
	
	private OutputStream output;
	
	private InputStream input;
	
	/***********************/
	
	public Team myTeam;
	
	public String myNickname;
	
	public List<Player> players = new ArrayList<Player>();
		
	static public SocketController sharedInstance()
	{
		return data;
	}
	
	public Boolean connect(String server, int port)
	{
		this.server = server;
		this.port = port;
		
		try {
			
			this.socket = new Socket(server, port);
			
			this.output = this.socket.getOutputStream();
						
			System.out.println("Conectado!");
			
			MessageHandler handler = new MessageHandler(this.socket);
			
			Thread thread = new Thread(handler);
			thread.start();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public void disconnect()
	{
		try {
			this.socket.close();
			System.out.println("Desconectado!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean sendMessage(byte[] bytes)
	{		
		try {
			
			this.output.write(bytes, 0, bytes.length);			
			this.output.flush();
			
			System.out.println("Mnesagem enviada com sucesso!");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean serverSendGame(ArrayList<String> gamedata, int dicevalue)
	{
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		map.put("game", gamedata);
		map.put("dice", dicevalue);
		
		System.out.println("Map que estou e viando!: " + map);
		
		String text = map.toString() + "\n";
		
		this.sendMessage(text.getBytes());
		
		return true;
	}
	
	public Boolean serverAuthenticate(String nickname)
	{
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		map.put("nickname", nickname);
		
		String text = map.toString()  + "\n";
		
		this.sendMessage(text.getBytes());
		
		return true;		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> map = (HashMap<String, Object>) arg;
		
		if(map.containsKey("nickname"))
		{
			String nickname = (String) map.get("nickname");
			String teamstring = (String) map.get("team");
			
			if (nickname == this.myNickname)
			{
				this.myTeam = Team.newTeam(teamstring);
			}
			else
			{
				Team team = Team.newTeam(teamstring);
				Player player = new Player(nickname, team);
								
				this.players.add(player);
			}
		}
		else if(map.containsKey("game"))
		{
			ArrayList<String> gamedata = (ArrayList<String>) map.get("game");
			int dice = (int) map.get("dice");
			
			LudoController.sharedInstance.setDiceValue(dice);
			LudoController.sharedInstance.processGameData(gamedata);
		}
		
		System.out.println(map);
		
		this.setChanged();
		this.notifyObservers(map);
	}
	
	public Boolean isReady()
	{
		if (this.players.size() == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
