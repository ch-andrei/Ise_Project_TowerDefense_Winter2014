package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composante graphique de l'arrière-fond du panneau du jeu
 * @author Yordan Neshev
 */
public class UGameBackgroungComponent extends JComponent {

    private Image img;
    private final int larg1 = 4000;
    private final int haut1 = 2000;
    private int larg = 4000;
    private int haut = 2000;
    private int hauteurEcran = 480;
    private boolean bouger = true;

    /**
     * Constructeur de l'arrière fond
     * @param level niveau en jeu duquel dépend l'arrière fond
     */
    public UGameBackgroungComponent(int level) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/bg" + level + ".jpg"));
        setSize(larg, haut);
    }

    /**
     * Récupérer la largeur actuelle de l'arrière fond
     * @return largeur de l'arrière fond
     */
    public int getLarg() {
        return larg;
    }

    /**
     * Récupérer l'hauteur actuelle de l'arrière fond
     * @return hauteur de l'arrière fond
     */
    public int getHaut() {
        return haut;
    }

    /**
     * Remettre l'image à ses dimensions initiales, par défaut
     */
    public void resetSize() {
        larg = larg1;
        haut = haut1;
        setSize(larg, haut);
    }

    /**
     * Dessiner l'arrière fond
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, -(haut - hauteurEcran), larg, haut, this);
    }

    /**
     * Bouger l'arrière fond vers la gauche lorsque le joueur tire
     * @param vit vitesse de déplacement de l'arrière fond dépendant de la vitesse du projectile du joueur
     */
    public void bouger(int vit) {
        if (bouger) {
            setLocation(getX() - vit, getY());
            if (larg - (Math.abs(getX())) < 800) {
                setLocation(-(larg - 800), getY());
            }
            if (larg - (Math.abs(getX())) == 800) {
                bouger = false;
            }
        }
        if (!bouger) {
            setLocation(-(larg - 800), getY());
        }
    }

    /**
     * Bouger l'arrière fond vers la droite lorsque l'ennemi tire
     * @param vitx vit vitesse de déplacement de l'arrière fond dépendant de la vitesse du projectile ennemi
     */
    public void bouger2(int vitx) {
        setLocation(getX() + vitx, getY());
        if (getX() > 0) {
            bouger = true;
            setLocation(0, getY());
        }
    }

    /**
     * Permet de positionner l'ennemi d'après la position de l'arrière fond
     * @return position de l'ennemi
     */
    public int getPosEnnemi() {
        int pos = (larg - (Math.abs(getX()))) - (int) (410.0 * ((double) larg / 4000.0));
        return pos;
    }

    /**
     * Permet de positionner le joueur d'après la position de l'arrière fond
     * @return position du joueur
     */
    public int getPosJoueur() {
        int pos = getX() + 180;
        return pos;
    }

    /**
     * Permet de positionner la base du joueur d'après la position de l'arrière fond
     * @return position de la base du joueur
     */
    public int getPosBase1() {
        int pos = getX() + 100;
        return pos;
    }

    /**
     * Permet de positionner la base de l'ennemi d'après la position de l'arrière fond
     * @return position de la base de l'ennemi
     */
    public int getPosBase2() {
        int pos = (larg - (Math.abs(getX()))) - (int) (400.0 * ((double) larg / 4000.0));

        return pos;
    }

    /**
     * Permet de positionner le mur d'après la position de l'arrière fond
     * @return position du mur
     */
    public int getPosMur() {
        int pos = getX() + larg / 2;
        return pos;
    }

    /**
     * Permet de savoir si l'arrière fond est à dimension par défaut (n'est pas zoomé)
     * @return vrai si l'hauteur et la largeur actuelles de l'arrière fond correspondent à l'hauteur et la largeur par défaut
     */
    public boolean resizeFini() {
        if (larg == larg1 && haut == haut1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet de savoir si l'arrière fond est en train d'être redimensionné
     * @return vrai si l'arrière fond est en train d'être redimensionné
     */
    public boolean resizeEnCours() {
        if (larg > 1200 && haut > 600) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet de redimensionner l'arrière fond (zoom)
     * @param largeur portion à enlever de la largeur actuelle de l'arrière fond
     * @param hauteur portion à enlever de l'hauteur actuelle de l'arrière fond
     */
    public void resizeCustom(int largeur, int hauteur) {
        larg = larg + largeur;
        haut = haut + (int) (hauteur * 0.5);

        if (larg < 1200) {
            larg = 1200;
        }
        if (haut < 600) {
            haut = 600;
        }
        if (larg > 4000) {
            larg = 4000;
        }
        if (haut > 2000) {
            haut = 2000;
        }
    }
}
