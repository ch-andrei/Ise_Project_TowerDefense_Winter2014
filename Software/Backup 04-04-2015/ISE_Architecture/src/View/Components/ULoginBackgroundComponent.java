package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Classe qui contient le composant de l'image fond d'ecran du menu
 * 
 * @author Eric Kavalec
 */
public class ULoginBackgroundComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader()
					.getResource("images/backgroundmenu.png"));

	public ULoginBackgroundComponent() {
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
