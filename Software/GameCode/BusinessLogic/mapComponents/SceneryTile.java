package BusinessLogic.mapComponents;

/**
 * Object representing a SceneryTile on a map. Extends Tile
 * @author AndreiC
 */
public class SceneryTile extends Tile{


	public enum Type{
		Water, Ground, Sand, Tree
	}

	private int sceneryElement;
	private final int DEFAULT_SCENERY_ELEMENT = 0;
	// an int will be associated with a specific texture in game and will represent a scenery element (ex: tree)

	/**
	 * basic constructor for a SceneryTile
	 * @param x
	 * @param y
	 */
	public SceneryTile(int x, int y) {
		super(x, y);
		sceneryElement = DEFAULT_SCENERY_ELEMENT;
	}

	/**
	 * constructor for SceneryTile
	 * @param x
	 * @param y
	 * @param element
	 */
	public SceneryTile(int x, int y, int element) {
		this(x, y);
		sceneryElement = element;
	}

	/**
	 * getting for SceneryElement held by runtime instance of SceneryTile
	 * @return
	 */
	public int getSceneryElement() {
		return sceneryElement;
	}

	/**
	 * setter for SceneryElement 
	 * @param sceneryElement
	 */
	public void setSceneryElement(int sceneryElement) {
		this.sceneryElement = sceneryElement;
	}

	/**
	 * returns a string representing a tile
	 */
	public String toString(){
		return "Scenery " + super.toString();
	}
}
