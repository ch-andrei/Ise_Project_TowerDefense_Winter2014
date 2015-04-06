package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import BL.Model.Player;

/**
 * Component that displays high scores text components
 * 
 * @author YN
 */
public class UHighscoresTextComponent extends JComponent {

	/**
	 * highscores contains a list of previous best scores
	 */
	private ArrayList<Player> highscores;

	/**
	 * @param highscore
	 *            : contains list of best scores
	 */
	public UHighscoresTextComponent(ArrayList<Player> highscore) {
		this.highscores = highscore;
		setSize(new Dimension(1280, 720));
		setLocation(0, 0);
		setVisible(true);
	}

	/**
	 * paints high scores component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(new Font("Impact", Font.BOLD, 35));
		String str = "";
		for (int i = 0; i < highscores.size(); i++) {
			str = str + "\n      " + Integer.toString(i + 1) + ". ("
					+ highscores.get(i).getName();
			str = str + ")    " + highscores.get(i).getPoints() + " points";
		}
		drawString(g, str, 470, 100);
	}

	/**
	 * draws text for high scores menu
	 */
	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n")) {
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
}
