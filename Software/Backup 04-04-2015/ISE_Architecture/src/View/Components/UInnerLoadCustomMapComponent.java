package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Clase qui contient le composant pour retourner au menu principal a partir de
 * la fenetre monde
 * 
 * @author Eric Kavalec
 */
public class UInnerLoadCustomMapComponent extends JComponent {

	// resolution des images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/loadMapS.png"));

	public UInnerLoadCustomMapComponent() {
		setSize(new Dimension(200, 120));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * pour changer l'image du composant
	 */
	public void ChangerTaille() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource("images/loadMapS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/loadMapB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/loadMapS.png"));
		}
		invalidate();
		repaint();
	}

	/**
	 * dessine le composant
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
