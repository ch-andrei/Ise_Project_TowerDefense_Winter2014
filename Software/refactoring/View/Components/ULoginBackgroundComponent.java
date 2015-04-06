package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * class that contains back ground image of the Login window
 * 
 * @author YN
 */
public class ULoginBackgroundComponent extends JComponent {

	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader()
					.getResource("images/backgroundmenu.png"));

	/**
	 * constructor for login background component
	 */
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
