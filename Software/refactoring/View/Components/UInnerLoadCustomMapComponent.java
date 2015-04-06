package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Class which contains the component to return to the main menu from the
 * inner menu
 * 
 * @author YN
 */
public class UInnerLoadCustomMapComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/loadMapS.png"));

	public UInnerLoadCustomMapComponent() {
		setSize(new Dimension(200, 120));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * to change the component's image
	 */
	public void changeSize() {
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
	 * draws the component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
