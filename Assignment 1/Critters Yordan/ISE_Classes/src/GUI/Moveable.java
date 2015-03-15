package GUI;

import javax.swing.JComponent;

public abstract class Moveable extends JComponent {

    /**
     * Method that defines how each component moves
     */
    public abstract void move();

    /**
     * Method that allows to determine collisions between components (for future graphical program)
     * @param other Second Moveable object
     * @return true if the two objects are in collision, false otherwise
     */
    public boolean collisionWith(Moveable other) {
        return this.getBounds().intersects(other.getBounds());
    }
}
