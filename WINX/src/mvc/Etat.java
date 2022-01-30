package mvc;

import java.awt.Point;
import java.util.ArrayList;

public class Etat {
	
	/*
	 * To manage the audio
	 */
	private Audio audio;
	/*
	 * Class that manage the brokenLine
	 */
	private Parcours parcours = new Parcours();
	
	/*
	 * jumpHeight : height of the jump at each user event (click)
	 * ovalY : current oval ordinate
	 * gravity : increase the position to simulate gravity
	 */
	private int jumpHeight;
	private int ovalY;
	
	/*
	 * The collisions will be a kind of score
	 */
	private int nbrCollisions;
	
	public Etat() {
		this.audio = new Audio();
		this.ovalY = (int) (Affichage.heigth * 0.4);
		this.jumpHeight = (int) (Affichage.heigth * 0.083);
		this.nbrCollisions=0;
		audio.loopWINXO();
	}
	
	/*
	 * Decrease the oval position 
	 */
	public void jump () {
		if (ovalY-jumpHeight >= 0) {
			ovalY-=jumpHeight;
		}
		else {
			ovalY=1;
		}
	}
	
	/*
	 * Increase the oval ordinate 
	 */
	public void moveDown () {
		if (ovalY<Affichage.heigth-Affichage.ovalHeigth) {
			setOvalY(getOvalY()+(Affichage.heigth/150));
		}
	}
	
	/*
	 * Test if the oval crosses the brokenLine
	 */
	boolean isLose () {
		ArrayList<Point> brokenLine = parcours.getBrokenLine();
		
		// Calcul the distance between the 2 first visibles points
		double distanceBetweenTwoPoints = Math.sqrt( ( (brokenLine.get(2).y - brokenLine.get(1).y)*(brokenLine.get(2).y - brokenLine.get(1).y) ) /
				( (double) (brokenLine.get(2).x - brokenLine.get(1).y) * (brokenLine.get(2).x - brokenLine.get(1).y) ) );

		// Calcul the ordinate of the line at oval abscissa
		int lineOrdinate = (int) (brokenLine.get(1).y + distanceBetweenTwoPoints * (brokenLine.get(2).y - brokenLine.get(1).y));
		
		// If the oval cross the line by its top ( ovalY > lineOrdinate) or its bottom (ovalY + ovalHeigth < lineOrdinate)
		return (ovalY > lineOrdinate || ovalY + Affichage.ovalHeigth < lineOrdinate);
			
	}

	/*
	 * Getter and setter
	 */
	
	public int getOvalY() {
		return ovalY;
	}
	
	public void setOvalY(int y) {
		this.ovalY = y;
	}
	
	public Parcours getParcours () {
		return parcours;
	}

	public int getNbrCollisions() {
		return nbrCollisions;
	}

	public void setNbrCollisions(int nbrCollisions) {
		this.nbrCollisions = nbrCollisions;
	}
	
}
