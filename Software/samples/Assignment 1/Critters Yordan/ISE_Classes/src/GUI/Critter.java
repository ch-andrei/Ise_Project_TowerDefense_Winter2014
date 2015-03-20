package GUI;

import BL.Player;

public class Critter extends Moveable {

    //Critter Attributes
    private double reward;
    private double rewardDefault = 1;
    private double hitPoint;
    private double hitPointDefault = 5;
    private double strength;
    private double strengthDefault = 1;
    private double speed;
    private double speedDefault = 1;
    private int level;
    private int levelDefault = 1;
    private Player user;
    private int positionX;
    private int positionY;
    private int[] entry = new int[2];
    private int[] exit = new int[2];
    private boolean dead;
    private boolean hasMoved = false;

    /**
     * Default constructor of the Critter class that initializes all critter attributes to their default values
     */
    public Critter() {
        this.reward = rewardDefault;
        this.hitPoint = hitPointDefault;
        this.strength = strengthDefault;
        this.speed = speedDefault;
        this.level = levelDefault;
        this.dead = false;
    }

    /**
     * Constructor of the Critter class that allows customization of the critter attributes
     * @param reward Custom reward points of a critter
     * @param hitPoint Custom hp of a critter
     * @param strength Custom strength of a critter
     * @param speed Custom speed of movement of a critter
     * @param level Custom level of a critter
     */
    public Critter(double reward, double hitPoint, double strength, double speed, int level) {
        this.reward = reward;
        this.hitPoint = hitPoint;
        this.strength = strength;
        this.speed = speed;
        this.level = level;
        this.dead = false;
    }

    /**
     * Constructor of the Critter class that allows to create a default critter of a certain level scaling its default attributes
     * @param level Custom level of the critter
     */
    public Critter(int level) {
        this.level = level;
        this.dead = false;
        //Scale the attributes of the critter to its level
        updateBaseAttrsToLevel(level);
    }

    /**
     * Get reward points of the death of a critter
     * @return reward points per critter
     */
    public double getReward() {
        return reward;
    }

    /**
     * Set reward points of the death of a critter
     * @param reward reward points per critter
     */
    public void setReward(double reward) {
        this.reward = reward;
    }

    /**
     * Get hp of a critter
     * @return life points of the critter
     */
    public double getHitPoint() {
        return hitPoint;
    }

    /**
     * Set hp of a critter
     * @param hitPoint life points of a critter
     */
    public void setHitPoint(double hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * Get the strength points of a critter
     * @return strength points
     */
    public double getStrength() {
        return strength;
    }

    /**
     * Set the strength points of a critter
     * @param strength strength points
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * Get the speed of movement of a critter
     * @return speed of movement
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Set the speed of movement of a critter
     * @param speed speed of movement
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Get the level of a critter
     * @return level of critter
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the level of a critter
     * @param level level of a critter
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Scale the default attributes of a critter according to its level
     * @param level level of a critter
     */
    private void updateBaseAttrsToLevel(int level) {
        this.reward = rewardDefault * level;
        this.hitPoint = hitPointDefault * level;
        this.strength = strengthDefault * level;
        this.speed = speedDefault * level;
    }

    /**
     * Check if a critter is dead
     * @return true if the critter is dead, false otherwise
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set whether a critter is dead or not
     * @param dead True if the critter is dead, false otherwise
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Get current X coordinate of the critter on the map
     * @return X coordinate of the critter on the map
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Set current X coordinate of the critter on the map
     * @param positionX current X coordinate
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Get current Y coordinate of the critter on the map
     * @return Y coordinate of the critter on the map
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Set current Y coordinate of the critter on the map
     * @param positionY current Y coordinate
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Get a matrix containing the X and Y entry coordinates of the map
     * @return X and Y coordinates of the entry on the map
     */
    public int[] getEntry() {
        return entry;
    }

    /**
     * Set the X amd Y coordinates of the entry point on the map
     * @param xEntry X coordinate of the entry point
     * @param yEntry Y coordinate of the entry point
     */
    public void setEntry(int xEntry, int yEntry) {
        this.entry[0] = xEntry;
        this.entry[1] = yEntry;
    }

    /**
     * Get a matrix containing the X and Y exit coordinates of the map
     * @return X and Y coordinates of the exit on the map
     */
    public int[] getExit() {
        return exit;
    }

    /**
     * Set the X amd Y coordinates of the exit point on the map
     * @param xExit X coordinate of the exit point
     * @param yExit Y coordinate of the exit point
     */
    public void setExit(int xExit, int yExit) {
        this.exit[0] = xExit;
        this.exit[1] = yExit;
    }

    /**
     * Set the Player that has generate this critter on the map
     * @param user Player instance
     */
    public void setUser(Player user) {
        this.user = user;
    }

    /**
     * Give coins to the user when he kills a critter
     * @param user Player to whom to give coins
     */
    public void giveCoins(Player user) {
        double coins = user.getCoins() + (this.reward * this.level);
        user.setCoins(coins);
        System.out.println("A critter just died. The user won " + (this.reward * this.level) + " coins. The user now has " + user.getCoins() + " coins.");
    }

    /**
     * Steal coins from the Player when a critter exits the map alive
     * @param user Player from whom to steal coins
     */
    public void stealCoins(Player user) {
        double coins = user.getCoins() - this.strength;
        user.setCoins(coins);
        System.out.println("A critter just exitted the map. The user lost " + this.strength + " coins. The user now has " + user.getCoins() + " coins.");
    }

    /**
     * Attack a critter by a certain amount of damage
     * @param damage Amount of damage to inflict on the critter
     */
    public void attackCritter(double damage) {
        this.hitPoint = this.hitPoint - damage;
        System.out.println("A critter was attacked and lost " + damage + " hp. It now has " + this.hitPoint + " hp.");
        if (this.hitPoint <= 0) {
            this.dead = true;
            System.out.println("A critter just died.");
        }
    }

    @Override
    public void move() {
        //Assuming all tiles are part of the map (this will get solved when an actual map class is available)
        boolean scenery = false;
        if (!hasMoved) {
            //Positionning the critter on the entry point
            this.positionX = entry[0];
            this.positionY = entry[1];
            hasMoved = true;
        }
        //Move horizontally if X coordinate of the critter is before the X coordinate of the exit point
        if (this.getPositionX() < exit[0] && !scenery) {
            System.out.println("Move horizontally from x=" + positionX + " to x=" + (positionX + 1));
            this.positionX = positionX + 1;
        }
        //Move vertically if Y coordinate of the critter is before the Y coordinate of the exit point
        if (this.getPositionY() < exit[1] && !scenery) {
            System.out.println("Move vertically from y=" + positionY + " to y=" + (positionY + 1));
            this.positionY = positionY + 1;
        }
        //If the critter has exitted the map and it isn't dead, steal coins from the user
        if (!this.dead && (this.getPositionX() >= exit[0] || this.getPositionY() >= exit[1])) {
            stealCoins(this.user);
            this.dead = true;
        }
    }
}
