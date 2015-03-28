package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import sun.awt.image.ToolkitImage;

/**
 * Composante graphique du projectile
 * @author Yordan Neshev
 */
public class UGameProjectileComponent extends JComponent {

    private int vitesseX;
    private int vitesseY;
    private double angle;
    private int vitY;
    private int posX;
    private int posY;
    private int gravite = 1;
    private int temps = 0;
    private int vent = 0;
    private boolean bougerX = true;
    private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/lance1.png"));
    private Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/lanceE1.png"));
    private final int larg1 = 100;
    private final int haut1 = 16;
    private int larg = 100;
    private int haut = 16;
    private boolean quiTire;
    private int armeEq;

    /**
     * Constructeur de la composante graphique du projectile
     * @param posx position initiale horizontale du projectile
     * @param posyposition initiale verticale du projectile
     * @param quiTireTemp vrai si le joueur tire, faux si l'ennemi tire
     * @param arme arme equipée par le joueur
     */
    public UGameProjectileComponent(int posx, int posy, boolean quiTireTemp, int arme) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/lance"+arme+".png"));
        img2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/lanceE"+arme+".png"));
        armeEq = arme;
        quiTire = quiTireTemp;
        posX = posx;
        posY = posy;
        setSize(150, 150);
        setLocation(posX, posY);
    }

    /**
     * Dessiner la composante graphique du projectile
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        try {
            Graphics2D g2d = (Graphics2D) g;
            //Le joueur tire
            if (vitesseX >= 0) {
                //g.drawImage(img, 0, 0, larg, haut, this);

                //rotation du projectile
                angle();
                double rotationRequired = Math.toRadians(angle);

                double locationX = 0;
                double locationY = 0;
                if (vitesseY < 0) {
                    locationX = img.getWidth(this);
                    locationY = img.getHeight(this) / 2;
                    setSize(100, (int) (larg1 * Math.sin(Math.toRadians(-Math.toDegrees(rotationRequired))) + haut));
                }
                if (vitesseY > 0) {
                    locationX = 0;
                    locationY = img.getHeight(this) / 2;
                    setSize((int) (larg1 * Math.cos(Math.toRadians(Math.toDegrees(rotationRequired))) + haut), (int) (larg1 * (Math.sin(Math.toRadians(Math.toDegrees(rotationRequired)))) + haut));
                }
                if (vitesseY == 0) {
                    locationX = 0;
                    locationY = 0;
                    setSize(100, 16);
                }

                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                BufferedImage buffered = ((ToolkitImage) img).getBufferedImage();
                BufferedImage bufferedRes = internalResize(buffered, larg, haut);
                g2d.drawImage(op.filter(bufferedRes, null), 0, 0, null);
            }
            if (vitesseX < 0) {
                //g.drawImage(img2, 0, 0, larg, haut, this);

                //rotation du projectile
                angle2();
                double rotationRequired = -Math.toRadians(angle);

                double locationX = 0;
                double locationY = 0;
                if (vitesseY < 0) {
                    locationX = 0;
                    locationY = img2.getHeight(this) / 2;
                }
                if (vitesseY > 0) {
                    locationX = img2.getWidth(this);
                    locationY = img2.getHeight(this) / 2;
                }
                if (vitesseY == 0) {
                    locationX = 0;
                    locationY = 0;
                }
                setSize(110, 110);
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                BufferedImage buffered = ((ToolkitImage) img2).getBufferedImage();
                BufferedImage bufferedRes = internalResize(buffered, larg, haut);
                g2d.drawImage(op.filter(bufferedRes, null), 0, 0, null);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Redimensionner l'image de l'arme d'après sa longueur et sa largeur sans perdre sa transparence
     * @param source Image initiale à redimensionner
     * @param destWidth largeur finale
     * @param destHeight hauteur finale
     * @return Image finale redimensionnee de l'arme
     */
    private static BufferedImage internalResize(BufferedImage source, int destWidth, int destHeight) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();
        double xScale = ((double) destWidth) / (double) sourceWidth;
        double yScale = ((double) destHeight) / (double) sourceHeight;
        Graphics2D g2d = null;
        BufferedImage resizedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TRANSLUCENT);
        try {
            g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
            g2d.drawRenderedImage(source, at);
        } finally {
            if (g2d != null) {
                g2d.dispose();
            }
        }
        return resizedImage;
    }

    /**
     * Permet d'initialiser l'angle d'après le projectile du joueur
     */
    public void angle() {
        angle = Math.atan2(vitesseY, vitesseX);
        angle = (Math.toDegrees(angle));
        if (angle < -75) {
            angle = -75;
        }
    }

    /**
     * Permet d'initialiser l'angle d'après le projectile ennemi
     */
    public void angle2() {
        angle = Math.atan2(vitesseY, -vitesseX);
        angle = (Math.toDegrees(angle));
    }

    /**
     * Initialiser la vitesse verticale du projectile
     * @param vity vitesse verticale du projectile
     */
    public void setVitY(int vity) {
        vitY = vity;
    }

    /**
     * Initialiser la vitesse horizontale du projectile
     * @param vitx vitesse horizontale du projectile
     * @param vent2 force du vent (négatif si la direction est la gauche, positif si la direction est la droite)
     */
    public void setVitX(int vitx, int vent2) {
        vent = vent2;
        vitesseX = vitx - vent;
        if (vent >= vitx) {
            vitesseX = vitx - vent / 2;
        }
        if (vitesseX < 5 && quiTire) {
            vitesseX = 5;
        }
    }

    /**
     * Récupérer la vitesse horizontale actuelle
     * @return vitesse horizontale actuelle
     */
    public int getVitX() {
        return vitesseX;
    }

    /**
     * Récupérer la vitesse verticale actuelle
     * @return vitesse verticale actuelle
     */
    public int getVitY() {
        return vitesseY;
    }

    /**
     * Bouger le projectile d'après les position horizontale et verticale qu'il adopte lors de leurs calcul à partir des vitesses X et Y
     */
    public void bouger() {
        positionX();
        positionY();
        if (getY() <= 70 && vitesseY < 0) {
            posY = 70;
        }
        setLocation(posX, posY);
    }

    /**
     * Calcul de la position horizontale du projectile à partir de la vitesse horizontale
     */
    public void positionX() {
        posX = posX + vitesseX / 10;
    }

    /**
     * Calcul de la position verticale du projectile à partir de la vitesse verticale, la gravite et le temps
     */
    public void positionY() {
        double temps2 = Math.round(temps);
        vitesseY = vitY + (gravite * (int) temps2);
        posY = (posY + vitesseY / 4);
    }

    /**
     * Incrémenter le temps de vol du projectile d'après la vitesse du thread du jeu
     */
    public void Timer() {
        temps = (temps + 1);
    }

    /**
     * Remettre à défaut les dimensions du projectile
     */
    public void resetSize() {
        larg = larg1;
        haut = haut1;
        setSize(150, 150);
    }

    /**
     * Permet de détecter une collision entre le projectile et une autre composante graphique
     * @param other composante graphique à tester pour collision avec le projectile
     * @return vrai en lieu de collision, faux lorsqu'il n'y a pas de collision
     */
    public boolean collisionAvec(JComponent other) {
        return this.getBounds().intersects(other.getBounds());
    }

    /**
     * Permet de redimensionner le projectile (zoom)
     * @param largeur portion à enlever de la largeur actuelle du projectile
     * @param hauteur portion à enlever de l'hauteur actuelle du projectile
     */
    public void resizeCustom(int largeur, int hauteur) {
        larg = larg + largeur;
        haut = haut + (int) (hauteur * (double) (16.0 / 100.0));

        if (larg < 25) {
            larg = 25;
        }
        if (haut < 4) {
            haut = 4;
        }
        if (larg > 100) {
            larg = 100;
        }
        if (haut > 16) {
            haut = 16;
        }
    }
}
