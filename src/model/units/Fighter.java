package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.map.Location;


/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernandda Macías Herrera
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
                 IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Fighter</i> can <b>only equip Axes</b>.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipFighter(this);
  }


  @Override
  public void addEquipableItem() {
    this.addItem(new Axe("Axe", 20, 2, 10));
  }

}