package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.LudoController;
import model.Team;
import socket.SocketController;

public class PreGame extends JFrame implements Observer
{
	private Toolkit tk;
	
	private Dimension dimension;
	
	private PreGamePanel prePanel;
	private String nickname;
	
	public PreGame(String title)
	{
		this.tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.dimension = new Dimension(screenWidth/2, screenHeight/2);
		
		this.prePanel = new PreGamePanel(this.dimension, this);
		
		this.setSize(this.dimension);
		this.getContentPane().add(this.prePanel);
		
		/******************************************/
		/********* POSICAO DA TELA ***************/
		/******************************************/
		
		int xpos = screenWidth/4;
		int ypos = screenHeight/4;
		
		this.setLocation(xpos, ypos);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(title);
		
		SocketController.sharedInstance().addObserver(this);
		
		this.setVisible(true);
	}

	
	protected void setNickname(String name)
	{
		this.nickname = name;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		
		HashMap<String, Object> map = (HashMap<String, Object>) arg;
		
		if(map.containsKey("nickname"))
		{
			String shortNickname = this.nickname;
			
			shortNickname = shortNickname.replaceAll(" ", "");
			
			if(shortNickname.equals(map.get("nickname")))
			{
				String team = (String) map.get("team");
				SocketController.sharedInstance().myNickname = this.nickname;
				
				if(team.equals(Team.Blue.getName()))
				{
					SocketController.sharedInstance().myTeam = Team.Blue;
				}
				else if(team.equals(Team.Red.getName()))
				{
					SocketController.sharedInstance().myTeam = Team.Red;
				}
				else if(team.equals(Team.Green.getName()))
				{
					SocketController.sharedInstance().myTeam = Team.Green;
				}
				else if(team.equals(Team.Yellow.getName()))
				{
					SocketController.sharedInstance().myTeam = Team.Yellow;
				}
				
				LudoController.sharedInstance.waitingGame();	
			}
			/*
			else
			{
				JOptionPane.showMessageDialog(null,
						"Por favor tente novamente!",
						"Problemas com autenticação.", JOptionPane.INFORMATION_MESSAGE);
			}*/
		}
	}

}
