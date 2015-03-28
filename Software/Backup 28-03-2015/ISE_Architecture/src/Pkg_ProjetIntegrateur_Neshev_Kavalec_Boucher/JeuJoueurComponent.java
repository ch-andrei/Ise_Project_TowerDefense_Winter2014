package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import sun.awt.image.ToolkitImage;

/**
 * Composante graphique du joueur
 * @author Yordan Neshev
 */
public class JeuJoueurComponent extends JComponent {

    private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/player1.gif"));
    private Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/arm1.png"));
    private Image img3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/playerarm1.gif"));
    private final int largBonhomme1 = 58;
    private final int hautBonhomme1 = 86;
    private int largBonhomme = 58;
    private int hautBonhomme = 86;
    private double angleBr;
    private boolean dessinerBras = true;
    private final int posY = 240;
    private int posX = 130;
    private int decal = 0;
    private int decalx = 0;
    private int vitesseY;
    private int vitY;
    private int temps = 0;
    private double gravite = 1;
    private boolean frappe = false;

    /**
     * Constructeur de la composante graphique du joueur
     * @param armure armure equipée par le joueur
     * @param arme arme équipée par le joueur
     */
    public JeuJoueurComponent(int armure, int arme) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/player" + armure + ".gif"));
        //img2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/arm"+arme+".png"));
        img3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/playerarm" + armure + ".gif"));
        if (!frappe) {
            setSize(75, 80);
            setLocation(posX - (largBonhomme1 - largBonhomme) - (int) ((double) decalx * 0.1), (int) ((double) decal * 0.8) + posY + (hautBonhomme1 - hautBonhomme));
        }
    }

    /**
     * Récupérer la largeur actuelle de la composante du joueur
     * @return largeur actuelle de la composante du joueur
     */
    public int getLarg() {
        return largBonhomme;
    }

    /**
     * Récupérer l'hauteur actuelle de la composante du joueur
     * @return hauteur actuelle de la composante du joueur
     */
    public int getHaut() {
        return hautBonhomme;
    }

    /**
     * Dessiner la composante graphique du joueur
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            //Si le joueur est à sa position initiale et doit tirer
            if (dessinerBras) {
                setSize(75, 80);
                g.drawImage(img, 0, 0, largBonhomme1, hautBonhomme1, this);
                //rrotation du bras
                double rotationRequired = Math.toRadians(angleBr);
                double locationX = img2.getWidth(this) / 2;
                double locationY = img2.getHeight(this) / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                BufferedImage buffered = ((ToolkitImage) img2).getBufferedImage();
                g2d.drawImage(op.filter(buffered, null), -12, 0, null);
                if (!frappe) {
                    setLocation(posX - (largBonhomme1 - largBonhomme) - (int) ((double) decalx * 0.1), (int) ((double) decal * 0.8) + posY + (hautBonhomme1 - hautBonhomme));
                }
            }
            // Si le joueur est en mouvement
            if (!dessinerBras) {
                setSize(largBonhomme + ((largBonhomme1 - largBonhomme) / 2), hautBonhomme + ((hautBonhomme1 - hautBonhomme) / 2));
                g.drawImage(img3, 0, 0, largBonhomme, hautBonhomme, this);
                if (!frappe) {
                    setLocation(posX - (largBonhomme1 - largBonhomme) - (int) ((double) decalx * 0.1), (int) ((double) decal * 0.8) + posY + (hautBonhomme1 - hautBonhomme));
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Bouger le joueur d'après sa position par défaut correspondant à l'arrière fond
     * @param vit position du joueur indiquée d'après la position et la dimension de l'arrière fond 
     */
    public void bouger(int vit) {
        posX = vit - largBonhomme1;
        if (!frappe) {
            setLocation(posX - (largBonhomme1 - largBonhomme) - (int) ((double) decalx * 0.1), (int) ((double) decal * 0.8) + posY + (hautBonhomme1 - hautBonhomme));
        }
    }

    /**
     * Indiquer que le joueur est en mouvement
     */
    public void enleverBras() {
        dessinerBras = false;
    }

    /**
     * Indiquer que le joueur est prêt à tirer
     */
    public void ajouterBras() {
        dessinerBras = true;
    }

    /**
     * Remettre à défaut les dimensions du joueur
     */
    public void resetSize() {
        largBonhomme = largBonhomme1;
        hautBonhomme = hautBonhomme1;
        setSize(75, 80);
    }

    /**
     * Permet de redimensionner la joueur (zoom)
     * @param largeur portion à enlever de la largeur actuelle du joueur
     * @param hauteur portion à enlever de l'hauteur actuelle du joueur
     */
    public void resizeCustom(int largeur, int hauteur) {
        largBonhomme = largBonhomme + (int) (largeur * (double) (58.0 / 86.0));
        hautBonhomme = hautBonhomme + largeur;
        if (largBonhomme < 7) {
            largBonhomme = 7;
        }
        if (hautBonhomme < 11) {
            hautBonhomme = 11;
        }
        if (largBonhomme > 58) {
            largBonhomme = 58;
        }
        if (hautBonhomme > 86) {
            hautBonhomme = 86;
        }
    }

    /**
     * Décalage de l'hauteur de la base du joueur d'après laquelle on peut positionner le joueur lors du zoom
     * @param i différence entre l'hauteur actuelle et initiale de la base
     */
    public void decalageY(int i) {
        decal = i;
    }

    /**
     * Décalage de la largeur de la base du joueur d'après laquelle on peut positionner le joueur lors du zoom
     * @param i différence entre la largeur actuelle et initiale de la base
     */
    public void decalageX(int i) {
        decalx = i;
    }

    /**
     * Mettre à jour l'angle entre la position la largeur et l'hauteur de l'arme pour pouvoir tourner le bras du joueur
     * @param angle angle entre la position la largeur et l'hauteur de l'arme
     */
    public void updateAngleBras(double angle) {
        angleBr = -angle;
        if (angleBr > 0) {
            angleBr = 0;
        }
        if (angleBr < -75) {
            angleBr = -75;
        }
    }

    /**
     * Remettre à défaut les paramètres du saut du joueur
     */
    public void reset() {
        temps = 0;
        vitesseY = 0;
        vitY = 0;
    }

    /**
     * Récupérer la vitesse verticale finale du joueur
     * @return vitesse verticale finale du joueur
     */
    public int getVitY() {
        return vitesseY;
    }

    /**
     * Récupérer la vitesse verticale initiale du joueur
     * @return vitesse verticale initiale du joueur
     */
    public int getVitYIni() {
        return vitY;
    }

    /**
     * Permet de faire sauter le joueur lorsqu'il est frappé par le projectile ennemi
     * @param vitx vitesse horizontale du projectile
     * @param vity vitesse verticale du projectile
     */
    public void sauter(int vitx, int vity) {
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
        if (frappe) {
            setLocation(posX - (largBonhomme1 - largBonhomme) - (int) ((double) decalx * 0.1), getY() + vitesseY);
        }
    }

    /**
     * Permet de savoir si le joueur a été frappé par le projectile ennemi
     * @param i  vrai si le joueur est frappé
     */
    public void estFrappe(boolean i) {
        frappe = i;
    }
}
