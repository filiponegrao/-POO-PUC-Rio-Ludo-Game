package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.*;

public class EventHandlers {


	public static MouseListener getMouseEvent()
	{
		MouseListener l = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				PinModel p = LudoController.sharedInstance.getPinOnPosition(e.getX(), e.getY());
				
				if(p != null)
				{
					LudoController.sharedInstance.movePinToSquare(p);
					System.out.println("passa no touch do pino");
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
