package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Classe qui contient le composant pour acceder aux meilleurs scores a partir de la fenetre monde
 * @author Eric Kavalec
 */
public class UInnerHighscoresComponent extends JComponent {

    //resolution des images: 300,75
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/mondehighscores.png"));

    public UInnerHighscoresComponent() {
        setSize(new Dimension(200, 120));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * pour changer l'image du composant 
     */
    public void ChangerTaille() {
        if (img == Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/mondehighscores.png"))) {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/mondehighscoresG.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/mondehighscores.png"));
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