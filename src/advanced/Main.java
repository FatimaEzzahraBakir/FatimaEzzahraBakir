package advanced;

import fr.ubordeaux.simpleUI.*;

public class Main {
	public static void main(String[] args) {
		
		Galcon galcon = new Galcon(2, 10, 3);
		galcon.init();
		//galcon.startGameAI(0);
		//galcon.startGameAI(1);
		
		Manager manager = new Manager();
		Run r = new Run(1000,800);

		
		
		Application.run(Galcon.getItemList(), manager, r);
	}
}
