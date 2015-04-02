package StructureComponents;

public class Slow extends StructureDecorator {

	double slow;
	
	public double getSlow(){
		return this.slow;
	}
	
	public void setSlow(double newSlow){
		this.slow = newSlow;
	}
	
	public Slow(IStructure decoratedStructure) {
		super(decoratedStructure);
		super.setUpgradeCost(super.getUpgradeCost()+2);
		this.setSlow(0.2);
	}
	
	public void properties(){
		super.properties();
		System.out.println("Slow: " + slow + "\n");
	}
	
	public int upgrade(int points){
		points = super.upgrade(points);
		if(points < super.getUpgradeCost())
		{
			return points;
		}
		else
		{
			if(this.slow == 0.75){
				setSlow(this.getSlow()+0.05);
				return points;
			}
			else
				return points;
		}
	}
	
	public int shoot(int[][] critterPosition, int critterHP, double critterSpeed){
		critterHP = super.shoot(critterPosition, critterHP, critterSpeed);
		boolean inRange = super.getDistance(critterPosition);
		if(inRange)
		{
			critterSpeed = critterSpeed*slow;
			return critterHP;
		}
		else
		{
			return critterHP;
		}
	}
	
	
}
