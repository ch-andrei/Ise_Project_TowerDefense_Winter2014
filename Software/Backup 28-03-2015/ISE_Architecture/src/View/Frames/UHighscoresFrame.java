package View.Frames;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BL.Controller.Controller;
import BL.Model.Player;
import View.Components.UHighscoresBackgroundComponent;
import View.Components.UHighscoresTextComponent;
import View.Components.UShopBackButtonComponent;

/**
 * Classe qui contient la fenetre des meilleurs scores
 * @author Eric Kavalec
 */
public class UHighscoresFrame extends JFrame {

    private JPanel pnl = new JPanel(null);
    private UHighscoresBackgroundComponent highscoresBackgroundComponent = new UHighscoresBackgroundComponent();
    private UShopBackButtonComponent highscoresRetourComponent = new UShopBackButtonComponent();
    private UHighscoresTextComponent highscoresTextComponent;

    public UHighscoresFrame() {
        super("Highscores");


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * gerer le bouton de retour a la fenetre precedante
         */
        highscoresRetourComponent.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                highscoresRetourComponent.ChangerTaille();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                highscoresRetourComponent.ChangerTaille();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.getInstance().closeHighscoresFrame();
            }
        });

        ArrayList<Player> highscores = Controller.getInstance().loadHighscores();

        highscoresTextComponent = new UHighscoresTextComponent(highscores);

        pnl.add(highscoresBackgroundComponent);
        pnl.add(highscoresRetourComponent);
        pnl.add(highscoresTextComponent);

        highscoresBackgroundComponent.setLocation(0, 0);
        highscoresRetourComponent.setLocation(630, 420);

        pnl.setComponentZOrder(highscoresRetourComponent, 0);
        pnl.setComponentZOrder(highscoresTextComponent, 0);

        pnl.setSize(800, 480);
        this.add(pnl);
        // Note: La taille de la fenêtre est différente de celle du panneau, il faut quelle soit un peu plus grande en Y
        this.setPreferredSize(new Dimension(800, 510));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * fermer la fenetre
     */
    public void FermerHighscoresFenetre() {
        this.dispose();
    }
}