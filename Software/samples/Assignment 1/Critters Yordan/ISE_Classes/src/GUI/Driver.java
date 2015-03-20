package GUI;

import BL.Player;

public class Driver {

    private Player user;
    private CritterGroupGenerator cgg;

    /**
     * Constructor of the Driver class
     * @param user Current player
     */
    public Driver(Player user) {
        this.user = user;
    }

    public void play() {
        //Entry point on the map (x and y coordinates)
        int xEntry = 0;
        int yEntry = 0;
        //Exit point on the map (x and y coordinates)
        int xExit = 10;
        int yExit = 10;
        int level = 1;
        cgg = new CritterGroupGenerator(user, xEntry, yEntry, xExit, yExit, level);
        cgg.setCritterAttributes(10, 10, 10, 10);
        //Test the generation of a next wave
        cgg.generateNextWave();
        //Test if the hp of a critter is reduced when it is attacked
        cgg.getCritterGroup()[0].attackCritter(3);
        //Test if the user receives coins when he kills a critter
        cgg.getCritterGroup()[0].giveCoins(user);
        //Test if the user loses coins when a critter exits the map without being killed
        cgg.getCritterGroup()[0].stealCoins(user);
        //Test the reset of the critter attributes
        cgg.resetCritterAttributes();
        cgg.setCurrentLevel(0);
        cgg.setNumberOfCritters(0);
        cgg.generateNextWave();
        cgg.getCritterGroup()[0].attackCritter(3);
        cgg.getCritterGroup()[0].giveCoins(user);
        cgg.getCritterGroup()[0].stealCoins(user);
        //Test the movement of the critters group
        moveCritters();
    }

    /**
     * Thread that moves the critters inside the critter wave until they die or they exit the map
     */
    protected void moveCritters() {
        Thread threadOfGame = new Thread() {

            @Override
            public void run() {
                while (true) {
                    while (!cgg.waveDone()) {
                        //Move all units
                        cgg.moveWaveUnits();
                        try {
                            //Sleep the thread for a certain time
                            //The faster the movement speed of the critters, the less time the thread is slept
                            long sleepTime = (long) (1000 / cgg.getCritterSpeed());
                            System.out.println("Speed of movement: " + cgg.getCritterSpeed());
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException ex) {
                        }
                    }
                    if (cgg.waveDone()) {
                        //End the program once all critters have exitted the map
                        System.out.println("End of movement.");
                        System.exit(0);
                    }
                }
            }
        };
        threadOfGame.start();
    }
}
