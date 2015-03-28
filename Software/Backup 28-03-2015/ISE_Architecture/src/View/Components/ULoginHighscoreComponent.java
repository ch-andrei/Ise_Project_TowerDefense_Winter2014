package View.Components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composant qui contient l'image pour passer vers la fenetre des meilleurs scores
 * @author Eric Kavalec
 */
public class ULoginHighscoreComponent extends JComponent {
    
    //resolution des images: 300,75
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/highscore.png"));

    public ULoginHighscoreComponent() {
        setSize(new Dimension(300, 75));
        setLocation(0, 0);
        setVisible(true);
    }
    
            /**
 * pour changer la taille de l'image
 */
    public void ChangerTaille(){
        if (img == Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/highscore.png"))){
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/highscoreG.png"));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/highscore.png"));
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