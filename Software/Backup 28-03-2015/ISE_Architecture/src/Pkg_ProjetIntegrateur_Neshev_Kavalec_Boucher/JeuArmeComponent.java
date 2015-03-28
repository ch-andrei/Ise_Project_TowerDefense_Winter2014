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
 * Composante graphique Arme qui permet de régler la vitesse du projectile
 * dépendamment de la position de la souris du joueur
 * @author Yordan Neshev
 */
public class JeuArmeComponent extends JComponent {

    private final int posX1 = 170;
    private final int posY1 = 240;
    private final int longueur = 100;
    private int posX2;
    private int posY2;
    private double rotation;
    private double angle;
    private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/GunPowerF.png"));

    /**
     * Constructeur de la composante Arme
     * @param X position horizontale de la souris du joueur en pixels
     * @param Y position verticale de la souris du joueur en pixels
     */
    public JeuArmeComponent(int X, int Y) {
        posX2 = X;
        posY2 = Y;
        setSize(1000, 1000);
    }

    /**
     * Cette méthode permet de mettre à jour la position de la souris du joueur pour pouvoir
     * dessiner la composante arme d'après cette position
     * @param X position horizontale de la souris du joueur en pixels
     * @param Y position verticale de la souris du joueur en pixels
     */
    public void updateDroite(int X, int Y) {
        try {
            posX2 = X;
            posY2 = Y;
            //Angle entre X et Y
            angle = Math.atan2((-Y + posY1), (X - posX1));
            if (((posX2 - posX1) * (posX2 - posX1)) + ((posY1 - posY2) * (posY1 - posY2)) > (longueur * longueur)) {
                angle = Math.atan2((-Y + posY1), (X - posX1));
                //Dessiner l'arme d'après la force maximale permise (100) et l'angle entre X et Y
                posX2 = posX1 + (int) (longueur * Math.cos(angle));
                posY2 = posY1 - (int) (longueur * Math.sin(angle));
            }
            if (posX2 < posX1) {
                posX2 = posX1;
            }
            if (posY2 > posY1) {
                posY2 = posY1;
            }
        } catch (ArithmeticException e) {
        }
    }

    /**
     * Permet de récupérer l'angle entre la position X et Y de la souris du joueur en degrés
     * @return angle entre X et Y
     */
    public double getAngle() {
        return Math.toDegrees(angle);
    }

    /**
     * Permet de récupérer la position X initiale et invariable de l'arme
     * @return position initiale X
     */
    public int getposX1() {
        return posX1;
    }

    /**
     * Permet de récupérer la position Y initiale et invariable de l'arme
     * @return position initale Y
     */
    public int getposY1() {
        return posY1;
    }

    /**
     * Permet de récupérer la position X finale et variable de l'arme
     * @return position finale X
     */
    public int getposX2() {
        return posX2;
    }

    /**
     * Permet de récupérer la position Y finale et variable de l'arme
     * @return position finale Y
     */
    public int getposY2() {
        return posY2;
    }

    /**
     * Permet de dessiner la composante Arme d'après l'angle entre les positions X et Y
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            double rotationRequired = -angle + Math.toRadians(90);
            // Limiter l'angle pour qu'il soit entre 0 et 75 degrées
            if (Math.toDegrees(rotationRequired) < 15) {
                rotationRequired = Math.toRadians(15);
            }
            if (Math.toDegrees(rotationRequired) > 90) {
                rotationRequired = Math.toRadians(90);
            }
            rotation = rotationRequired;
            Graphics2D g2d = (Graphics2D) g;
            int largTemp = posX2 - posX1;
            int hautTemp = posY1 - posY2;
            // Trouver, par Pythagore, la longueur de l'arme à ce moment
            int hauteurImgTemp = (int) Math.sqrt(largTemp * largTemp + hautTemp * hautTemp);
            img.getWidth(this);
            img.getHeight(this);
            double locationX = 8;
            double locationY = hauteurImgTemp;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            BufferedImage buffered = ((ToolkitImage) img).getBufferedImage();
            BufferedImage bufferedRes = internalResize(buffered, 16, hauteurImgTemp);
            g2d.drawImage(op.filter(bufferedRes, null), posX1, posY1 - (int) locationY, null);

        } catch (Exception e) {
        }
    }

    /**
     * Permet de récupérer la rotation présente de l'arme en radians
     * @return rotation de l'arme
     */
    public double rotationArme() {
        return rotation;
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
}
