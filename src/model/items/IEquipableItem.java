package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @param unit
   *    es el nuevo owner del item
   */
  void setOwner(IUnit unit);


  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();


  void equipArcher(Archer archer);

  void equipFighter(Fighter fighter);

  void equipSword(SwordMaster swordM);

  void equipCleric(Cleric cleric);

  void equipHero(Hero hero);

  void equipSorcerer(Sorcerer sorcerer);


  /*
  COMBATE
   */

  /**
   *
   * @param item
   *      inidica si un item es fuerte con respecto a este
   */
  boolean stronger(IEquipableItem item);

  boolean animaBookStrongerThan();

  boolean axeStrongerThan();

  boolean luzBookStrongerThan();

  boolean oscuridadBookStrongerThan();

  boolean spearStrongerThan();

  boolean swordStrongerThan();

  boolean staffStrongerThan();

  boolean bowStrongerThan();

  boolean weaker(IEquipableItem item);

  boolean animaBookWeakerThan();

  boolean axeWeakerThan();

  boolean luzBookWeakerThan();

  boolean oscuridadBookWeakerThan();

  boolean spearWeakerThan();

  boolean swordWeakerThan();

  boolean staffWeakerThan();

  boolean bowWeakerThan();


  void attack(IEquipableItem item2);


  boolean IamNull();






}
