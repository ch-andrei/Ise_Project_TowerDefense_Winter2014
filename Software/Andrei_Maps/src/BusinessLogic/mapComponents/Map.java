package BusinessLogic.mapComponents;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import BusinessLogic.GUI.Observeable;

/**
 * logical implementation of a game time map
 * @author AndreiC
 *
 */
public class Map{

	public Random rand = new Random((int)System.currentTimeMillis());

	/**
	 * 	size of the map: length horizontal, width vertical
	 */
	private int length, width; 

	/**
	 * 	 grid representation of all tiles on the map
	 contains SceneryTiles and PathTiles
	 */
	private Tile[][] tiles;


	/**
	 * x,y coordinates for start and end of the path
	 */
	private PathTile pathEntry, pathExit;

	/**
	 * min and max allowed sizes for map
	 */
	private final int MIN_SIZE = 5, MAX_SIZE = 20; 

	/**
	 * default constructor for an instance of Map
	 * @param length
	 * @param width
	 */
	public Map(int length, int width){

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
	 * if map contains existing pathTiles prior to method call, may function incorrectly
	 * @param corners 
	 * 				queue of int[2] representing x,y coordinates of corners of the path to build
	 */
	public void buildPath(Queue<int[]> corners) throws PathException{ 

		if (corners.size() == 0){
			return;
		}

		// keep track of nodes to link: previous and current
		int[] crt = corners.remove();

		// first node must be entry of the path
		
		if (getPathEntry() == null){
			tiles[crt[0]][crt[1]] = new PathTile(crt[0],crt[1]);
			setPathEntry(crt[0],crt[1]);
			setPathExit(crt[0],crt[1]);
		}
		
		int[] prev;
		
		if (getPathExit() == null) {
			prev = new int[] {crt[0], crt[1]};
		}
		else {
			prev = new int[] {getPathExit().getX(), getPathExit().getY()};
		}

		linkPathFromTo(prev[0], prev[1], crt[0], crt[1]);
		prev = new int[] {crt[0], crt[1]};
		setPathExit(crt[0],crt[1]);

		// dequeue corner coordinates from corners queue and link path between prev and crt corners
		while (!corners.isEmpty()){
			crt = corners.remove();
			linkPathFromTo(prev[0], prev[1], crt[0], crt[1]);
			prev = new int[] {crt[0], crt[1]};
			setPathExit(crt[0],crt[1]);
		}
	}

	/**
	 * adapter for linking paths with singular corner inputs (as opposed to whole path as corners)
	 * @param x
	 * @param y
	 * @throws PathException 
	 */
	public void buildPathAdapter(int x, int y) throws PathException{
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x,y});
		buildPath(q);
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
					
					double r = gen.nextDouble();
					
					int element = 0;
					if (r > 0.7) element = 1;
					else if (r > 0.5) element = 2;
					else if (r > 0.3) element = 3;
					else if (r > 0.15) element = 4;
					else if (r > 0.05) element = 5;

					tile.setSceneryElement(element);
					
					counter++;
				}
			}
		}
		
		generateUniqueAreas((int)(density*30),seed);
	}

	/**
	 * generates and places random unique areas on the map (lakes, forests, etc)
	 * @param number total number of random areas to generate
	 */
	public void generateUniqueAreas(int number, int seed){
		int xStart, yStart;
		Random random = new Random(seed);
		int element = 0;
		int counter = 0;
		int area;
		while (counter < number){
			
			double r = random.nextDouble();
			
			if (r > 0.8) element = 1;
			else if (r > 0.6) element = 2;
			else if (r > 0.4) element = 3;
			else if (r > 0.2) element = 4;
			else if (r > 0.1) element = 5;
			
			area = random.nextInt(10)+1;
			xStart = random.nextInt(length-1)+1;
			yStart = random.nextInt(width-1)+1;
			
			for (int i = xStart-random.nextInt(area); i <= xStart+random.nextInt(area); i++){
				for (int j = yStart-random.nextInt(area); j <= yStart+random.nextInt(area); j++){
					
					if (!(i >= 0 && i < length && j >= 0 && j < width)) continue;
					
					if (tiles[i][j] instanceof SceneryTile && random.nextInt(100) > 40){
						SceneryTile tile = (SceneryTile) tiles[i][j];
						tile.setSceneryElement(element);
					}
				}
			}	
			counter++;
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

	public void applyRandomChange(){
		placeSceneryTile(rand.nextInt(getLength()), rand.nextInt(getWidth()));
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

	/**
	 * returns a generated random map. Five hardcoded paths can be randomly selected. 
	 * @return
	 */
	public static Map generateRandomMapWithPath(){
		Map map = new Map(20,20,(int)System.currentTimeMillis(),0.25); // x size, y size

		try {
			Queue<int[]> corners = new LinkedList<int[]>();

			// add corners to the queue
			// lines between corners must be strictly vertical or horizontal
			// it is assumed that such filtering will be done during gametime
			// by restricting player's input
			// otherwise, no path will be drawn on the map

			Random gen = new Random(System.currentTimeMillis());
			int random = gen.nextInt(5);

			switch (random){
			case 0:
				corners.add(new int[] {0,3});
				corners.add(new int[] {15,3});
				corners.add(new int[] {15,15});
				corners.add(new int[] {6,15});
				corners.add(new int[] {6,8});
				corners.add(new int[] {2,8});
				break;
			case 1:
				corners.add(new int[] {6,0});
				corners.add(new int[] {6,6});
				corners.add(new int[] {15,6});
				corners.add(new int[] {15,15});
				corners.add(new int[] {2,15});
				break;
			case 2:
				corners.add(new int[] {0,12});
				corners.add(new int[] {5,12});
				corners.add(new int[] {5,2});
				corners.add(new int[] {8,2});
				corners.add(new int[] {8,18});
				corners.add(new int[] {16,18});
				corners.add(new int[] {16,6});
				break;
			case 3:
				corners.add(new int[] {15,0});
				corners.add(new int[] {15,3});
				corners.add(new int[] {4,3});
				corners.add(new int[] {4,8});
				corners.add(new int[] {12,8});
				corners.add(new int[] {12,15});
				break;
			case 4:
				corners.add(new int[] {18,0});
				corners.add(new int[] {18,18});
				corners.add(new int[] {6,18});
				corners.add(new int[] {6,3});
				corners.add(new int[] {15,3});
				corners.add(new int[] {15,15});
				break;
			}

			// build path
			map.buildPath(corners);
		} catch (PathException e) {
			e.printStackTrace();
		}

		return map;
	}
}
