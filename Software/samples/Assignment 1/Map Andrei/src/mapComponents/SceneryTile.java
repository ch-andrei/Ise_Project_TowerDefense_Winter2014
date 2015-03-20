package mapComponents;

/**
 * Object representing a SceneryTile on a map. Extends Tile
 * @author AndreiC
 */
public class SceneryTile extends Tile{
		
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
	
	public SceneryTile(int x, int y, int element) {
		this(x, y);
		sceneryElement = element;
	}
	
	public int getSceneryElement() {
		return sceneryElement;
	}

	public void setSceneryElement(int sceneryElement) {
		this.sceneryElement = sceneryElement;
	}
	
	public String toString(){
		return "Scenery " + super.toString();
	}
}
