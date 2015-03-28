package View.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Interface graphique (GUI) de l'application Numerix.
 * 
 * Ne gère que les éléments graphiques de l'application, sauf la classe FenetreRecords
 * qui se gère toute seule.
 * @author Yordan Neshev
 */
public class UGameFrameNumerix extends JFrame {

    //Grandeurs de la fenêtre
    private final static int HAUTEUR = 594;
    private final static int LARGEUR = 720;
    //Composantes du Timer
    private Timer timerFinPartie;
    private int tempsFinPartie = 90;
    //Points accumulés du joueur
    private int pointsAccumules = 0;
    //Variable permettant de détecter lorsqu'une bonne position pour les cases spéciales a été trouvée
    boolean testCouleurBleuVert = true;
    //Les composantes de la fenêtre
    private JPanel pnlJeu = new JPanel(new GridLayout(9, 7));
    private JPanel pnlTemps = new JPanel(new FlowLayout());
    private JPanel pnlPoints = new JPanel(new FlowLayout());
    private JPanel pnlSequence = new JPanel(new FlowLayout());
    private JPanel pnlAbandonner = new JPanel(new FlowLayout());
    private JPanel pnlSoumettre = new JPanel(new FlowLayout());
    private JPanel pnlAnnuler = new JPanel(new FlowLayout());
    private JPanel pnlBorder = new JPanel(new BorderLayout());
    private JPanel pnlBox = new JPanel();
    private JLabel lblTemps = new JLabel("" + tempsFinPartie);
    private JLabel lblPointage = new JLabel("" + pointsAccumules);
    private JLabel lblSequence = new JLabel("");
    private JButton btnSoumettre = new JButton("Soumettre");
    private JButton btnAbandonner = new JButton("Abandonner le jeu");
    private JButton btnAnnuler = new JButton("Annuler la séquence");
    //Tableau contenant tous les bouttons.
    private UMapTile[][] tableauBtnMagiques = new UMapTile[9][7];
    //Variables indiquant la position de la case dans la grille des boutons
    private int ligneBtn;
    private int colonneBtn;
    //Composantes du menu
    private MenuBar mnuMonMenu;
    private Menu mnuFichier;
    private Menu mnuAide;
    private MenuItem mnuNouvPartie;
    private MenuItem mnuListeRecord;
    private MenuItem mnuQuitter;
    private MenuItem mnuApropos;
    //Partie applicative de l'application qui gère la séquence et le nombre de points
    private Modele modele;
    //Fenêtre des Records
    private FenetreRecords fenetreRecords;
    //Couleur de fond de l'application
    private Color couleurFond = new Color(45, 205, 115);

    /**
     * Constructeur de l'interface de l'écran de jeu
     * @param modele Partie applicative de l'application
     */
    public UGameFrameNumerix(Modele modele) {
        try {
            this.modele = modele;
        } catch (NullPointerException e) {
        } catch (Exception e1) {
        }
        setTitle("Numerix");
        setSize(LARGEUR, HAUTEUR);
        LayoutManager layout = new BoxLayout(pnlBox, BoxLayout.Y_AXIS);
        pnlBox.setLayout(layout);

        //Etiquette du Temps
        lblTemps.setPreferredSize(new Dimension(60, 60));
        lblTemps.setHorizontalAlignment(JLabel.CENTER);
        Font f = new Font("Broadway", Font.BOLD, 39);
        lblTemps.setFont(f);
        lblTemps.setOpaque(true);
        lblTemps.setBackground(Color.RED);
        lblTemps.setForeground(Color.WHITE);
        pnlTemps.setBackground(couleurFond);
        pnlTemps.setPreferredSize(new Dimension(0, -170));
        pnlTemps.add(lblTemps);
        timerPartie();

        //Etiquette du Pointage
        lblPointage.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        lblPointage.setPreferredSize(new Dimension(100, 45));
        lblPointage.setHorizontalAlignment(JLabel.CENTER);
        Font f2 = new Font("Elephant", Font.PLAIN, 20);
        lblPointage.setFont(f2);
        lblPointage.setOpaque(true);
        lblPointage.setBackground(Color.WHITE);
        lblPointage.setForeground(Color.DARK_GRAY);
        pnlPoints.setBackground(couleurFond);
        pnlPoints.setPreferredSize(new Dimension(0, -175));
        pnlPoints.add(lblPointage);


        //Etiquette de la Sequence
        lblSequence.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        lblSequence.setPreferredSize(new Dimension(175, 35));
        lblSequence.setHorizontalAlignment(JLabel.CENTER);
        Font f3 = new Font("Times New Roman", Font.BOLD, 25);
        lblSequence.setFont(f3);
        lblSequence.setOpaque(true);
        lblSequence.setBackground(new Color(45, 115, 250));
        lblSequence.setForeground(Color.WHITE);
        pnlSequence.setBackground(couleurFond);
        pnlSequence.setPreferredSize(new Dimension(0, -195));
        pnlSequence.add(lblSequence);


        //Bouton Soumettre
        btnSoumettre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnSoumettre.setPreferredSize(new Dimension(250, 98));
        Font f4 = new Font("Goudy Stout", Font.BOLD, 17);
        btnSoumettre.setFont(f4);
        btnSoumettre.setOpaque(true);
        btnSoumettre.setBackground(new Color(45, 115, 250));
        btnSoumettre.setForeground(Color.WHITE);
        pnlSoumettre.setBackground(couleurFond);
        pnlSoumettre.setPreferredSize(new Dimension(250, -120));
        pnlSoumettre.add(btnSoumettre);

        //Bouton Annuler la séquence
        btnAnnuler.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnAnnuler.setPreferredSize(new Dimension(150, 35));
        Font f8 = new Font("Times New Roman", Font.BOLD, 15);
        btnAnnuler.setFont(f8);
        btnAnnuler.setOpaque(true);
        btnAnnuler.setBackground(Color.BLACK);
        btnAnnuler.setForeground(Color.WHITE);
        pnlAnnuler.setBackground(couleurFond);
        pnlAnnuler.setPreferredSize(new Dimension(0, -200));
        pnlAnnuler.add(btnAnnuler);


        //Bouton Abandonner
        btnAbandonner.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnAbandonner.setPreferredSize(new Dimension(150, 35));
        Font f5 = new Font("Times New Roman", Font.BOLD, 15);
        btnAbandonner.setFont(f5);
        btnAbandonner.setOpaque(true);
        btnAbandonner.setBackground(Color.BLACK);
        btnAbandonner.setForeground(Color.WHITE);
        pnlAbandonner.setBackground(couleurFond);
        pnlAbandonner.setPreferredSize(new Dimension(0, -200));
        pnlAbandonner.add(btnAbandonner);

        //Ajout des étiquettes et des boutons dans le panneau pnlBox
        pnlBox.add(pnlTemps);
        pnlBox.add(pnlPoints);
        pnlBox.add(Box.createRigidArea(new Dimension(0, 100)));
        pnlBox.add(pnlSequence);
        pnlBox.add(pnlSoumettre);
        pnlBox.add(pnlAnnuler);
        pnlBox.add(Box.createRigidArea(new Dimension(0, 70)));
        pnlBox.add(pnlAbandonner);
        pnlBox.setBackground(couleurFond);

        //Ajout du pnlBox dans le panneau principal pnlBorder
        pnlBorder.add(pnlBox, BorderLayout.EAST);
        pnlBorder.setBackground(couleurFond);
        pnlBorder.setVisible(false);
        this.add(pnlBorder);

        //Création du menu
        mnuMonMenu = new MenuBar();
        mnuFichier = new Menu("Fichier");
        mnuAide = new Menu("Aide");
        mnuNouvPartie = new MenuItem("Nouvelle partie");
        mnuListeRecord = new MenuItem("Liste des records");
        mnuQuitter = new MenuItem("Quitter");
        mnuApropos = new MenuItem("À propos");

        //Ajout des composantes du menu
        mnuFichier.add(mnuNouvPartie);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuListeRecord);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        mnuAide.add(mnuApropos);
        mnuMonMenu.add(mnuFichier);
        mnuMonMenu.add(mnuAide);
        this.setMenuBar(mnuMonMenu);

        //Appel des événements
        try {
            listeRecords();
            aPropos();
            quitterJeu();
            nouvellePartie();
            abandonnerJeu();
        } catch (NullPointerException e) {
        } catch (Exception e1) {
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Timer de la partie de jeu gérant les 90 secondes desquelles le joueur dispose.
     */
    private void timerPartie() {
        timerFinPartie = new javax.swing.Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    tempsFinPartie--;
                    lblTemps.setText("" + tempsFinPartie); //Affichage du temps

                    //Lorsque la temps atteint 0, la partie se finit
                    if (tempsFinPartie == 0) {
                        timerFinPartie.stop();

                        //Affichage des records et vérification d'un nouveau record
                        try {
                            pnlBorder.setVisible(false);
                            fenetreRecords = new FenetreRecords();
                            fenetreRecords.setVisible(false);
                            if (fenetreRecords.getResultat3() < pointsAccumules) {
                                fenetreRecords.nouveauRecord(pointsAccumules, JOptionPane.showInputDialog("Quel est votre nom?"));
                            }
                            FenetreRecords FenetreRec2 = new FenetreRecords();
                        } catch (IOException e) {
                        }
                    }
                } catch (NullPointerException e) {
                } catch (Exception e1) {
                }
            }
        });
    }

    /**
     * Événement de la composante du menu À propos affichant le nom et la date de remise
     */
    private void aPropos() {
        mnuApropos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Nom: Yordan Neshev" + "\nDate de remise: 3 décembre 2012");
            }
        });
    }

    /**
     * Événement de la composante du menu Liste des records affichant la liste des records du jeu Numerix
     */
    private void listeRecords() {
        mnuListeRecord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    fenetreRecords = new FenetreRecords();
                } catch (IOException e) {
                } catch (Exception e1) {
                }
            }
        });
    }

    /**
     * Événement de la composante du menu Quitter mettant fin à l'application
     */
    private void quitterJeu() {
        mnuQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    /**
     * Événement du bouton Abandonner le jeu mttant fin à la partie en cours
     */
    private void abandonnerJeu() {
        btnAbandonner.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                tempsFinPartie = 1;
            }
        });
    }

    /**
     * Crée une nouvelle partie
     * Réinitialise toutes les séquences et cases du panneau de jeu, ainsi que le temps et le pointage atteint
     * Événement de la composante du menu Nouvelle Partie débutant une nouvelle partie
     */
    private void nouvellePartie() {
        //Ajout du panneau de Jeu   
        for (int ligne = 0; ligne <= 8; ligne++) {
            for (int colonne = 0; colonne <= 6; colonne++) {
                try {
                    Random Aleatoire = new Random();
                    int NombreAleatoire = Aleatoire.nextInt(5) + 1;
                    UMapTile btnJeu = new UMapTile(ligne, colonne);
                    btnJeu.setBackground(Color.LIGHT_GRAY);
                    btnJeu.setText("" + NombreAleatoire);
                    btnJeu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    tableauBtnMagiques[ligne][colonne] = btnJeu;
                    choixCasesEnable(btnJeu);
                    soumettreSequence();
                    annulerCases();
                    tableauBtnMagiques[ligne][colonne].setVisible(false);
                    pnlJeu.add(btnJeu);
                } catch (NullPointerException e) {
                } catch (Exception e1) {
                }
            }
        }
        pnlJeu.setBackground(Color.DARK_GRAY);
        pnlBorder.add(pnlJeu, BorderLayout.CENTER);

        /**
         * Évenement de la composante du menu Nouvelle Partie
         * Réinitialisation de toutes les cases
         * Réinitialisation du temps, de la séquence et du pointage
         */
        mnuNouvPartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int ligne = 0; ligne <= 8; ligne++) {
                    for (int colonne = 0; colonne <= 6; colonne++) {
                        try {
                            Random rn = new Random();
                            int randomNum = rn.nextInt(5) + 1;
                            tableauBtnMagiques[ligne][colonne].setText("" + randomNum);
                            tableauBtnMagiques[ligne][colonne].setVisible(true);
                            tableauBtnMagiques[ligne][colonne].setBackground(Color.LIGHT_GRAY);
                            tableauBtnMagiques[ligne][colonne].setEnabled(true);
                            tableauBtnMagiques[ligne][colonne].setDejaclicke(false);
                        } catch (NullPointerException e) {
                        } catch (Exception e1) {
                        }
                    }
                }
                try {
                    pnlBorder.add(pnlJeu, BorderLayout.CENTER);
                    modele.viderSequence();
                    lblSequence.setText("");
                    pointsAccumules = 0;
                    lblPointage.setText("" + pointsAccumules);
                    if (tempsFinPartie != 90) {
                        tempsFinPartie = 91;
                    }
                    pnlBorder.setVisible(true);
                    timerFinPartie.start();
                } catch (NullPointerException e) {
                } catch (Exception e1) {
                }
            }
        });
    }

    /**
     * Évenement du bouton Soummetre permettant de soumettre la séquence en jeu pour obtenir des points
     */
    private void soumettreSequence() {

        btnSoumettre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //Vérification que la séquence est de 3 cases ou plus
                    if (modele.sequenceSize() >= 3) {
                        //Calcul des points
                        pointsAccumules = pointsAccumules + modele.calculPoints();
                        lblPointage.setText("" + pointsAccumules);

                        //Vérification si la séquence effectuée comporte une case verte rajoutant 5 secondes au temps de la partie
                        for (int i = 0; i < modele.sequenceSize(); i++) {
                            if (modele.afficherSequence().get(i).getBackground() == Color.GREEN) {
                                tempsFinPartie = tempsFinPartie + 5;
                            }
                        }

                        //Réinitialisation de l'étiquette affichant la séquence
                        lblSequence.setText("");

                        //Réinitialisation des cases du tableau en vue qu'elle puissent être cliqués pour former de nouvelles séquences
                        for (int ligne = 0; ligne <= 8; ligne++) {
                            for (int colonne = 0; colonne <= 6; colonne++) {
                                if (tableauBtnMagiques[ligne][colonne].isDejaclicke()) {
                                    tableauBtnMagiques[ligne][colonne].setVisible(false);
                                }
                                tableauBtnMagiques[ligne][colonne].setBackground(Color.LIGHT_GRAY);
                                tableauBtnMagiques[ligne][colonne].setEnabled(true);
                                tableauBtnMagiques[ligne][colonne].setDejaclicke(false);
                            }
                        }

                        /**
                         * Ajout de cases spéciales au cas où la séquence effectuée est de 5 cases ou plus
                         */
                        if (modele.sequenceSize() >= 5) {
                            Random aleatoire = new Random();

                            while (testCouleurBleuVert) {
                                int aleatoireLigneB = aleatoire.nextInt(9);
                                int aleatoireColonneB = aleatoire.nextInt(7);
                                int aleatoireLigneV = aleatoire.nextInt(9);
                                int aleatoireColonneV = aleatoire.nextInt(7);

                                for (int i = 0; i < modele.sequenceSize(); i++) {
                                    if (tableauBtnMagiques[aleatoireLigneB][aleatoireColonneB].isVisible() && tableauBtnMagiques[aleatoireLigneV][aleatoireColonneV].isVisible() && aleatoireLigneB != aleatoireLigneV && aleatoireColonneB != aleatoireColonneV) {
                                        tableauBtnMagiques[aleatoireLigneB][aleatoireColonneB].setBackground(Color.BLUE);
                                        tableauBtnMagiques[aleatoireLigneV][aleatoireColonneV].setBackground(Color.GREEN);
                                        testCouleurBleuVert = false;
                                    }
                                }
                            }
                        }

                        testCouleurBleuVert = true;

                        //Initialisation du timer de 10 secondes relatif à la séquence réalisée
                        TimerPersonnel tempsBtnDisparait = new TimerPersonnel();
                        tempsBtnDisparait.executeTimer(tableauBtnMagiques, modele.afficherSequence(), tempsFinPartie);
                        tempsBtnDisparait.runTimer();

                        //Réinitialisation de la séquence
                        modele.viderSequence();
                    }
                } catch (NullPointerException e) {
                } catch (ArrayIndexOutOfBoundsException e1) {
                } catch (Exception e2) {
                }
            }
        });
    }

    //Événement du bouton Anuler la séquence permettant d'annuler une séquence éronnée
    private void annulerCases() {
        btnAnnuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //Réinitialisation de la séquence et du panneau des cases
                    modele.viderSequence();
                    lblSequence.setText("");
                    for (int ligne = 0; ligne <= 8; ligne++) {
                        for (int colonne = 0; colonne <= 6; colonne++) {
                            if (tableauBtnMagiques[ligne][colonne].getBackground() != Color.BLUE && tableauBtnMagiques[ligne][colonne].getBackground() != Color.GREEN) {
                                tableauBtnMagiques[ligne][colonne].setBackground(Color.LIGHT_GRAY);
                            }
                            tableauBtnMagiques[ligne][colonne].setEnabled(true);
                            tableauBtnMagiques[ligne][colonne].setDejaclicke(false);
                        }
                    }
                } catch (NullPointerException e) {
                } catch (Exception e1) {
                }
            }
        });
    }

    /**
     * Événement gérant la validation de la case cliquée
     * Validation du choix de la case cliquée selon les conditions permises de réalisation d'une séquence
     * @param btnMagClique Case choisis
     */
    private void choixCasesEnable(UMapTile btnMagClique) {

        btnMagClique.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    UMapTile btnMagClique2 = (UMapTile) ae.getSource();

                    // Si la séquence effectuée pour le moment est de longueur 1 ou plus, plusieurs cas possibles de séquence apparaissent
                    if (modele.sequenceSize() >= 1) {
                        //Si la case choisis comporte un nombre égal au précédent de la séquence
                        if (Integer.parseInt(btnMagClique2.getText()) == Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 1).getText()) + 1) {
                            //Si la séquence effectuée jusqu'au moment est de longueur 2 ou plus
                            if (modele.sequenceSize() >= 2) {
                                if (Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 1).getText()) != Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 2).getText())) {
                                    enableCases(btnMagClique2, tableauBtnMagiques);
                                }
                            }
                            //Si la séquence effectuée jusqu'au moment est de longueur 1
                            if (modele.sequenceSize() == 1) {
                                enableCases(btnMagClique2, tableauBtnMagiques);
                            }
                        }

                        //Si la case choisis comporte un nombre plus grand de 1 par rapport au nombre précédent de la séquence
                        if (Integer.parseInt(btnMagClique2.getText()) == Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 1).getText())) {
                            //Si la séquence effectuée jusqu'au moment est de longueur 2 ou plus
                            if (modele.sequenceSize() >= 2) {
                                if (Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 1).getText()) == Integer.parseInt(modele.afficherSequence().get(modele.sequenceSize() - 2).getText())) {
                                    enableCases(btnMagClique2, tableauBtnMagiques);
                                }
                            }
                            //Si la séquence effectuée jusqu'au moment est de longueur 1
                            if (modele.sequenceSize() == 1) {
                                enableCases(btnMagClique2, tableauBtnMagiques);
                            }
                        }
                    }

                    //Si c'est la première case a étre choisis pour former une séquence, aucune condition ne s'applique
                    if (modele.sequenceSize() == 0) {
                        enableCases(btnMagClique2, tableauBtnMagiques);
                    }
                } catch (NullPointerException e) {
                } catch (Exception e1) {
                }
            }
        });
    }

    /**
     * Activation des cases positionnées horizontalement ou verticalement par rapport à la case cliquée
     * @param btnMagClique3 case choisis
     * @param tableauBout tableau contenant toutes les cases
     */
    private void enableCases(UMapTile btnMagClique3, UMapTile tableauBout[][]) {
        try {
            btnMagClique3.setDejaclicke(true);
            //Ajout de la case dans la liste de la séquence en jeu
            modele.ajoutCaseSequence(btnMagClique3);

            //Si ce n'est pas une case spéciale, on change la couleur de la case cliquée pour pouvoir visuellement la différencier en tant
            //qu'une case faisant partie de la séquence en jeu
            lblSequence.setText(lblSequence.getText() + modele.afficherSequence().get(modele.sequenceSize() - 1).getText() + " - ");
            if (btnMagClique3.getBackground() != Color.BLUE && btnMagClique3.getBackground() != Color.GREEN) {
                btnMagClique3.setBackground(couleurFond);
            }


            ligneBtn = btnMagClique3.getLigne();
            colonneBtn = btnMagClique3.getColonne();

            //En premier. désactivation de toutes les cases
            for (int ligne = 0; ligne <= 8; ligne++) {
                for (int colonne = 0; colonne <= 6; colonne++) {
                    tableauBout[ligne][colonne].setEnabled(false);
                }
            }
        } catch (NullPointerException e) {
        } catch (Exception e1) {
        }

        //En deuxième, activation de toutes les cases qui ne sont pas déja cliqués et qui peuvent être cliquées
        //au nord, au sud, à l'est et à l'ouest par rapport à la position de la case choisis.
        try {
            if (!tableauBout[ligneBtn][colonneBtn - 1].isDejaclicke()) {
                tableauBout[ligneBtn][colonneBtn - 1].setEnabled(true);
            }
            if (!tableauBout[ligneBtn][colonneBtn + 1].isDejaclicke()) {
                tableauBout[ligneBtn][colonneBtn + 1].setEnabled(true);
            }
            if (!tableauBout[ligneBtn - 1][colonneBtn].isDejaclicke()) {
                tableauBout[ligneBtn - 1][colonneBtn].setEnabled(true);
            }
            if (!tableauBout[ligneBtn + 1][colonneBtn].isDejaclicke()) {
                tableauBout[ligneBtn + 1][colonneBtn].setEnabled(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                if (!tableauBout[ligneBtn][colonneBtn + 1].isDejaclicke()) {
                    tableauBout[ligneBtn][colonneBtn + 1].setEnabled(true);
                }
                if (!tableauBout[ligneBtn - 1][colonneBtn].isDejaclicke()) {
                    tableauBout[ligneBtn - 1][colonneBtn].setEnabled(true);
                }
                if (!tableauBout[ligneBtn + 1][colonneBtn].isDejaclicke()) {
                    tableauBout[ligneBtn + 1][colonneBtn].setEnabled(true);
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                try {
                    if (!tableauBout[ligneBtn - 1][colonneBtn].isDejaclicke()) {
                        tableauBout[ligneBtn - 1][colonneBtn].setEnabled(true);
                    }
                    if (!tableauBout[ligneBtn + 1][colonneBtn].isDejaclicke()) {
                        tableauBout[ligneBtn + 1][colonneBtn].setEnabled(true);
                    }
                } catch (ArrayIndexOutOfBoundsException e3) {
                    try {
                        if (!tableauBout[ligneBtn + 1][colonneBtn].isDejaclicke()) {
                            tableauBout[ligneBtn + 1][colonneBtn].setEnabled(true);
                        }
                    } catch (ArrayIndexOutOfBoundsException e4) {
                    } catch (Exception e5) {
                    }
                }
            }
        }
    }
}
