package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * component containing image to go to high scores menu
 * 
 * @author YN
 */
public class ULoginHighscoreComponent extends JComponent {

	// resolution of images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/highScoresS.png"));

	/**
	 * constructor for login high scores component
	 */
	public ULoginHighscoreComponent() {
		setSize(new Dimension(400, 100));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * change image size 
	 */
	public void changeSize() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/highScoresS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/highScoresB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/highScoresS.png"));
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