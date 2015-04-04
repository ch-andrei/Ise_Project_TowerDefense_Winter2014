package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Composant qui contient l'image pour creer une nouvelle partie
 * 
 * @author Eric Kavalec
 */
public class ULoginCreateMapComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource(
					"images/createCustomMapS.png"));

	public ULoginCreateMapComponent() {
		setSize(new Dimension(400, 100));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * change la taille de l'image
	 */
	public void ChangerTaille() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/createCustomMapS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/createCustomMapB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/createCustomMapS.png"));
		}
		invalidate();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}