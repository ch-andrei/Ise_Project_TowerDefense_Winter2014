package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Class which contains the background image component for the inner menu
 * 
 * @author YN
 */
public class UInnerBackgroundComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource(
					"images/innerMenuBackground.png"));

	public UInnerBackgroundComponent() {
		setSize(new Dimension(1280, 720));
		setLocation(0, 0);
		setVisible(true);
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
