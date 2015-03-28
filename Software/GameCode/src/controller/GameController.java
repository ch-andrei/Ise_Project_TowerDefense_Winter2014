package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import presentation.MapObserver;
import BusinessLogic.mapComponents.Map;

/**
 * implements KeyListener to get user Key input. provides control for a specific panel.
 * can only control one Panel.
 * @author AC
 *
 */
public class GameController implements KeyListener{

	protected final static int squareSize = 36;

	/**
	 * singleton static GameController
	 */
	private static GameController controller;

	protected MapObserver mapPanel;

	Random rand = new Random();

	/**
	 * private GameController instance constructor built as a singleton
	 * @param panel
	 */
	private GameController(MapObserver panel)
	{
		controller = this;
		
		this.mapPanel = panel;
		
		panel.addKeyListener(this);
	}

	/**
	 * singleton getter for instance of GameController
	 * @param panel
	 * @return
	 */
	public static GameController getGameController(MapObserver panel){
		if (controller == null){
			controller = new GameController(panel);
		}
		return controller;
	}

	/**
	 * handles user key input.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch( key ) { 
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER:
			mapPanel.getGame().getMap().startAction();
			break;
		case KeyEvent.VK_SPACE:
			mapPanel.getGame().getMap().applyRandomChange();
			break;
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT :
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
