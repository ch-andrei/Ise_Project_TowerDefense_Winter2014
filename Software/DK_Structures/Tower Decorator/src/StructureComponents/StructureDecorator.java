package StructureComponents;

abstract class StructureDecorator implements IStructure {

	protected IStructure decoratedStructure;
	
	public StructureDecorator (IStructure structureDecorated){
		this.decoratedStructure = structureDecorated;
	}
	
	
	public void properties() {
		decoratedStructure.properties();

	}
	
	
	public void setTower(int[][] position) {
		decoratedStructure.setTower(position);
	}

	
	public int upgrade(int points) {
		return decoratedStructure.upgrade(points);
	}

	
	public int buyAndSetTower(int points, int[][] position) {
		return decoratedStructure.buyAndSetTower(points, position);
	}

	
	public boolean getDistance(int[][] critterPosition) {
		return decoratedStructure.getDistance(critterPosition);
	}

	
	public int shoot(int[][] critterPosition, int critterHP,
			double critterSpeed) {
		return decoratedStructure.shoot(critterPosition, critterHP, critterSpeed);
	}

	
	public int sell(int points) {
		return decoratedStructure.sell(points);
	}

	
	public int getDamage() {
		return decoratedStructure.getDamage();
	}
	public void setDamage(int damage) {
		decoratedStructure.setDamage(damage);;
	}
	public int getLevel() {
		return decoratedStructure.getLevel();
	}
	public void setLevel(int level) {
		decoratedStructure.setLevel(level);;
	}
	public int getBuyCost() {
		return decoratedStructure.getBuyCost();
	}
	public void setBuyCost(int buyCost) {
		decoratedStructure.setBuyCost(buyCost);
	}
	public int getSellCost() {
		return decoratedStructure.getSellCost();
	}
	public void setSellCost(int sellCost) {
		decoratedStructure.setSellCost(sellCost);;
	}
	public int getUpgradeCost() {
		return decoratedStructure.getUpgradeCost();
	}
	public void setUpgradeCost(int upgradeCost) {
		decoratedStructure.setUpgradeCost(upgradeCost);;
	}
	public double getFireRate() {
		return decoratedStructure.getFireRate();
	}
	public void setFireRate(double fireRate) {
		decoratedStructure.setFireRate(fireRate);;
	}
	public double getRange() {
		return decoratedStructure.getRange();
	}
	public void setRange(double range) {
		decoratedStructure.setRange(range);;
	}
	public int[][] getPosition() {
		return decoratedStructure.getPosition();
	}

	public void setPosition(int[][] position) {
		decoratedStructure.setPosition(position);;
	}
}
