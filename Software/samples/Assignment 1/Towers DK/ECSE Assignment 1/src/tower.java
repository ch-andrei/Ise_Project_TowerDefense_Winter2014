
abstract public class tower {
	
	//These are the usual tower properties, as described in the assignment document
	private int damage;
	private int level;
	private int buyCost;
	private int sellCost;
	private int upgradeCost;
	private double fireRate;
	private double range;
	private String element;
	private int[] position = new int[2];
	
	public tower(int damage, int level, int buyCost, int sellCost,
			int upgradeCost, double fireRate, double range, String element) {
		this.damage = damage;
		this.level = level;
		this.buyCost = buyCost;
		this.sellCost = sellCost;
		this.upgradeCost = upgradeCost;
		this.fireRate = fireRate;
		this.range = range;
		this.element = element;
	}
	
	//As mentioned in the system requirements from my team, the player has to be able to
	//see the properties of as selected tower
	public abstract void properties();
	
	//Upgrading towers are a necessity in the TD game
	public abstract int upgrade(int points);
	
	//Setting a tower in the map
	public void setTower(int[] position){
		this.setPosition(position);
		System.out.println("The tower is now at (x,y): " + position[0] + "," + position[1] + "\n");
	}
	
	//Buy a tower, deduct the points, and set it. The setTower method is used in the buyAndSetTower
	//This is done so that the player has to set the tower after buying it
	public int buyAndSetTower(int points, int[] position) {
		if(points < this.getBuyCost())
		{
			System.out.println("You don't have enough points to buy a tower\n");
			return points;
		}
		else
		{
			points = points - this.getBuyCost();
			System.out.println("You now have " + points + " points");
			this.setTower(position);
			this.properties();
			return points;
		}
	}
	
	//Abstract method for a tower to shoot. Every tower has its own way to calculate damage and
	//applies different effect to critter
	public abstract void shoot(int[] critterPosition, int critterHP, double critterSpeed, String critterElement, 
			int critterDefense);
	
	//Selling a tower, as specified in the assignment and system requirements made
	public int sell(int points){
		System.out.println("You now have " + (points + this.getSellCost()) + " points."
				+ "\n" + points + "->" + (points + this.getSellCost()) + "\n");
		points = points + this.getSellCost();
		this.position = new int[2];	
		return (points);
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(int buyCost) {
		this.buyCost = buyCost;
	}
	public int getSellCost() {
		return sellCost;
	}
	public void setSellCost(int sellCost) {
		this.sellCost = sellCost;
	}
	public int getUpgradeCost() {
		return upgradeCost;
	}
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	public double getFireRate() {
		return fireRate;
	}
	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}
	
	
}
