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
  public boolean stronger(IEquipableItem item) {
    return item.weakerThanSword();
  }

  @Override
  public boolean weakerThanSpear() { return true; }





}
