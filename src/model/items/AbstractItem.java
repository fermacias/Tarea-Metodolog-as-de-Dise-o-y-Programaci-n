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

  public boolean weakerThanAnimaBook() { return false; }

  public boolean weakerThanAxe() { return false; }

  public boolean weakerThanLuzBook() { return false; }

  public boolean weakerThanOscuridadBook() { return false; }

  public boolean weakerThanSpear() { return false; }

  public boolean weakerThanSword()  { return false; }

  public boolean weakerThanStaff()  { return false; }

  public boolean weakerThanBow()  { return false; }



}
