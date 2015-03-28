package BL.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import BL.Model.Player;
import View.Frames.UCreateMapFrame;
import View.Frames.UHighscoresFrame;
import View.Frames.UInnerMenuFrame;
import View.Frames.ULoginFrame;
import View.Frames.UShopFrame;

/**
 * Classe qui s'occupe de l'organisation entre les fenetres et gestion des
 * sauvegardes
 * 
 * @author Eric Kavalec
 */
public class Controller {

	private static Controller g = new Controller();
	private ULoginFrame loginMenuFrame;
	private UHighscoresFrame highscoresFrame;
	private UInnerMenuFrame innerMenuFrame;
	private UCreateMapFrame gameFrame;
	private UShopFrame shopFrame;
	public Player player;
	private boolean backToMenu;
	public int currentLevel;

	private Controller() {
	}

	/**
	 * Retourne l'instance de gestion
	 *
	 * @return l'instance de gestion
	 */
	public static Controller getInstance() {
		return Controller.g;
	}

	/**
	 * Ouvre la fenêtre menu
	 */
	public void openLoginFrame() {
		loginMenuFrame = new ULoginFrame();
	}

	/**
	 * ferme la fenetre highscore, retourne a la fenetre precedante selon le cas
	 */
	public void closeHighscoresFrame() {
		highscoresFrame.FermerHighscoresFenetre();
		if (backToMenu) {
			openLoginFrame();
		} else {
			innerMenuFrame = new UInnerMenuFrame();
		}
	}

	/**
	 * passe de la fenetre menu vers monde
	 */
	public void changeLoginToInnerFrame() {
		loginMenuFrame.closeLoginFrame();
		innerMenuFrame = new UInnerMenuFrame();
	}

	/**
	 * passe de la fenetre monde vers jeu
	 * 
	 * @param level
	 *            le niveau actuel
	 */
	public void changeInnerToGameFrame(int level) {
		innerMenuFrame.FermerMondeFenetre();
		currentLevel = level;
		gameFrame = new UCreateMapFrame();
	}

	/**
	 * passe de la fenetre menu vers highscores
	 */
	public void changeLoginToHighscoresFrame() {
		backToMenu = true;
		highscoresFrame = new UHighscoresFrame();
		loginMenuFrame.closeLoginFrame();
	}

	/**
	 * passe de la fenetre monde vers shop
	 */
	public void changeInnerToShopFrame() {
		innerMenuFrame.FermerMondeFenetre();
		shopFrame = new UShopFrame();
	}

	/**
	 * passe de la fenetre shop vers monde
	 */
	public void changeShopToInnerFrame() {
		shopFrame.FermerShopFenetre();
		innerMenuFrame = new UInnerMenuFrame();
	}

	/**
	 * passe de la fenetre monde vers highscores
	 */
	public void changeInnerToHighscoresFrame() {
		backToMenu = false;
		innerMenuFrame.FermerMondeFenetre();
		highscoresFrame = new UHighscoresFrame();
	}

	/**
	 * passe de la fenetre monde vers menu
	 */
	public void changeInnerToLoginFrame() {
		backToMenu = true;
		innerMenuFrame.FermerMondeFenetre();
		loginMenuFrame = new ULoginFrame();
	}

	/**
	 * passe de la fenetre jeu vers monde
	 */
	private void changeGameToInnerFrame() {
		gameFrame.FermerJeuFenetre();
		innerMenuFrame = new UInnerMenuFrame();
	}

	/**
	 * lorsque le joueur gagne
	 * 
	 * @param pointsGagne
	 *            le nombre de points gagnes durant la partie
	 */
	public void playerWins(int pointsGagne) {
		pointsGagne = pointsGagne * 100;
		player.setPoints(player.getPoints() + pointsGagne);
		player.setMoney(player.getMoney() + pointsGagne);
		String message = "Bravo! Vous avez gagné\nArgent gagné: "
				+ pointsGagne + "$";
		if (currentLevel == player.getMaxLevel()) {
			player.setMaxLevel(currentLevel + 1);
			message = message + "\nVous avez débloqé le niveau "
					+ (currentLevel + 1)
					+ ".\nDe nouveaux items sont disponibles dans le magasin.";
		}
		JOptionPane.showMessageDialog(gameFrame, message);
		changeGameToInnerFrame();
	}

	/**
	 * lorsque l'ennemi gagne
	 * 
	 * @param pointsGagne
	 *            le nombre de points gagnes durant la partie
	 */
	public void enemyWins(int pointsGagne) {
		pointsGagne = pointsGagne * 25;
		player.setPoints(player.getPoints() + pointsGagne);
		player.setMoney(player.getMoney() + pointsGagne);
		String message = "Vous avez perdu.\nArgent gagné: " + pointsGagne
				+ "$";
		JOptionPane.showMessageDialog(gameFrame, message);
		changeGameToInnerFrame();
	}

	/**
	 * arreter la partie avant qu'elle se termine
	 */
	public void stopMidGame() {
		int reponse = JOptionPane.showConfirmDialog(gameFrame,
				"Are you sure you want to go back to the menu?", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			changeGameToInnerFrame();
		}
	}

	/**
	 * charger une partie
	 */
	public void loadGame() {

		ArrayList<Player> listPlayers = readPlayersDatabaseFile();
		String nom = "";
		String password = "";
		try {
			nom = JOptionPane.showInputDialog(loginMenuFrame, "Name:", null,
					JOptionPane.PLAIN_MESSAGE);
		} catch (java.lang.NullPointerException e) {
		}
		if (nom != null) {
			try {
				password = JOptionPane.showInputDialog(loginMenuFrame,
						"Password:", null, JOptionPane.PLAIN_MESSAGE);
			} catch (java.lang.NullPointerException e) {
			}
			if (password != null) {
				boolean playerExiste = false;

				for (int i = 0; i < listPlayers.size(); i++) {
					if (listPlayers.get(i).getNom().equals(nom)) {
						playerExiste = true;
						if (listPlayers.get(i).getPassword().equals(password)) {
							player = listPlayers.get(i);
							// / ouvrir la fenetre monde
							changeLoginToInnerFrame();
						} else {
							JOptionPane.showMessageDialog(loginMenuFrame,
									"Wrong Password!");
						}
					}
				}
				if (!playerExiste) {
					JOptionPane.showMessageDialog(loginMenuFrame,
							"Your account does not exist!");
				}
			}
		}
	}

	/**
	 * nouvelle partie
	 */
	public void newGame() {

		String nom = "";
		try {
			nom = JOptionPane.showInputDialog(loginMenuFrame,
					"Enter a username:", null, JOptionPane.PLAIN_MESSAGE);
			while (nom.equals("") || nom.length() > 10) {
				JOptionPane.showMessageDialog(loginMenuFrame,
						"Enter a password:", null, JOptionPane.ERROR_MESSAGE);
				nom = JOptionPane.showInputDialog(loginMenuFrame,
						"Enter a username", null, JOptionPane.PLAIN_MESSAGE);
			}
		} catch (java.lang.NullPointerException e) {
		}
		if (nom != null) {

			String password = "";
			try {
				password = JOptionPane.showInputDialog(loginMenuFrame,
						"Enter a password:", null, JOptionPane.PLAIN_MESSAGE);
			} catch (java.lang.NullPointerException e) {
			}
			if (password != null) {
				ArrayList<Player> listPlayers = readPlayersDatabaseFile();
				boolean playerExists = false;
				for (int i = 0; i < listPlayers.size(); i++) {
					if (listPlayers.get(i).getNom().equals(nom)) {
						// player = listePlayer.get(i); pense pas avoir besoin
						// de cette ligne
						playerExists = true;
					}
				}
				if (!playerExists) {
					player = new Player();
					player.setNom(nom);
					player.setPassword(password);
					//
					ArrayList<Integer> itemDefault = new ArrayList<Integer>();
					itemDefault.add(1);
					player.setArmesAchete(itemDefault);
					player.setArmuresAchete(itemDefault);
					//

					listPlayers.add(player);
					writePlayersDatabaseFile(listPlayers);
					// / ouvrir la fenetre monde
					changeLoginToInnerFrame();
				} else {
					JOptionPane.showMessageDialog(loginMenuFrame,
							"Ce nom de joueur est déja utilisé");
				}
			}
		}
	}

	/**
	 * enregistrer une partie
	 */
	public void saveGame() {
		ArrayList<Player> listePlayer = readPlayersDatabaseFile();

		for (int i = 0; i < listePlayer.size(); i++) {
			if (listePlayer.get(i).getNom().equals(player.getNom())) {
				listePlayer.remove(i);
			}
		}
		listePlayer.add(player);
		writePlayersDatabaseFile(listePlayer);
	}

	/**
	 * ecrire un joueur dans le fichier qui contient la savegarde des joueurs
	 * 
	 * @param listPlayers
	 *            la liste des joueurs
	 */
	private void writePlayersDatabaseFile(ArrayList<Player> listPlayers) {
		try {
			FileOutputStream fos = new FileOutputStream("players");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listPlayers);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
		}
	}

	/**
	 * lire le fichier qui contient la savegarde des joueurs
	 * 
	 * @return listePlayer la liste des joueurs qui etaient dans le fichier
	 */
	private ArrayList<Player> readPlayersDatabaseFile() {
		ArrayList<Player> listePlayer = new ArrayList<Player>();
		try {
			FileInputStream fichier = new FileInputStream("players");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			listePlayer = (ArrayList<Player>) ois.readObject();
			ois.close();
		} catch (java.io.IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return listePlayer;
	}

	/**
	 * lire et extraire le fichier qui contient les joueurs, et trier en ordre
	 * de pointage
	 * 
	 * @return listeHighscores la liste des meilleurs scores
	 */
	public ArrayList<Player> loadHighscores() {
		ArrayList<Player> listPlayers = readPlayersDatabaseFile();
		// trier la liste en ordre decroissant
		Collections.sort(listPlayers, Collections.reverseOrder());
		// garder les 5 meilleurs pointages
		ArrayList<Player> listHighscores = new ArrayList();
		for (int i = 0; i < listPlayers.size(); i++) {
			if (i < 5) {
				listHighscores.add(listPlayers.get(i));

			}
		}
		return listHighscores;
	}

	/**
	 * demander a l'utilisateur de confirmer si il veut sauvegarder
	 */
	public void confirmSave() {
		int reponse = JOptionPane
				.showConfirmDialog(
						innerMenuFrame,
						"Are you sure that you want to overwrite your previously saved progress?",
						null, JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			saveGame();
			JOptionPane.showMessageDialog(innerMenuFrame,
					"Your progress has been saved!", null,
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * demander a l'utilisateur de confirmer si il veut retourner a la fenetre
	 * principale
	 */
	public void confirmBackToLoginFrame() {
		int reponse = JOptionPane
				.showConfirmDialog(
						innerMenuFrame,
						"Do you want to save your progress before exitting to the main menu?",
						null, JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			saveGame();
			JOptionPane.showMessageDialog(innerMenuFrame,
					"Your progress has been saved!", null,
					JOptionPane.PLAIN_MESSAGE);
			Controller.getInstance().changeInnerToLoginFrame();
		} else if (reponse == JOptionPane.NO_OPTION) {
			Controller.getInstance().changeInnerToLoginFrame();
		}
	}
}
