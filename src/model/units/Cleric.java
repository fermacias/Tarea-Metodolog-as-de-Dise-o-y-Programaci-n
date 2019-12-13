package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;


/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
                IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }


  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Cleric</i> can <b>only equip Staffs</b>.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipCleric(this);
  }


  /**
   * {@inheritDoc}
   * <p>
   * The <i>Cleric</i> cannot begin a combat.
   */
  @Override
  public void combat(IUnit unit2) {  }

  @Override
  public void addEquipableItem() {
    this.addItem(new Staff("Staff", 20, 2, 10));
  }





}
