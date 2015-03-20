package BL;

public class Player {
    //Coins that the player owns

    private double coins;

    /**
     * Constructor of the Player class that takes into account the initial amount of coins the player has
     * @param coins number of coins the player has
     */
    public Player(double coins) {
        this.coins = coins;
    }

    /**
     * Get the number of coins the player has
     * @return number of coins the player has
     */
    public double getCoins() {
        return coins;
    }

    /**
     * Set the number of coins the player has to a certain value
     * @param coins new number of coins
     */
    public void setCoins(double coins) {
        this.coins = coins;
    }
}
