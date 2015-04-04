package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;


/**
 * Classe qui contient le composant pour retourner a la fenetre precedante
 * @author Eric Kavalec
 */
public class UHighscoresBackButtonComponent extends JComponent {

    //resolution des images: 300,75
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/retour.png"));

    public UHighscoresBackButtonComponent() {
        setSize(new Dimension(300, 75));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
 * pour changer son image 
 */
    public void ChangerTaille() {
        if (img == Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/retour.png"))) {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/retourG.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/retour.png"));
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
