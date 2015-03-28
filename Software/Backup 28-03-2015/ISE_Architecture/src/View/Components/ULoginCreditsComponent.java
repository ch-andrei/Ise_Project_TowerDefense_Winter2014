package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Clase qui contient le composant de l'image pour afficher les createurs du jeu
 * @author Eric Kavalec
 */
public class ULoginCreditsComponent extends JComponent {

    //resolution des images: 300,75
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/plus.png"));

    public ULoginCreditsComponent() {
        setSize(new Dimension(300, 75));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * changer la taille de l'image
     */
    public void ChangerTaille() {
        if (img == Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/plus.png"))) {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/plusG.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/plus.png"));
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