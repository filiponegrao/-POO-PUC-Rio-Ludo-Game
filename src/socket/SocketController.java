package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.sun.javafx.collections.MappingChange.Map;

import model.*;

public class SocketController implements Observer
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
			
			this.output = this.socket.getOutputStream();
						
			System.out.println("Conectado!");
			
			MessageHandler handler = new MessageHandler(this.socket);
			
			Thread thread = new Thread(handler);
			thread.start();
			
			// para fins de teste
			this.serverAuthenticate("filiponegrao");
			this.serverSendGame();
			
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
	
	public Boolean serverSendGame()
	{
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		map.put("teste1", "teste1");
		map.put("teste2", "teste2");
		
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
		
		System.out.println(map);
	}
	
}
