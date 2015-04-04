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
 * Clase qui contient la fenetre du menu
 * 
 * @author Eric Kavalec
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
		 * gere le composant pour charger une partie
		 */
		menuLoadGameComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuLoadGameComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuLoadGameComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().loadGame();
			}
		});

		/**
		 * gere le composant pour creer une nouvelle partie
		 */
		menuNewGameComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuNewGameComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuNewGameComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().newGame();
			}
		});

		/**
		 * gere le composant pour afficher la fenetre des meilleurs scores
		 */
		menuHighscoreComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuHighscoreComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuHighscoreComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeLoginToHighscoresFrame();
			}
		});

		menuCreateMapComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuCreateMapComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuCreateMapComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeLoginToCreateMapFrame();
			}
		});

		/**
		 * gere le composant pour afficher plus d'informations sur les createurs
		 * du jeu
		 */
		menuCreditsComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				menuCreditsComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuCreditsComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				showCredits();
			}
		});

		pnl.setSize(width, height);
		this.add(pnl);
		// Note: La taille de la fenêtre est différente de celle du panneau,
		// il faut quelle soit un peu plus grande en Y
		this.setPreferredSize(new Dimension(width, height + 30));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * pour fermer la fenetre
	 */
	public void closeLoginFrame() {
		this.dispose();
	}

	/**
	 * affiche les createurs du jeu
	 */
	private void showCredits() {
		JOptionPane
				.showMessageDialog(
						this,
						"Éric Kavalec\nYordan Neshev\nMaxime Boucher\n\nMerci à Sebastien Neron pour l'aide avec certaines images",
						null, JOptionPane.PLAIN_MESSAGE);
	}
}