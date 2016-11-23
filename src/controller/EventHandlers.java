package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import model.*;
import socket.SocketController;

public class EventHandlers {


	public static MouseListener getMouseEvent()
	{
		MouseListener l = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
				Team currentTeam = LudoController.sharedInstance.getCurrentTeam();
				Team myTeam = SocketController.sharedInstance().myTeam;
				
				if(myTeam != currentTeam)
				{
					JOptionPane.showMessageDialog(null, "Não é a sua vez!");
					return;
				}
				
				PinModel p = LudoController.sharedInstance.getPinOnPosition(e.getX(), e.getY(), myTeam);
				
				if(p != null)
				{
					LudoController.sharedInstance.movePinToSquare(p);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
		
		return l;
	}

}
