package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * component containing image to start new game
 * 
 * @authorYN
 */
public class ULoginNewGameComponent extends JComponent {

	// resolution of images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/newGameS.png"));

	/**
	 * constructor for login new game component
	 */
	public ULoginNewGameComponent() {
		setSize(new Dimension(400, 100));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * change image size
	 */
	public void changeSize() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource("images/newGameS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/newGameB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/newGameS.png"));
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