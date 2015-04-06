package BL.Model;

import java.io.Serializable;

/**
 * Class containing a player, with his name, password, money and score
 * 
 * @author YN
 */
public class Player implements Serializable, java.lang.Comparable {

	private String name;
	private String password;
	private int points;
	private int maxLevel;
	private int gameMoney = 50;
	private int gameLife = 20;

	/**
	 * default constructor for player
	 */
	public Player() {
		name = "NewGame";
		password = "";
		points = 0;
		maxLevel = 1;
	}

	/**
	 * extended constuctor for player
	 * @param name
	 *            name of the player
	 * @param password
	 *            password of the player
	 * @param points
	 *            total points
	 * @param money
	 *            money
	 * @param MaxLevel
	 *            max reached level
	 */
	public Player(String name, String password, int points, int money,
			int MaxLevel) {
		this.name = name;
		this.password = password;
		this.points = points;
		this.maxLevel = MaxLevel;
	}

	/**
	 * compares this to another player 
	 * used to determined list order for high scores
	 */
	@Override
	public int compareTo(Object otherPlayer) {
		int points1 = ((Player) otherPlayer).getPoints();
		int points2 = this.getPoints();
		if (points1 > points2) {
			return -1;
		} else if (points1 == points2) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * getter for name
	 * @return player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the maxLevel
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * @param maxLevel
	 *            the maxLevel to set
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getter for game money
	 * @return
	 */
	public int getGameMoney() {
		return gameMoney;
	}

	/**
	 * setter for game money
	 * @param gameMoney
	 */
	public void setGameMoney(int gameMoney) {
		this.gameMoney = gameMoney;
	}

	/**
	 * get game lives
	 * @return
	 */
	public int getGameLife() {
		return gameLife;
	}

	/**
	 * set game lives
	 * @param gameLife
	 */
	public void setGameLife(int gameLife) {
		this.gameLife = gameLife;
	}
}
