package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

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

  /**
   * @param item
   *    se entrega
   * @param unit2
   *    recibe el item
   */
  void giveItem(IEquipableItem item, IUnit unit2);

  /**
   * @return boolean
   *    indica si una unidad puede recibir un item o no
   *    en las alpacas siempre es true
   *    en el resto es true si tiene menos de tres items
   */
  boolean canTake();


  /*
  COMBATE
   */

  void attack(IUnit unit2);


  /**
   *
   * unit1.combat(item, unit1, true) indica un ataque
   * caso 1: si el ataque es posible se produce y se ejecuta
   * unit2.combat(item, unit2, false) el contra ataque
   * si estes es posible se ejecuta y el combate acaba
   * caso 2: si el ataque no es posible el combate acaba
   *
   * si en algun momento del combate una de las unidades es
   * derrotada el combate acaba
   * para atacar se debe estar equipado
   *
   * @param unit
   *      unidad a la que deseo atacar
   * @param bool
   *      es true si es el primer ataque
   *      es false si es el contra ataque
   */
  void combat(IUnit unit, boolean bool);


}
