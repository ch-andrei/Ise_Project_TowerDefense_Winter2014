package StructureComponents;

public class Freeze extends StructureDecorator {
	
	double effectChance = 20;
	
	public Freeze(IStructure decoratedStructure) {
		super(decoratedStructure);
		super.setUpgradeCost(super.getUpgradeCost()+3);
	}
	
	public int shoot(int[][] critterPosition, int critterHP, double critterSpeed){
		critterHP = super.shoot(critterPosition, critterHP, critterSpeed);
		boolean inRange = super.getDistance(critterPosition);
		if(inRange && (Math.random()*100 < effectChance))
		{
			critterSpeed = 0;
			return critterHP;
		}
		else
		{
			return critterHP;
		}
	}
	
	
}
