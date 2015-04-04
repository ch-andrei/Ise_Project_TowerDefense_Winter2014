package View.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BL.Controller.Controller;
import BL.Model.Map;
import View.Components.UCreateMapCustomizationComponent;
import View.Components.UMapTile;
import View.Panels.UCreateMapGridPanel;

public class UCreateMapFrame extends JFrame {

	private final static int WIDTH = 1280;
	private final static int HEIGHT = 720;
	private final static int SIDEMENUWIDTH = 560;
	private int mapSizeDefault = 15;
	private UMapTile[][] tableauBtnMagiques;
	private ArrayList<UMapTile> tilesOnPath = new ArrayList<UMapTile>();
	private UCreateMapGridPanel pnlJeu;
	private JPanel pnlBorder = new JPanel(new BorderLayout());
	private JPanel pnlBox0 = new JPanel(null);
	private JPanel pnlSidePanel = new JPanel(new FlowLayout());
	private JPanel pnlBox1 = new JPanel();
	private JPanel pnlBox1Outside = new JPanel();
	private JPanel pnlBox2 = new JPanel();
	private JPanel pnlBox2Outside = new JPanel();
	private JPanel pnlBox3 = new JPanel();
	private JPanel pnlBox3Outside = new JPanel();
	private ImageIcon icon;
	private int sceneryIcon;
	private int pathBackground;
	JLabel lblPathLength = new JLabel("Current path length: "
			+ tilesOnPath.size());;
	private boolean choosingMap = true;

	public UCreateMapFrame() {
		super("Jeu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnlBorder.setBackground(Color.BLACK);

		chooseMapSize();
		simulateGridMap(mapSizeDefault);

		this.add(pnlBorder);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT + 30));
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void chooseMapSize() {
		pnlSidePanel.setPreferredSize(new Dimension(SIDEMENUWIDTH, 720));
		pnlSidePanel.setBackground(Color.BLACK);

		Font flbl = new Font("Broadway", Font.BOLD, 28);
		Font fbtn = new Font("Broadway", Font.BOLD, 28);
		Font fbtnCancel = new Font("Broadway", Font.BOLD, 24);

		JLabel lblChooseMapSize = new JLabel(
				"<html><div style=\"text-align: center;\">Please choose one of the following map sizes:</html>");
		lblChooseMapSize.setHorizontalTextPosition(JLabel.CENTER);
		lblChooseMapSize.setFont(flbl);
		lblChooseMapSize.setBackground(Color.BLACK);
		lblChooseMapSize.setForeground(Color.WHITE);
		lblChooseMapSize.setPreferredSize(new Dimension(430, 100));
		pnlSidePanel.add(lblChooseMapSize);

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 5)));

		JButton btnMapSize8 = new JButton("8x8");
		btnMapSize8.setPreferredSize(new Dimension(430, 90));
		btnMapSize8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnMapSize8.setBackground(Color.BLACK);
		btnMapSize8.setForeground(Color.WHITE);
		btnMapSize8.setFont(fbtn);
		pnlSidePanel.add(btnMapSize8);

		btnMapSize8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				emptyGrid();
				simulateGridMap(8);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				emptyGrid();
				emptySideMenu();
				startCreateMap(8);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 10)));

		JButton btnMapSize10 = new JButton("10x10");
		btnMapSize10.setPreferredSize(new Dimension(430, 90));
		btnMapSize10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnMapSize10.setBackground(Color.BLACK);
		btnMapSize10.setForeground(Color.WHITE);
		btnMapSize10.setFont(fbtn);
		pnlSidePanel.add(btnMapSize10);

		btnMapSize10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				emptyGrid();
				simulateGridMap(10);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				emptyGrid();
				emptySideMenu();
				startCreateMap(10);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 10)));

		JButton btnMapSize12 = new JButton("12x12");
		btnMapSize12.setPreferredSize(new Dimension(430, 90));
		btnMapSize12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnMapSize12.setBackground(Color.BLACK);
		btnMapSize12.setForeground(Color.WHITE);
		btnMapSize12.setFont(fbtn);
		pnlSidePanel.add(btnMapSize12);

		btnMapSize12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				emptyGrid();
				simulateGridMap(12);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				emptyGrid();
				emptySideMenu();
				startCreateMap(12);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 10)));

		JButton btnMapSize15 = new JButton("15x15 (Default)");
		btnMapSize15.setPreferredSize(new Dimension(430, 90));
		btnMapSize15.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnMapSize15.setBackground(Color.BLACK);
		btnMapSize15.setForeground(Color.WHITE);
		btnMapSize15.setFont(fbtn);
		pnlSidePanel.add(btnMapSize15);

		btnMapSize15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				emptyGrid();
				emptySideMenu();
				startCreateMap(15);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 10)));

		JButton btnMapSize20 = new JButton("20x20");
		btnMapSize20.setPreferredSize(new Dimension(430, 90));
		btnMapSize20.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnMapSize20.setBackground(Color.BLACK);
		btnMapSize20.setForeground(Color.WHITE);
		btnMapSize20.setFont(fbtn);
		pnlSidePanel.add(btnMapSize20);

		btnMapSize20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				emptyGrid();
				simulateGridMap(20);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyGrid();
				simulateGridMap(mapSizeDefault);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				emptyGrid();
				emptySideMenu();
				startCreateMap(20);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 5)));

		JButton cancel = new JButton("CANCEL");
		cancel.setPreferredSize(new Dimension(150, 30));
		cancel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.RED);
		cancel.setFont(fbtnCancel);
		pnlSidePanel.add(cancel);

		cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeCreateMapToLoginFrame();
			}
		});

		pnlBorder.add(pnlSidePanel, BorderLayout.EAST);
	}

	private void startCreateMap(int gridSize) {
		this.mapSizeDefault = gridSize;
		fillMap(gridSize);
		fillSideMenu();
		enableFirstTile();
	}

	private void emptyGrid() {
		pnlJeu.removeAll();
		pnlBorder.remove(pnlJeu);
		revalidate();
		repaint();
	}

	private void emptySideMenu() {
		pnlSidePanel.removeAll();
		pnlBorder.remove(pnlSidePanel);
		revalidate();
		repaint();
	}

	private void cancelSequence() {
		emptyGrid();
		tilesOnPath.clear();
		lblPathLength.setText("Current path length: " + tilesOnPath.size());
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
				tableauBtnMagiques[i][j] = null;
			}
		}
		fillMap(mapSizeDefault);
		enableFirstTile();
	}

	private void makePathTilesTransparent() {
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
				if (isTileOnPath(i, j)) {
					tableauBtnMagiques[i][j].setOpaque(false);
					tableauBtnMagiques[i][j].setContentAreaFilled(false);
					tableauBtnMagiques[i][j].setBorderPainted(true);
				}
			}
		}
	}

	private void removePathTilesTransparency() {
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
				if (isTileOnPath(i, j)) {
					tableauBtnMagiques[i][j].setOpaque(true);
					tableauBtnMagiques[i][j].setContentAreaFilled(true);
					tableauBtnMagiques[i][j].setBorderPainted(true);
				}
			}
		}
		pnlJeu.setImgBoolean(false);
	}

	private void setSceneryToDisabledTiles(int sceneryNumber) {
		this.sceneryIcon = sceneryNumber;
		Image img = Toolkit.getDefaultToolkit().getImage(
				getClass().getClassLoader().getResource(
						"images/scenery" + sceneryNumber + "S.png"));
		icon = new ImageIcon(img);
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
				if (!isTileOnPath(i, j)) {
					tableauBtnMagiques[i][j].setScenery(true);
					tableauBtnMagiques[i][j].setIcon(icon);
					tableauBtnMagiques[i][j].setDisabledIcon(icon);
				}
			}
		}
		revalidate();
		repaint();
	}

	private void removeSceneryFromDisabledTiles() {
		disableAllTilesNotOnPath();
	}

	private void setGridPanelImage(int imgNumber) {
		this.pathBackground = imgNumber;
		pnlJeu.setImg(imgNumber);
		revalidate();
		repaint();
	}

	private void loadMapCustomizationMenu() {
		pnlSidePanel.setPreferredSize(new Dimension(SIDEMENUWIDTH, 720));
		pnlSidePanel.setBackground(Color.BLACK);

		pnlSidePanel.add(Box.createRigidArea(new Dimension(450, 5)));

		Font flbl = new Font("Times New Roman", Font.BOLD, 32);
		Font flbl2 = new Font("Times New Roman", Font.BOLD, 29);
		Font fbtn = new Font("Broadway", Font.PLAIN, 28);
		Font fbtnCancel = new Font("Broadway", Font.BOLD, 24);

		JLabel lblMapCustomizationInstruction = new JLabel(
				"<html><div style=\"text-align: center;\">Customize your map with the following available art.</html>");
		lblMapCustomizationInstruction.setHorizontalTextPosition(JLabel.CENTER);
		lblMapCustomizationInstruction.setFont(flbl);
		lblMapCustomizationInstruction.setBackground(Color.BLACK);
		lblMapCustomizationInstruction.setForeground(Color.WHITE);
		lblMapCustomizationInstruction
				.setPreferredSize(new Dimension(430, 120));
		pnlSidePanel.add(lblMapCustomizationInstruction);

		JLabel lblSceneryArt = new JLabel(
				"<html><div style=\"text-align: center;\">Scenery Art:</html>");
		lblSceneryArt.setHorizontalTextPosition(JLabel.CENTER);
		lblSceneryArt.setFont(flbl2);
		lblSceneryArt.setBackground(Color.BLACK);
		lblSceneryArt.setForeground(Color.WHITE);
		pnlSidePanel.add(lblSceneryArt);

		UCreateMapCustomizationComponent scenery1 = new UCreateMapCustomizationComponent(
				"scenery1");
		scenery1.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(scenery1);

		scenery1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSceneryToDisabledTiles(1);
			}
		});

		UCreateMapCustomizationComponent scenery2 = new UCreateMapCustomizationComponent(
				"scenery2");
		scenery2.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(scenery2);

		scenery2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSceneryToDisabledTiles(2);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(450, 10)));

		JLabel lblPathArt = new JLabel(
				"<html><div style=\"text-align: center;\">Path Art:</html>");
		lblPathArt.setHorizontalTextPosition(JLabel.CENTER);
		lblPathArt.setFont(flbl2);
		lblPathArt.setBackground(Color.BLACK);
		lblPathArt.setForeground(Color.WHITE);
		pnlSidePanel.add(lblPathArt);

		UCreateMapCustomizationComponent path1 = new UCreateMapCustomizationComponent(
				"path1");
		path1.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path1);

		path1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(1);
			}
		});

		UCreateMapCustomizationComponent path2 = new UCreateMapCustomizationComponent(
				"path2");
		path2.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path2);

		path2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(2);
			}
		});

		UCreateMapCustomizationComponent path3 = new UCreateMapCustomizationComponent(
				"path3");
		path3.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path3);

		path3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(3);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(118, 100)));

		UCreateMapCustomizationComponent path4 = new UCreateMapCustomizationComponent(
				"path4");
		path4.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path4);

		path4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(4);
			}
		});

		UCreateMapCustomizationComponent path5 = new UCreateMapCustomizationComponent(
				"path5");
		path5.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path5);

		path5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(5);
			}
		});

		UCreateMapCustomizationComponent path6 = new UCreateMapCustomizationComponent(
				"path6");
		path6.setPreferredSize(new Dimension(100, 100));
		pnlSidePanel.add(path6);

		path6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePathTilesTransparent();
				setGridPanelImage(6);
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(450, 10)));

		JButton btnModifyPattern = new JButton("Modify Map Pattern");
		btnModifyPattern.setPreferredSize(new Dimension(430, 70));
		btnModifyPattern.setBorder(BorderFactory.createLineBorder(Color.WHITE,
				2));
		btnModifyPattern.setBackground(Color.BLACK);
		btnModifyPattern.setForeground(Color.WHITE);
		btnModifyPattern.setFont(fbtn);
		pnlSidePanel.add(btnModifyPattern);

		btnModifyPattern.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				emptySideMenu();
				fillSideMenu();
				removePathTilesTransparency();
				removeSceneryFromDisabledTiles();
				enableNextTiles(tilesOnPath.get(tilesOnPath.size() - 1));
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(450, 5)));

		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.setPreferredSize(new Dimension(430, 70));
		btnSaveMap.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnSaveMap.setBackground(Color.BLACK);
		btnSaveMap.setForeground(Color.WHITE);
		btnSaveMap.setFont(fbtn);
		pnlSidePanel.add(btnSaveMap);

		btnSaveMap.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				saveCurrentMap();
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(450, 15)));

		JButton cancel = new JButton("CANCEL");
		cancel.setPreferredSize(new Dimension(150, 30));
		cancel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.RED);
		cancel.setFont(fbtnCancel);
		pnlSidePanel.add(cancel);

		cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeCreateMapToLoginFrame();
			}
		});

		pnlBorder.add(pnlSidePanel, BorderLayout.EAST);
		revalidate();
		repaint();
	}

	private void fillSideMenu() {
		pnlSidePanel.setPreferredSize(new Dimension(SIDEMENUWIDTH, 720));
		pnlSidePanel.setBackground(Color.BLACK);

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 5)));

		Font flbl = new Font("Times New Roman", Font.BOLD, 25);
		Font fbtn = new Font("Broadway", Font.PLAIN, 28);
		Font fbtnCancel = new Font("Broadway", Font.BOLD, 24);

		JLabel lblMapInstructions = new JLabel(
				"<html><div style=\"text-align: center;\">Build the desired path by starting from the map's entry point and ending on an exit point adjacent to the map's walls.</html>");
		lblMapInstructions.setHorizontalTextPosition(JLabel.CENTER);
		lblMapInstructions.setFont(flbl);
		lblMapInstructions.setBackground(Color.BLACK);
		lblMapInstructions.setForeground(Color.WHITE);
		lblMapInstructions.setPreferredSize(new Dimension(430, 120));
		pnlSidePanel.add(lblMapInstructions);

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 10)));

		lblPathLength.setText("Current path length: " + tilesOnPath.size());
		lblPathLength.setHorizontalTextPosition(JLabel.CENTER);
		lblPathLength.setFont(flbl);
		lblPathLength.setBackground(Color.BLACK);
		lblPathLength.setForeground(Color.WHITE);
		lblPathLength.setPreferredSize(new Dimension(430, 30));
		pnlSidePanel.add(lblPathLength);

		JLabel lblPathLengthMinimum = new JLabel("Minimum path length: "
				+ (mapSizeDefault * 2));
		lblPathLengthMinimum.setHorizontalTextPosition(JLabel.CENTER);
		lblPathLengthMinimum.setFont(flbl);
		lblPathLengthMinimum.setBackground(Color.BLACK);
		lblPathLengthMinimum.setForeground(Color.WHITE);
		lblPathLengthMinimum.setPreferredSize(new Dimension(430, 30));
		pnlSidePanel.add(lblPathLengthMinimum);

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 30)));

		JButton btnCancelSequence = new JButton("Cancel Sequence");
		btnCancelSequence.setPreferredSize(new Dimension(430, 90));
		btnCancelSequence.setBorder(BorderFactory.createLineBorder(Color.WHITE,
				2));
		btnCancelSequence.setBackground(Color.BLACK);
		btnCancelSequence.setForeground(Color.WHITE);
		btnCancelSequence.setFont(fbtn);
		pnlSidePanel.add(btnCancelSequence);

		btnCancelSequence.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				cancelSequence();
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 20)));

		JButton btnValidateExit = new JButton("Validate Path");
		btnValidateExit.setPreferredSize(new Dimension(430, 90));
		btnValidateExit.setBorder(BorderFactory
				.createLineBorder(Color.WHITE, 2));
		btnValidateExit.setBackground(Color.BLACK);
		btnValidateExit.setForeground(Color.WHITE);
		btnValidateExit.setFont(fbtn);
		pnlSidePanel.add(btnValidateExit);

		btnValidateExit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (validExitTile()) {
					emptySideMenu();
					disableAllTilesNotOnPath();
					loadMapCustomizationMenu();
				} else {
					if (tilesOnPath.size() < mapSizeDefault * 2) {
						JOptionPane.showMessageDialog(pnlBorder,
								"Your path length is unsifficient!", null,
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(pnlBorder,
								"Your path pattern is incorrect!", null,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		pnlSidePanel.add(Box.createRigidArea(new Dimension(430, 185)));

		JButton cancel = new JButton("CANCEL");
		cancel.setPreferredSize(new Dimension(150, 30));
		cancel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.RED);
		cancel.setFont(fbtnCancel);
		pnlSidePanel.add(cancel);

		cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().changeCreateMapToLoginFrame();
			}
		});

		pnlBorder.add(pnlSidePanel, BorderLayout.EAST);
		revalidate();
		repaint();
	}

	private void simulateGridMap(int size) {
		pnlJeu = new UCreateMapGridPanel(new GridLayout(size, size));
		pnlJeu.setBackground(Color.BLACK);
		for (int i = 0; i < size * size; i++) {
			JButton empty = new JButton("");
			empty.setEnabled(false);
			empty.setBackground(Color.DARK_GRAY);
			pnlJeu.add(empty);
		}
		pnlBorder.add(pnlJeu, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	private void fillMap(int size) {
		pnlJeu = new UCreateMapGridPanel(new GridLayout(size, size));
		pnlJeu.setBackground(Color.BLACK);
		Font f = new Font("Times New Roman", Font.BOLD, 24);
		if (size == 15) {
			f = new Font("Times New Roman", Font.BOLD, 18);
		} else if (size > 15) {
			f = new Font("Times New Roman", Font.BOLD, 6);
		}
		tableauBtnMagiques = new UMapTile[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				UMapTile tile = new UMapTile(i, j);
				tile.setFont(f);
				disableTile(tile);
				tableauBtnMagiques[i][j] = tile;
				listenToTile(tile);
				pnlJeu.add(tile);
			}
		}
		pnlBorder.add(pnlJeu, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	private void listenToTile(UMapTile tile) {
		tile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UMapTile tileClicked = (UMapTile) e.getSource();
				if (!tableauBtnMagiques[tileClicked.getLigne()][tileClicked
						.getColonne()].isDejaclicke()) {
					// Add to tiles on path
					addToTilesOnPath(
							tableauBtnMagiques[tileClicked.getLigne()][tileClicked
									.getColonne()], true);
					// Enable Next Tiles
					disableAllTilesNotOnPath();
					enableNextTiles(tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()]);
					lblPathLength.setText("Current path length: "
							+ tilesOnPath.size());
				} else {
					tableauBtnMagiques[tileClicked.getLigne()][tileClicked
							.getColonne()].setDejaclicke(false);
				}
			}
		});
	}

	private void addToTilesOnPath(UMapTile tile, boolean intermediateAdd) {
		try {
			if (intermediateAdd) {
				UMapTile lastTile = tilesOnPath.get(tilesOnPath.size() - 1);
				addIntermediateTilesToPath(lastTile, tile);
			}
			tile.setDejaclicke(true);
			tilesOnPath.add(tile);
			tile.setText("" + tilesOnPath.size());
			// System.out.println("Valid: " + validTile(tile));
		} catch (Exception e) {
			tile.setDejaclicke(true);
			tilesOnPath.add(tile);
			tile.setText("" + tilesOnPath.size());
		}
	}

	private void addIntermediateTilesToPath(UMapTile lastTile,
			UMapTile currentTile) {
		try {
			int ligneLastBtn = lastTile.getLigne();
			int colonneLastBtn = lastTile.getColonne();

			if (currentTile.getLigne() == ligneLastBtn) {
				if (colonneLastBtn < currentTile.getColonne()) {
					for (int column = colonneLastBtn + 1; column < currentTile
							.getColonne(); column++) {
						addToTilesOnPath(
								tableauBtnMagiques[currentTile.getLigne()][column],
								false);
					}
				} else {
					for (int column = colonneLastBtn - 1; column > currentTile
							.getColonne(); column--) {
						addToTilesOnPath(
								tableauBtnMagiques[currentTile.getLigne()][column],
								false);
					}
				}
			} else {
				if (ligneLastBtn < currentTile.getLigne()) {
					for (int ligne = ligneLastBtn + 1; ligne < currentTile
							.getLigne(); ligne++) {
						addToTilesOnPath(
								tableauBtnMagiques[ligne][currentTile
										.getColonne()],
								false);
					}
				} else {
					for (int ligne = ligneLastBtn - 1; ligne > currentTile
							.getLigne(); ligne--) {
						addToTilesOnPath(
								tableauBtnMagiques[ligne][currentTile
										.getColonne()],
								false);
					}
				}
			}

		} catch (Exception e) {

		}
	}

	private void disableAllTilesNotOnPath() {
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
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

	private boolean validExitTile() {
		try {
			UMapTile exitTile = tilesOnPath.get(tilesOnPath.size() - 1);
			int line = exitTile.getLigne();
			int column = exitTile.getColonne();
			if ((line == 0 || column == 0 || line == mapSizeDefault - 1 || column == mapSizeDefault - 1)
					&& tilesOnPath.size() >= (mapSizeDefault * 2)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean validTile(UMapTile tile) {
		System.out.println("T " + adjacentTileOnTop(tile));
		System.out.println("B " + adjacentTileOnBottom(tile));
		System.out.println("L " + adjacentTileOnLeft(tile));
		System.out.println("R " + adjacentTileOnRight(tile));
		if (adjacentTileOnBottom(tile) && adjacentTileOnLeft(tile)
				&& adjacentTileOnRight(tile) && pathOnTop(tile)) {
			return false;
		} else if (adjacentTileOnTop(tile) && adjacentTileOnLeft(tile)
				&& adjacentTileOnRight(tile) && pathOnBottom(tile)) {
			return false;
		} else if (adjacentTileOnTop(tile) && adjacentTileOnBottom(tile)
				&& adjacentTileOnRight(tile) && pathOnLeft(tile)) {
			return false;
		} else if (adjacentTileOnTop(tile) && adjacentTileOnBottom(tile)
				&& adjacentTileOnLeft(tile) && pathOnRight(tile)) {
			return false;
		} else if (adjacentTileOnTop(tile) && adjacentTileOnRight(tile)
				&& pathOnBottom(tile) && pathOnLeft(tile)) {
			return false;
		} else if (adjacentTileOnTop(tile) & adjacentTileOnLeft(tile)
				&& pathOnBottom(tile) && pathOnRight(tile)) {
			return false;
		}

		else {
			return true;
		}

		/*
		 * 
		 * if (pathOnTop(tile) && pathOnBottom(tile) && pathOnLeft(tile) &&
		 * pathOnRight(tile)) { return false; }
		 */

	}

	private boolean adjacentTileOnTop(UMapTile tile) {
		try {
			if (tableauBtnMagiques[tile.getLigne() - 1][tile.getColonne()]
					.isDejaclicke()) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean adjacentTileOnBottom(UMapTile tile) {
		try {
			if (tableauBtnMagiques[tile.getLigne() + 1][tile.getColonne()]
					.isDejaclicke()) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean adjacentTileOnLeft(UMapTile tile) {
		try {
			if (tableauBtnMagiques[tile.getLigne()][tile.getColonne() - 1]
					.isDejaclicke()) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean adjacentTileOnRight(UMapTile tile) {
		try {
			if (tableauBtnMagiques[tile.getLigne()][tile.getColonne() + 1]
					.isDejaclicke()) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean pathOnTop(UMapTile tile) {
		if (tile.getLigne() == 0) {
			return true;
		}
		boolean pathOnTop = false;
		try {
			int column = tile.getColonne();
			for (int line = tile.getLigne() - 1; line >= 0; line--) {
				if (tableauBtnMagiques[line][column].isDejaclicke()) {
					pathOnTop = true;
				}
			}
		} catch (Exception e) {
			pathOnTop = true;
		}
		return pathOnTop;
	}

	private boolean pathOnBottom(UMapTile tile) {
		if (tile.getLigne() == mapSizeDefault - 1) {
			return true;
		}
		boolean pathOnBottom = false;
		try {
			int column = tile.getColonne();
			for (int line = tile.getLigne() + 1; line < mapSizeDefault; line++) {

				if (tableauBtnMagiques[line][column].isDejaclicke()) {
					pathOnBottom = true;
				}
			}
		} catch (Exception e) {
			pathOnBottom = true;
		}
		return pathOnBottom;
	}

	private boolean pathOnLeft(UMapTile tile) {
		if (tile.getColonne() == 0) {
			return true;
		}
		boolean pathOnLeft = false;
		try {
			int line = tile.getLigne();
			for (int column = tile.getColonne() - 1; line >= 0; line--) {

				if (tableauBtnMagiques[line][column].isDejaclicke()) {
					pathOnLeft = true;
				}
			}
		} catch (Exception e) {
			pathOnLeft = true;
		}
		return pathOnLeft;
	}

	private boolean pathOnRight(UMapTile tile) {
		if (tile.getColonne() == mapSizeDefault - 1) {
			return true;
		}
		boolean pathOnRight = false;
		try {
			int line = tile.getLigne();
			for (int column = tile.getColonne() + 1; line < mapSizeDefault; line++) {

				if (tableauBtnMagiques[line][column].isDejaclicke()) {
					pathOnRight = true;
				}
			}
		} catch (Exception e) {
			pathOnRight = true;
		}
		return pathOnRight;
	}

	private void enableFirstTile() {
		for (int i = 0; i < mapSizeDefault; i++) {
			for (int j = 0; j < mapSizeDefault; j++) {
				if ((i == 0 || j == 0)
						|| (i == mapSizeDefault - 1 || j == mapSizeDefault - 1)) {
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
		tile.setIcon(null);
		tile.setDisabledIcon(null);
		tile.setBackground(Color.LIGHT_GRAY);
	}

	private void enableNextTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();

		try {
			if (!tableauBtnMagiques[ligneBtn][colonneBtn - 1].isDejaclicke()) {
				enableLeftTiles(tile);
			}
			if (!tableauBtnMagiques[ligneBtn][colonneBtn + 1].isDejaclicke()) {
				enableRightTiles(tile);
			}
			if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn].isDejaclicke()) {
				enableTopTiles(tile);
			}
			if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn].isDejaclicke()) {
				enableBottomTiles(tile);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				if (!tableauBtnMagiques[ligneBtn][colonneBtn + 1]
						.isDejaclicke()) {
					enableRightTiles(tile);
				}
				if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn]
						.isDejaclicke()) {
					enableTopTiles(tile);
				}
				if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
						.isDejaclicke()) {
					enableBottomTiles(tile);
				}
			} catch (ArrayIndexOutOfBoundsException e2) {
				try {
					if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn]
							.isDejaclicke()) {
						enableTopTiles(tile);
					}
					if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
							.isDejaclicke()) {
						enableBottomTiles(tile);
					}
				} catch (ArrayIndexOutOfBoundsException e3) {
					try {
						if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn]
								.isDejaclicke()) {
							enableBottomTiles(tile);
						}
					} catch (ArrayIndexOutOfBoundsException e4) {
					} catch (Exception e5) {
					}
				}
			}
		}
	}

	private void enableLeftTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();
		try {
			if (!tableauBtnMagiques[ligneBtn][colonneBtn - 1].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn][colonneBtn - 1]);
				enableLeftTiles(tableauBtnMagiques[ligneBtn][colonneBtn - 1]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	private void enableRightTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();
		try {
			if (!tableauBtnMagiques[ligneBtn][colonneBtn + 1].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn][colonneBtn + 1]);
				enableRightTiles(tableauBtnMagiques[ligneBtn][colonneBtn + 1]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	private void enableTopTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();
		try {
			if (!tableauBtnMagiques[ligneBtn - 1][colonneBtn].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn - 1][colonneBtn]);
				enableTopTiles(tableauBtnMagiques[ligneBtn - 1][colonneBtn]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	private void enableBottomTiles(UMapTile tile) {
		int ligneBtn = tile.getLigne();
		int colonneBtn = tile.getColonne();
		try {
			if (!tableauBtnMagiques[ligneBtn + 1][colonneBtn].isDejaclicke()) {
				enableTile(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
				enableBottomTiles(tableauBtnMagiques[ligneBtn + 1][colonneBtn]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	private void saveCurrentMap() {
		String nameMap = "";
		try {
			nameMap = JOptionPane.showInputDialog(pnlBorder,
					"Enter a name for your map:", null,
					JOptionPane.PLAIN_MESSAGE);
			while (nameMap.equals("") || nameMap.length() > 12
					|| Controller.getInstance().isMapNameTaken(nameMap)) {
				if (Controller.getInstance().isMapNameTaken(nameMap)) {
					JOptionPane.showMessageDialog(pnlBorder,
							"This map name is already taken!", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(pnlBorder,
							"Enter a valid name!", null,
							JOptionPane.ERROR_MESSAGE);
				}
				nameMap = JOptionPane.showInputDialog(pnlBorder,
						"Enter a name for your map:", null,
						JOptionPane.PLAIN_MESSAGE);
			}
		} catch (java.lang.NullPointerException e1) {
		}

		Map map = new Map(nameMap, mapSizeDefault, tilesOnPath, sceneryIcon,
				pathBackground);
		Controller.getInstance().saveMap(map);
		JOptionPane.showMessageDialog(pnlBorder,
				"Your map was successfuly saved!", null,
				JOptionPane.INFORMATION_MESSAGE);
		Controller.getInstance().changeCreateMapToLoginFrame();
	}

	public void closeCreateMapFrame() {
		this.dispose();
	}
}