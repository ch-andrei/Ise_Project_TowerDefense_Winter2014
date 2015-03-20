public class driver {

	public static void main(String[] args){

		int critterHP;
		int critterDefense;
		int[] critterPosition = new int[2];
		double critterSpeed;
		String critterElement;
		int totalPoints;

		int[] towerPosition = new int[2];

		//Initialize the three kinds of tower
		iceTower iTower = new iceTower();
		fireTower fTower = new fireTower();
		trueDamageTower tdTower = new trueDamageTower();

		//These are the properties of a critter with its position
		critterHP = 10;
		critterDefense = 2;
		critterPosition[0] = 1;
		critterPosition[1] = 2;
		critterSpeed = 5;
		critterElement = "Thunder";

		totalPoints = 100;

		towerPosition[0] = 1;
		towerPosition[1] = 2;

		//The total points should get updated by deducting the buy cost
		totalPoints = iTower.buyAndSetTower(totalPoints, towerPosition);
		if(totalPoints == (100 - iTower.getBuyCost()))
		{
			System.out.println("--------------------------\nCASE 1: Pass, points are updated after buy\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 1: Fail, points were not updated after buy\n--------------------------\n");
		}
		if(towerPosition[0] == iTower.getPosition()[0] && towerPosition[1] == iTower.getPosition()[1])
		{
			System.out.println("--------------------------\nCASE 2: Pass, tower position is correct\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 2: Fail, tower position is incorrect\n--------------------------\n");
		}

		//The properties of the tower and the status of the critter should get printed
		System.out.println("--------------------------\nThe tower should be able to hit the critter\n");
		iTower.shoot(critterPosition, critterHP, critterSpeed, critterElement, critterDefense);
		System.out.print("--------------------------\n");
		
		critterPosition[0] = 10;
		critterPosition[1] = 10;
		
		//The tower should NOT be able to hit this critter
		System.out.println("--------------------------\nThe tower should NOT be able to hit the critter\n");
		iTower.shoot(critterPosition, critterHP, critterSpeed, critterElement, critterDefense);
		System.out.print("--------------------------\n");
		
		//The properties of the tower should get printed
		iTower.properties();

		//The total points should get updated by deducting the upgrade cost
		totalPoints = iTower.upgrade(totalPoints);
		if(totalPoints == (100 - iTower.getBuyCost() - iTower.getUpgradeCost()))
		{
			System.out.println("--------------------------\nCASE 3: Pass, points are updated after upgrade\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 3: Fail, points were not updated after upgrade\n--------------------------\n");
		}
		//The properties of the tower should get updated after the upgrade
		if(iTower.getDamage() == 10 && iTower.getLevel() == 2 && iTower.getUpgradeCost() == 10
				&& iTower.getSellCost() == (5/2+5/2) && iTower.getRange() == (1.25) && iTower.getSlow() == (25.00))
		{
			System.out.println("--------------------------\nCASE 4: Pass, tower properties are updated after upgrade\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 4: Fail, tower properties were not updated after upgrade\n--------------------------\n");
		}

		//Do test cases for failing to buy or upgrade towers
		totalPoints = 0;
		//The total points should NOT get updated by deducting the buy cost
		totalPoints = fTower.buyAndSetTower(totalPoints, towerPosition);
		if(totalPoints == 0)
		{
			System.out.println("--------------------------\nCASE 5: Pass, points are not updated after buyy\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 5: Fail, points were updated after buy\n--------------------------\n");
		}
		if(towerPosition[0] != fTower.getPosition()[0] && towerPosition[1] != fTower.getPosition()[1])
		{
			System.out.println("--------------------------\nCASE 6: Pass, tower position has not been updated\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 6: Fail, tower position has been updated\n--------------------------\n");
		}

		//The total points should NOT get updated by deducting the upgrade cost
		totalPoints = iTower.upgrade(totalPoints);
		if(totalPoints == 0)
		{
			System.out.println("--------------------------\nCASE 7: Pass, points were not updated after upgrade\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 7: Fail, points were updated after upgrade\n--------------------------\n");
		}
		//The properties of the tower should NOT get updated after the upgrade
		if(iTower.getDamage() == 10 && iTower.getLevel() == 2 && iTower.getUpgradeCost() == 10
				&& iTower.getSellCost() == (5/2+5/2) && iTower.getRange() == (1.25) && iTower.getSlow() == (25.00))
		{
			System.out.println("--------------------------\nCASE 8: Pass, tower properties were not updated after upgrade\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 8: Fail, tower properties were updated after upgrade\n--------------------------\n");
		}

		//The total points should get updated by adding the sell cost
		int expectedPoints = totalPoints + iTower.getSellCost();
		totalPoints = iTower.sell(totalPoints);
		if(totalPoints == expectedPoints)
		{
			System.out.println("--------------------------\nCASE 9: Pass, points are updated after sale\n--------------------------\n");
		}
		else
		{
			System.out.println("--------------------------\nCASE 9: Fail, points were not updated after sale\n--------------------------\n");
		}
	}
}
