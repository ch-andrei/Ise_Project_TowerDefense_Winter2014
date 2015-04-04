package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Classe qui contient le composant pour passer de la fenetre monde a la fenetre
 * du magasin
 * 
 * @author Eric Kavalec
 */
public class UInnerShopComponent extends JComponent {

	// resolution des images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/storeS.png"));

	public UInnerShopComponent() {
		setSize(new Dimension(200, 120));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * pour changer la taille du composant
	 */
	public void ChangerTaille() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource("images/storeS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader()
							.getResource("images/storeB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader()
							.getResource("images/storeS.png"));
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