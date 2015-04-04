package Pkg_ProjetIntegrateur_Neshev_Kavalec_Boucher;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BL.Controller.Controller;

/**
 * Panneau principal du jeu
 * @author Yordan Neshev
 */
public class UGamePanel extends JPanel {

    private UGameCancelComponent cancelComponent;
    private JeuJoueurComponent joueur;
    private UGameTileComponent mur;
    private UGameStatsFrameComponent frame;
    private JeuBaseComponent base;
    private JeuBaseComponent base2;
    private UGameProjectileComponent projectile;
    private JeuArmeComponent arme;
    private JeuEnnemiComponent ennemi;
    private boolean enJeu = true;
    private ArrayList<UGameProjectileComponent> listeProjectile = new ArrayList<UGameProjectileComponent>();
    private UGameBackgroungComponent img;
    private int forceVent;
    private int temp;
    private int temp2;
    private int directionVent;
    private String direction;
    private boolean ennemiEnVol = false;
    private boolean ennemiFrappe = false;
    private boolean projectileEnnemi = false;
    private boolean joueurDejaTire = false;
    private boolean joueurFrappe = false;
    private int vitProjectileTemp;
    private Thread threadJeu;
    private int timeWait = 40;
    private int armeEq;
    private int armureEq;
    private int niv;
    private int pointsGagnes = 0;
    private String name;

    /**
     * Constructeur du panneau du jeu
     * @param armeUtilise arme équipée par le joueur
     * @param armureUtilise armure équipée par le joueur
     * @param niveau niveau actuel du jeu
     * @param nom nom du joueur
     */
    public UGamePanel(int armeUtilise, int armureUtilise, int niveau, String nom) {
        
        cancelComponent= new UGameCancelComponent();
        cancelComponent.setLocation(450,10);
        add(cancelComponent);
        
        armeEq = armeUtilise;
        armureEq = armureUtilise;
        niv = niveau;
        name = nom;
        setLayout(null);
        setSize(800, 480);
        frame = new UGameStatsFrameComponent(niv, name);
        img = new UGameBackgroungComponent(niv);
        base = new JeuBaseComponent(img.getPosBase1());
        base2 = new JeuBaseComponent(img.getPosBase2());
        mur = new UGameTileComponent(img.getPosMur());
        arme = new JeuArmeComponent(140, 320);
        ennemi = new JeuEnnemiComponent(niv);
        frame.setVieJ(130 * armureEq);
        frame.setVieE(130 * niv);
        joueur = new JeuJoueurComponent(armureUtilise, armeEq);
        ventForce();
        ventDirection();
        add(arme);
        frame.setVent("  Vent: " + forceVent + " (" + direction + ")");
        add(frame);
        add(base2);
        add(ennemi);
        add(joueur);
        add(mur);
        add(base);
        add(base2);
        add(img);
        bougerDroite();
        gameThread();
        setVisible(true);
        
        cancelComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.getInstance().stopMidGame();
            }
        });
    }

    /**
     * Permet de mettre fin au thread du jeu (pauser le jeu)
     * @param i vrai pour initialiser le thread du jeu, faux pour l'arrêter
     */
    private void setEnJeu(boolean i) {
        enJeu = i;
    }

    /**
     * Thread principal du jeu où la mise à jour de ce dernier se fait
     */
    protected void gameThread() {
        threadJeu = new Thread() {

            @Override
            public void run() {
                while (enJeu) {
                    //Mise à jour du jeu
                    majJeu();
                    invalidate();
                    repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        threadJeu.start();
    }

    /**
     * Toutes les mises à jour du jeu concernant la partie graphique y sont executées
     */
    public void majJeu() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    //Mouvement du projectile
                    for (UGameProjectileComponent projectileTemp : listeProjectile) {
                        projectileTemp.bouger();
                        projectileTemp.Timer();
                        if (projectileTemp.getY() > 480 || projectileTemp.getX() < -100 || projectileTemp.getX() > 1000) {
                            listeProjectile.remove(projectileTemp);
                            remove(projectileTemp);
                            if (projectileEnnemi) {
                                projectileEnnemi = false;
                            }
                        }
                        //Diminuer les images (Zoom out)
                        if (projectileTemp.getVitY() < 0 && projectileTemp.getY() <= 70 && img.resizeEnCours()) {
                            int res = (int) (projectileTemp.getVitY() / 5.0);
                            img.resizeCustom(res * 10, res * 10);
                            projectileTemp.resizeCustom(res, res);
                            ennemi.resizeCustom(res, res);
                            joueur.resizeCustom(res, res);
                            base.resizeCustom(res, res);
                            mur.resizeCustom(res, res);
                            base2.resizeCustom(res, res);
                        }
                        //Augmenter les images (Zoom in)
                        if (projectileTemp.getVitY() >= 0 && !img.resizeFini()) {
                            int res = (int) (projectileTemp.getVitY() / 4.0);
                            img.resizeCustom(res * 10, res * 10);
                            projectileTemp.resizeCustom(res, res);
                            ennemi.resizeCustom(res, res);
                            joueur.resizeCustom(res, res);
                            base.resizeCustom(res, res);
                            mur.resizeCustom(res, res);
                            base2.resizeCustom(res, res);
                        }

                        //Détection des collisions
                        if (projectileTemp.collisionAvec(ennemi) && projectileTemp.getVitX() >= 0) {
                            ennemiFrappe = true;
                            ennemiEnVol = true;
                            temp = -projectileTemp.getVitX();
                            temp2 = projectileTemp.getVitY();
                            projectileTemp.setVitX((int) (temp), 0);
                        }
                        if (projectileTemp.collisionAvec(joueur) && projectileTemp.getVitX() <= 0) {
                            joueurFrappe = true;
                            joueur.estFrappe(true);
                            temp = -projectileTemp.getVitX();
                            temp2 = projectileTemp.getVitY();
                            projectileTemp.setVitX((int) (temp), 0);
                        }
                        if (projectileTemp.collisionAvec(mur) || (projectileTemp.getX() == mur.getX() - 5 && projectileTemp.getY() > mur.getY() - 100) || (projectileTemp.getX() == mur.getX() + 5 && projectileTemp.getY() > mur.getY() - 100)) {
                            listeProjectile.remove(projectileTemp);
                            remove(projectileTemp);
                            if (projectileEnnemi) {
                                projectileEnnemi = false;
                            }
                        }
                    }
                    //Détecter si le joueur ou l'ennemi se retrouvent avec 0 vie pour arrêter la partie
                    if (frame.getVieJ() <= 0) {
                        setEnJeu(false);
                        Controller.getInstance().enemyWins(pointsGagnes);
                    }
                    if (frame.getVieE() <= 0) {
                        setEnJeu(false);
                        Controller.getInstance().playerWins(pointsGagnes);
                    }
                    //Ennemi Saute et Tire
                    if (ennemiEnVol) {
                        if (img.getLarg() != 4000) {
                            img.resetSize();
                            projectile.resetSize();
                            ennemi.resetSize();
                            base.resetSize();
                            mur.resetSize();
                            joueur.resetSize();
                            base2.resetSize();
                        }
                        //Ennemi rebondit a cause du coup
                        if (ennemi.getVitY() <= -(ennemi.getVitYIni()) && joueurDejaTire) {
                            ennemi.estFrappe(true);
                            ennemi.sauter(-temp / 20, -temp2);
                        }
                        //Ennemi Tire
                        if ((ennemi.getVitY() >= -(ennemi.getVitYIni())) && listeProjectile.isEmpty()) {
                            ennemi.estFrappe(false);
                            if (!ennemiFrappe) {
                                timeWait = timeWait - 1;
                                if (timeWait <= 0) {
                                    ennemiTirer();
                                }
                            }
                            if (ennemiFrappe) {
                                timeWait = timeWait - 1;
                                if (timeWait <= 0) {
                                    ennemiTirer();
                                    ennemiFrappe = false;
                                    frame.perdreVieE(26 * armeEq);
                                    pointsGagnes = pointsGagnes + 1;
                                }
                            }
                        }
                    }
                    //Joueur rebondit s'il est frappé
                    if (joueurFrappe) {
                        if (img.getLarg() != 4000) {
                            img.resetSize();
                            projectile.resetSize();
                            ennemi.resetSize();
                            base.resetSize();
                            mur.resetSize();
                            joueur.resetSize();
                            base2.resetSize();
                        }
                        //Joueur rebondit a cause du coup
                        if (joueur.getVitY() <= -(joueur.getVitYIni())) {
                            joueur.sauter(-temp / 20, -temp2);
                        }
                        if ((joueur.getVitY() >= -(joueur.getVitYIni())) && listeProjectile.isEmpty()) {
                            joueurFrappe = false;
                            joueur.estFrappe(false);
                            joueur.reset();
                            frame.perdreVieJ(26 * niv);
                        }
                    }
                    //Bouger vers la gauche les components quand le joueur tire
                    if (!listeProjectile.isEmpty() && projectile.getVitX() >= 0) {
                        img.bouger((int) ((double) projectile.getVitX() * 2.0));
                        base.bouger(img.getPosBase1());
                        base2.bouger(img.getPosBase2());
                        mur.bouger(img.getPosMur());
                        joueur.bouger(img.getPosJoueur());
                        ennemi.bouger(img.getPosEnnemi());
                        if ((img.getLarg() - (Math.abs(img.getX())) <= 800) && projectile.getX() > 800) {
                            ennemiEnVol = true;
                            joueurDejaTire = false;
                        }
                    }
                    //Bouger vers la gauche les components quand le joueur rate l'ennemi
                    if (joueurDejaTire && img.getX() <= 0 && listeProjectile.isEmpty()) {
                        img.bouger(50);
                        mur.bouger(img.getPosMur());
                        base.bouger(img.getPosBase1());
                        base2.bouger(img.getPosBase2());
                        joueur.bouger(img.getPosJoueur());
                        ennemi.bouger(img.getPosEnnemi());
                        if (img.getLarg() != 4000) {
                            img.resetSize();
                            projectile.resetSize();
                            ennemi.resetSize();
                            base.resetSize();
                            mur.resetSize();
                            joueur.resetSize();
                            base2.resetSize();
                        }
                        if (img.getLarg() - (Math.abs(img.getX())) <= 800) {
                            if (!ennemiEnVol) {
                                ennemiEnVol = true;
                                joueurDejaTire = false;
                            }
                        }
                    }
                    //Bouger vers la droite les components quand l'ennemi tire
                    if (projectileEnnemi) {
                        img.bouger2((int) ((double) vitProjectileTemp * 2.0));
                        base.bouger(img.getPosBase1());
                        mur.bouger(img.getPosMur());
                        base2.bouger(img.getPosBase2());
                        joueur.bouger(img.getPosJoueur());
                        ennemi.bouger(img.getPosEnnemi());
                        joueurDejaTire = false;
                    }
                    //Bouger vers la droite les components quand l'ennemi rate
                    if (!joueurDejaTire && !projectileEnnemi && img.getX() < 0 && listeProjectile.isEmpty() && img.getLarg() - (Math.abs(img.getX())) != 800) {
                        img.bouger2(50);
                        base.bouger(img.getPosBase1());
                        mur.bouger(img.getPosMur());
                        base2.bouger(img.getPosBase2());
                        joueur.bouger(img.getPosJoueur());
                        ennemi.bouger(img.getPosEnnemi());
                    }
                    ennemi.decalage(base2.getDecalageY());
                    joueur.decalageY(base.getDecalageY());
                    joueur.decalageX(base.getDecalageX());

                    //Réinitialiser la force et la direction du vent
                    if (img.getX() == 0 && !arme.isVisible() && listeProjectile.isEmpty()) {
                        if (img.getLarg() != 4000) {
                            img.resetSize();
                            projectile.resetSize();
                            ennemi.resetSize();
                            base.resetSize();
                            mur.resetSize();
                            joueur.resetSize();
                            base2.resetSize();
                        }
                        ventForce();
                        ventDirection();
                        frame.setVent("  Vent: " + forceVent + " (" + direction + ")");
                        if (directionVent == 1) {
                            frame.setVent("   Vent: " + forceVent + " (" + direction + ")");
                        }
                        arme.setVisible(true);
                        joueur.ajouterBras();
                        add(img);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * Mettre à jour la position de la souris pour pouvoir permettre à l'arme et au bras du joueur de suivre cette direction
     */
    public void bougerDroite() {
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                arme.updateDroite(e.getX(), e.getY());
                joueur.updateAngleBras(arme.getAngle());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                arme.updateDroite(e.getX(), e.getY());
                joueur.updateAngleBras(arme.getAngle());
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                //Joueur Tire
                if (arme.isVisible() && listeProjectile.isEmpty()) {
                    projectile = new UGameProjectileComponent(arme.getposX2() - 50, arme.getposY2() - 50, true, armeEq);
                    if (directionVent == 1) {
                        forceVent = -forceVent;
                    }
                    projectile.setVitX(arme.getposX2() - arme.getposX1(), forceVent);
                    projectile.setVitY(arme.getposY2() - arme.getposY1());
                    listeProjectile.add(projectile);
                    add(projectile);
                    add(img);
                    arme.setVisible(false);
                    joueur.enleverBras();
                    joueurDejaTire = true;
                }
            }
        });
    }

    /**
     * Initialiser la force du vent
     */
    public void ventForce() {
        forceVent = new Random().nextInt(31);
    }

    /**
     * Initialiser la direction du vent
     */
    public void ventDirection() {
        directionVent = new Random().nextInt(2);
        if (directionVent == 0) {
            direction = "gauche";
        }
        if (directionVent == 1) {
            direction = "droite";
        }
    }

    /**
     * L'ennemi tire
     */
    public void ennemiTirer() {
        projectile = new UGameProjectileComponent(ennemi.getX(), ennemi.getY() - 50, false, niv);
        int vitxx = new Random().nextInt(25) + 35;
        int vityy=0;
        //Modifier la précision de l'ennemi selon le niveau en jeu
        if (niv == 1) {
            vityy = new Random().nextInt(50) + 20;
        }
        if (niv == 2) {
            vityy = new Random().nextInt(40) + 25;
        }
        if (niv == 3) {
            vityy = new Random().nextInt(30) + 30;
        }
        if (niv == 4) {
            vityy = new Random().nextInt(25) + 35;
        }
        if (niv == 5) {
            vityy = new Random().nextInt(20) + 40;
        }
        if (niv == 6) {
            vityy = new Random().nextInt(10) + 45;
        }
        if (vitxx == 35 || vitxx == 50) {
            vitxx = 37;
        }
        projectile.setVitX(-vitxx, 0);
        projectile.setVitY(-vityy);
        vitProjectileTemp = vitxx;
        listeProjectile.add(projectile);
        add(projectile);
        add(img);
        arme.setVisible(false);
        joueur.enleverBras();
        ennemiEnVol = false;
        projectileEnnemi = true;
        ennemi.reset();
        timeWait = 40;
    }
}
