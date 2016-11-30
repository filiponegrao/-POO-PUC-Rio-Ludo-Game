package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LudoController;
import model.Team;
import socket.SocketController;

public class WaitingGameWindow extends JFrame implements Observer
{
	private Toolkit tk;
	
	private Dimension dimension;
	private JPanel waitingPanel;
	private JLabel label;
	private String name;
	
	public WaitingGameWindow() 
	{
		this.tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.dimension = new Dimension(screenWidth/2, screenHeight/2);
		
		this.setSize(this.dimension);
		
		this.waitingPanel = new JPanel();
		this.waitingPanel.setLayout(null);
		this.waitingPanel.setSize(this.dimension);
		this.setBackgroundByTeam();
		
		this.name = SocketController.sharedInstance().myNickname;
		
		this.label = new JLabel("Olá " + this.name + ", aguarde outros jogadores");
		this.label.setLocation(0, this.dimension.height/5);
		this.label.setSize(this.dimension.width, 40);
		this.label.setForeground(Color.WHITE);
		this.label.setOpaque(false);
		this.label.setFont(new Font("Helvetica", 0, 16));
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.waitingPanel.add(this.label);
		
		this.getContentPane().add(this.waitingPanel);
		
		/******************************************/
		/********* POSICAO DA TELA ***************/
		/******************************************/
		
		int xpos = screenWidth/4;
		int ypos = screenHeight/4;
		
		this.setLocation(xpos, ypos);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		SocketController.sharedInstance().addObserver(this);

	}
	
	public void setBackgroundByTeam()
	{
		Team myTeam = SocketController.sharedInstance().myTeam;
		
		if(myTeam == Team.Blue)
		{
			this.waitingPanel.setBackground(MyColors.myBlue);
		}
		else if(myTeam == Team.Red)
		{
			this.waitingPanel.setBackground(MyColors.myRed);
		}
		else if(myTeam == Team.Green)
		{
			this.waitingPanel.setBackground(MyColors.myGreen);
		}
		else //amarelo
		{
			this.waitingPanel.setBackground(MyColors.myYellow);
		}
				
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		HashMap<String, Object> map = (HashMap<String, Object>) arg;
		
		//vai receber do servidor mensagens com os nomes dos oponentes
		if(map.containsKey("nickname"))
		{
			//checa se já tem os 4 jogadores
			Boolean ready = SocketController.sharedInstance().isReady();
			
			if(ready)
			{
				LudoController.sharedInstance.loadScreen();
				System.out.println("abrindo ludo");
			}
		}
		else if(map.containsKey("timeout"))
		{
			JOptionPane.showMessageDialog(null,
					"Este jogo será encerrado por falta de jogadores",
					"Tempo expirado", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			
		}
	}
	
}
