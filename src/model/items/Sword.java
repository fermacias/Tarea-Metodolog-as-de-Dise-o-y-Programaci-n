package model.items;

import model.units.SwordMaster;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Sword</i> can equip a SwordMaster.
   */
  public void equipSword(final SwordMaster swordM) { this.equipTo(swordM); }

  /*
  COMBATE
  */

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Sword</i> is stronger than AnimaBook, LuzBook, OscuridadBook, Axe
   */
  @Override
  public boolean stronger(IEquipableItem item) {
    return item.swordStrongerThan();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Sword</i> is weaker than AnimaBook, LuzBook, OscuridadBook, spear
   */
  @Override
  public boolean weaker(IEquipableItem item) { return item.swordWeakerThan(); }

  /**
   * {@inheritDoc}
   * <p>
   * The Spear is stronger than <i>Sword</i>.
   */
  @Override
  public boolean spearStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The Axe is waker than <i>Sword</i>.
   */
  @Override
  public boolean axeWeakerThan() {return true;}


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




}
