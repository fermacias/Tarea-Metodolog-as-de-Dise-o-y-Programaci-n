package model.items;

import model.units.Archer;
import model.units.Fighter;
import model.units.SwordMaster;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
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


  public void equipArcher(final Archer archer) {  }

  public void equipFighter(final Fighter fighter) {
    this.equipTo(fighter);
  }

  public void equipSword(final SwordMaster swordM) {  }

}
