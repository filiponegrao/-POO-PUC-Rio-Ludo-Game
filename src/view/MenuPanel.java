package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.LudoController;

public class MenuPanel extends JMenuBar implements ActionListener
{
	private JMenu menu;

	public MenuPanel()
	{
		//Define os parametros
		//		this.jframe = jframe;

		//Define o titulo
		this.menu = new JMenu("Menu");	

		//Cria opcoes
		JMenuItem novoJogo = new JMenuItem("Novo Jogo");
		JMenuItem salvarJogo = new JMenuItem("Salvar Jogo");
		JMenuItem carregarJogo = new JMenuItem("Carregar Jogo");
//		JMenuItem voltarEtapa = new JMenuItem("Voltar uma etapa");

		salvarJogo.addActionListener(this);
		carregarJogo.addActionListener(this);
		novoJogo.addActionListener(this);

		this.menu.add(novoJogo);
		this.menu.add(salvarJogo);
		this.menu.add(carregarJogo);
//		this.menu.add(voltarEtapa);

		this.add(this.menu);

	}


	public void actionPerformed(ActionEvent e) 
	{	
		String command = e.getActionCommand();
		System.out.println(command);

		if(command.equals("Salvar Jogo"))
		{
			try 
			{
				LudoController.sharedInstance.saveCurrentGame();
				JOptionPane.showMessageDialog(null,
						"Seu jogo foi salvo! Selecione a opção Carregar Jogo para continuar esta partida mais tarde",
						"Partida Salva", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(command.equals("Carregar Jogo"))
		{
			try 
			{
				LudoController.sharedInstance.loadGame();

				JOptionPane.showMessageDialog(null,
						"Seu jogo foi carregado! Agora você pode continuar a partida anterior.",
						"Carregar Jogo", JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (IOException e2) 
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else if(command.equals("Novo Jogo"))
		{
			LudoController.sharedInstance.resetGame();
			JOptionPane.showMessageDialog(null,
					"Iniciando nova partida.",
					"Novo Jogo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
