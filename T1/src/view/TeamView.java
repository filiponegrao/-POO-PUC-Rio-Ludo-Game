package view;
import model.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/*Classe que deve desenhar os times (suas peças)
 >obter caminhos a serem percorridos por cada time
 >desenhar peças e as movimentar no tabuleiro
 >atualizar informações no modelo sobre status dos times (Classe TeamModel)
 >Sei lá :)
 */
public class TeamView extends JPanel
{
	private TeamModel redTeam;
	private Square[] redRoute;
	private TeamModel greenTeam;
	private Square[] greenRoute;
	private TeamModel yellowTeam;
	private Square[] yellowRoute;
	private TeamModel blueTeam;
	private Square[] blueRoute;
	
	private Graphics2D g2d;
	private Dimension mainDimension;	
	private Dimension houseDimension;
	private Dimension squareDimension;

	private LudoTableModel superLudo = new LudoTableModel();
	
	public TeamView(Dimension d)
	{
		this.mainDimension = d;
		this.setSize(d);
		
		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);		
		
		//Informacoes do modelo
		this.redTeam = new TeamModel(Team.Red);
		this.redRoute = this.superLudo.getRedPath();
	}
	
	public void paintComponent(Graphics g)
	{
		//Inicializa as variáveis necessárias
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		
		//testando pintar o caminho das peças vermelhas pra ver se tá ok
		for (int i = 0; i < this.redRoute.length; i++) 
		{
			int x =  this.redRoute[i].xPosition() * squareDimension.width;
			int y =  this.redRoute[i].yPosition() * squareDimension.height;
			Rectangle2D red = new Rectangle2D.Double(x,y,squareDimension.width, squareDimension.height);

			this.g2d.fill(red);
			this.g2d.setPaint(MyColors.myDarkRed);
			this.g2d.fill(red);			
		}
	}
	
	public void moveTo()
	{
		
	}
}
