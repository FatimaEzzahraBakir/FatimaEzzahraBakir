package advanced;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import fr.ubordeaux.simpleUI.Application;
import fr.ubordeaux.simpleUI.ApplicationRunnable;
import fr.ubordeaux.simpleUI.Arena;
import fr.ubordeaux.simpleUI.TimerRunnable;
import fr.ubordeaux.simpleUI.TimerTask;

public class Run implements ApplicationRunnable<Item> {

	private int width;
	private int height;

	public Run(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void run(final Arena<Item> arg0, Collection<? extends Item> arg1) {//Arena<I> arenaModel, Collection<I> itemCollection
		MouseListener mouseHandler = new MouseListener();

		/*
		 * We build the graphical interface by adding the graphical component
		 * corresponding to the Arena - by calling createComponent - to a JFrame.
		 */
		final JFrame frame = new JFrame("Galcon");

		/*
		 * This is our KeyHandler that will be called by the Arena in case of key events
		 */
		KeyListener keyListener = new KeyListener(frame);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(arg0.createComponent(this.width, this.height, mouseHandler, keyListener));
		frame.pack();
		frame.setVisible(true);

		/*
		 * We initially draw the component
		 */
		arg0.refresh();

		/*
		 * We ask the Application to call the following run function every seconds. This
		 * method just refresh the component.
		 */
		Application.timer(20, new TimerRunnable() {

			public void run(TimerTask timerTask) {
				arg0.refresh();
				for (Item item : arg1) {
					if(item != null){
						item.move();						
					}
				}					
			}
		});
		
		Application.timer(5*1000, new TimerRunnable() {

			public void run(TimerTask timerTask) {
				for (Planet myPlanet : Galcon.getPlanets()){
					myPlanet.produceShips();
				}
			}
		});
		
		Application.timer(5*1000, new TimerRunnable() {

			public void run(TimerTask timerTask) {
				Galcon.startGameAI(0);
				//Game.startGameAI(1);
			}
		});
	}

}
