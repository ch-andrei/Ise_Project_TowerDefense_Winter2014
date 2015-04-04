package View.Frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BL.Controller.Controller;
import View.Components.UShopBackgroundComponent;
import View.Components.UShopBackButtonComponent;
import View.Components.UShopTextComponent;

/**
 * Classe qui contient la fenetre du magasin
 * @author Eric Kavalec
 */
public class UShopFrame extends JFrame {

    private JPanel pnl = new JPanel(null);
    private UShopBackgroundComponent shopBackgroundComponent = new UShopBackgroundComponent();
    private UShopBackButtonComponent shopRetourComponent = new UShopBackButtonComponent();
    private int nombreTotal = 6;

    public UShopFrame() {
        super("Menu");

        CreerComponentsBase();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /**
         * gere le composant pour retourner a la fenetre monde
         */
        shopRetourComponent.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                shopRetourComponent.ChangerTaille();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                shopRetourComponent.ChangerTaille();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.getInstance().changeShopToInnerFrame();
            }
        });



        pnl.setSize(800, 480);
        this.add(pnl);
        // Note: La taille de la fenêtre est différente de celle du panneau, il faut quelle soit un peu plus grande en Y
        this.setPreferredSize(new Dimension(800, 510));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * pour fermer la fenetre
     */
    public void FermerShopFenetre() {
        this.dispose();
    }

    /**
     * creer la liste des texte des onglets (armes et armures)
     */
    private void CreerTextGauche() {
        ArrayList<String> text = new ArrayList<String>();
        text.add("Armes");
        text.add("Armures");
        CreerTextComponentsGauche(text);
    }

    /**
     * creer le texte pour les armes
     * un texte blanc signifie que l'arme est disponible
     * un texte gris signifie que l'arme n'est pas disponible
     * un texte jaune signifie que l'arme a deja ete achete
     * un texte mauve signifie que l'arme est equipe
     */
    private void CreerTextArmes() {
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("1.Lance");
        liste.add("2.Lance de glace (" + TrouverPrixArme(2) + "$)");
        liste.add("3.Lance de feu (" + TrouverPrixArme(3) + "$)");
        liste.add("4.Lance en sapphire (" + TrouverPrixArme(4) + "$)");
        liste.add("5.Lance en emeraude (" + TrouverPrixArme(5) + "$)");
        liste.add("6.Lance en diamant (" + TrouverPrixArme(6) + "$)");

        Color couleur;
        for (int i = 0; i < liste.size(); i++) {
            couleur = Color.WHITE;
            if (Controller.getInstance().player.getMaxLevel() < i + 1) {
                couleur = Color.GRAY;
            }
            if (Controller.getInstance().player.getArmeEquip() == i + 1) {
                couleur = Color.YELLOW;
            } else {
                for (int j = 0; j < Controller.getInstance().player.getArmesAchete().size(); j++) {
                    if (Controller.getInstance().player.getArmesAchete().get(j).equals(i + 1)) {
                        couleur = Color.MAGENTA;

                    }
                }
            }

            UShopTextComponent text = new UShopTextComponent(liste.get(i), couleur, 25);
            pnl.add(text);
            // pour diviser en deux les colonnes
            if (i < nombreTotal / 2) {
                text.setLocation(100, 200 + i * 45);
            } else {
                text.setLocation(400, 200 + (i - (nombreTotal / 2)) * 45);
            }
            text.setSize(new Dimension(320, 50));
            pnl.setComponentZOrder(text, 0);
            text.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    AcheterArme((UShopTextComponent) e.getSource());
                }
            });
        }
    }

    /**
     * creer le texte pour les armures
     * un texte blanc signifie que l'armure est disponible
     * un texte gris signifie que l'armure n'est pas disponible
     * un texte jaune signifie que l'armure a deja ete achete
     * un texte mauve signifie que l'armure est equipe
     */
    private void CreerTextArmures() {
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("1.Veste en leopard");
        liste.add("2.Armure de Glace (" + TrouverPrixArmure(2) + "$)");
        liste.add("3.Armure de Viking (" + TrouverPrixArmure(3) + "$)");
        liste.add("4.Armure de Chevalier (" + TrouverPrixArmure(4) + "$)");
        liste.add("5.Vêtements coloniaux (" + TrouverPrixArmure(5) + "$)");
        liste.add("6.Armure du futur (" + TrouverPrixArmure(6) + "$)");

        Color couleur;
        for (int i = 0; i < liste.size(); i++) {
            couleur = Color.WHITE;
            if (Controller.getInstance().player.getMaxLevel() < i + 1) {
                couleur = Color.GRAY;
            }
            if (Controller.getInstance().player.getArmureEquip() == i + 1) {
                couleur = Color.YELLOW;
            } else {

                for (int j = 0; j < Controller.getInstance().player.getArmuresAchete().size(); j++) {
                    if (Controller.getInstance().player.getArmuresAchete().get(j).equals(i + 1)) {
                        couleur = Color.MAGENTA;

                    }
                }
            }

            UShopTextComponent text = new UShopTextComponent(liste.get(i), couleur, 25);
            pnl.add(text);
            // pour diviser en deux les colonnes
            if (i < nombreTotal / 2) {
                text.setLocation(85, 200 + i * 45);
            } else {
                text.setLocation(390, 200 + (i - (nombreTotal / 2)) * 45);
            }
            text.setSize(new Dimension(340, 50));
            pnl.setComponentZOrder(text, 0);
            text.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    AcheterArmure((UShopTextComponent) e.getSource());
                }
            });
        }
    }

    /**
     * creer les textes des onglets (armes et armures)
     */
    private void CreerTextComponentsGauche(ArrayList<String> liste) {

        for (int i = 0; i < liste.size(); i++) {
            UShopTextComponent text = new UShopTextComponent(liste.get(i), Color.BLACK, 35);
            pnl.add(text);
            text.setLocation(165 + i * 160, 135);
            text.setSize(new Dimension(200, 50));
            pnl.setComponentZOrder(text, 0);
            text.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (((UShopTextComponent) (e.getSource())).getText().equals("Armes")) {
                        ResetInterface();
                        CreerTextArmes();
                    } else if (((UShopTextComponent) (e.getSource())).getText().equals("Armures")) {
                        ResetInterface();
                        CreerTextArmures();
                    }
                }
            });
        }
    }

    /**
     * reinitialise l'interface
     */
    private void ResetInterface() {

        pnl.removeAll();
        pnl.invalidate();
        pnl.repaint();
        CreerComponentsBase();
    }

    /**
     * affiche les composants de base (autre que les textes des ongles et les textes des armes et armures)
     */
    private void CreerComponentsBase() {
        UShopTextComponent shopArgentComponent = new UShopTextComponent("Argent: " + Controller.getInstance().player.getMoney() + "$", Color.white, 35);
        pnl.add(shopBackgroundComponent);
        pnl.add(shopRetourComponent);
        pnl.add(shopArgentComponent);

        shopBackgroundComponent.setLocation(0, 0);
        shopRetourComponent.setLocation(600, 10);
        shopArgentComponent.setLocation(20, 0);

        pnl.setComponentZOrder(shopRetourComponent, 0);
        pnl.setComponentZOrder(shopArgentComponent, 0);

        shopArgentComponent.setSize(600, 75);
        CreerTextGauche();
    }

    /**
     * l'utilisateur a clique sur un text d'une arme 
     * faire l'achat de l'arme si possible
     * @param composant le composant sur lequel il a clique
     */
    private void AcheterArme(UShopTextComponent composant) {
        if (composant.getColor().equals(Color.GRAY)) {
            JOptionPane.showMessageDialog(this, "Cet arme n'est pas débloqué", null, JOptionPane.PLAIN_MESSAGE);
        } else if (composant.getColor().equals(Color.YELLOW)) {
            JOptionPane.showMessageDialog(this, "Cet arme à déja été achetée", null, JOptionPane.PLAIN_MESSAGE);
        } else if (composant.getColor().equals(Color.MAGENTA)) {
            EquiperArme(composant);
        } else if (composant.getColor().equals(Color.WHITE)) {
            int prix = TrouverPrixArme(ConvertirNumero(composant));
            int argent = Controller.getInstance().player.getMoney();
            if (argent >= prix) {
                int reponse = JOptionPane.showConfirmDialog(this, "Acheter cette arme? (" + prix + "$)", null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (reponse == JOptionPane.YES_OPTION) {
                    //acheter l'arme
                    ArrayList<Integer> armes = new ArrayList<Integer>();
                    for (int i = 0; i < Controller.getInstance().player.getArmesAchete().size(); i++) {
                        for (int j = 0; j < nombreTotal; j++) {
                            if (Controller.getInstance().player.getArmesAchete().get(i).equals(j)) {
                                armes.add(j);
                            }
                        }
                    }
                    armes.add(ConvertirNumero(composant));
                    Controller.getInstance().player.setArmesAchete(armes);

                    //Soustraire le prix
                    Controller.getInstance().player.setMoney(Controller.getInstance().player.getMoney() - prix);
                    //Equiper l'arme (& reset l'interface en même temps)
                    EquiperArme(composant);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas assez d'argent pour acheter cette arme", null, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * l'utilisateur a clique sur un text d'une armure 
     * faire l'achat de l'armure si possible
     * @param composant le composant sur lequel il a clique
     */
    private void AcheterArmure(UShopTextComponent composant) {
        if (composant.getColor().equals(Color.GRAY)) {
            JOptionPane.showMessageDialog(this, "Cet armure n'est pas débloqué", null, JOptionPane.PLAIN_MESSAGE);
        } else if (composant.getColor().equals(Color.YELLOW)) {
            JOptionPane.showMessageDialog(this, "Cet armure à déja été achetée", null, JOptionPane.PLAIN_MESSAGE);
        } else if (composant.getColor().equals(Color.MAGENTA)) {
            EquiperArmure(composant);
        } else if (composant.getColor().equals(Color.WHITE)) {
            int prix = TrouverPrixArmure(ConvertirNumero(composant));
            int argent = Controller.getInstance().player.getMoney();
            if (argent >= prix) {
                int reponse = JOptionPane.showConfirmDialog(this, "Acheter cette armure? (" + prix + "$)", null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (reponse == JOptionPane.YES_OPTION) {
                    //acheter l'armure
                    ArrayList<Integer> armures = new ArrayList<Integer>();
                    for (int i = 0; i < Controller.getInstance().player.getArmuresAchete().size(); i++) {
                        for (int j = 0; j < nombreTotal; j++) {
                            if (Controller.getInstance().player.getArmuresAchete().get(i).equals(j)) {
                                armures.add(j);
                            }
                        }
                    }
                    armures.add(ConvertirNumero(composant));
                    Controller.getInstance().player.setArmuresAchete(armures);

                    //Soustraire le prix
                    Controller.getInstance().player.setMoney(Controller.getInstance().player.getMoney() - prix);
                    //Equiper l'armure (& reset l'interface en même temps)
                    EquiperArmure(composant);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vous n'avez pas assez d'argent pour acheter cette armure", null, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * un numero est attribue aux textes d'armure ou d'arme, cette methode sert a obtenir le numero d'un de ces composants
     * @param composant le composant dont on voudrait connaitre le numero correspondant
     * @return le numero qui lui est attribue
     */
    private int ConvertirNumero(UShopTextComponent composant) {
        String text = composant.getText();
        int retour = Integer.parseInt(text.substring(0, 1));
        return retour;
    }

    /**
     * trouver le prix correspondant a une arme
     * @param numeroArme le numero correspondant a cette arme
     * @return le prix de cette arme 
     */
    private int TrouverPrixArme(int numeroArme) {
        return numeroArme * 100;
    }

    /**
     * trouver le prix correspondant a une armure
     * @param numeroArmure le numero correspondant a cette armure
     * @return le prix de cette armure 
     */
    private int TrouverPrixArmure(int numeroArmure) {
        return numeroArmure * 150;
    }

    /**
     * equiper une arme
     * @composant le composant de corspondant a l'arme qu'on desire equipe
     */
    private void EquiperArme(UShopTextComponent composant) {
        Controller.getInstance().player.setArmeEquip(ConvertirNumero(composant));
        JOptionPane.showMessageDialog(this, "Arme équipée", null, JOptionPane.PLAIN_MESSAGE);
        ResetInterface();
    }

        /**
     * equiper une armure
     * @composant le composant de corspondant a l'armure qu'on desire equipe
     */
    private void EquiperArmure(UShopTextComponent composant) {
        Controller.getInstance().player.setArmureEquip(ConvertirNumero(composant));
        JOptionPane.showMessageDialog(this, "Armure équipée", null, JOptionPane.PLAIN_MESSAGE);
        ResetInterface();
    }
}