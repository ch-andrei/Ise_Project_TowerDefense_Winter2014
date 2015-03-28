package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Classe qui contient les composants pour choisir les niveaux dans la fenetre
 * monde
 * 
 * @author Eric Kavalec
 */
public class UInnerLevelComponent extends JComponent {

	// resolution des images: 25,25
	public int level;
	private boolean debloque;
	private boolean focus = false;

	/**
	 * @param level
	 *            le level associe au composant
	 * @param debloque
	 *            determine si ce niveau est debloque ou non
	 */
	public UInnerLevelComponent(int level, boolean debloque) {
		this.debloque = debloque;
		this.level = level;
		setSize(new Dimension(32, 32));

		// arranger les boutons dans la fenetre
		if (level == 1) {
			setLocation(700, 310);
		} else if (level == 2) {
			setLocation(835, 175);
		} else if (level == 3) {
			setLocation(885, 410);
		} else if (level == 4) {
			setLocation(470, 315);
		} else if (level == 5) {
			setLocation(700, 115);
		} else if (level == 6) {
			setLocation(550, 535);
		}
		setVisible(true);
	}

	/**
	 * changer l'image
	 */
	public void ChangerTaille() {
		if (debloque) {
			if (focus) {
				focus = false;
			} else {
				focus = true;
			}
			invalidate();
			repaint();
		}
	}

	/**
	 * dessiner le composant selon si il est debloque ou non. Un composant blanc
	 * est debloque, un composant gris nèest pas debloque et il est jaune si
	 * l'utilisateur a sa souris dessus
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		if (debloque) {
			if (focus) {
				g.setColor(Color.GREEN);
			}
		} else {
			g.setColor(Color.GRAY);
		}

		g.fillOval(0, 0, 32, 32);
		g.setFont(new Font("Impact", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("" + level, 12, 23);
	}
}
