package socket;

import java.net.*;
import model.*;

public class Player
{
	public Socket socket;
	
	public String nickname;
	
	public Team team;
	
	public Player(Socket socket, String nickname, Team team)
	{
		this.socket = socket;
		this.nickname = nickname;
		this.team = team;
	}
	
}
