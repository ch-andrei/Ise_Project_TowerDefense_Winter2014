package BusinessLogic.mapComponents;

/**
 * Object representing a PathTile on a map. Extends Tile
 * @author AndreiC
 */
public class PathTile extends Tile{

	private PathTile nextTile;

	/**
	 * basic constructor for a PathTile
	 * @param x
	 * @param y
	 */
	public PathTile(int x, int y) {
		super(x, y);
		nextTile = null;
	}
	
	/**
	 * getter for nextTile
	 * @return
	 */
	public PathTile getNextTile() {
		return nextTile;
	}

	/**
	 * setter for nextTile
	 * @param nextTile
	 */
	public void setNextTile(PathTile nextTile) {
		this.nextTile = nextTile;
	}
	
	/**
	 * returns string representing a tile
	 */
	public String toString(){
		return "Path " + super.toString();
	}
}
