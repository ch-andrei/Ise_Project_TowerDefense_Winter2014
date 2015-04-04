package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class UCreateMapCustomizationComponent extends JComponent {

	Image img;

	public UCreateMapCustomizationComponent(String imgName) {
		setSize(new Dimension(100, 100));
		setVisible(true);
		img = Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/" + imgName + "S.png"));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
