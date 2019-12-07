package model.units;

import java.lang.annotation.Target;
import java.util.List;

import factory.ItemFactory.IItemFactory;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public interface IUnit {

  /**
   * @param tactician
   *      set this tactician as the unit´s owner
   */
  void setTactician(Tactician tactician);


  /**
   *
   * @return
   *      the tactician whose the unit´s owner
   */
  Tactician getTactician();

  /**
   *
   * @return max hit points of the unit
   */
  int getMaxHitPoints();

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @param hp
   *    the new value to currentHitPoints
   */
  void setCurrentHitPoints(int hp);

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @param item
   *      add item to items
   */
  void addItem(IEquipableItem item);

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   *
   * @param location
   *      the newlocation to the unit
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /*
  EQUIP ITEM
   */

  /**
   *
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /*
  EXCHANGE ITEMS
   */
  /**
   *
   * An unit gives an item to another
   *
   * @param item
   *    unit give that item
   * @param unit2
   *    unite that recieve the item
   */
  void giveItem(IEquipableItem item, IUnit unit2);

  /**
   * Say if an unit can recieve a new item
   *
   * @return boolean
   *    return true if the unit can recieve an item
   *    and false if the unit cant recieve an item
   *    alpaca can always recieve an item
   *
   */
  boolean canTake();


  /*
  COMBAT
   */

  /**
   *
   * Make a combat, if the first unit is eqquiped, the item attack.
   * Then, if the second unit is eqquiped, the item attack.
   * If the unit has 0 hit points, the combat ends.
   *
   * @param unit
   *      the second unit
   *
   */
  void combat(IUnit unit);


  /*
  OBSERVER
   */

  /**
   *
   * Remove the unit from the map and the unitList
   *
   */
  void die();

  /*
  FACTORY
   */

  /**
   *
   * @param itemFactory
   *      the itemFactory I want to use
   *
   */
  void itemFactory(IItemFactory itemFactory);

  /**
   *
   * create and add to the itemsList a new item using the unitFactory
   *
   */
  void newItem();

  /**
   *
   * create and add to the itemList the equipable item of the unit
   *
   */
  void addEquipableItem();

  /**
   *
   * @return
   *      true if the unit is a Hero
   */
  boolean isAHero();


  /**
   *
   * @return
   *      true if the current hit points are not 0
   */
  boolean alive();

}
