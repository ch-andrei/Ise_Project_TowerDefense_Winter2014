package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Class which contains the component to save a game from the inner menu
 * 
 * @author YN
 */
public class UInnerSaveGameComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/saveGameS.png"));

	public UInnerSaveGameComponent() {
		setSize(new Dimension(200, 120));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * to change the component's image
	 */
	public void changeSize() {
		if (img == Toolkit.getDefaultToolkit()
				.getImage(
						getClass().getClassLoader().getResource(
								"images/saveGameS.png"))) {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/saveGameB.png"));
		} else {
			img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/saveGameS.png"));
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
