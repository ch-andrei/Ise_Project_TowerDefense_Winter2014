package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Classe qui contient le composant de l'image d'ariere plan de la fenetre du magasin
 * @author Eric Kavalec
 */
public class UShopBackgroundComponent extends JComponent {

    // résolution de l'image: 800,480
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/backgroundshop.png"));

    public UShopBackgroundComponent() {
        setSize(new Dimension(800, 480));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * dessine le composant
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}
