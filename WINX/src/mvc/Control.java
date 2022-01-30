package mvc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Control implements MouseListener{
	/*
	 * MVC usefull components
	 */
	private Affichage view;
	private Etat model;
	private Fly fly;
	
	/*
	 * Start of the game
	 */
	public static boolean hasStarted;
	
	public Control(Affichage a, Etat e, Fly f) {
		view = a;
		model = e;
		fly = f;
		this.hasStarted=false;
	}	
	
	@Override
	/*
	 * change the oval position at each click
	 */
	public void mouseClicked(MouseEvent e) {
		hasStarted = true;
		model.jump();
		view.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
