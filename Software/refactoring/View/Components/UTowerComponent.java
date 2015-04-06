package View.Components;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JComponent;
/**
 * Abstract class that all different towers extends to 
 * have different properties
 * 
 * @author YN
 */
public abstract class UTowerComponent extends JComponent {

	private String towerName;
	private double damage;
	private int cost;
	private int sellCost;
	private int upgradeCost;
	private int level;
	private double shootingRadius;
	private double shootingRate;
	private int index;
	private int tileSize;
	private boolean onMap = false;
	private boolean alreadyShot = false;
	private long lastTimeShoot = 0;

	public UTowerComponent() {
	}

	/**
	 * Constructor of a tower, used by the different types of towers
	 * @param name
	 * 			name of the tower
	 * @param damage
	 * 			damage dealt by the tower
	 * @param cost
	 * 			buying cost of the tower
	 * @param shootingRad
	 * 			shooting radius/range of the tower
	 * @param shootingRate
	 * 			shooting rate of the tower
	 * @param sellCost
	 * 			sell cost of the tower
	 * @param upgradeCost
	 * 			upgrade cost of the tower
	 */
	public UTowerComponent(String name, double damage, int cost,
			double shootingRad, double shootingRate, int sellCost,
			int upgradeCost) {
		setSize(new Dimension(100, 100));
		setVisible(true);
		this.towerName = name;
		this.damage = damage;
		this.cost = cost;
		this.shootingRadius = shootingRad;
		this.shootingRate = shootingRate;
		this.sellCost = sellCost;
		this.upgradeCost = upgradeCost;
		this.level = 1;
	}

	/**
	 * gets the tower's image
	 * @return
	 * 			returns the tower's image
	 */
	public abstract Image getImg();

	/**
	 * gets the tower's ID
	 * @return
	 * 			returns the tower's ID
	 */
	public abstract int getTowerID();

	/**
	 * method to resize the tower's image depending on the size of a tile
	 * @param size
	 * 			tile size, which changes depending on the size of the map
	 */
	public abstract void setImgGridSize(int size);

	/**
	 * upgrades the values of the tower
	 */
	public abstract void upgrade();

	/**
	 * gets the tower's upgrade specifications
	 * @return
	 * 			returns the tower's upgrade specifications
	 */
	public abstract String getUpgradeSpecs();

	/**
	 * determines if the critter is in range or not
	 * @param x
	 * 			the x position of the critter
	 * @param y
	 * 			the y position of the critter
	 * @return
	 * 			returns true if the critter is in range, else it returns false
	 */
	public boolean critterInRange(int x, int y) {
		if (x < 0 || x > 720 || y < 0 || y > 720) {
			return false;
		}
		int towerX = getX();
		int towerY = getY();
		int xDif = Math.abs(towerX - x);
		int yDif = Math.abs(towerY - y);
		double range = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));

		if (range <= shootingRadius && ableToShoot()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * determines if the tower can shoot, it depends on its shooting rate
	 * @return
	 * 			returns true if it can shoot, else it returns false
	 */
	private boolean ableToShoot() {
		if (!alreadyShot) {
			alreadyShot = true;
			lastTimeShoot = System.currentTimeMillis();
			return true;
		} else {
			if (lastTimeShoot + (1000.0 / shootingRate) < System
					.currentTimeMillis()) {
				lastTimeShoot = System.currentTimeMillis();
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * gets the tower's name
	 * @return
	 * 			returns the tower's name
	 */
	public String getTowerName() {
		return towerName;
	}

	/**
	 * sets the tower's name
	 * @param towerName
	 * 			the tower's name to set
	 */
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	/**
	 * gets the tower's damage
	 * @return
	 * 			returns the towers damage
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * sets the tower's damage
	 * @param damage
	 * 			the tower's damage to set
	 */
	public void setDamage(double damage) {
		this.damage = damage;
	}

	/**
	 * gets the tower's buy cost
	 * @return
	 * 			returns the tower's buy cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * sets the tower's cost
	 * @param cost
	 * 			the tower's cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * gets the tower's shooting radius
	 * @return
	 * 			returns the tower's shooting radius
	 */
	public double getShootingRadius() {
		return shootingRadius;
	}

	/**
	 * sets the tower's shooting radius
	 * @param ratio
	 * 			ratio of a tile by which the tower's range gets added at each upgrade
	 */
	public void setShootingRadius(double ratio) {
		this.shootingRadius = shootingRadius + (ratio * tileSize);
	}

	/**
	 * gets the tower's shooting rate
	 * @return
	 * 			returns the tower's shooting rate
	 */
	public double getShootingRate() {
		return shootingRate;
	}

	/**
	 * sets the tower's shooting rate
	 * @param shootingRate
	 * 			the tower's shooting rate to set
	 */
	public void setShootingRate(double shootingRate) {
		this.shootingRate = shootingRate;
	}

	/**
	 * determines if the tower is set on the map
	 * @return
	 * 			returns true if the tower is on the map, else it returns false
	 */
	public boolean isOnMap() {
		return onMap;
	}
	
	/**
	 * sets the tower's boolean value of if it is on the map or not
	 * @param onMap
	 * 			this boolean is true if the tower is on the map, false if not
	 */
	public void setOnMap(boolean onMap) {
		this.onMap = onMap;
	}

	/**
	 * sets the tower's sell cost
	 * @param sellC
	 * 			the tower's sell cost to set
	 */
	public void setSellCost(int sellC) {
		this.sellCost = sellC;
	}

	/**
	 * gets the tower's sell cost
	 * @return
	 * 			returns the tower's sell cost
	 */
	public int getSellCost() {
		return sellCost;
	}

	/**
	 * gets the tower's index in the tower list
	 * @return
	 * 			returns the tower's index in the tower list
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * sets the tower's index in the tower list
	 * @param index
	 * 			the tower's index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * gets the tower's upgrade cost
	 * @return
	 * 			returns the tower's upgrade cost
	 */
	public int getUpgradeCost() {
		return upgradeCost;
	}

	/**
	 * sets the tower's upgrade cost
	 * @param upgradeCost
	 * 			the tower's upgrade cost to set
	 */
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	/**
	 * gets the size of a tile
	 * @return
	 * 			returns the size of a tile
	 */
	public int getTileSize() {
		return tileSize;
	}

	/**
	 * sets the size of a tile
	 * @param tileSize
	 * 			the tile's size
	 */
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * gets the tower's level
	 * @return
	 * 			returns the tower's level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * sets the tower's level
	 * @param level
	 * 			the tower's level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
