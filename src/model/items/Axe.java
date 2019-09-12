package model.items;

import model.units.Fighter;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 * @since 1.0
 */
public class Axe extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Axe</i> can equip a Fighter.
   */
  @Override
  public void equipFighter(final Fighter fighter) {
    this.equipTo(fighter);
  }

  /*
  COMBATE
  */

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Axe/i> is stronger than AnimaBook, LuzBook, OscuridadBook, Spear
   */
  @Override
  public boolean stronger(IEquipableItem item) {
    return item.axeStrongerThan();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Axe/i> is stronger than AnimaBook, LuzBook, OscuridadBook, Sword
   */
  @Override
  public boolean weaker(IEquipableItem item) { return item.axeWeakerThan(); }

  /**
   * {@inheritDoc}
   * <p>
   * The sword is stronger than <i>Axe</i>.
   */
  @Override
  public boolean swordStrongerThan()  { return true; }

  /**
   * {@inheritDoc}
   * <p>
   * The spear is weaker than <i>Axe</i>.
   */
  @Override
  public boolean spearWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The AnimaBook is stronger than <i>Axe</i>.
   */
  @Override
  public boolean animaBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The LuzBook is stronger than <i>Axe</i>.
   */
  @Override
  public boolean luzBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The oscuridadBook is stronger than <i>Axe</i>.
   */
  @Override
  public boolean oscuridadBookStrongerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The animaBook is weaker than <i>Axe</i>.
   */
  @Override
  public boolean animaBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The luzBook is weaker than <i>Axe</i>.
   */
  @Override
  public boolean luzBookWeakerThan() {return true;}

  /**
   * {@inheritDoc}
   * <p>
   * The oscuridadBook is weaker than <i>Axe</i>.
   */
  @Override
  public boolean oscuridadBookWeakerThan() {return true;}


}
