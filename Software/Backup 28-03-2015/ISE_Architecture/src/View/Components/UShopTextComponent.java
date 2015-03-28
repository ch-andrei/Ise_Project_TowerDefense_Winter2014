package View.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Classe qui contient le composant qui affiche les textes dans la fenetre du magasin 
 * @author Eric Kavalec 
 */
public class UShopTextComponent extends JComponent {

    private String text;
    private Color color;
    private int taillefont;

    /**
     * @param text le texte a afficher
     * @param color la couleure attribue a ce texte
     * @param taillefont la taille de ce texte
     */
    public UShopTextComponent(String text, Color color, int taillefont) {
        this.text = text;
        this.color = color;
        this.taillefont = taillefont;
        setSize(new Dimension(0, 0));
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * Dessiner les textes
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getColor());
        g.setFont(new Font("Impact", Font.BOLD, taillefont));
        g.drawString(getText(), 0, taillefont + 15);
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
