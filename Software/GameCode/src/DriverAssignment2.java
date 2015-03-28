import javax.swing.JFrame;

import presentation.Frame;
import BusinessLogic.GameTime;
import controller.GameController;

public class DriverAssignment2 {

	public static void main (String[] args){
		GameTime game = new GameTime();
		// generates a game with a random map 
		// map generator makes random element distributions on the map (water, tree, bush, etc.)
		
		JFrame frame = Frame.getFrame(game); 
		// frame is a singleton

		game.getMap().addObserver(((Frame) frame).getMapPanel());
		
		// CONTROLS: are displayed 'in-game'
		GameController controller = GameController.getGameController(((Frame) frame).getMapPanel());
		// controller is a singleton
	}
}
