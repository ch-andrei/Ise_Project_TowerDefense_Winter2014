package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import BL.Controller.Controller;

/**
 * Composant qui affiche les statistiques
 * 
 * @author Eric Kavalec
 */
public class UInnerStatsComponent extends JComponent {

	public UInnerStatsComponent() {
		setSize(new Dimension(200, 480));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * redessine le composant
	 */
	public void UpdateStats() {
		invalidate();
		repaint();
	}

	/**
	 * Dessiner les statistiques
	 * 
	 * @param g
	 *            le graphics a dessiner
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String str = "Name: " + Controller.getInstance().player.getNom()
				+ "\n\nPoints: " + Controller.getInstance().player.getPoints()
				+ "\n\nMoney: " + Controller.getInstance().player.getMoney()
				+ "\n\nMax Level: "
				+ Controller.getInstance().player.getMaxLevel();
		// Create border around text
		g.setColor(Color.BLACK);
		g.setFont(new Font("Impact", Font.BOLD, 32));
		drawString(g, "Name: " + Controller.getInstance().player.getNom(), 0, 3);
		drawString(g, "Points: " + Controller.getInstance().player.getPoints(),
				0, 79);
		drawString(g, "Money: " + Controller.getInstance().player.getMoney(),
				0, 155);
		g.setFont(new Font("Impact", Font.BOLD, 31));
		drawString(g,
				"Max Level: " + Controller.getInstance().player.getMaxLevel(),
				0, 232);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.BOLD, 30));
		drawString(g, str, 0, 0);

	}

	/**
	 * Dessine le string en séparant les lignes
	 * 
	 * @param g
	 *            contient le graphique
	 * @param text
	 *            contient le texte à dessiner
	 * @param x
	 *            détermine la position horizontale du texte à dessiner
	 * @param y
	 *            détermine la position verticale du texte à dessiner
	 */
	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
}