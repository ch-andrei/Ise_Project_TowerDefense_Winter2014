package BusinessLogic;

import BusinessLogic.mapComponents.Map;

/**
 * represents a game. Holds relevant game information such as map current game is played on.
 * @author AC
 *
 */
public class GameTime {
	
	private Map map;
	
	/**
	 * Constructor for instance of GameTime
	 */
	public GameTime(){
		this.map = Map.generateRandomMapWithPath();
		// generates COMPLITELY random 20x20 map (chooses from 5 preset paths)
	}
	
	/**
	 * getter for currently played on map
	 * @return
	 */
	public Map getMap(){
		return this.map;
	}
}
