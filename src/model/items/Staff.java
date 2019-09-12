package model.items;

import model.units.Cleric;
import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Staff</i> can equip a Cleric.
   */
  public void equipCleric( Cleric cleric) { this.equipTo(cleric); }

  /*
  COMBATE
  */

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Staff</i> is stronger than AnimaBook, LuzBook, OscuridadBook
   */
  @Override
  public boolean stronger(IEquipableItem item) {
    return item.staffStrongerThan();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Staff</i> is weaker than AnimaBook, LuzBook, OscuridadBook
   */
  @Override
  public boolean weaker(IEquipableItem item) { return item.staffWeakerThan(); }


  /**
   * {@inheritDoc}
   * <p>
   * The AnimaBook is stronger than <i>Spear</i>.
   */
  @Override
  public boolean animaBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The LuzBook is stronger than <i>Spear</i>.
   */
  @Override
  public boolean luzBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The OscuridadBook is stronger than <i>Spear</i>.
   */
  @Override
  public boolean oscuridadBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The AnimaBook is weaker than <i>Spear</i>.
   */
  @Override
  public boolean animaBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The LuzBook is weaker than <i>Spear</i>.
   */
  @Override
  public boolean luzBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The OscuridadBook is weaker than <i>Spear</i>.
   */
  @Override
  public boolean oscuridadBookWeakerThan() {return true;}


  /**
   * {@inheritDoc}
   * <p>
   * The <i>Staff</i> cannot attack.
   */
  @Override
  public void attack(IEquipableItem item2) {  }

  /**
   *
   * @param unit2
   *    Is cured by the staff
   */
  public void heal(IUnit unit2) {
    int minDist = minRange;
    int maxDist = maxRange;
    double dist = this.getOwner().getLocation().distanceTo(unit2.getLocation());

    if (minDist <= dist && dist <= maxDist) {
      int hp = this.getPower() + unit2.getCurrentHitPoints();

      if(hp > unit2.getMaxHitPoints()) { hp = unit2.getMaxHitPoints(); }
      unit2.setCurrentHitPoints(hp);

    }
  }



}
