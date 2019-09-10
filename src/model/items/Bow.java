package model.items;

import model.units.Archer;
import model.units.Cleric;
import model.units.Fighter;
import model.units.SwordMaster;

/**
 * @author Ignacio Slater Muñoz
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

  public void equipArcher(final Archer archer) {
    this.equipTo(archer);
  }

  /*
  COMBATE
  */

  public boolean stronger(IEquipableItem item) {
    return item.bowStrongerThan();
  }

  public boolean weaker(IEquipableItem item) {return item.bowWeakerThan(); }


  @Override
  public boolean animaBookStrongerThan() {return true;}

  @Override
  public boolean luzBookStrongerThan() {return true;}

  @Override
  public boolean oscuridadBookStrongerThan() {return true;}

  @Override
  public boolean animaBookWeakerThan() {return true;}

  @Override
  public boolean luzBookWeakerThan() {return true;}

  @Override
  public boolean oscuridadBookWeakerThan() {return true;}






}
