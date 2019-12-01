package model.units;

import model.items.IEquipableItem;
import model.items.Sword;
import model.map.Location;


/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
                     IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>SwordMaster</i> can <b>only equip Swords</b>.
   *
   * @param item the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.equipSword(this);
  }

  @Override
  public void addEquipableItem() {
    this.addItem(new Sword("Sword", 0, 0, 0));
  }

}


