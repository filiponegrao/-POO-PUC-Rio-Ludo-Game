package socket;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.*;

public class SocketController
{

	private static SocketController data = new SocketController();
	
	/** Server information */
	
	private String server;
	
	private int port;
	
	private Socket socket;
	
	/***********************/
	
	public Team myTeam;
	
	public String myNickname;
	
//	public List<Player> players;
	
	
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
			System.out.println("Conectado!");
			
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


	
	
	
}
