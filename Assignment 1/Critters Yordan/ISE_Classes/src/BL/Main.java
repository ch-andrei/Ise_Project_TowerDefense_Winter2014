package BL;

import GUI.Driver;

public class Main {

    public static void main(String[] args) {
        int initialCoins = 50;
        //Instantiate a new player
        Player user = new Player(initialCoins);
        //Instantiate a new driver class with the current player
        Driver driver = new Driver(user);
        //Run the driver class
        driver.play();
    }
}
