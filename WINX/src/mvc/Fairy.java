package mvc;

public class Fairy extends Thread {
	private long delay;
	private int frameState = 1;
	private int abscissa;
	private int ordinate;
	private int character;
	
	public Fairy () {
		/*
		 * Random coordinates and delay (sensation of speed) for each fairies.
		 */
		this.abscissa = (int) ((int)(Math.random() * ((Affichage.width*0.9 - 0) + 1)) + 0) ;
		this.ordinate = (int) ((Math.random() * ((Affichage.heigth*0.8-200 - 0) + 1)) + 0) ;
		this.delay = (long)(Math.random() * ((1000/48 - 1000/12) + 1)) + 1000/12 ;
		
		/*
		 * Type of fairy (which character)
		 */
		this.character = (int)(Math.random() * 6) + 1 ;
		
		(new Thread () {
			@Override
			public void run() {
				while (true) {
					
					// Changement of state to create an impression of movement
					frameState++;
					if(frameState > 2) {
						frameState = 1;
					}
					
					// Decreasing the abscissa for an impression of movement
					abscissa -= Parcours.scrollSpeed;
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	/*
	 * Getters and setters
	 */

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public int getFrameState() {
		return this.frameState;
	}

	public void setFrameState(int state) {
		this.frameState = state;
	}

	public int getAbscissa() {
		return abscissa;
	}

	public void setAbscissa(int abscissa) {
		this.abscissa = abscissa;
	}

	public int getOrdinate() {
		return ordinate;
	}

	public void setOrdinate(int ordinate) {
		this.ordinate = ordinate;
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}
	
}
