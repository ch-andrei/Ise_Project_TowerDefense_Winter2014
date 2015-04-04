package View.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BL.Controller.Controller;
import BL.Model.Map;
import View.Components.UMapTile;
import View.Panels.UCreateMapGridPanel;

/**
 * Classe qui contient la fenetre du jeu
 * 
 * @author Eric Kavalec
 */
public class UGameFrame extends JFrame {

	private final static int HEIGHT = 720;
	private final static int WIDTH = 1280;
	private final static int SIDEMENUWIDTH = 560;
	private String mapName;
	private Thread threadJeu;
	private UMapTile[][] tableauBtnMagiques;
	private ArrayList<UMapTile> tilesOnPath = new ArrayList<UMapTile>();
	private UCreateMapGridPanel pnlJeu;
	private JPanel pnlBorder = new JPanel(new BorderLayout());
	private JPanel pnlBox0 = new JPanel();
	private JPanel pnlBox1 = new JPanel();
	private JPanel pnlBox1Outside = new JPanel();
	private JPanel pnlBox2 = new JPanel();
	private JPanel pnlBox2Outside = new JPanel();
	private JPanel pnlBox3 = new JPanel();
	private JPanel pnlBox3Outside = new JPanel();
	private boolean enJeu = true;

	public UGameFrame(String mapName) {
		super("Jeu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mapName = mapName;
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

		loadGridPanel();
		loadSideMenuPanel();

		pnlBox1.setBackground(Color.GREEN);
		pnlBox1.setPreferredSize(new Dimension(530, 220));
		JButton btn = new JButton("START WAVE");
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pnlJeu.buildPath();
			}
		});
		pnlBox1.add(btn);
		pnlBox1.add(Box.createRigidArea(new Dimension(530, 100)));

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

	private void loadSideMenuPanel() {

	}

	protected void gameThread() {
		threadJeu = new Thread() {

			@Override
			public void run() {
				while (enJeu) {
					updateGUI();
					invalidate();
					repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		threadJeu.start();
	}

	private void updateGUI() {

	}

	private void loadGridPanel() {
		if (Controller.getInstance().isMapNameTaken(mapName)) {
			Map myMap = Controller.getInstance().getMapFromName(mapName);
			pnlJeu = new UCreateMapGridPanel(myMap);
			tableauBtnMagiques = new UMapTile[myMap.getMapSize()][myMap
					.getMapSize()];

			for (int i = 0; i < myMap.getMapSize(); i++) {
				for (int j = 0; j < myMap.getMapSize(); j++) {
					tableauBtnMagiques[i][j] = pnlJeu.getTableauBtnMagiques()[i][j];
				}
			}
		} else {
			Map myMap = new Map("errorMap", 15, null, 1, 1);
			pnlJeu = new UCreateMapGridPanel(myMap);
			System.out.println("No Such Map with that name");
		}
	}

	/**
	 * fermer la fenetre
	 */
	public void FermerJeuFenetre() {
		this.dispose();
	}
}