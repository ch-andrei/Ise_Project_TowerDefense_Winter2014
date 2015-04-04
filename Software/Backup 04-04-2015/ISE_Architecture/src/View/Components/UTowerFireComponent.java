package View.Components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class UTowerFireComponent extends UTowerComponent {
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/path.png"));

	public UTowerFireComponent() {
		super("hi", 0, 0, 0, 0);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);

	}

}
