package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Composante graphique qui permet d'afficher le nom du joueur, la vie du joueur et de l'ennemi, le niveau en jeu, la force et la direction du vent
 * @author Yordan Neshev
 */
public class UGameStatsFrameComponent extends JComponent {

    private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/FrameT.png"));
    private int vieJ = 130;
    private int vieE = 130;
    private int vieTotaleJ = 130;
    private int vieTotaleE = 130;
    private int niveau;
    private String vent;
    private String nom;

    /**
     * Constructeur de l'encadrage tenant en compte le nom du joueur et le niveau en jeu
     * @param niv niveau en jeu
     * @param nom2 nom du joueuer
     */
    public UGameStatsFrameComponent(int niv, String nom2) {
        niveau = niv;
        nom = nom2;
        setSize(800, 100);
        setLocation(-9, 0);
    }

    /**
     * Initialiser la force et la direction du vent
     * @param vent2 force et direction du vent
     */
    public void setVent(String vent2) {
        vent = vent2;
    }

    /**
     * Récupérer la vie actuelle du joueur
     * @return vie actuelle du joueur
     */
    public int getVieJ() {
        return vieJ;
    }

    /**
     * Récupérer la vie actuelle de l'ennemi
     * @return vie actuelle de l'ennemi
     */
    public int getVieE() {
        return vieE;
    }

    /**
     * Dessiner l'encadrage contenant le nom du joueur, la vie du joueur et de l'ennemi, le niveau en jeu, la force et la direction du vent
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        g.setFont(new Font("Impact", Font.BOLD, 14));
        //Nom Joueur
        g.setColor(Color.BLACK);
        g.fillRect(15, 8, 130, 19);
        g.setColor(Color.WHITE);
        g.drawString("Joueur:  " + nom, 30, 22);
        //Vie Joueur
        g.setColor(Color.red);
        g.fillRect(15, 32, vieJ, 19);
        g.setColor(Color.BLACK);
        g.drawString("Joueur", 62, 47);
        //Vie Ennemi
        g.setColor(Color.red);
        g.fillRect(658, 32, vieE, 19);
        g.setColor(Color.BLACK);
        g.drawString("Ennemi", 702, 47);
        //Level
        g.setColor(Color.BLACK);
        g.fillRect(351, 3, 99, 53);
        g.setColor(Color.WHITE);
        g.drawString("Level", 385, 25);
        g.setColor(Color.WHITE);
        g.drawString("" + niveau, 397, 47);
        //Vent
        g.setColor(Color.BLACK);
        g.fillRect(658, 8, 130, 19);
        g.setColor(Color.WHITE);
        g.drawString(vent, 660, 22);

    }

    /**
     * Enlever de la vie de la vie actuelle de l'ennemi lorsqu'il est frappé
     * @param vieEnleve vie à enlever dépendamment de l'armure de l'ennemi et et de l'arme du joueur
     */
    public void perdreVieE(int vieEnleve) {
        vieE = vieE - (int) (((double) vieEnleve * 130.0) / (double) vieTotaleE);
        if (vieE < 0) {
            vieE = 0;
        }
    }

    /**
     * Initialiser la vie initiale de l'ennemi
     * @param vie vie initiale de l'ennemi
     */
    public void setVieE(int vie) {
        vieTotaleE = vie;
        if (vieTotaleE < 130) {
            vieTotaleE = 130;
        }
    }

    /**
     * Enlever de la vie de la vie actuelle du joueur lorsqu'il est frappé
     * @param vieEnleve vie à enlever dépendamment du niveau de l'ennemi
     */
    public void perdreVieJ(int vieEnleve) {
        vieJ = vieJ - (int) (((double) vieEnleve * 130.0) / (double) vieTotaleJ);
        if (vieJ < 0) {
            vieJ = 0;
        }
    }

    /**
     * Initialiser la vie initiale du joueur
     * @param vie vie initiale du joueur
     */
    public void setVieJ(int vie) {
        vieTotaleJ = vie;
        if (vieTotaleJ < 130) {
            vieTotaleJ = 130;
        }
    }
}
