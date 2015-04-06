package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * class handling background components of the high score menu
 * 
 * @author YN
 */
public class UHighscoresBackgroundComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource(
					"images/backgroundhighscores.png"));

	/**
	 * constructor for background component
	 */
	public UHighscoresBackgroundComponent() {
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
