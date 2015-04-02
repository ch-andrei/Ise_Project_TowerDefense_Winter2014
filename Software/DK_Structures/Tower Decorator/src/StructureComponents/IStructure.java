package StructureComponents;

public interface IStructure {
	public void properties();
	public void setTower(int[][] position);
	public int upgrade(int points);
	public int buyAndSetTower(int points, int[][] position);
	public boolean getDistance(int[][] critterPosition);
	public int shoot(int[][] critterPosition, int critterHP, double critterSpeed);
	public int sell(int points);
	public int getDamage();
	public void setDamage(int damage);
	public int getLevel();
	public void setLevel(int level);
	public int getBuyCost();
	public void setBuyCost(int buyCost);
	public int getSellCost();
	public void setSellCost(int sellCost);
	public int getUpgradeCost();
	public void setUpgradeCost(int upgradeCost);
	public double getFireRate();
	public void setFireRate(double fireRate);
	public double getRange();
	public void setRange(double range);
	public int[][] getPosition();
	public void setPosition(int[][] position);
}
