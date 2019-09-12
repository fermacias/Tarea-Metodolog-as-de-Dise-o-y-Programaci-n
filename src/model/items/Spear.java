package model.items;

import model.units.Hero;

/**
* This class represents a <i>spear</i>.
* <p>
* Spears are strong against swords and weak against axes
*
* @author Ignacio Slater Muñoz
* @author Fernanda Macías Herrera
* @since 1.0
*/
public class Spear extends AbstractItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
  super(name, power, minRange, maxRange);
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Spear</i> can equip a Hero.
   */
  public void equipHero(final Hero hero){
  this.equipTo(hero);
  }

  /*
  COMBATE
  */

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Spear</i> is stronger than AnimaBook, LuzBook, OscuridadBook, Sword
   */
  @Override
  public boolean stronger(IEquipableItem item) {
  return item.spearStrongerThan();
  }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Spear</i> is weaker than AnimaBook, LuzBook, OscuridadBook, axe
   */
  @Override
  public boolean weaker(IEquipableItem item) { return item.spearWeakerThan(); }

  /**
   * {@inheritDoc}
   * <p>
   * The Axe is stronger than <i>Spear</i>.
   */
  @Override
  public boolean axeStrongerThan() { return true; }

  /**
   * {@inheritDoc}
   * <p>
   * The Sword is weaker than <i>Spear</i>.
   */
  @Override
  public boolean swordWeakerThan() { return true; }

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
