package mvc;

import java.awt.Point;
import java.util.ArrayList;

public class Parcours extends Thread{
	/*
	 * Store all the points to draw the broken line
	 */
	private ArrayList<Point> brokenLine = new ArrayList<Point>();
	
	/*
	 * Ordinate boundaries
	 */
	public static int lowOBoundary = (int) (Affichage.ovalHeigth * 0.5);
	public static int highOBoundary = (int) (Affichage.heigth - Affichage.ovalHeigth*0.5);
	
	/*
	 * Abscissa boundaries ratio
	 */
	public static double lowABoundaryRatio = 0.23;
	public static double highABoundaryRatio = 0.32;
	
	/*
	 * First point coordinates
	 */
	
	public static int firstX = (int) ( (Affichage.width*0.1) + Affichage.ovalWidth*0.5);
	public static int firstY = (int) ( (Affichage.heigth-(Affichage.ovalHeigth*1.2)));
	
	/*
	 * position : actual advancement 
	 * scroll speed, in px/ ?
	 */
	
	private double position=0;
	public static double scrollSpeed= 3.1;
	
	/*
	 * False if the user never click (to start the game)
	 */
	
	public Parcours () {
		/*
		 * create the first few points for the starting screen
		 */
		brokenLine.add(new Point(firstX, firstY));
		while(getLastPointAbscissa() < Affichage.width) {
			addPoint();
		}
		
		(new Thread () {
			@Override
			public void run () {
				
				while (true) {
					
					// Add a Point to continue the line, along the advancement
					if (getLastPointAbscissa()< (Affichage.width)*(1+lowABoundaryRatio)){
						addPoint();
					}
					
					// Delete the points which are not useful to draw the broken line
					if (getBrokenLine().get(1).x < 0){
						getBrokenLine().set(0, null);
						getBrokenLine().remove(0);
						System.out.println("Point deleted");
					}
					/*
					 * Simulate moving to the right by decreasing brokenLine points abscissa
					 */
					if (Control.hasStarted) decreasePoints();
					
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/*
	 * Decreasing abscissa of all visible points 
	 * plus the first one outside the window on the left and right
	 */
	public void decreasePoints () {
		ArrayList<Point> visiblePoints = getVisiblePoints();
		for (int i=0; i<visiblePoints.size();i++ ) {
			visiblePoints.get(i).x -= scrollSpeed;
			position+= scrollSpeed;
			scrollSpeed *= 1.0002;
		}
	}
	
	/*
	 * Add a point to continue the brokenLine
	 */
	public void addPoint () {
		/*
		 * random coordinates
		 */
	    double abscissaRatio = (Math.random() * highABoundaryRatio + lowABoundaryRatio)  ;
	    int ordinate = (int) (Math.random() * (highOBoundary - lowOBoundary + 1) + lowOBoundary)  ;
	    
	    /*
	     * calculate new coordinates in function of the last point
	     */
		brokenLine.add( new Point( (int) (getLastPointAbscissa()+(Affichage.width*abscissaRatio) ) , ordinate) );
	}
	
	/*
	 * Getter : Ordinate of the last point of 'brokenLine' ArrayList
	 */
	public int getLastPointAbscissa () {
		return brokenLine.get(brokenLine.size()-1).x;
	}
	
	public ArrayList<Point> getBrokenLine () {
		return brokenLine;
	}
	
	/*
	 * Getter : all visible points
	 * plus the first one outside the window on the left and right
	 */
	public ArrayList<Point> getVisiblePoints () {
		ArrayList<Point> visiblePoints = new ArrayList<Point>();
		
		
		for (int i=0; i<brokenLine.size(); i++) {
			
			// If the abscissa is in the window (+ plus the first one outside the window on the left and right)
			//if (brokenLine.get(i).x >= position-(Affichage.width*(1+highABoundaryRatio)) && brokenLine.get(i).x <= (Affichage.width+position)*(1+highABoundaryRatio)) {
				visiblePoints.add(brokenLine.get(i));
			//}
		}
		return visiblePoints;
	}

	/*
	 * Getter and setter for position
	 */
	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

}
