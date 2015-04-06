package View.Frames;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BL.Controller.Controller;
import BL.Model.Player;
import View.Components.UHighscoresBackButtonComponent;
import View.Components.UHighscoresBackgroundComponent;
import View.Components.UHighscoresTextComponent;

/**
 * class that contains the windows for high scores
 * 
 * @author YN
 */
public class UHighscoresFrame extends JFrame {
	private final static int HEIGHT = 720;
	private final static int WIDTH = 1280;
	private JPanel pnl = new JPanel(null);
	private UHighscoresBackgroundComponent highscoresBackgroundComponent = new UHighscoresBackgroundComponent();
	private UHighscoresBackButtonComponent highscoresRetourComponent = new UHighscoresBackButtonComponent();
	private UHighscoresTextComponent highscoresTextComponent;

	public UHighscoresFrame() {
		super("Highscores");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * handles return button for the exit
		 */
		highscoresRetourComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				highscoresRetourComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				highscoresRetourComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().closeHighscoresFrame();
			}
		});

		ArrayList<Player> highscores = Controller.getInstance()
				.loadHighscores();

		highscoresTextComponent = new UHighscoresTextComponent(highscores);

		pnl.add(highscoresBackgroundComponent);
		pnl.add(highscoresRetourComponent);
		pnl.add(highscoresTextComponent);

		highscoresBackgroundComponent.setLocation(0, 0);
		highscoresRetourComponent.setLocation(1030, 565);

		pnl.setComponentZOrder(highscoresRetourComponent, 0);
		pnl.setComponentZOrder(highscoresTextComponent, 0);

		pnl.setSize(WIDTH, HEIGHT);
		this.add(pnl);
		// Note: size of the windows must be slightly higher than the previous window
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT + 30));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * close window
	 */
	public void closeHighScoresWindow() {
		this.dispose();
	}
}