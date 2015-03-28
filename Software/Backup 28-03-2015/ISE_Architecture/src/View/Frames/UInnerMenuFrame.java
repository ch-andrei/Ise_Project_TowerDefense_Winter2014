package View.Frames;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BL.Controller.Controller;
import View.Components.UInnerBackButtonComponent;
import View.Components.UInnerBackgroundComponent;
import View.Components.UInnerHighscoresComponent;
import View.Components.UInnerLevelComponent;
import View.Components.UInnerSaveGameComponent;
import View.Components.UInnerShopComponent;
import View.Components.UInnerStatsComponent;

/**
 * Classe qui contient la fenetre du monde, qui est comme le deuxieme menu,
 * celui ou l'on choisi le niveau
 * 
 * @author Eric Kavalec
 */
public class UInnerMenuFrame extends JFrame {

	private JPanel pnl = new JPanel(null);
	private UInnerBackgroundComponent mondeBackgroundComponent = new UInnerBackgroundComponent();
	private UInnerShopComponent mondeShopComponent = new UInnerShopComponent();
	private UInnerHighscoresComponent mondeHighscoreComponent = new UInnerHighscoresComponent();
	private UInnerBackButtonComponent mondeRetourComponent = new UInnerBackButtonComponent();
	private UInnerSaveGameComponent mondeSaveGameComponent = new UInnerSaveGameComponent();
	private UInnerStatsComponent mondeStatsComponent = new UInnerStatsComponent();
	private int width = 1280;
	private int height = 720;

	public UInnerMenuFrame() {
		super("Monde");

		mondeStatsComponent.UpdateStats();

		pnl.add(mondeBackgroundComponent);

		pnl.add(mondeShopComponent);
		pnl.add(mondeHighscoreComponent);
		pnl.add(mondeRetourComponent);
		pnl.add(mondeSaveGameComponent);

		pnl.add(mondeStatsComponent);

		mondeBackgroundComponent.setLocation(0, 0);

		mondeShopComponent.setLocation(375, 580);
		mondeHighscoreComponent.setLocation(575, 580);
		mondeRetourComponent.setLocation(775, 580);
		mondeSaveGameComponent.setLocation(975, 580);

		mondeStatsComponent.setLocation(80, 70);

		pnl.setComponentZOrder(mondeShopComponent, 0);
		pnl.setComponentZOrder(mondeHighscoreComponent, 0);
		pnl.setComponentZOrder(mondeRetourComponent, 0);
		pnl.setComponentZOrder(mondeSaveGameComponent, 0);
		pnl.setComponentZOrder(mondeStatsComponent, 0);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * gestion du composant pour aller vers le magasin
		 */
		mondeShopComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				mondeShopComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mondeShopComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeInnerToShopFrame();
			}
		});

		/**
		 * gestion du composant pour aller vers la fenetre des meilleurs scores
		 */
		mondeHighscoreComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				mondeHighscoreComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mondeHighscoreComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeInnerToHighscoresFrame();
			}
		});

		/**
		 * gestion du composant pour retourner vers le menu principal
		 */
		mondeRetourComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				mondeRetourComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mondeRetourComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().confirmBackToLoginFrame();
			}
		});

		/**
		 * gestion du composant pour sauvegarder la partie
		 */
		mondeSaveGameComponent.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				mondeSaveGameComponent.ChangerTaille();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mondeSaveGameComponent.ChangerTaille();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().confirmSave();
			}
		});

		/**
		 * rajouter les composants pour acceder aux niveaux, la couleure de
		 * ceux-ci depend de si ils sont debloques ou non. on ne peut pas
		 * acceder a un niveau qui n'est pas debloque.
		 */
		for (int i = 0; i < 6; i++) {
			if (Controller.getInstance().player.getMaxLevel() < (i + 1)) { // pas
																			// debloque
				UInnerLevelComponent mlc = new UInnerLevelComponent(i + 1,
						false);
				pnl.add(mlc);
				pnl.setComponentZOrder(mlc, 0);
			} else {
				UInnerLevelComponent mlc = new UInnerLevelComponent(i + 1, true);
				pnl.add(mlc);
				pnl.setComponentZOrder(mlc, 0);
				mlc.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						((UInnerLevelComponent) e.getSource()).ChangerTaille();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						((UInnerLevelComponent) e.getSource()).ChangerTaille();
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						Controller.getInstance().changeInnerToGameFrame(
								((UInnerLevelComponent) e.getSource()).level);
					}
				});
			}
		}

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
	public void FermerMondeFenetre() {
		this.dispose();
	}
}