package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import factory.ItemFactory.*;
import factory.UnitFactory.*;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeListener;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private List<Tactician> tacticianList = new ArrayList<>();
  private Field field;

  private List<Tactician> turnsList = new ArrayList<>();
  private Tactician currentTactician;

  int roundNumber = 0;
  int maxRoundNumber;



  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public void gameController(int numberOfPlayers, int mapSize) {

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

  /* ACCESS METHODS */

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return tacticianList;
  }

  /**
   * @return the list of the tactician´s turns to play.
   */
  public List<Tactician> getTurns() {
    return turnsList;
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


  /* PLAY */

  /**
   * Create new random turns to play a new round.
   * A player can not have two turns followed.
   */
  public void newOrder() {
    while (this.tacticianList.size()!=0) {
      int n = this.tacticianList.size();
      if(roundNumber != 0 && turnsList.size()==0) { n--; }
      // It choose a new Tactician to the next turn
      int r = (int)(Math.random()*(n));
      turnsList.add(tacticianList.remove(r));
    }
    tacticianList = turnsList;
  }


  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    // puede haber algo mas jejjej
    // falta completar
    turnsList.remove(currentTactician);
    if(!turnsList.isEmpty()){
      currentTactician = turnsList.get(0);
    }
  }


  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {

    // if the tactician lose during his/her turn
    if(currentTactician.getName().equals(tactician)) {
      tacticianList.remove(currentTactician);
      this.endTurn();
      return;
    }

    for(Tactician tactician1 : tacticianList) {
      if(tactician1.getName().equals(tactician)) {
        tacticianList.remove(tactician1);
        turnsList.remove(tactician1);
        return;
      }
    }

  }

  /**
   * Assign units to each tactician, and items to each unit
   *
   * @param n
   *    the number of units per tactician
   * @param factories
   *    the factories to creat the units
   */
  public void createUnits(int n, IUnitFactory... factories) {
    List<IUnitFactory> factoryList = Arrays.asList(factories);
    for(Tactician tactician : tacticianList) {
      for(int i=0; i<n; i++) {
        tactician.unitFactory(factoryList.get(0));
        tactician.newUnit();
        factoryList.add(factoryList.remove(0));
      }
    }
  }


  /**
   * Assing n items to each unit of each tactician using different factories
   * @param n
   *    the number of items per unit
   * @param factories
   *    the factories to be used
   */
  public void giveItems(int n, IItemFactory... factories) {
    List<IItemFactory> factoryList = Arrays.asList(factories);
    for(Tactician tactician : tacticianList) {
      tactician.createItems(4, factoryList);
    }
  }




  /* HACER */
  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of rounds the game can last
   */
  public void initGame(final int maxTurns) {
    maxRoundNumber = maxTurns;
    // asigna unidades
    this.createUnits(3, new ArcherFactory(), new ArcherFactory(), new ClericFactory(),
            new FighterFactory(), new HeroFactory(), new SorcererFactory(), new SwordMasterFactory());

    // asigna items
    this.giveItems(4, new AnimaBookFactory(), new AxeFactory(), new BowFactory(),
              new SpearFactory(), new StaffFactory(), new SwordFactory());

    // asigna area de inicio




  }

  /* HACER */
  /**
   * Starts a game without a limit of rounds.
   */
  public void initEndlessGame() {
    maxRoundNumber = -1;
    // asigna unidades e items
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














