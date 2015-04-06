package View.Frames;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BL.Controller.Controller;
import View.Components.ULoginBackgroundComponent;
import View.Components.ULoginCreateMapComponent;
import View.Components.ULoginCreditsComponent;
import View.Components.ULoginHighscoreComponent;
import View.Components.ULoginLoadGameComponent;
import View.Components.ULoginNewGameComponent;

/**
 * class containing the login window 
 * 
 * @author YN
 */
public class ULoginFrame extends JFrame {

	private JPanel pnl = new JPanel(null);
	private ULoginBackgroundComponent menuBackgroundComponent = new ULoginBackgroundComponent();
	private ULoginLoadGameComponent menuLoadGameComponent = new ULoginLoadGameComponent();
	private ULoginNewGameComponent menuNewGameComponent = new ULoginNewGameComponent();
	private ULoginHighscoreComponent menuHighscoreComponent = new ULoginHighscoreComponent();
	private ULoginCreateMapComponent menuCreateMapComponent = new ULoginCreateMapComponent();
	private ULoginCreditsComponent menuCreditsComponent = new ULoginCreditsComponent();
	private int width = 1280;
	private int height = 720;

	/**
	 * constructor for login frame
	 */
	public ULoginFrame() {
		super("Login Frame");

		pnl.add(menuBackgroundComponent);
		pnl.add(menuLoadGameComponent);
		pnl.add(menuNewGameComponent);
		pnl.add(menuCreateMapComponent);
		pnl.add(menuHighscoreComponent);
		pnl.add(menuCreditsComponent);

		menuBackgroundComponent.setLocation(0, 0);
		menuNewGameComponent.setLocation(200, 150);
		menuLoadGameComponent.setLocation(200, 250);
		menuHighscoreComponent.setLocation(200, 350);
		menuCreateMapComponent.setLocation(200, 450);
		menuCreditsComponent.setLocation(0, 625);

		pnl.setComponentZOrder(menuLoadGameComponent, 0);
		pnl.setComponentZOrder(menuNewGameComponent, 0);
		pnl.setComponentZOrder(menuHighscoreComponent, 0);
		pnl.setComponentZOrder(menuCreateMapComponent, 0);
		pnl.setComponentZOrder(menuCreditsComponent, 0);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * handles component to load existing game
		 */
		menuLoadGameComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuLoadGameComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuLoadGameComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().loadGame();
			}
		});

		/**
		 * handles component to start a new game
		 */
		menuNewGameComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuNewGameComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuNewGameComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().newGame();
			}
		});

		/**
		 * handles component to open high scores 
		 */
		menuHighscoreComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuHighscoreComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuHighscoreComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeLoginToHighscoresFrame();
			}
		});

		/**
		 * handles crate map component
		 */
		menuCreateMapComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuCreateMapComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuCreateMapComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeLoginToCreateMapFrame();
			}
		});

		/**
		 * handles component to display game developers
		 */
		menuCreditsComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuCreditsComponent.changeSize();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuCreditsComponent.changeSize();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				showCredits();
			}
		});

		pnl.setSize(width, height);
		this.add(pnl);
		// Note: size must be slight bigger for the previous component
		this.setPreferredSize(new Dimension(width, height + 30));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * closes the window
	 */
	public void closeLoginFrame() {
		this.dispose();
	}

	/**
	 * shows game developers
	 */
	private void showCredits() {
		JOptionPane
				.showMessageDialog(
						this,
						"MASTERMINDS:\nYordan Neshev\nDang Khoa Do\nAndrei Chubarev\nSteve Voyer",
						null, JOptionPane.PLAIN_MESSAGE);
	}
}