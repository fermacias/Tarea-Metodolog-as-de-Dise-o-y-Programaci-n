package model.items;

import model.units.Archer;
import model.units.Fighter;
import model.units.SwordMaster;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
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

  public void equipSword(final SwordMaster swordM) {
    this.equipTo(swordM);
  }

  /*
  COMBATE
  */

  @Override
  public boolean stronger(IEquipableItem item) {
    return item.swordStrongerThan();
  }

  @Override
  public boolean weaker(IEquipableItem item) { return item.swordWeakerThan(); }


  @Override
  public boolean spearStrongerThan() {return true;}

  @Override
  public boolean axeWeakerThan() {return true;}


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
