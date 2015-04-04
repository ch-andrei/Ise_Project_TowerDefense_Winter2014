package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import BL.Model.Player;

/**
 * Composant qui affiche les meilleurs scores
 * @author Eric Kavalec
 */
public class UHighscoresTextComponent extends JComponent {

    /**
     * highscores contient la liste des meilleurs scores
     */
    private ArrayList<Player> highscores;

    /**
     * @param highscore : contient la liste des meilleurs scores
     */
    public UHighscoresTextComponent(ArrayList<Player> highscore) {
        this.highscores = highscore;
        setSize(new Dimension(500, 500));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * Dessiner les meilleurs scores 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.BOLD, 35));
        String str = "";
        for (int i = 0; i < highscores.size(); i++) {
            str = str + "\n" + Integer.toString(i + 1) + ". (" + highscores.get(i).getNom();
            str = str + ") " + highscores.get(i).getPoints() + " points";
        }
        drawString(g, str, 10, 100);
    }

    /**
     * Dessine le string en séparant les lignes 
     * g contient le graphique
     * text contient le texte à dessiner
     * x détermine la position horizontale du texte à dessiner
     * y détermine la position verticale du texte à dessiner
     */
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }
}


