package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Composante graphique du mur
 * 
 * @author Yordan Neshev
 */
public class UGameTileComponent extends JComponent {

	private Image imgScenery = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/wall2.gif"));
	private Image imgPath = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/wall2.gif"));
	private final int larg1 = 150;
	private final int haut1 = 325;
	private int larg = 150;
	private int haut = 325;
	private int posIni;

	/**
	 * Constructeur de la composante graphique du mur
	 * 
	 * @param pos
	 *            position initale du mur d'après la position de l'arrière
	 *            fond
	 */
	public UGameTileComponent(int pos) {
		posIni = pos;
		setSize(larg, haut);
		setLocation(pos, 155 + (haut1 - haut));
	}

	/**
	 * Desinner la composante graphique du mur
	 * 
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgPath, 0, 0, larg, haut, this);
		setLocation(posIni, 155 + (haut1 - haut));
	}

	/**
	 * Bouger le mur d'après sa position par défaut correspondant à
	 * l'arrière fond
	 * 
	 * @param pos
	 *            position du mur indiquée d'après la position et la dimension
	 *            de l'arrière fond
	 */
	public void bouger(int pos) {
		posIni = pos;
		setLocation(posIni, 155 + (haut1 - haut));
	}

	/**
	 * Remettre à défaut les dimensions du mur
	 */
	public void resetSize() {
		larg = larg1;
		haut = haut1;
		setSize(larg, haut);
	}

	/**
	 * Permet de redimensionner le mur (zoom)
	 * 
	 * @param largeur
	 *            portion à enlever de la largeur actuelle du mur
	 * @param hauteur
	 *            portion à enlever de l'hauteur actuelle du mur
	 */
	public void resizeCustom(int largeur, int hauteur) {
		larg = larg + largeur;
		haut = haut + (int) (hauteur * (double) (325 / 150));

		if (larg < 30) {
			larg = 30;
		}
		if (haut < 65) {
			haut = 65;
		}
		if (larg > 150) {
			larg = 150;
		}
		if (haut > 325) {
			haut = 325;
		}
		setSize(larg, haut);
	}
}
