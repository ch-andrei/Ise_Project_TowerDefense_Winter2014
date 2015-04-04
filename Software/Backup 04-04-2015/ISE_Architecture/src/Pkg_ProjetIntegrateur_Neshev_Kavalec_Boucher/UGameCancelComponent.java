package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composant qui contient l'image pour retourner du jeu vers le menu
 * @author Eric Kavalec
 */
public class UGameCancelComponent extends JComponent {
    

    
    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/x.png"));

    public UGameCancelComponent() {
        setSize(new Dimension(30,30));
        setLocation(0, 0);
        setVisible(true);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}
