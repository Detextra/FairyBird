package mvc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Affichage extends JPanel{
	
		
	/* Default windows size 
	 * */
	public static final int width = 1000;
	public static final int heigth = 600;
	
	/*
	 * MVC useful component
	 */
	private Etat model;
	private FairyView birds = new FairyView();
	
	/*
	 * Background image path
	 */
	private Image background;
	private ImageIcon stream;
	private static final String path ="/assets/background.png";
	
	/*
	 * Main character (oval) image path
	 */
	private ImageIcon stream2 = new ImageIcon(getClass().getResource("/assets/hero.png"));
	private Image hero = stream2.getImage();
	
	/* Default oval dimension
	*/
		
	public static final int ovalWidth = 150;
	public static final int ovalHeigth = 231;
	
	/*
	 * Construct the windows with default dimension
	 */
	public Affichage (Etat e) {
		setPreferredSize(new Dimension(width, heigth));
		model = e;
		
		/*
		 * Draw image background
		 */
		this.stream = new ImageIcon(getClass().getResource(path));
		this.background = this.stream.getImage();
		
		/*
		 * Refresh the output
		 */
		(new Thread () {
			@Override
			public void run () {
				while (true) {
					revalidate();
					repaint();
					//
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/*
	 * Draw the oval.
	 * getOvalY() is a function from the Etat class.
	 * it permits to get the new oval position after user click
	 */
	public void paint(Graphics g) {		
		super.paint(g);
		g.setColor(Color.yellow);
		g.drawImage(background, 0, 0, null);
		try {
			birds.draw(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		drawBrokenLine(g);
		drawOval(g);
		drawScore(g);
	}
	
	/*
	 * Draw the oval
	 */
	public void drawOval(Graphics g) {

		g.drawImage(hero, (int) (width*0.1), model.getOvalY(), null);

	}
	
	/*
	 * Draw the brokenLine
	 */
	public void drawBrokenLine (Graphics g) {
		ArrayList<Point> visiblePoints = model.getParcours().getVisiblePoints();
		Graphics2D g2 = (Graphics2D) g;

		for (int i=0; i<visiblePoints.size()-1; i++) {
			g2.setStroke(new BasicStroke(20));
			g.setColor(Color.PINK);

			g.drawLine(visiblePoints.get(i).x, visiblePoints.get(i).y,
					visiblePoints.get(i+1).x, visiblePoints.get(i+1).y);
		}
	}
	
	/*
	 * Draw the score
	 */
	public void drawScore (Graphics g) {
		g.drawString("Collision : "+(int)model.getNbrCollisions(), width/10, heigth/6);
	}
	
	/*
	 * Getter and setter
	 */
	
	public FairyView getBirds() {
		return birds;
	}

	public void setBirds(FairyView birds) {
		this.birds = birds;
	}
}
