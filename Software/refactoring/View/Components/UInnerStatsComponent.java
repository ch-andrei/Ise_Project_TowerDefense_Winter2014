package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import BL.Controller.Controller;

/**
 * Component which shows the statistics
 * 
 * @author YN
 */
public class UInnerStatsComponent extends JComponent {

	public UInnerStatsComponent() {
		setSize(new Dimension(200, 480));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * redraws the component
	 */
	public void UpdateStats() {
		invalidate();
		repaint();
	}

	/**
	 * draws the statistics
	 * 
	 * @param g
	 *            the graphics to draw
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String str = "Name: " + Controller.getInstance().player.getNom()
				+ "\n\nScore: " + Controller.getInstance().player.getPoints()
				+ "\n\nMax Level: "
				+ Controller.getInstance().player.getMaxLevel();
		// Create border around text
		g.setColor(Color.BLACK);
		g.setFont(new Font("Impact", Font.BOLD, 32));
		drawString(g, "Name: " + Controller.getInstance().player.getNom(), 0, 3);
		drawString(g, "Score: " + Controller.getInstance().player.getPoints(),
				0, 79);
		g.setFont(new Font("Impact", Font.BOLD, 31));
		drawString(g,
				"Max Level: " + Controller.getInstance().player.getMaxLevel(),
				0, 155);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.BOLD, 30));
		drawString(g, str, 0, 0);

	}

	/**
	 * Draws the string by separating the lines
	 * 
	 * @param g
	 *            contains the graphics
	 * @param text
	 *            contains the text to draw
	 * @param x
	 *            determines the horizontal position of the text to draw
	 * @param y
	 *            determines the vertical position of the text to draw
	 */
	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
}