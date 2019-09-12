package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
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

  /*
  EQUIP
   */

  /**
   * @param archer
   *    if the item is a bow, is equipped by the item
   */
  void equipArcher(Archer archer);

  /**
   * @param fighter
   *    if the item is an axe, is equipped by the item
   */
  void equipFighter(Fighter fighter);

  /**
   * @param swordM
   *    if the item is a sword, is equipped by the item
   */
  void equipSword(SwordMaster swordM);

  /**
   * @param cleric
   *    if the item is a staff, is equipped by the item
   */
  void equipCleric(Cleric cleric);

  /**
   * @param hero
   *    if the item is a spear, is equipped by the item
   */
  void equipHero(Hero hero);

  /**
   * @param sorcerer
   *    if the item is a book, is equipped by the item
   */
  void equipSorcerer(Sorcerer sorcerer);


  /*
  COMBATE
   */

  /**
   * @param item
   *    is item2
   *
   * @return boolean
   *      says if the item is stronger that item2
   */
  boolean stronger(IEquipableItem item);

  /**
   * @return boolean
   *    says if an anima book is stronger than the item
   */
  boolean animaBookStrongerThan();

  /**
   * @return boolean
   *    says if an axe is stronger than the item
   */
  boolean axeStrongerThan();

  /**
   * @return boolean
   *    says if a luz book is stronger than the item
   */
  boolean luzBookStrongerThan();

  /**
   * @return boolean
   *    says if an oscuridad book is stronger than the item
   */
  boolean oscuridadBookStrongerThan();

  /**
   * @return boolean
   *    says if a spear is stronger than the item
   */
  boolean spearStrongerThan();

  /**
   * @return boolean
   *    says if a sword is stronger than the item
   */
  boolean swordStrongerThan();

  /**
   * @return boolean
   *    says if a staff is stronger than the item
   */
  boolean staffStrongerThan();

  /**
   * @return boolean
   *    says if a bow is stronger than the item
   */
  boolean bowStrongerThan();

  /**
   * @param item
   *    is item2
   *
   * @return boolean
   *      says if the item is weaker that item2
   */
  boolean weaker(IEquipableItem item);

  /**
   * @return boolean
   *    says if an anima book is weaker than the item
   */
  boolean animaBookWeakerThan();

  /**
   * @return boolean
   *    says if an axe is weaker than the item
   */
  boolean axeWeakerThan();

  /**
   * @return boolean
   *    says if a luz book is weaker than the item
   */
  boolean luzBookWeakerThan();

  /**
   * @return boolean
   *    says if an oscuridad book is weaker than the item
   */
  boolean oscuridadBookWeakerThan();

  /**
   * @return boolean
   *    says if a spear is weaker than the item
   */
  boolean spearWeakerThan();

  /**
   * @return boolean
   *    says if a sword is weaker than the item
   */
  boolean swordWeakerThan();

  /**
   * @return boolean
   *    says if a staff is weaker than the item
   */
  boolean staffWeakerThan();

  /**
   * @return boolean
   *    says if a bow is weaker than the item
   */
  boolean bowWeakerThan();

  /**
   * @param item2
   *    is attacked by the item
   */
  void attack(IEquipableItem item2);

  /**
   * @return boolean
   *    says if the item is a NullItem
   */
  boolean IamNull();






}
