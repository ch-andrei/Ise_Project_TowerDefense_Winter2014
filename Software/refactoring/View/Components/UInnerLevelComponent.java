package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Class which contains the components to choose a level from the inner menu
 * 
 * @author YN
 */
public class UInnerLevelComponent extends JComponent {

	public int level;
	private boolean unlocked;
	private boolean focus = false;

	/**
	 * @param level
	 *            the level associated to the component
	 * @param unlocked
	 *            determines if the level is unlocked
	 */
	public UInnerLevelComponent(int level, boolean unlocked) {
		this.unlocked = unlocked;
		this.level = level;
		setSize(new Dimension(32, 32));

		if (level == 1) {
			setLocation(700, 310);
		} else if (level == 2) {
			setLocation(835, 175);
		} else if (level == 3) {
			setLocation(885, 410);
		} else if (level == 4) {
			setLocation(470, 315);
		} else if (level == 5) {
			setLocation(700, 115);
		} else if (level == 6) {
			setLocation(550, 535);
		}
		setVisible(true);
	}

	/**
	 * changes the image
	 */
	public void changeSize() {
		if (unlocked) {
			if (focus) {
				focus = false;
			} else {
				focus = true;
			}
			invalidate();
			repaint();
		}
	}

	/**
	 * Draws the component depending on if it is unlocked or not. A white component
	 * is unlocked, whereas a gray component is not unlocked. If the component is yellow,
	 * the player has his pointer on it
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		if (unlocked) {
			if (focus) {
				g.setColor(Color.GREEN);
			}
		} else {
			g.setColor(Color.GRAY);
		}

		g.fillOval(0, 0, 32, 32);
		g.setFont(new Font("Impact", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("" + level, 12, 23);
	}
}
