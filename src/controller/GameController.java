package controller;

import java.util.ArrayList;
import java.util.List;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

// import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
  * Controller of the game.
  * The controller manages all the input received from de game's GUI.
  *
  * @author Ignacio Slater Muñoz
  * @author Fernanda Macías Herrera
  * @version 2.0
  * @since 2.0
  */
public class GameController { // Diosito

  private final int numberOfPlayers;
  private int mapSize;
  private List<Tactician> tacticianList = new ArrayList<>(); //lista de observers jijij


  //revisar
  private Tactician currentTactician; // Tactician que esta jugando
  private int maxTurns;



  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(final int  numberOfPlayers, int mapSize) {
    this.numberOfPlayers = numberOfPlayers;
    this.mapSize = mapSize;
  }

  /**
   *
   * @return the number of players
   */
  public int getNumberOfPlayers() { return numberOfPlayers; }

  /**
   *
   * @return the map size
   */
  public int getMapsize() {return mapSize;}

  /**
   *
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() { return tacticians; }

  //revisar
  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return null;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return null;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return 0;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {

  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    if(tacticians.contains(tactician)) { tacticians.remove(tactician); }
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    this.maxTurns = maxTurns;

  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.maxTurns = -1;

  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    for(Tactician tactician : tacticianList)
      winners.add(tactician.getName());
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return currentTactician.getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {

  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return this.getSelectedUnit().getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    this.getSelectedUnit().equipItem(this.getSelectedUnit().getItems().get(index));
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {
  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

  }
}









/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameControlle {

  List<Tactician> tacticianList = new ArrayList<>();
  Field field;

  List<Tactician> turnsList = newArrayList<>();
  Tactician currentTactician;

  int roundNumber = 0;
  final int maxRoundNumber;



  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {

    /* Crear mapa aleatoreo */
    field = new Field();
    field.addCells(true, new Location(0,0));
    // agregar mapSize celdas


    /* Crear e iniciar tacticians */
    int num = 1;
    while (num <= numberOfPlayers) {
      Tactician tactician = new Tactician("Player " + String.valueOf(num), this);
      tacticianList.add(tactician);
      // iniciar y asignar sus unidades
      num++;
    }


  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return tacticianList;
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return field;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return currentTactician;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return roundNumber;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRoundNumber;
  }

  /* HACER */
  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
  }

  /* HACER */
  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
  }

  /* HACER */
  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
  }

  /* HACER */
  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    return null;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return null;
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {

  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return null;
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {

  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {

  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {

  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

  }
}














