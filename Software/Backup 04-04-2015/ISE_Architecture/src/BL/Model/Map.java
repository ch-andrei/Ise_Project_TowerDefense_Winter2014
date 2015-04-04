package BL.Model;

import java.io.Serializable;
import java.util.ArrayList;

import View.Components.UMapTile;

public class Map implements Serializable, java.lang.Comparable {

	private String mapName;
	private int mapSize;
	private ArrayList<UMapTile> tilesOnPath = new ArrayList<UMapTile>();
	private int sceneryIcon;
	private int pathBackground;

	public Map(String mapName, int mapSize, ArrayList<UMapTile> tilesOnPath,
			int sceneryIcon, int pathBackground) {
		this.mapName = mapName;
		this.mapSize = mapSize;
		this.setTilesOnPath(tilesOnPath);
		this.sceneryIcon = sceneryIcon;
		this.pathBackground = pathBackground;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public ArrayList<UMapTile> getTilesOnPath() {
		return tilesOnPath;
	}

	public void setTilesOnPath(ArrayList<UMapTile> tilesOnPath) {
		this.tilesOnPath = tilesOnPath;
	}

	public int getSceneryIcon() {
		return sceneryIcon;
	}

	public void setSceneryIcon(int sceneryIcon) {
		this.sceneryIcon = sceneryIcon;
	}

	public int getPathBackground() {
		return pathBackground;
	}

	public void setPathBackground(int pathBackground) {
		this.pathBackground = pathBackground;
	}

	@Override
	public int compareTo(Object otherMap) {
		String otherMapName = ((Map) otherMap).getMapName();
		if (this.mapName.equals(otherMapName)) {
			return 0;
		} else {
			return 1;
		}
	}

}
