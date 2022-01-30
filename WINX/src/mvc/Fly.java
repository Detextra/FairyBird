package mvc;

public class Fly extends Thread{
	
	/*
	 * Useful MVC components
	 */
	private Etat model;
	
	public Fly(Etat model) {
		super();
		this.model = model;
		
		(new Thread () {
			@Override
			public void run () {
				
				while (true) {
					/*
					 * Simulate a constant gravity
					 */
					if (Control.hasStarted) model.moveDown();
					
					// Calculate the number of collision to print the score
					if (model.isLose()) {
						model.setNbrCollisions(model.getNbrCollisions()+1);
					}
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (model.getOvalY()<0) {
						break;
					}
				}
			}
		}).start();
	}
	
}
