package View.Components;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Classe permettant de créer un JButton qui a une position verticale et
 * horizontale
 * 
 * @author Yordan Neshev
 */
public class UMapTile extends JButton {

	// Ligne à laquelle se trouve la case
	private int ligne;
	// Colonne à laquelle se trouve la case
	private int colonne;
	// Vrai si la casse a déjà été cliquée (fait partie de la séquence en
	// jeu)
	private boolean dejaClicke = false;
	private boolean scenery = false;

	/**
	 * Permet de savoir si la case a déjà été cliquée (pour ne pas pouvoir
	 * la cliquer plusieurs fois)
	 * 
	 * @return true si la case a déjà été cliquée et fait partie de la
	 *         séquence, faux si la case ne fait pas partie de la séquence et
	 *         n'a pas été cliquée
	 */
	public boolean isDejaclicke() {
		return dejaClicke;
	}

	/**
	 * Permet d'initialiser l'état de la case par rapport au fait si elle a
	 * déjà été cliquée ou non
	 * 
	 * @param dejaclicke
	 *            True, si la case est cliquée. False, si la case n'est pas
	 *            cliquée
	 */
	public void setDejaclicke(boolean dejaclicke) {
		this.dejaClicke = dejaclicke;
		if (dejaclicke) {
			this.setEnabled(false);
			this.setBackground(Color.GREEN);
		} else {
			this.setBackground(Color.LIGHT_GRAY);
		}
	}

	/**
	 * Constructeur du BtnMagique
	 * 
	 * @param ligne
	 *            position verticale dans la grille des cases
	 * @param colonne
	 *            position horizontale dans la grille des cases
	 */
	public UMapTile(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.setBackground(Color.LIGHT_GRAY);
		this.setEnabled(true);
	}

	/**
	 * Récupérer la position verticale de la case
	 * 
	 * @return la position verticale de la case dans la grille des cases
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Récupérer la position horizontale de la case
	 * 
	 * @return la position horizontale de la case dans la grille des cases
	 */
	public int getColonne() {
		return colonne;
	}

	public boolean isScenery() {
		return scenery;
	}

	public void setScenery(boolean scenery) {
		this.scenery = scenery;
	}
}
