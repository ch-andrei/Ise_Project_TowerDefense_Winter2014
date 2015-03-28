package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composant qui contient l'image pour charger une partie
 * @author Eric Kavalec
 */
public class ULoginLoadGameComponent extends JComponent {

    //resolution des images: 300,75
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/loadgame.png"));

    public ULoginLoadGameComponent() {
        setSize(new Dimension(300, 75));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * change la taille de l'image
     */
    public void ChangerTaille() {
        if (img == Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/loadgame.png"))) {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/loadgameG.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/loadgame.png"));
        }
        invalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}