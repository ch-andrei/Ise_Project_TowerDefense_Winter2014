package View.Components;

import javax.swing.JComponent;

public abstract class UTowerComponent extends JComponent {

	private String towerName;
	private int hp;
	private int damage;
	private int cost;
	private int shootingRadius;

	public UTowerComponent(String name, int hp, int damage, int cost,
			int shootingRad) {
		this.towerName = name;
		this.hp = hp;
		this.damage = damage;
		this.cost = cost;
		this.shootingRadius = shootingRad;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getShootingRadius() {
		return shootingRadius;
	}

	public void setShootingRadius(int shootingRadius) {
		this.shootingRadius = shootingRadius;
	}

	public boolean isDead() {
		if (this.hp <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
