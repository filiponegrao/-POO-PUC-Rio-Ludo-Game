package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.LudoController;

public class MenuPanel extends JMenuBar {
	
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
        
        this.menu.add(novoJogo);
        this.menu.add(salvarJogo);
        
        this.add(this.menu);
        
	}
}
