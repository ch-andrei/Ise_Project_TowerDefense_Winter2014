package StructureComponents;

public abstract class SimpleStructure implements IStructure{

	//These are the usual tower properties, as described in the assignment document
	private int damage = 10;
	private int level = 1;
	private int buyCost = 5;
	private int sellCost = buyCost/2;
	private int upgradeCost = buyCost;
	private double fireRate = 1;
	private double range = 1;
	private int[][] position;
	
	public SimpleStructure(int damage, int level, int buyCost, int sellCost,
			int upgradeCost, double fireRate, double range) {
		this.damage = damage;
		this.level = level;
		this.buyCost = buyCost;
		this.sellCost = sellCost;
		this.upgradeCost = upgradeCost;
		this.fireRate = fireRate;
		this.range = range;
	}
	
	//As mentioned in the system requirements from my team, the player has to be able to
	//see the properties of as selected tower
	public void properties(){
		System.out.println("The Tower has these properties\n" + "Damage: " + this.getDamage()
				+ "\nLevel: " + this.getLevel() + "\nRange: " + this.getRange() + "\nFire Rate: " + this.getFireRate()
				+ "\nUpgrade Cost: " + this.getUpgradeCost() + "\nSell Cost: " + this.getSellCost() + "\n");
	}
	
	//Upgrading towers are a necessity in the TD game
	public int upgrade(int points) {
		if(points < this.getUpgradeCost())
		{
			System.out.println("You require more points to upgrade the tower\n");
			return points;
		}
		else 
		{
			//Upgrade the properties of the tower
			System.out.println("You have upgraded the Tower\n");
			this.setDamage(this.getDamage() + 5);
			this.setLevel(this.getLevel()+1);
			this.setUpgradeCost(this.getUpgradeCost()*2);
			this.setSellCost(this.getSellCost()+(this.getUpgradeCost()/2));
			this.setFireRate(this.getFireRate() + 1.5);
			this.setRange(this.getRange() + 0.25);
			points = points - this.getUpgradeCost();
			return (points);
		}
	}
	//Setting a tower in the map
	public void setTower(int[][] position){
		this.setPosition(position);
		System.out.println("The tower is now at (x,y): " + position[0] + "," + position[1] + "\n");
	}
	
	//Buy a tower, deduct the points, and set it. The setTower method is used in the buyAndSetTower
	//This is done so that the player has to set the tower after buying it
	public int buyAndSetTower(int points, int[][] position) {
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
	
	public boolean getDistance(int[][] critterPosition){
		double distance = Math.sqrt(Math.pow((double)(critterPosition[0][1]-this.getPosition()[0][1]),2)+Math.pow((double)(critterPosition[1][0]-this.getPosition()[1][0]),2));
		if(distance <= this.getRange())
			return true;
		else
			return false;
	}
	
	//Abstract method for a tower to shoot. Every tower has its own way to calculate damage and
	//applies different effect to critter
	public int shoot(int[][] critterPosition, int critterHP, double critterSpeed) {
		boolean inRange = getDistance(critterPosition);
		if(inRange)
		{
			//The damage dealt is exactly the damage of the tower
			critterHP = critterHP - this.getDamage();
			
			System.out.println("The True Damage Tower has inflicted " + this.getDamage() + " to the critter"
					+ "\nCritter HP: " + critterHP + "\n");
			return critterHP;
		}
		else
		{
			System.out.println("You are too far away\n The critter is at (x,y): " + critterPosition[0] + "," + critterPosition[1]
					+ "\nYou are at (x,y): " + this.getPosition()[0] + "," + this.getPosition()[1] + "\n");
			return critterHP;
		}
	}
	
	//Selling a tower, as specified in the assignment and system requirements made
	public int sell(int points){
		System.out.println("You now have " + (points + this.getSellCost()) + " points."
				+ "\n" + points + "->" + (points + this.getSellCost()) + "\n");
		points = points + this.getSellCost();
		this.position = new int[1][1];	
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
	public int[][] getPosition() {
		return position;
	}

	public void setPosition(int[][] position) {
		this.position = position;
	}
	
	
	
}
