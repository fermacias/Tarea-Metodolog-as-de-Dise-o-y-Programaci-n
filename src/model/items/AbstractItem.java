package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);                //El min debe ser mayor a 0
    this.maxRange = Math.max(maxRange, this.minRange);    //El max debe ser mayor estricto al min
  }

  @Override
  public void equipTo(final IUnit unit) {
    unit.setEquippedItem(this);
    owner = unit;
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public void setOwner(IUnit unit) { this.owner = unit; }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  /*
  PARA EQUIPAR
  */

  public void equipArcher(final Archer archer) {  }

  public void equipFighter(final Fighter fighter) {  }

  public void equipSword(final SwordMaster sword) {  }

  public void equipCleric(final Cleric cleric) {  }

  public void equipHero(final Hero hero) {  }

  public void equipSorcerer(final Sorcerer sorcerer) {  }

  /*
  PARA EL COMBATE
  */

  public boolean animaBookStrongerThan() {return false;}

  public boolean axeStrongerThan() {return false;}

  public boolean luzBookStrongerThan() {return false;}

  public boolean oscuridadBookStrongerThan() {return false;}

  public boolean spearStrongerThan() {return false;}

  public boolean swordStrongerThan() {return false;}

  public boolean staffStrongerThan() {return false;}

  public boolean bowStrongerThan() {return false;}

  public boolean animaBookWeakerThan() {return false;}

  public boolean axeWeakerThan() {return false;}

  public boolean luzBookWeakerThan() {return false;}

  public boolean oscuridadBookWeakerThan() {return false;}

  public boolean spearWeakerThan() {return false;}

  public boolean swordWeakerThan() {return false;}

  public boolean staffWeakerThan() {return false;}

  public boolean bowWeakerThan() {return false;}

  /*
  COMBAT
   */

  @Override
  public void attack(IEquipableItem item2) {
    int minDist = minRange;
    int maxDist = maxRange;
    double dist = this.owner.getLocation().distanceTo(item2.getOwner().getLocation());

    if (minDist <= dist && dist <= maxDist) {
      int damage = power;
      if(this.stronger(item2)) { damage = (int)(damage * 1.5); }
      if(this.weaker(item2)) { damage = damage - 20; }

      if(damage < 0) { damage = 0; }
      item2.getOwner().setCurrentHitPoints(damage);

    }
  }

  public boolean IamNull(){
    return false;
  }


}
