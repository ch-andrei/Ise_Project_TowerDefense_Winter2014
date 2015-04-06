package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Class which contains the component to access the high scores menu from the
 * inner menu
 * 
 * @author YN
 */
public class UInnerHighscoresComponent extends JComponent {

	// resolution des images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource(
					"images/InnerHighScoresS.png"));

	public UInnerHighscoresComponent() {
		setSize(new Dimension(200, 120));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * to change the component's image
	 */
	public void changeSize() {
		if (img == Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/InnerHighScoresS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/InnerHighScoresB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/InnerHighScoresS.png"));
		}
		invalidate();
		repaint();
	}

	/**
	 * draws the component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}