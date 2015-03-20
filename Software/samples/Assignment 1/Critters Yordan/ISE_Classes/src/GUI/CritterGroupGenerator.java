package GUI;

import BL.Player;

public class CritterGroupGenerator {

    //Critter Group Generator Attributes
    private Critter[] critterGroup;
    private int currentLevel;
    private int numberOfCritters;
    private boolean customAttributes = false;
    private double rewardCustom;
    private double hitPointCustom;
    private double strengthCustom;
    private double speedCustom;
    private int[] entry = new int[2];
    private int[] exit = new int[2];
    private Player user;

    /**
     * Default constructor of the Critter Group Generator that takes only the user instance, the entry and exit points on the map
     * @param user Current player
     * @param entryX X coordinate of the entry point on the map
     * @param entryY Y coordinate of the entry point on the map
     * @param exitX X coordinate of the exit point on the map
     * @param exitY Y coordinate of the exit point on the map
     */
    public CritterGroupGenerator(Player user, int entryX, int entryY, int exitX, int exitY) {
        entry[0] = entryX;
        entry[1] = entryY;
        exit[0] = exitX;
        exit[1] = exitY;
        //Starting at level 0 with 0 critter currently available
        this.currentLevel = 0;
        this.numberOfCritters = 0;
        this.user = user;
    }

    /**
     * Constructor of the Critter Group Generator that also takes into account the desired start level of the critters (other than the default one of zero)
     * @param user Current player
     * @param entryX X coordinate of the entry point on the map
     * @param entryY Y coordinate of the entry point on the map
     * @param exitX X coordinate of the exit point on the map
     * @param exitY Y coordinate of the exit point on the map
     * @param level Level at which to initiate the critters
     */
    public CritterGroupGenerator(Player user, int entryX, int entryY, int exitX, int exitY, int level) {
        entry[0] = entryX;
        entry[1] = entryY;
        exit[0] = exitX;
        exit[1] = exitY;
        this.currentLevel = level - 1;
        this.numberOfCritters = level - 1;
        this.user = user;
    }

    /**
     * Customize the attributes of the critters contained inside the critter group (other than default attributes)
     * @param reward Reward points per critter death
     * @param hitPoint Life points of a critter
     * @param strength Strength points of a critter 
     * @param speed Speed of movement of a critter
     */
    public void setCritterAttributes(double reward, double hitPoint, double strength, double speed) {
        this.rewardCustom = reward;
        this.hitPointCustom = hitPoint;
        this.strengthCustom = strength;
        this.speedCustom = speed;
        customAttributes = true;
    }

    /**
     * Reset critter attributes to default values
     */
    public void resetCritterAttributes() {
        customAttributes = false;
    }

    /**
     * Generate the next wave of critter taking into account the current level and number of critters
     */
    public void generateNextWave() {
        //Increment current level by 1
        this.currentLevel = currentLevel + 1;
        //Increment the number of critters to be sent by 1
        this.numberOfCritters = this.numberOfCritters + 1;
        //Create a new empty list of the size of the number of critters
        critterGroup = new Critter[numberOfCritters];
        //Populate the list of critters
        for (int i = 0; i < numberOfCritters; i++) {
            if (customAttributes) {
                //Create critters with custom attributes
                critterGroup[i] = new Critter(rewardCustom, hitPointCustom, strengthCustom, speedCustom, currentLevel);
            } else {
                //Create critters with default attributes
                critterGroup[i] = new Critter(currentLevel);
            }
            //Set the entry point on the map
            critterGroup[i].setEntry(entry[0], entry[1]);
            //Set the exit point on the map
            critterGroup[i].setExit(exit[0], exit[1]);
            //Set the current player
            critterGroup[i].setUser(this.user);
        }
        System.out.println("Generated " + numberOfCritters + " critters at entry point: [" + entry[0] + "," + entry[1] + "].");
    }

    /**
     * Move all units inside the critter group
     */
    public void moveWaveUnits() {
        for (Critter c : critterGroup) {
            c.move();
        }
    }

    /**
     * Return an instance of the critter group list
     * @return critter group list
     */
    public Critter[] getCritterGroup() {
        return critterGroup;
    }

    /**
     * Get current level of the wave
     * @return level of the wave
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Set the current level of the wave
     * @param currentLevel current level of the wave
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Get the number of critters curenntly generated
     * @return number of critters generated
     */
    public int getNumberOfCritters() {
        return numberOfCritters;
    }

    /**
     * Set the number of critters to be generated
     * @param numberOfCritters number of critters
     */
    public void setNumberOfCritters(int numberOfCritters) {
        this.numberOfCritters = numberOfCritters;
    }

    /**
     * Check whether all units inside the critter group are either dead or have finished moving
     * @return True if the wave is done, false otherwise
     */
    public boolean waveDone() {
        boolean waveDone = true;

        for (Critter c : critterGroup) {
            if (!c.isDead()) {
                waveDone = false;
            }
        }

        return waveDone;
    }

    /**
     * Return the speed at which the critters are moving
     * @return movement speed of the critters
     */
    public double getCritterSpeed() {
        double speed = 0;
        if (critterGroup.length > 0) {
            speed = critterGroup[0].getSpeed();
        } else {
            System.out.println("Please first generate a wave of critters.");
        }
        return speed;
    }
}
