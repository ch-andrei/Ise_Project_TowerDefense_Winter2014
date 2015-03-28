package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/** 
 * Classe qui contient le composant pour l'arriere plan de la fenetre des meilleurs scores
 * @author Eric Kavalec
 */
public class UHighscoresBackgroundComponent extends JComponent {

    // résolution de l'image: 800,480
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/backgroundhighscores.png"));

    /**
     * contructeur de l'image d'arriere plan
     */
    public UHighscoresBackgroundComponent() {
        setSize(new Dimension(800, 480));
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}
