package BL.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe qui contient un joueur, qui contient son nom, mot de passe, argent, pointage,
 * niveau maximal atteint, les armes ainsi que les armues qu'il a achete et l'arme et l'armure qu'il a sur lui.
 * @author Eric Kavalec
 */
public class Player implements Serializable, java.lang.Comparable {

    private String nom;
    private String password;
    private int money;
    private int points;
    private int maxLevel;
    public ArrayList<Integer> armesAchete = new ArrayList<Integer>();
    private ArrayList<Integer> armuresAchete = new ArrayList<Integer>();
    private int armeEquip;
    private int armureEquip;

    public Player() {
        nom = "NewGame";
        password = "";
        points = 0;
        money = 0;
        maxLevel = 1;
        armesAchete = null;
        armuresAchete = null;
        armeEquip = 1;
        armureEquip = 1;
    }

    /**
     * @param nom le nom du joueur
     * @param password son mot de passe
     * @param son pointage total
     * @param son argent
     * @param le niveau maximal atteint
     * @param armesAchete la liste des armes qu'il a achete
     * @param armuresAchete la liste des armures qu'il a achete
     * @param armeEquip l'arme qu'il a sur lui actuellement
     * @param armureEquip l'armure qu'il a sur lui actuellement
     */
    public Player(String nom, String password, int points, int money, int MaxLevel, ArrayList<Integer> armesAchete, ArrayList<Integer> armuresAchete, int armeEquip, int armureEquip) {
        this.nom = nom;
        this.password = password;
        this.points = points;
        this.money = money;
        this.maxLevel = MaxLevel;
        this.armesAchete = armesAchete;
        this.armuresAchete = armuresAchete;
        this.armeEquip = armeEquip;
        this.armureEquip = armureEquip;
    }

    /**
     * pour comparer le pointage entre deux joueurs, ce qui sert a trier la liste des joueurs 
     * selon leur pointage pour determiner les meilleurs scores
     */
    @Override
    public int compareTo(Object otherPlayer) {
        int points1 = ((Player) otherPlayer).getPoints();
        int points2 = this.getPoints();
        if (points1 > points2) {
            return -1;
        } else if (points1 == points2) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * @param maxLevel the maxLevel to set
     */
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the armeEquip
     */
    public int getArmeEquip() {
        return armeEquip;
    }

    /**
     * @param armeEquip the armeEquip to set
     */
    public void setArmeEquip(int armeEquip) {
        this.armeEquip = armeEquip;
    }

    /**
     * @return the armureEquip
     */
    public int getArmureEquip() {
        return armureEquip;
    }

    /**
     * @param armureEquip the armureEquip to set
     */
    public void setArmureEquip(int armureEquip) {
        this.armureEquip = armureEquip;
    }

    /**
     * @return the armesAchete
     */
    public ArrayList<Integer> getArmesAchete() {
        return armesAchete;

    }

    /**
     * @param armesAchete the armesAchete to set
     */
    public void setArmesAchete(ArrayList<Integer> armesAchete) {
        this.armesAchete = armesAchete;
    }

    /**
     * @return the armuresAchete
     */
    public ArrayList<Integer> getArmuresAchete() {
        return armuresAchete;
    }

    /**
     * @param armuresAchete the armuresAchete to set
     */
    public void setArmuresAchete(ArrayList<Integer> armuresAchete) {
        this.armuresAchete = armuresAchete;
    }
}
