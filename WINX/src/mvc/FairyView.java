package mvc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FairyView extends Thread{
	private ArrayList<Fairy> fairies = new ArrayList<Fairy>();
	
	/*
	 * Images for the birds drawing
	 */
	private Image fairy;
	private ImageIcon stream;
	private static final String path ="/assets/";
	
	public FairyView () {
		//Generate the first fairies
		for (int i=0; i<3; i++) {
			fairies.add(new Fairy());
		}
		
		/*
		 * Fairy generation
		 */
		(new Thread () {
			@Override
			public void run () {
				
				while (true) {
					fairies.add(new Fairy());
					try {
						// Generate a new fairy each (between 3 and 6) seconds
						int delay = (int) ((Math.random() * ((6000 - 3000) + 1)) + 3000) ;
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	void draw (Graphics g) throws IOException {
		
		
		// For each fairy
		for (int i=0; i<fairies.size(); i++) {
			
			this.stream = new ImageIcon(getClass().getResource(path+fairies.get(i).getCharacter() +"-"+ fairies.get(i).getFrameState()+".png"));
			this.fairy = this.stream.getImage();
			
			// Draw the fairy
			g.drawImage(fairy, fairies.get(i).getAbscissa(), 
					fairies.get(i).getOrdinate(), null);
		}
	}
}
