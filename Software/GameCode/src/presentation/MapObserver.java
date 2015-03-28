package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

import BusinessLogic.GameTime;
import BusinessLogic.GUI.IObserver;
import BusinessLogic.mapComponents.Map;
import BusinessLogic.mapComponents.PathTile;
import BusinessLogic.mapComponents.SceneryTile;
import BusinessLogic.mapComponents.Tile;

/**
 * MapObserver displays and updates a panel on the screen representing the game map and everything on it.
 * @author AC
 *
 */
public class MapObserver extends JPanel implements IObserver{

	protected PathTile current;

	private Object lock = new Object();

	protected boolean interrupted = false;

	protected final static int SQUARE_SIZE = 36;

	protected Thread action;

	protected Map map;

	protected GameTime game;

	protected MapObserver panel;

	Random rand = new Random();

	/**
	 * initializes an instance of MapObserver
	 * @param game
	 * @param sizex
	 * @param sizey
	 */
	public MapObserver(GameTime game, int sizex, int sizey){

		this.game = game;

		this.map = game.getMap();

		panel = this;

		//set panel properties
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(sizex, sizey));
		setDoubleBuffered(true);
		setVisible(true);
		this.setFocusable(true);
		this.requestFocus();

//		uncomment if want to refresh image in terms of 'fps'
//		refresh(Frame.FRAME_SLEEP_TIME);
	}

	/**
	 * spawns a new thread for updating frame. calls update() thus repainting the panel. sleeps framesleep length of time between updates.
	 * CURRENTLY NOT USED (Observer calls map repaints instead of constantly repainting frame by default)
	 * @param framesleep
	 */
	public void refresh(long framesleep){
		(new Thread() { // new thread for refreshing frame
			public void run() {
				while(true){
					update();
					try {
						Thread.sleep(framesleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/**
	 * repaints the panel
	 */
	@Override
	public void update(){
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * pains map and a moving pseudo critter
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.clearRect(0, 0, 1280, 720);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,1280,720);
		
		g.setColor(Color.WHITE);
		g.drawString("CONTROLS: ", 750, 70);
		g.drawString("SPACE - place a default scenery tile at a random position on the map (hold for many)", 750, 90);
		g.drawString("(although may break the path, this is handled in code)", 750, 110);
		g.drawString("ENTER - toggle critter wave on/off (press to start/stop/restart - press 2 times to restart!)", 750, 130);
		g.drawString("ESCAPE - exit game", 750, 150);

		for (int y = map.getWidth()-1; y >= 0; y--){
			for (int x = 0; x < map.getLength(); x++){

				Tile tile = map.getTile(x,y);

				if (tile instanceof PathTile){
					g.setColor(Color.GRAY);
				}
				else {
					SceneryTile sceneryTile = (SceneryTile) tile;
					int color = sceneryTile.getSceneryElement();
					Color c = new Color(24,84,14);
					switch (color){
					case 0:
						break;
					case 1:
						c = new Color(102,51,0);
						break;
					case 2:
						c = new Color(20,120,20);
						break;
					case 3:
						c = new Color(0,100,255);
						break;
					case 4:
						c = new Color(25,51,0);
						break;
					case 5:
						c = new Color(102,201,0);
						break;
					}
					g.setColor(c);
				}

				g.fillRect(((x * SQUARE_SIZE )), (this.panel.getHeight()-((y+1) * SQUARE_SIZE )), SQUARE_SIZE-1, SQUARE_SIZE-1);
			}
		}

		if (map.getCurrentCritterPosition() != null){
			g.setColor(new Color(200,0,0));
			g.fillOval(((map.getCurrentCritterPosition().getX() * SQUARE_SIZE )), (this.panel.getHeight()-((map.getCurrentCritterPosition().getY()+1) * SQUARE_SIZE )), SQUARE_SIZE-1, SQUARE_SIZE-1);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * returns current panel
	 * @return
	 */
	public MapObserver getPanel(){
		return panel;
	}

	/**
	 * return current game instance
	 * @return
	 */
	public GameTime getGame(){
		return game;
	}

	/**
	 * returns current thread action
	 * @return
	 */
	public Thread getAction(){
		return action;
	}
}
