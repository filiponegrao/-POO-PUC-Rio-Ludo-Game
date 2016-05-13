package view;
import model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/*Classe que deve desenhar os times (suas peças)
 >obter caminhos a serem percorridos por cada time
 >desenhar peças e as movimentar no tabuleiro
 >atualizar informações no modelo sobre status dos times (Classe TeamModel)
 >Sei lá :)
 */
public class TeamView 
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
		this.mainDimension = new Dimension(d.width, d.height);	

		//Propriedades do itens visuais		
		this.houseDimension = new Dimension((mainDimension.width/15 * 6), (mainDimension.width/15 * 6));
		this.squareDimension = new Dimension(mainDimension.width/15, mainDimension.height/15);		

		//Informacoes do modelo
		this.redTeam = new TeamModel(Team.Red);
		this.redRoute = this.superLudo.getRedPath();
		
		this.greenTeam = new TeamModel(Team.Green);
		this.greenRoute = this.superLudo.getGreenPath();
		
		this.yellowTeam = new TeamModel(Team.Yellow);
		this.yellowRoute = this.superLudo.getYellowPath();
		
		this.blueTeam = new TeamModel(Team.Blue);
		this.blueRoute = this.superLudo.getBluePath();
	}

	public void createPieces(Team team, int amount, Graphics2D g)
	{
		this.g2d = g;

		if(team == Team.Red)
		{
			int x = this.houseDimension.width/3;
			int y = this.houseDimension.height/3;
			
			for (int i = 0; i < amount/2; i++)
			{
				Ellipse2D leftPiece = new Ellipse2D.Double(x, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkRed);
				this.g2d.fill(leftPiece);
				Ellipse2D rightPiece = new Ellipse2D.Double(x + squareDimension.width * 1.5, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkRed);
				this.g2d.fill(rightPiece);
				
				y += squareDimension.height * 1.5;
			}
		}	
		else if(team == Team.Green)
		{
			int x = this.mainDimension.width - this.houseDimension.width/3 * 2;
			int y = this.houseDimension.height/3;
			
			for (int i = 0; i < amount/2; i++)
			{
				Ellipse2D leftPiece = new Ellipse2D.Double(x, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkGreen);
				this.g2d.fill(leftPiece);
				Ellipse2D rightPiece = new Ellipse2D.Double(x + squareDimension.width * 1.5, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkGreen);
				this.g2d.fill(rightPiece);
				
				y += squareDimension.height * 1.5;
			}
		}
		else if(team == Team.Blue)
		{
			int x = this.houseDimension.width/3;
			int y = this.mainDimension.height - this.houseDimension.height/3 * 2;
			
			for (int i = 0; i < amount/2; i++)
			{
				Ellipse2D leftPiece = new Ellipse2D.Double(x, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkBlue);
				this.g2d.fill(leftPiece);
				Ellipse2D rightPiece = new Ellipse2D.Double(x + squareDimension.width * 1.5, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkBlue);
				this.g2d.fill(rightPiece);
				
				y += squareDimension.height * 1.5;
			}
		}
		else if(team == Team.Yellow)
		{
			int x = this.mainDimension.width - this.houseDimension.width/3 * 2;
			int y = this.mainDimension.height - this.houseDimension.height/3 * 2;
			
			for (int i = 0; i < amount/2; i++)
			{
				Ellipse2D leftPiece = new Ellipse2D.Double(x, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkYellow);
				this.g2d.fill(leftPiece);
				Ellipse2D rightPiece = new Ellipse2D.Double(x + squareDimension.width * 1.5, y, squareDimension.width/2, squareDimension.height/2);
				this.g2d.setPaint(MyColors.myDarkYellow);
				this.g2d.fill(rightPiece);
				
				y += squareDimension.height * 1.5;
			}
		}
		
	}

	public void updatePieces()
	{
		
	}
	
	public void moveTo()
	{

	}
	public void paintPath()
	{
		//metodo provisorio pra testar se paths estao corretos
		Square[] path = this.blueRoute;
		for(int i=0;i<path.length;i++)
		{
			Double x =  (double) (((double)path[i].xPosition() * (double)squareDimension.width) + 5.0);
			Double y =  (double) (((double)path[i].yPosition() * (double)squareDimension.height) + 5.0);
			Rectangle2D square = new Rectangle2D.Double(x,y,(double)squareDimension.width, (double)squareDimension.height);
			this.g2d.setPaint(Color.CYAN);
			this.g2d.fill(square);
		}
	}
}
