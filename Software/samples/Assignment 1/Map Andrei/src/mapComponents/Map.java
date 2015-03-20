package mapComponents;

import java.util.Queue;
import java.util.Random;

/**
 * logical implementation of a game time map
 * @author AndreiC
 *
 */
public class Map {

	private int length, width; 
	// size of the map: length horizontal, width vertical

	private Tile[][] tiles;
	// grid representation of all tiles on the map
	// contains SceneryTiles and PathTiles

	private PathTile pathEntry, pathExit;
	// x,y coordinates for start and end of the path

	private final int MIN_SIZE = 5, MAX_SIZE = 30; 
	// min and max allowed sizes for map

	/**
	 * default constructor for an instance of Map
	 * @param length
	 * @param width
	 */
	public Map(int length, int width){

		// set length and width according to inputs to the constructor
		// limit size if input size exceeds or is smaller than allowed
		this.length = (length > MAX_SIZE) ? MAX_SIZE : length;
		if (this.length < MIN_SIZE) {
			this.length = MIN_SIZE;
		}

		this.width = (width > MAX_SIZE) ? MAX_SIZE : width;
		if (this.width < MIN_SIZE) {
			this.width = MIN_SIZE;
		}

		tiles = new Tile[length][width];

		for (int i = 0; i < this.length; i++){
			for (int j = 0 ; j < this.width; j++){
				tiles[i][j] = new SceneryTile(i,j);
			}
		}
		// instantiated tiles array with default parameters 

		pathEntry = pathExit = null;
	}

	/**
	 * extended constructor with functionality: 
	 * 1. generation of random scenery elements.
	 * input seed and density for scenery elements
	 * @param length
	 * @param width
	 * @param seed
	 * @param density
	 */
	public Map(int length, int width, int seed, double density){

		this(length,width);

		generateRandomScenery(seed, density); 
		// seed is used in Random(seed).
		// density indicates amount of scenery elements on the map: 0.0-1.0, values less or more will be truncated 
	}

	/**
	 * builds path along a series of points defined as corners.
	 * each point must be reachable in a straight line from preceding point.
	 * uses helper methods: setPathEntry, linkPathFromTo.
	 * assumes map contains no existing pathTiles prior to method call.
	 * @param corners 
	 * 				queue of int[2] representing x,y coordinates of corners of the path to build
	 */
	public void buildNewPath(Queue<int[] > corners) throws PathException{ 

		if (corners.size() == 0){
			return;
		}

		// keep track of nodes to link: previous and current
		int[] crt = corners.remove();
		int[] prev = new int[] {crt[0], crt[1]};

		// first node must be entry of the path
		tiles[crt[0]][crt[1]] = new PathTile(crt[0],crt[1]);
		setPathEntry(crt[0],crt[1]);

		// dequeue corner coordinates from corners queue and link path between prev and crt corners
		while (!corners.isEmpty()){
			crt = corners.remove();
			linkPathFromTo(prev[0], prev[1], crt[0], crt[1]);
			prev = new int[] {crt[0], crt[1]};
		}

		setPathExit(crt[0],crt[1]);
	}

	/**
	 * sets path entry
	 * @param x
	 * @param y
	 */
	private void setPathEntry(int x, int y){
		pathEntry = (tiles[x][y] instanceof PathTile) ? (PathTile)tiles[x][y] : null;
	}

	/**
	 * sets path exit
	 * @param x
	 * @param y
	 */
	private void setPathExit(int x, int y){
		pathExit = (tiles[x][y] instanceof PathTile) ? (PathTile)tiles[x][y] : null;
	}

	/**
	 * builds path by placing PathTiles and linking these between start and end coordinates.
	 * requires straight horizontal or vertical line between input start and end coordinates.
	 * requires starting tile to be on path prior to method call. Does not do anything otherwise
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	private void linkPathFromTo(int startX, int startY, int endX, int endY) throws PathException {

		if (!(tiles[startX][startY] instanceof PathTile)){
			throw new PathException("Starting Tile is not a pathtile. LinkPathFromTo aborted.");
		}
		
		PathTile crtTile = (PathTile) tiles[startX][startY];

		if (startX == endX){ // path going vertically up or down

			int counter = endY - startY; 
			// difference between coordinates of start and end points

			if (counter == 0){ // if already at destination, return
				return;
			}

			else if (counter > 0){ // going up
				for (int i = 1; i <= counter; i++){
					linkPathFromToHelper(crtTile, startX, startY+i);
					crtTile = (PathTile) tiles[startX][startY+i];
				}
			} 

			else { // going down 
				for (int i =-1; i >= counter; i--){
					linkPathFromToHelper(crtTile, startX, startY+i);
					crtTile = (PathTile) tiles[startX][startY+i];
				}
			}
		}

		else if (startY == endY){ // path going horizontally left or right

			int counter = endX - startX; 
			// difference between coordinates of start and end points

			if (counter == 0){ // if already at destination, return
				return;
			} 

			else if (counter > 0){ // going right
				for (int i = 1; i <= counter; i++){
					linkPathFromToHelper(crtTile, startX+i, startY);
					crtTile = (PathTile) tiles[startX+i][startY];
				}
			} 

			else { // going left
				for (int i =-1; i >= counter; i--){
					linkPathFromToHelper(crtTile, startX+i, startY);
					crtTile = (PathTile) tiles[startX+i][startY];
				}
			}
		}

		else {
			throw new PathException("Indicated corners are not diagonal. Path linking aborted.");
		}
	}

	/**
	 * helper method for linking path. Creates new PathTile at x,y and sets it as next tile for CrtTile input.
	 * @param crtTile
	 * @param x
	 * @param y
	 * @throws PathException
	 */
	private void linkPathFromToHelper(PathTile crtTile, int x, int y) throws PathException{
		if (tiles[x][y] instanceof PathTile) 
			throw new PathException("Tile is already on path. Link path aborted.");

		tiles[x][y] = new PathTile(x, y);
		crtTile.setNextTile((PathTile)tiles[x][y]);
	}

	/**
	 * verifies if map is valid according to the default ruleset. returns 'true' if map is valid, 'false' otherwise
	 */
	public boolean verifyValidity(){

		if (pathEntry == null && pathExit == null){
			return true;
		}

		PathTile crt = (pathEntry != null) ? pathEntry : new PathTile(0, 0);

		while (crt.getNextTile() != null){
			crt = crt.getNextTile();
		}
		if (pathExit != null && crt.equals(pathExit)){
			return true;
		}

		return false;
	}

	/**
	 * returns a string representing a map. # for path, - for scenery
	 */
	public String toString(){
		String s = "";
		for (int y = width-1; y >= 0; y--){
			for (int x = 0; x < length; x++){
				if (tiles[x][y] instanceof PathTile){
					s += "-###-";
				}
				else {
					SceneryTile tile = (SceneryTile) tiles[x][y];
					s += "--" + tile.getSceneryElement() + "--";
				}
			}
			s+="\n";
		}
		return s;
	}

	/**
	 * returns string containing coordinates of all path tiles as a sequence starting from pathEntry
	 * @return
	 */
	public String pathToString(){

		String s = "";

		s += "Entry is " + pathEntry + ".\n";

		PathTile crt = pathEntry;

		while (crt != null){
			s += crt + ";\n"; 
			crt = crt.getNextTile();
		}

		s+= "Exit is " + pathExit + ".";

		return s;
	}

	/**
	 * generates random scenery elements for the map. Seed used in Random. Density between 0-1.0, adjusted otherwise.
	 * @param seed
	 * @param density
	 */
	private void generateRandomScenery(int seed, double density){
		// make sure density is appropriately chosen
		if (density < 0) density = 0;
		else if (density > 1) density = 1;

		Random gen = new Random(seed);

		int counter = 0, area = length * width, maxElements = (int) (area * density);

		for (int i = 0; i < length; i++){
			for (int j = 0; j < width; j++){
				if (tiles[i][j] instanceof SceneryTile && 
						counter < maxElements && 
						gen.nextInt(500) < density * 500){
					SceneryTile tile = (SceneryTile) tiles[i][j];
					tile.setSceneryElement(gen.nextInt(5));
					counter++;
				}
			}
		}
	}
	
	/**
	 * creates a new PathTile at coordinates x,y
	 * @param x
	 * @param y
	 */
	public void placePathTile (int x, int y){
		fixLinksForPathWhenPlacingNewTile(x,y);
		tiles[x][y] = new PathTile(x,y);
	}

	/**
	 * creates a new SceneryTile at coordinates x,y
	 * @param x
	 * @param y
	 */
	public void placeSceneryTile (int x, int y){
		fixLinksForPathWhenPlacingNewTile(x,y);
		tiles[x][y] = new SceneryTile(x,y);
	}

	/**
	 * creates a new SceneryTile at coordinates x,y and sets its sceneryElement to element
	 * @param x
	 * @param y
	 * @param element
	 */
	public void placeSceneryTileElement(int x, int y, int element){
		fixLinksForPathWhenPlacingNewTile(x,y);
		tiles[x][y] = new SceneryTile(x, y, element);
	}

	/**
	 * update path when replacing tiles (set nextPathTile to null for tile to be replaced if it is on Path)
	 * @param x
	 * @param y
	 */
	private void fixLinksForPathWhenPlacingNewTile(int x, int y){
		if (tiles[x][y] instanceof PathTile) {
			PathTile tile = (PathTile) tiles[x][y];
			tile.setNextTile(null);
		}
	}
	
	/**
	 * getter for Tile from tiles[][] at coordinates x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}

	/**
	 * getter for map length
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * setter for map length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * getter for map width
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * setter for map length
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * getter for pathEntry
	 * @return
	 */
	public PathTile getPathEntry() {
		return pathEntry;
	}

	/**
	 * setter for pathEntry
	 * @param pathEntry
	 */
	public void setPathEntry(PathTile pathEntry) {
		this.pathEntry = pathEntry;
	}

	/**
	 * getter for pathExit
	 * @return
	 */
	public PathTile getPathExit() {
		return pathExit;
	}
	
	/**
	 * setter for pathExit
	 * @param pathExit
	 */
	public void setPathExit(PathTile pathExit) {
		this.pathExit = pathExit;
	}
}
