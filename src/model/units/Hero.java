package model.units;

import model.items.IEquipableItem;
import model.items.Spear;
import model.map.Location;


/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int hitPoints, final int movement, final Location location,
              IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Hero</i> can <b>only equip Spears</b>.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipHero(this);
  }


  @Override
  public void addEquipableItem() {
    this.addItem(new Spear("Spear", 0, 0, 0));
  }

  /*
  THE GAME
   */

  @Override
  public boolean isAHero() { return true; }

}
