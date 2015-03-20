package mapComponents;

/**
 * abstract class representing a tile to be part of a Map
 * @author AndreiC
 */
public abstract class Tile {
	private int x, y;
	
	/**
	 * basic constructor for a tile
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * custom equals method. Returns true of this tile and input tile have equal x and y parameters
	 * @param tile
	 * @return
	 */
	public boolean equals(Tile tile){
		if (tile.getX() == this.x && tile.getY()==this.y){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "" + x + ", " + y;
	}
}
