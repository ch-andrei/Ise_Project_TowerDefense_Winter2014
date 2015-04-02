package StructureComponents;

public class DamageBoost extends StructureDecorator{
	
	public DamageBoost(IStructure structureDecorated) {
		super(structureDecorated);
		super.setDamage(super.getDamage()+5);
	}
	
	public int upgrade(int points){
		points = super.upgrade(points);
		if(points < super.getUpgradeCost())
		{
			return points;
		}
		else
		{
			super.setDamage(super.getDamage()+5);
			return points;
		}
	}

}
