package View.Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import BL.Model.Map;
import View.Components.UMapTile;

public class UCreateMapGridPanel extends JPanel {

	private Image img;
	private boolean imgSet = false;
	private UMapTile[][] tableauBtnMagiques;
	private ArrayList<UMapTile> tilesOnPath;
	private int pathLength;
	private int currentTile;
	// Save tile X coordinate in (0,0), save tile Y coordinate in (0,1)
	private ArrayList<Integer[][]> path = new ArrayList<Integer[][]>();

	public UCreateMapGridPanel(GridLayout gridLayout) {
		super(gridLayout);
		setSize(new Dimension(720, 720));
		setVisible(true);
	}

	public UCreateMapGridPanel(Map map) {
		this(new GridLayout(map.getMapSize(), map.getMapSize()));
		try {
			// Fetch variables from map
			int mapSize = map.getMapSize();
			int sceneryNumber = map.getSceneryIcon();
			int pathNumber = map.getPathBackground();
			tilesOnPath = map.getTilesOnPath();
			this.pathLength = tilesOnPath.size();

			// Set background (path)
			this.setImg(pathNumber);

			tableauBtnMagiques = new UMapTile[mapSize][mapSize];
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					UMapTile tile = new UMapTile(i, j);
					tile.setEnabled(false);
					tableauBtnMagiques[i][j] = tile;
					listenToTile(tile);
					this.add(tile);
				}
			}

			makePathButtonsTransparent(mapSize, tilesOnPath);
			setSceneryPathsIcon(mapSize, tilesOnPath, sceneryNumber);
		} catch (Exception e) {
		}
	}

	public void buildPath() {
		for (int i = 0; i < tilesOnPath.size(); i++) {
			Integer[][] tileCenter = new Integer[1][2];
			tileCenter[0][0] = tilesOnPath.get(i).getX()
					+ (tilesOnPath.get(i).getWidth() / 2);
			tileCenter[0][1] = tilesOnPath.get(i).getY()
					+ (tilesOnPath.get(i).getHeight() / 2);
			path.add(tileCenter);
			// System.out.println("Tile: " + i + " X: " + path.get(i)[0][0]+
			// " Y: " + path.get(i)[0][1]);
		}
		this.currentTile = 0;
	}

	public Integer[][] getNextTileCoordinates() {
		Integer[][] nextTile = path.get(currentTile);
		currentTile++;
		if (currentTile == pathLength) {
			currentTile = 0;
		}
		return nextTile;
	}

	private void listenToTile(UMapTile tile) {
		tile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UMapTile tileClicked = (UMapTile) e.getSource();
				buildPath();
			}
		});
	}

	private void makePathButtonsTransparent(int mapSize,
			ArrayList<UMapTile> tilesOnPath) {
		try {
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					if (isTileOnPath(i, j, tilesOnPath)) {
						tableauBtnMagiques[i][j].setOpaque(false);
						tableauBtnMagiques[i][j].setContentAreaFilled(false);
						tableauBtnMagiques[i][j].setBorderPainted(true);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	private void enableSceneryTiles(int mapSize, ArrayList<UMapTile> tilesOnPath) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!isTileOnPath(i, j, tilesOnPath)) {
					tableauBtnMagiques[i][j].setEnabled(true);
				}
			}
		}
	}

	private void disableSceneryTiles(int mapSize,
			ArrayList<UMapTile> tilesOnPath) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!isTileOnPath(i, j, tilesOnPath)) {
					tableauBtnMagiques[i][j].setEnabled(false);
				}
			}
		}
	}

	private void setSceneryPathsIcon(int mapSize,
			ArrayList<UMapTile> tilesOnPath, int sceneryNumber) {
		try {
			Image img = Toolkit.getDefaultToolkit().getImage(
					getClass().getClassLoader().getResource(
							"images/scenery" + sceneryNumber + "S.png"));
			ImageIcon icon = new ImageIcon(img);
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					if (!isTileOnPath(i, j, tilesOnPath)) {
						tableauBtnMagiques[i][j].setScenery(true);
						tableauBtnMagiques[i][j].setIcon(icon);
						tableauBtnMagiques[i][j].setDisabledIcon(icon);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	private boolean isTileOnPath(int i, int j, ArrayList<UMapTile> tilesOnPath) {
		boolean onPath = false;
		try {
			for (int k = 0; k < tilesOnPath.size(); k++) {
				if (i == tilesOnPath.get(k).getLigne()
						&& j == tilesOnPath.get(k).getColonne()) {
					onPath = true;
				}
			}
		} catch (Exception e) {
		}
		return onPath;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(int imgNumber) {
		this.img = img = Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/path" + imgNumber + ".png"));
		imgSet = true;
	}

	public void setImgBoolean(boolean imgSet) {
		this.imgSet = imgSet;
	}

	public UMapTile[][] getTableauBtnMagiques() {
		return tableauBtnMagiques;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imgSet) {
			g.drawImage(img, 0, 0, this);
		}
	}
}
