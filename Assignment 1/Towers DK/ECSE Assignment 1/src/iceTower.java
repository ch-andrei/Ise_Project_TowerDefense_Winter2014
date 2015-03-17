
public class iceTower extends tower {
	
	private static int initialDamage = 5;
	private static int initialLevel = 1;
	private static int buyCost = 5;
	private static int initialSellCost = buyCost/2;
	private static int initialUpgradeCost = buyCost;
	private static double initialFireRate = 1;
	private static double initialRange = 1;
	private double slow = 20;
	private static String element = "Ice";
	
	//Ice Tower has a slow effect, so we call the construction in "tower" and assign
	//slow to the initial slow
	public iceTower(){
		super(initialDamage, initialLevel, buyCost, initialSellCost, initialUpgradeCost, 
				initialFireRate, initialRange, element);
		this.slow = slow;
	}
	
	public double getSlow() {
		return slow;
	}
	public void setSlow(double slow) {
		this.slow = slow;
	}

	//The shoot method takes in consideration of the critter's element + defense and slows them
	public void shoot(int[] critterPosition, int critterHP, double critterSpeed, String critterElement, int critterDefense) {
		double distance = Math.sqrt(Math.pow((double)(critterPosition[0]-this.getPosition()[0]),2)+Math.pow((double)(critterPosition[1]-this.getPosition()[1]),2));
		int damageDealt;
		if(distance <= this.getRange())
		{
			//Slows down the critter
			critterSpeed = critterSpeed/this.getSlow()*100;
			
			//If the critter is of thunder element, inflict twice the damage
			if(critterElement.equals("Thunder"))
			{
				damageDealt = this.getDamage()*2 - critterDefense;
			}
			//If the critter is of fire element, inflict half of the damage
			else if(critterElement.equals("Fire"))
			{
				damageDealt = this.getDamage()/2 - critterDefense;
			}
			//Else just deal the normal damage
			else
			{
				damageDealt = this.getDamage() - critterDefense;
			}
			
			critterHP = critterHP - damageDealt;
			
			System.out.println("The Ice Tower has inflicted " + damageDealt + " to the critter and has slowed it down by "
					+ slow + "%\nCritter HP: " + critterHP + "|| Critter Speed: " + critterSpeed + "\n");
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
			//Upgrade the properties of the tower, for the Ice Tower, upgrade the slow factor
			System.out.println("You have upgraded the Ice Tower\n");
			this.setDamage(this.getDamage() + 5);
			this.setLevel(this.getLevel()+1);
			this.setSellCost(this.getSellCost()+this.getUpgradeCost()/2);
			this.setUpgradeCost(this.getUpgradeCost()*2);
			this.setFireRate(this.getFireRate() + 1);
			this.setRange(this.getRange() + 0.25);
			this.setSlow(this.getSlow()*1.25);
			points = points - this.getUpgradeCost();
			this.properties();
			System.out.println("You now have " + points + " points\n");
			return (points);
		}
	}
	
	//Print the properties of the tower, for the Ice Tower, print the slow factor
	public void properties(){
		System.out.println("The Ice Tower has these properties\n" + "Damage: " + this.getDamage()
				+ "\nLevel: " + this.getLevel() + "\nRange: " + this.getRange() + "\nFire Rate: " + this.getFireRate() 
				+ "\nSlow: " + this.getSlow() + "%" + "\nUpgrade Cost: " + this.getUpgradeCost() 
				+ "\nSell Cost: " + this.getSellCost() + "\n");
	}

}
