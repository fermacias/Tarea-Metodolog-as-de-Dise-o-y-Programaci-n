package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.IntStream;


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

  private Random random = new Random();

  // Observer/listener
  // Crea referencia bidireccional entre el listener (handler) y el observer (game controller)
  private PropertyChangeSupport
          loseNotification = new PropertyChangeSupport(this),
          finishedCombatNotification = new PropertyChangeSupport(this);

  private IUnit selectedUnit;
  private IEquipableItem selectedItem;


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   * @param seed            the seed to the map creation
   */
  public GameController(int numberOfPlayers, int mapSize, long seed) {
    // Initialize the handlers
    final LoseHandler loseHandler = new LoseHandler(this);
    loseNotification.addPropertyChangeListener(loseHandler);
    final FinishedCombatHandler heroDiedHandler = new FinishedCombatHandler(this);
    finishedCombatNotification.addPropertyChangeListener(heroDiedHandler);

    // Create a new random map
    field = new Field();
    field.getRandom().setSeed(seed);
    field.randomField(mapSize);

    // Create the tacticians
    for (int num = 1; num <= numberOfPlayers; num++) {
      Tactician tactician = new Tactician("Player " + num);
      tacticianList.add(tactician);
    }
  }

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    // Initialize the handlers
    final LoseHandler loseHandler = new LoseHandler(this);
    loseNotification.addPropertyChangeListener(loseHandler);
    final FinishedCombatHandler heroDiedHandler = new FinishedCombatHandler(this);
    finishedCombatNotification.addPropertyChangeListener(heroDiedHandler);

    // Create a new random map
    field = new Field();
    field.randomField(mapSize);

    // Create the tacticians
    for (int num = 1; num <= numberOfPlayers; num++) {
      Tactician tactician = new Tactician("Player " + num);
      tacticianList.add(tactician);
    }
  }

  /* ACCESS METHODS */

  /**
   *
   * @return the Random of this controller
   */
  public Random getRandom() { return random; }

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
      int n = this.tacticianList.size()-1;
      if(roundNumber != 0 && turnsList.size()==0) { n--; }
      int r = (int) (random.nextDouble()*n);
      turnsList.add(tacticianList.remove(r));
    }
    tacticianList.addAll(turnsList);
    currentTactician = turnsList.remove(0);
    currentTactician.yourTurn();
  }


  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if (!turnsList.isEmpty()){      // si aun quedan turnos por jugar
      currentTactician.yourTurnEnds();
      currentTactician = turnsList.remove(0);
      currentTactician.yourTurn();
    }
    else {  // si se acabo la ronda, pero no es la ultima
      roundNumber++;
      if (roundNumber!=maxRoundNumber) { this.newOrder(); }
    }
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
        if (tactician1.inTurn()) { this.endTurn(); }
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
   * Update the tactician state after one of his/her units died in a combat
   *
   * @param unit
   *      the unit that fought
   *
   */
  public void ownerState(IUnit unit) {
    Tactician tactician = unit.getTactician();
    if ((unit.isAHero() && !tactician.inTurn()) || tactician.getUnitsNumber()==1) {
      this.loser(tactician.getName());
      return;
    }
    if (unit.isAHero()) { this.endTurn(); }
    unit.die();
  }


  /**
   * Update the game state after a combat
   *
   * @param unit1
   *      the first unit in combat
   * @param unit2
   *      the second unit in combat
   */
  public void afterCombat(IUnit unit1, IUnit unit2) {
    if (!unit1.alive())
      finishedCombatNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitDied", null, unit1));

    if (!unit2.alive())
      finishedCombatNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitDied", null, unit2));
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
    int x=0, y=0;
    List<IUnitFactory> factoryList = new ArrayList<>();
    Collections.addAll(factoryList, factories);
    for(Tactician tactician : tacticianList) {
      for(int i=0; i<n; i++) {
        tactician.unitFactory(factoryList.get(0));
        tactician.newUnit();
        tactician.newLocation(i, field.getCell(x, y));
        factoryList.add(factoryList.remove(0));
        int s = (int)Math.sqrt(field.getSize());
        if ((x+1)*s<field.getSize() || y==s-1) { x++; y=0; }
        else { y++; }
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
    ArrayList<IItemFactory> factoryList = new ArrayList<>();
    Collections.addAll(factoryList, factories);
    for(Tactician tactician : tacticianList) {
      tactician.createItems(4, factoryList);
    }
  }


  /*
  INIT GAME
   */

  /**
   * Create new units for each Tactician, and new items for each unit
   */
  public void newDistribution() {
    // Assign units
    this.createUnits(3, new HeroFactory(), new AlpacaFactory(), new ArcherFactory(), new ClericFactory(),
            new FighterFactory(), new SorcererFactory(), new SwordMasterFactory());

    // Assign items
    this.giveItems(4, new AnimaBookFactory(), new AxeFactory(), new BowFactory(),
            new SpearFactory(), new StaffFactory(), new SwordFactory());
  }




  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of rounds the game can last
   */
  public void initGame(final int maxTurns) {
    turnsList = new ArrayList<>();
    maxRoundNumber = maxTurns;
    roundNumber = 0;
    this.newOrder();
    this.newDistribution();
  }


  /**
   * Starts a game without a limit of rounds.
   */
  public void initEndlessGame() {
    turnsList = new ArrayList<>();
    maxRoundNumber = -1;
    roundNumber = 0;
    this.newOrder();
    this.newDistribution();
  }


  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    if (tacticianList.size() == 1) { winners.add(this.getTacticianName(0)); }
    else if (maxRoundNumber==roundNumber) {
      int max = 0;
      for (Tactician tactician : tacticianList) {
        if (tactician.getUnitsNumber() > max)
          max = tactician.getUnitsNumber();
      }
      for (int i = 0; i < tacticianList.size(); i++) {
        if (tacticianList.get(i).getUnitsNumber() == max)
          winners.add(this.getTacticianName(i));
        tacticianList.get(i).killUnits();
      }
    }
    else
      return null;
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
    selectedUnit = field.getCell(x,y).getUnit();
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return selectedUnit.getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    selectedUnit.equipItem(this.getItems().get(index));
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
    selectedUnit.combat(field.getCell(x,y).getUnit());
  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {
    selectedItem = selectedUnit.getItems().get(index);
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
    selectedUnit.giveItem(selectedItem, field.getCell(x,y).getUnit());
  }
}














