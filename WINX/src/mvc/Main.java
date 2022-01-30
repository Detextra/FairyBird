package mvc;

import java.util.EventListener;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		/*
		 * MVC components
		 */
		Etat etat = new Etat();
		Affichage print = new Affichage(etat);
		Fly fly = new Fly(etat);
		Control ctrl = new Control(print, etat, fly);
		
		JFrame window = new JFrame("Ceci n'est pas un exercice");
		window.add(print);
		window.addMouseListener(ctrl);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
	}
}
