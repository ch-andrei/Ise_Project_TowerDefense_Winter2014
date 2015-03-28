package View.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.Components.UMapTile;

/**
 * Classe qui contient la fenetre du jeu
 * 
 * @author Eric Kavalec
 */
public class UCreateMapFrame extends JFrame {

	/**
	 * le panel du jeu
	 */
	// UGamePanel jeuPnl = new
	// UGamePanel(Controller.getInstance().player.getArmeEquip(),
	// Controller.getInstance().player.getArmureEquip(),
	// Controller.getInstance().currentLevel,
	// Controller.getInstance().player.getNom());
	private final static int HEIGHT = 720;
	private final static int WIDTH = 1280;
	private int mapSize = 15;
	private UMapTile[][] tableauBtnMagiques;
	private ArrayList<UMapTile> tilesOnPath = new ArrayList<UMapTile>();
	private JPanel pnlJeu = new JPanel(new GridLayout(15, 15));
	private JPanel pnlBorder = new JPanel(new BorderLayout());
	private JPanel pnlBox0 = new JPanel();
	private JPanel pnlBox1 = new JPanel();
	private JPanel pnlBox1Outside = new JPanel();
	private JPanel pnlBox2 = new JPanel();
	private JPanel pnlBox2Outside = new JPanel();
	private JPanel pnlBox3 = new JPanel();
	private JPanel pnlBox3Outside = new JPanel();

	public UCreateMapFrame() {
		super("Jeu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnlBox0.setLayout(new BoxLayout(pnlBox0, BoxLayout.Y_AXIS));
		pnlBox1.setLayout(new BoxLayout(pnlBox1, BoxLayout.Y_AXIS));
		pnlBox2.setLayout(new BoxLayout(pnlBox2, BoxLayout.Y_AXIS));
		pnlBox3.setLayout(new BoxLayout(pnlBox3, BoxLayout.Y_AXIS));

		pnlBox1Outside
				.setLayout(new BoxLayout(pnlBox1Outside, BoxLayout.X_AXIS));
		pnlBox2Outside
				.setLayout(new BoxLayout(pnlBox2Outside, BoxLayout.X_AXIS));
		pnlBox3Outside
				.setLayout(new BoxLayout(pnlBox3Outside, BoxLayout.X_AXIS));

		fillMap();
		enableFirstTile();

		pnlBox1.setBackground(Color.GREEN);
		pnlBox1.setPreferredSize(new Dimension(530, 220));
		pnlBox1.add(Box.createRigidArea(new Dimension(530, 220)));

		pnlBox2.setBackground(Color.GREEN);
		pnlBox2.setPreferredSize(new Dimension(530, 220));
		pnlBox2.add(Box.createRigidArea(new Dimension(530, 220)));

		pnlBox3.setBackground(Color.GREEN);
		pnlBox3.setPreferredSize(new Dimension(530, 220));
		pnlBox3.add(Box.createRigidArea(new Dimension(530, 220)));

		pnlBox1Outside.setBackground(Color.BLUE);
		pnlBox1Outside.setPreferredSize(new Dimension(560, 220));
		pnlBox2Outside.setBackground(Color.BLUE);
		pnlBox2Outside.setPreferredSize(new Dimension(560, 220));
		pnlBox3Outside.setBackground(Color.BLUE);
		pnlBox3Outside.setPreferredSize(new Dimension(560, 220));

		pnlBox1Outside.add(Box.createRigidArea(new Dimension(15, 220)));
		pnlBox1Outside.add(pnlBox1);
		pnlBox1Outside.add(Box.createRigidArea(new Dimension(15, 220)));

		pnlBox2Outside.add(Box.createRigidArea(new Dimension(15, 220)));
		pnlBox2Outside.add(pnlBox2);
		pnlBox2Outside.add(Box.createRigidArea(new Dimension(15, 220)));

		pnlBox3Outside.add(Box.createRigidArea(new Dimension(15, 220)));
		pnlBox3Outside.add(pnlBox3);
		pnlBox3Outside.add(Box.createRigidArea(new Dimension(15, 220)));

		pnlBox0.setBackground(Color.BLACK);
		pnlBox0.add(Box.createRigidArea(new Dimension(560, 15)));
		pnlBox0.add(pnlBox1Outside);
		pnlBox0.add(Box.createRigidArea(new Dimension(560, 15)));
		pnlBox0.add(pnlBox2Outside);
		pnlBox0.add(Box.createRigidArea(new Dimension(560, 15)));
		pnlBox0.add(pnlBox3Outside);
		pnlBox0.add(Box.createRigidArea(new Dimension(560, 15)));
		pnlBorder.setSize(new Dimension(WIDTH, HEIGHT));
		pnlBorder.add(pnlBox0, BorderLayout.EAST);
		pnlBorder.add(pnlJeu, BorderLayout.CENTER);

		this.add(pnlBorder);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT + 30));
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void fillMap() {
		tableauBtnMagiques = new UMapTile[mapSize][mapSize];
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				UMapTile tile = new UMapTile(i, j);
				tableauBtnMagiques[i][j] = tile;
				listen(tile);
				pnlJeu.add(tile);
			}
		}
	}

	private void listen(UMapTile tile) {
		tile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UMapTile tileClicked = (UMapTile) e.getSource();
				if (!tableauBtnMagiques[tileClicked.getLigne()][tileClicked
						.getColonne()].isDejaclicke()) {
					tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()].setDejaclicke(true);
					tilesOnPath.add(tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()]);
					disableAllTilesNotOnPath();
					enableNextTiles(tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()]);
				} else {
					tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()].setDejaclicke(false);
				}
			}
		});
	}

	private void disableAllTilesNotOnPath() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!isTileOnPath(i, j)) {
					disableTile(tableauBtnMagiques[i][j]);
				}
			}
		}
	}

	private boolean isTileOnPath(int i, int j) {
		boolean onPath = false;
		for (int k = 0; k < tilesOnPath.size(); k++) {
			if (i == tilesOnPath.get(k).getLigne()
					&& j == tilesOnPath.get(k).getColonne()) {
				onPath = true;
			}
		}
		return onPath;
	}

	private void enableFirstTile() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (i == 0 || j == 0) {
					enableTile(tableauBtnMagiques[i][j]);
				} else {
					tableauBtnMagiques[i][j].setEnabled(false);
				}

			}
		}
	}

	private void enableTile(UMapTile tile) {
		tile.setEnabled(true);
		tile.setBackground(Color.BLUE);
	}

	private void disableTile(UMapTile tile) {
		tile.setEnabled(false);
		tile.setBackground(Color.LIGHT_GRAY);
	}

	private void enableNextTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();

		try {
			if (!tableauBtnMagiques[ligneBtn][colonneBtn - 1].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn][colonneBtn - 1]);
			}
			if (!tableauBtnMagiques[ligneBtn][colonneBtn + 1].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn][colonneBtn + 1]);
			}
			if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn - 1][colonneBtn]);
			}
			if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if (!tableauBtnMagiques[ligneBtn][colonneBtn + 1]
						.isDejaclicke()) {
					enableTile(tableauBtnMagiques[ligneBtn][colonneBtn + 1]);
				}
				if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn]
						.isDejaclicke()) {
					enableTile(tableauBtnMagiques[ligneBtn - 1][colonneBtn]);
				}
				if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
						.isDejaclicke()) {
					enableTile(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
				}
			} catch (ArrayIndexOutOfBoundsException e2) {
				try {
					if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn]
							.isDejaclicke()) {
						enableTile(tableauBtnMagiques[ligneBtn - 1][colonneBtn]);
					}
					if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
							.isDejaclicke()) {
						enableTile(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
					}
				} catch (ArrayIndexOutOfBoundsException e3) {
					try {
						if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
								.isDejaclicke()) {
							enableTile(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
						}
					} catch (ArrayIndexOutOfBoundsException e4) {
					} catch (Exception e5) {
					}
				}
			}
		}

	}

	/**
	 * fermer la fenetre
	 */
	public void FermerJeuFenetre() {
		this.dispose();
	}
}