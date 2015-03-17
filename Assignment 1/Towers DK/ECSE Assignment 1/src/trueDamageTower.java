
public class trueDamageTower extends tower{

	private static int initialDamage = 5;
	private static int initialLevel = 1;
	private static int buyCost = 5;
	private static int initialSellCost = buyCost/2;
	private static int initialUpgradeCost = buyCost;
	private static double initialFireRate = 1;
	private static double initialRange = 1;
	private static String element = "Neutral";

	//Call the main constructor
	public trueDamageTower(){
		super(initialDamage, initialLevel, buyCost, initialSellCost, initialUpgradeCost, 
				initialFireRate, initialRange, element);
	}

	//The shoot method for the true damage tower does not take in consideration the defense of the critter
	public void shoot(int[] critterPosition, int critterHP, double critterSpeed, String critterElement, int critterDefense) {
		double distance = Math.sqrt(Math.pow((double)(critterPosition[0]-this.getPosition()[0]),2)+Math.pow((double)(critterPosition[1]-this.getPosition()[1]),2));
		if(distance <= this.getRange())
		{
			//The damage dealt is exactly the damage of the tower, no element influence or critter defense
			critterHP = critterHP - this.getDamage();
			
			System.out.println("The True Damage Tower has inflicted " + this.getDamage() + " to the critter"
					+ "\nCritter HP: " + critterHP + "\n");
		}
		else
		{
			System.out.println("You are too far away\n The critter is at (x,y): " + critterPosition[0] + "," + critterPosition[1]
					+ "\nYou are at (x,y): " + this.getPosition()[0] + "," + this.getPosition()[1] + "\n");
		}
	}

	//Upgrade method checks the amount of points and updates the properties of the tower if possible
	public int upgrade(int points) {
		if(points < this.getUpgradeCost())
		{
			System.out.println("You require more points to upgrade the tower\n");
			return points;
		}
		else 
		{
			//Upgrade the properties of the tower
			System.out.println("You have upgraded the True Damage Tower\n");
			this.setDamage(this.getDamage() + 10);
			this.setLevel(this.getLevel()+1);
			this.setUpgradeCost(this.getUpgradeCost()*2);
			this.setSellCost(this.getSellCost()+(this.getUpgradeCost()/2));
			this.setFireRate(this.getFireRate() + 1);
			this.setRange(this.getRange() + 0.25);
			points = points - this.getUpgradeCost();
			this.properties();
			System.out.println("You now have " + points + " points\n");
			return (points);
		}
	}
	
	//Print the properties of the tower
	public void properties(){
		System.out.println("The Ice Tower has these properties\n" + "Damage: " + this.getDamage()
				+ "\nLevel: " + this.getLevel() + "\nRange: " + this.getRange() + "\nFire Rate: " + this.getFireRate()
				+ "\nUpgrade Cost: " + this.getUpgradeCost() + "\nSell Cost: " + this.getSellCost() + "\n");
	}

}
