package controller;

import socket.SocketController;
import view.*;

//Autores:
//Fernanda de Miranda Carvalho: 1411287
//Filipo Teixeira Negrão: 1221846

public class Main 
{
	public static void main(String[] args) 
	{
		
		LudoController.sharedInstance.loadPreJogo();
		
		SocketController.sharedInstance().connect("127.0.0.1", 6969);
//		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	SocketController.sharedInstance().disconnect();
		    }
		}));
		
		//Teste de jogo
		//LudoController.sharedInstance.loadScreen();

		
	}
}
