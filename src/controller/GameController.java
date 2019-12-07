package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import handlers.FinishedCombatHandler;
import handlers.LoseHandler;
import factory.ItemFactory.*;
import factory.UnitFactory.*;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;


/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private List<Tactician> tacticianList = new ArrayList<>();
  private Field field;

  private List<Tactician> turnsList = new ArrayList<>();
  private Tactician currentTactician;

  private int roundNumber = 0;
  private int maxRoundNumber;

  // Observer/listener
  // Crea referencia bidireccional entre el listener (handler) y el observer (game controller)
  private PropertyChangeSupport
          loseNotification = new PropertyChangeSupport(this),
          finishedCombatNotification = new PropertyChangeSupport(this);



  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public void gameController(int numberOfPlayers, int mapSize) {

    // avisar al roberto
    final LoseHandler loseHandler = new LoseHandler(this);
    loseNotification.addPropertyChangeListener(loseHandler);
    final FinishedCombatHandler heroDiedHandler = new FinishedCombatHandler(this);
    finishedCombatNotification.addPropertyChangeListener(heroDiedHandler);

    /* Crear mapa aleatoreo */
    field = new Field();
    field.randomField(mapSize);

    /* Crear e iniciar tacticians */
    int num = 0;
    while (num < numberOfPlayers) {
      Tactician tactician = new Tactician("Player " + String.valueOf(num), this);
      tacticianList.add(tactician);
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
    if (!turnsList.isEmpty()){
      currentTactician = turnsList.get(0);
      currentTactician.yourTurn();
    }
    // else { roundNumber++; }
  }


  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    for (Tactician tactician1 : tacticianList)
      if (tactician1.getName().equals(tactician)) {
        tacticianList.remove(tactician1);
        turnsList.remove(tactician1);
        tactician1.killUnits();
        break;
      }
  }

  /**
   *
   * @param i
   *    the tactician´s position in tacticianList
   * @return
   *    the tactician´s name
   */
  public String getTacticianName(int i) { return tacticianList.get(i).getName();}


  /**
   *
   * @param tacticianName
   *    remove this tactician from the game
   */
  public void loser(String tacticianName) {
    loseNotification.firePropertyChange(new PropertyChangeEvent(this, "NewLoser", "", tacticianName));
  }


  /**
   * Search the tactician state after a combat by its unit
   *
   * @param unit
   *      the unit that fought
   *
   */
  public void ownerState(IUnit unit) {
    if (!unit.alive()) {
      Tactician tactician = unit.getTactician();
      if((unit.isAHero() && !tactician.inTurn()) || tactician.getUnitsNumber()==1) {
        this.loser(tactician.getName());
        return;
      }
      if(unit.isAHero() ) { this.endTurn(); }
      unit.die();
    }
  }


  /**
   * Research the game state after a combat
   *
   * @param unit1
   *      the first unit in combat
   * @param unit2
   *      the second unit in combat
   */
  public void afterCombat(IUnit unit1, IUnit unit2) {
    finishedCombatNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitStudy", null, unit1));
    finishedCombatNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitStudy", null, unit2));
  }

  /*
  FACTORY
   */

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
   * Assign n items to each unit of each tactician using different factories
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

  /*
  INIT GAME
   */

  
  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of rounds the game can last
   */
  public void initGame(final int maxTurns) {
    maxRoundNumber = maxTurns;
    // asigna unidades
    this.createUnits(3, new AlpacaFactory(), new ArcherFactory(), new ClericFactory(),
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
    // asigna unidades
    this.createUnits(3, new ArcherFactory(), new ArcherFactory(), new ClericFactory(),
            new FighterFactory(), new HeroFactory(), new SorcererFactory(), new SwordMasterFactory());

    // asigna items
    this.giveItems(4, new AnimaBookFactory(), new AxeFactory(), new BowFactory(),
            new SpearFactory(), new StaffFactory(), new SwordFactory());
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    int max = 0;
    for (Tactician tactician : tacticianList)
      if (tactician.getUnitsNumber() > max)
        max = tactician.getUnitsNumber();

    for (int i=0; i<tacticianList.size(); i++)
      if (tacticianList.get(i).getUnitsNumber() == max)
        this.getTacticianName(i);

    return winners;
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














