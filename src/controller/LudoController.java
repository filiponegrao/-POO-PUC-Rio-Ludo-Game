package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.*;
import view.*;

public class LudoController {

	public static LudoController sharedInstance = new LudoController();
	
	/*******************************/
	/******* MODEL COMPONENTS*******/
	/*******************************/
	
	private LudoTableModel model = new LudoTableModel();
	
	private Square[] squares;
	
	private PinModel[] redPins ;
	
	private PinModel[] greenPins;
	
	private PinModel[] bluePins;
	
	private PinModel[] yellowPins;
	
	private DiceModel dice;
	
	private int diceValue = 1;

	
	/*******************************/
	/****** VISUAL COMPONENTS*******/
	/*******************************/

	private MainWindow mainWindow;
	
	private LudoController()
	{
		this.squares = this.model.getModel();
		this.redPins = this.model.getRedPins();
		this.bluePins = this.model.getBluePins();
		this.greenPins = this.model.getGreenPins();
		this.yellowPins = this.model.getYellowPins();
		
		this.dice = new DiceModel();
	}
	
	public void loadScreen()
	{
		this.mainWindow = new MainWindow("LudoGame", squares);
		
		Graphics2D g = this.mainWindow.gamePanel().ludoTable().graphics();
		
//		JPanel ludopanel = this.mainWindow.gamePanel().ludoTable();
		
		PinView.printPin(g, 200, 200, 100, 100);
	}
	
	public void setDiceValue(int value)
	{
		this.diceValue = value;
	}
	
	public int getDiceValue()
	{
		return this.diceValue;
	}
	
}
