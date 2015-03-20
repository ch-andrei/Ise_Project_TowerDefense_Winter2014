import java.util.LinkedList;
import java.util.Queue;

import mapComponents.*;

public class Driver {
	public static void main (String[] args){

		System.out.println("The map is represented as follows:\n"
				+ "Path is denoted by #.\n"
				+ "Scenery elements are denoted by a number.\n"
				+ "Each number will be later matched to a texture.\n"
				+ "Seed can be specified for the map and a specific map can be regenerated.\n");

		testRandomEmptyMap();
		// generates a random map, prints it and checks if it is valid
		
		// if you want to change path parameters, you can do so in the last method of this class
		testRandomMapWithPath();
		// generates a random map with good path, prints it and checks if it is valid
		
		testRandomMapWithBrokenPath();
		// generates a random map with bad path, prints it and checks if it is valid
	}


	public static void testRandomEmptyMap(){
		Map map = new Map(20,20,(int)System.currentTimeMillis(),0.25); // x size, y size, seed, density of scenery elements
		System.out.println("***********************************************************************");
		System.out.println("Empty Map:\n" + map + "\n");
		System.out.println("Path:\n" + map.pathToString() + "\n");
		System.out.println("Map is valid: " + map.verifyValidity() + ".\n");
	}

	public static void testRandomMapWithPath(){
		Map map = generateRandomMapWithPath();
		System.out.println("***********************************************************************");
		System.out.println("Map with path:\n" + map + "\n");
		System.out.println("Path:\n" + map.pathToString() + "\n");
		System.out.println("Map is valid: " + map.verifyValidity() + ".\n");
	}

	public static void testRandomMapWithBrokenPath(){
		Map map = generateRandomMapWithPath();

		map.placeSceneryTile(15,8);
		// break path by placing a new tile along the path

		System.out.println("***********************************************************************");
		System.out.println("Map with path:\n" + map + "\n");
		System.out.println("Path:\n" + map.pathToString() + "\n");
		System.out.println("Map is valid: " + map.verifyValidity() + ".\n");
	}

	/**
	 * generates and returns a Map with randomized scenery and predetermined size and path
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
			corners.add(new int[] {0,3});
			corners.add(new int[] {15,3});
			corners.add(new int[] {15,15});
			corners.add(new int[] {6,15});
			corners.add(new int[] {6,8});
			corners.add(new int[] {2,8});

			// build path
			map.buildNewPath(corners);
		} catch (PathException e) {
			e.printStackTrace();
		}

		return map;
	}
}
