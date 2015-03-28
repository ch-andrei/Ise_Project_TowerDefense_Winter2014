package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composante graphique de l'ennemi
 * @author Yordan Neshev
 */
public class JeuEnnemiComponent extends JComponent {

    private Image img;
    private int vitesseX;
    private int vitesseY;
    private int vitY;
    private int posX = 3600;
    private int posY;
    private double gravite = 1;
    private int temps = 0;
    private int larg = 128;
    private int haut = 128;
    private final int larg1 = 128;
    private final int haut1 = 128;
    private int decal = 0;
    private boolean frappe = false;

    /**
     * Constructeur de la composante de l'ennemi d'après le niveau en jeu
     * @param niveau niveau actuel du jeu
     */
    public JeuEnnemiComponent(int niveau) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/warrior" + niveau + ".gif"));
        setSize(larg, haut);
        setLocation(posX + (larg1 - larg) / 2, (int) ((double) decal * 0.71) + 210 + ((haut1 - haut)));
    }

    /**
     * Récupérer la largeur actuelle de la composante de l'ennemi
     * @return largeur actuelle de la composante de l'ennemi
     */
    public int getLarg() {
        return larg;
    }

    /**
     * Récupérer l'hauteur actuelle de la composante de l'ennemi
     * @return hauteur actuelle de la composante de l'ennemi
     */
    public int getHaut() {
        return haut;
    }

    /**
     * Décalage de l'hauteur de la base de l'ennemi d'après laquelle on peut positionner l'ennemi lors du zoom
     * @param i différence entre l'hauteur actuelle et initiale de la base
     */
    public void decalage(int i) {
        decal = i;
    }

    /**
     * Dessiner la composante de l'ennemi
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, larg, haut, this);
        if (!frappe) {
            setLocation(posX + (larg1 - larg) / 2, (int) ((double) decal * 0.71) + 210 + ((haut1 - haut)));
        }
    }

    /**
     * Permet de savoir si l'ennemi a été frappé par le projectile du joueur
     * @param hit vrai si l'ennemi est frappé
     */
    public void estFrappe(boolean hit) {
        frappe = hit;
    }

    /**
     * Remettre à défaut les dimensions de l'ennemi
     */
    public void resetSize() {
        larg = larg1;
        haut = haut1;
        setSize(larg, haut);
    }

    /**
     * Permet de faire sauter l'ennemi lorsqu'il est frappé par le projectile du joueuer
     * @param vitx vitesse horizontale du projectile
     * @param vity vitesse verticale du projectile
     */
    public void sauter(int vitx, int vity) {
        if (frappe) {
            if (vity <= -10) {
                vitY = -15;
            }
            if (vity > -10 && vity <= 5) {
                vitY = -5;
            }
            if (vity > 5) {
                vitY = -15;
            }
            double temps2 = Math.round(temps);
            vitesseY = vitY + ((int) gravite * (int) temps2);
            temps = (temps + 1);
            setLocation(390, getY() + vitesseY);
        }
    }

    /**
     * Bouger l'ennemi d'après sa position par défaut correspondant à l'arrière fond
     * @param pos2 position de l'ennemi indiquée d'après la position et la dimension de l'arrière fond 
     */
    public void bouger(int pos2) {
        if (!frappe) {
            posX = pos2;
            setLocation(posX + (larg1 - larg) / 2, (int) ((double) decal * 0.71) + 210 + ((haut1 - haut)));
        }
    }

    /**
     * Remettre à défaut les paramètres du saut de l'ennemi
     */
    public void reset() {
        temps = 0;
        vitesseY = 0;
        vitY = 0;
    }

    /**
     * Récupérer la vitesse verticale finale de l'ennemi
     * @return vitesse verticale finale de l'ennemi
     */
    public int getVitY() {
        return vitesseY;
    }

    /**
     * Récupérer la vitesse verticale initiale de l'ennemi
     * @return vitesse verticale initiale de l'ennemi
     */
    public int getVitYIni() {
        return vitY;
    }

    /**
     * Permet de redimensionner l'ennemi (zoom)
     * @param largeur portion à enlever de la largeur actuelle de l'ennemi
     * @param hauteur portion à enlever de l'hauteur actuelle de l'ennemi
     */
    public void resizeCustom(int largeur, int hauteur) {
        larg = larg + (int) (largeur * (128.0 / 100.0));
        haut = haut + hauteur;

        if (larg < 18) {
            larg = 18;
        }
        if (haut < 14) {
            haut = 14;
        }
        if (larg > 128) {
            larg = 128;
        }
        if (haut > 100) {
            haut = 100;
        }
        setSize(larg, haut);
    }
}