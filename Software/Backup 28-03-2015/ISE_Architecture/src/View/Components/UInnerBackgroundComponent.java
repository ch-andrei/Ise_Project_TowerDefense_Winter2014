package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Composant qui contient l'image de fond de la fenetre monde
 * 
 * @author Eric Kavalec
 */
public class UInnerBackgroundComponent extends JComponent {

	// résolution de l'image: 800,480
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource(
					"images/innerMenuBackground.png"));

	public UInnerBackgroundComponent() {
		setSize(new Dimension(1280, 720));
		setLocation(0, 0);
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
