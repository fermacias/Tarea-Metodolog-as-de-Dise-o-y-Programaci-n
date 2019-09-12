package model.items;

import model.units.Archer;

/**
 * This class represents an Bow.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Bow</i> can equip an Archer.
   */
  @Override
  public void equipArcher(final Archer archer) {
    this.equipTo(archer);
  }

  /*
  COMBATE
  */

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Bow/i> is stronger than AnimaBook, LuzBook, OscuridadBook
   */
  @Override
  public boolean stronger(IEquipableItem item) {
    return item.bowStrongerThan();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Bow/i> is weaker than AnimaBook, LuzBook, OscuridadBook, Spear
   */
  @Override
  public boolean weaker(IEquipableItem item) {return item.bowWeakerThan(); }

  /**
   * {@inheritDoc}
   * <p>
   * The AnimaBook is stronger than <i>Bow</i>.
   */
  @Override
  public boolean animaBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The LuzBook is stronger than <i>Bow</i>.
   */
  @Override
  public boolean luzBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The OscuridadBook is stronger than <i>Bow</i>.
   */
  @Override
  public boolean oscuridadBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The AnimaBook is weaker than <i>Bow</i>.
   */
  @Override
  public boolean animaBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The LuzBook is weaker than <i>Bow</i>.
   */
  @Override
  public boolean luzBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The OscuridadBook is  weaker than <i>Bow</i>.
   */
  @Override
  public boolean oscuridadBookWeakerThan() {return true;}






}
