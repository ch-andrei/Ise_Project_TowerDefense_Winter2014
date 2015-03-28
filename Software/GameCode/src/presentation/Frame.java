package presentation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import BusinessLogic.GameTime;

public class Frame extends JFrame{

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final String APP_NAME = "Map Observer";
	
	public static JFrame frame;
	
	// sleep between refreshing frame
	public static final int FRAME_SLEEP_TIME = 15; // 15ms = ~60fps : but this is just sleep time, not actual frametime...
	
	private GameTime game;
	
	protected MapObserver mapPanel;
	
	public Frame(GameTime game){
		initialize(game);
	}
	
	/**
	 * Initializes instance of frame and sets window properties of the application.
	 */
	private void initialize(GameTime game){
		
		frame = this;
		
		this.game = game;
		
		this.mapPanel = new MapObserver(game,1280,720);
		
		this.setTitle(APP_NAME);  
		this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		
		if (mapPanel != null) frame.getContentPane().add((mapPanel.getPanel()), BorderLayout.CENTER);       												

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
		this.setVisible(true);
		
		pack();
	}
	
	/**
	 * singleton returns current frame
	 * @param game
	 * @return
	 */
	public static JFrame getFrame(GameTime game){
		if (frame == null){
			frame = new Frame(game);
		}
		return frame;
	}
	
	public MapObserver getMapPanel(){
		return mapPanel;
	}
}
