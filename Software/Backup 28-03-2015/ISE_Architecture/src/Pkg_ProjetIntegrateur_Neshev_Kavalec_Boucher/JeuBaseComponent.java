package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composante graphique reprsentant la base, soit du joueur, soit de l'ennemi
 * @author Yordan Neshev
 */
public class JeuBaseComponent extends JComponent {

    private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/base.png"));
    private final int larg1 = 110;
    private final int haut1 = 146;
    private int larg = 110;
    private int haut = 146;
    private int posIni;
    private final int posIni1;

    /**
     * Constructeur de la base
     * @param pos position initale de la base d'après le fait si elle appartient au joueur ou à l'ennemi
     */
    public JeuBaseComponent(int pos) {
        posIni1 = pos;
        posIni = pos;
        setSize(larg, haut);
        setLocation(pos, 300);
    }

    /**
     * Dessiner la composante de la base
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, larg, haut, this);
        //base du joueur
        if (posIni1 < 800) {
            setLocation(posIni - (larg1 - larg) / 2, 300 + (haut1 - haut));
        }
        //base de l'ennemi
        if (posIni1 > 800) {
            setLocation(posIni + (larg1 - larg) / 2, 300 + (haut1 - haut));
        }
    }

    /**
     * Bouger la base d'après sa position par défaut correspondant à l'arrière fond
     * @param pos position de la base indiquée d'après la position et la dimension de l'arrière fond
     */
    public void bouger(int pos) {
        posIni = pos;
        //base du joueur
        if (posIni1 < 800) {
            setLocation(posIni - (larg1 - larg) / 2, 300 + (haut1 - haut));
        }
        //base de l'ennemi
        if (posIni1 > 800) {
            setLocation(posIni + (larg1 - larg) / 2, 300 + (haut1 - haut));
        }
    }

    /**
     * Remettre à défaut les dimensions de la base
     */
    public void resetSize() {
        larg = larg1;
        haut = haut1;
        setSize(larg, haut);
    }

    /**
     * Permet de savoir de combien de pixels l'hauteur de la base a été réduite lors du zoom
     * @return différence entre l'hauteur actuelle et initiale de la base
     */
    public int getDecalageY() {
        return (haut1 - haut);
    }

    /**
     * Permet de savoir de combien de pixels la largeur de la base a été réduite lors du zoom
     * @return différence entre la largeur actuelle et initiale de la base
     */
    public int getDecalageX() {
        return (larg1 - larg);
    }

    /**
     * Permet de redimensionner la base (zoom)
     * @param largeur portion à enlever de la largeur actuelle de la base
     * @param hauteur portion à enlever de l'hauteur actuelle de la base
     */
    public void resizeCustom(int largeur, int hauteur) {
        larg = larg + largeur;
        haut = haut + (int) (hauteur * (double) (146.0 / 110.0));

        if (larg < 16) {
            larg = 16;
        }
        if (haut < 21) {
            haut = 21;
        }
        if (larg > 110) {
            larg = 110;
        }
        if (haut > 146) {
            haut = 146;
        }
        setSize(larg, haut);
    }
}
