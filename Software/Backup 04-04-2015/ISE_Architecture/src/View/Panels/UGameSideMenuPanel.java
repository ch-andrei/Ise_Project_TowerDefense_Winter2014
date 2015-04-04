package View.Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import View.Components.UMapTile;

public class UGameSideMenuPanel extends JPanel {

	private Image img;
	private boolean imgSet = false;
	private UMapTile[][] tableauBtnMagiques;
	private ArrayList<UMapTile> tilesOnPath;
	private int pathLength;
	private int currentTile;
	// Save tile X coordinate in (0,0), save tile Y coordinate in (0,1)
	private ArrayList<Integer[][]> path = new ArrayList<Integer[][]>();

	public UGameSideMenuPanel() {
		super();
		setSize(new Dimension(1280, 720));
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imgSet) {
			g.drawImage(img, 0, 0, this);
		}
	}
}
