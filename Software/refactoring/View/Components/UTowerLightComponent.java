package View.Components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * Class containing a type of tower (light tower)
 * 
 * @author YN
 */
public class UTowerLightComponent extends UTowerComponent {
	Image img = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/lightTower.png"));
	Image scaledImage = Toolkit.getDefaultToolkit().getImage(
			getClass().getClassLoader().getResource("images/lightTower.png"));

	/**
	 * calls the constructor of UTowerComponent, creates a tower and
	 * sets its tile size
	 * @param tileSize
	 * 			size, in pixel, of a single tile in the map
	 */
	public UTowerLightComponent(int tileSize) {
		super("Thunder Guard", 5, 5, (3 * tileSize), 0.5, 2, 5);
		setTileSize(tileSize);
	}

	/**
	 * draws the tower
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, this);

	}

	/**
	 * gets the tower's image
	 * @return
	 * 		returns the tower's image
	 */
	@Override
	public Image getImg() {
		return img;
	}

	/**
	 * gets the tower's ID
	 * @return
	 * 			returns the tower's ID
	 */
	@Override
	public int getTowerID() {
		return 2;
	}

	/**
	 * sets the size of the tower image depending on the size of a tile in the map
	 * @param int
	 * 			size of a tile in the map
	 */
	@Override
	public void setImgGridSize(int size) {
		if (size > 80 && size < 100) {
			scaledImage = img.getScaledInstance(90, 90, Image.SCALE_FAST);
		} else if (size > 65 && size < 78) {
			scaledImage = img.getScaledInstance(72, 72, Image.SCALE_FAST);
		} else if (size > 55 && size < 78) {
			scaledImage = img.getScaledInstance(60, 60, Image.SCALE_FAST);
		} else if (size > 45 && size < 55) {
			scaledImage = img.getScaledInstance(48, 48, Image.SCALE_FAST);
		} else if (size > 30 && size < 40) {
			scaledImage = img.getScaledInstance(36, 36, Image.SCALE_FAST);
		}
	}

	/**
	 * gets the tower's upgraded specifications
	 * @return
	 * 			returns the specifications of the upgraded tower
	 */
	@Override
	public String getUpgradeSpecs() {
		int upgradeLevel = getLevel() + 1;
		double upgradeDamage = getDamage() * 2;
		int upgradeCost = getUpgradeCost();
		double upgradeShootRadius = Math
				.round((getShootingRadius() + 0.25 * getTileSize()) * 10) / 10.0;
		double upgradeShootRate = 0;
		if (getLevel() == 2) {
			upgradeShootRate = 0.75;
		} else {
			upgradeShootRate = 0.5;
		}

		String upgradeSpecs = "\nNext Level: " + upgradeLevel
				+ "\nUpgrade Cost: " + upgradeCost + " $\nUpgraded Damage: "
				+ upgradeDamage + "\nUpgraded Shooting Radius: "
				+ upgradeShootRadius + " px\nUpgraded Shooting Rate: "
				+ upgradeShootRate + " hits/sec";

		return upgradeSpecs;
	}

	/**
	 * upgrades the tower
	 */
	@Override
	public void upgrade() {
		setDamage(getDamage() * 2);
		setSellCost((getSellCost() + getUpgradeCost() / 2));
		setUpgradeCost(getUpgradeCost() * 2);
		if (getLevel() == 3) {
			setShootingRate(0.75);
		}
		setShootingRadius(0.25);
		setLevel(getLevel() + 1);
	}
}
