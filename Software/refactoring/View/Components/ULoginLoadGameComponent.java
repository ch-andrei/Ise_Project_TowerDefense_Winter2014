package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * component that contains image to start new game
 * 
 * @author YN
 */
public class ULoginLoadGameComponent extends JComponent {

	// resolution of images: 300,75
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/loadGameS.png"));

	/**
	 * constructo for login load game component
	 */
	public ULoginLoadGameComponent() {
		setSize(new Dimension(400, 100));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * change image size
	 */
	public void changeSize() {
		if (img == Toolkit.getDefaultToolkit()
				.getImage(
						getClass().getClassLoader().getResource(
								"images/loadGameS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/loadGameB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/loadGameS.png"));
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