package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public class Sorcerer extends AbstractUnit{
    /**
     * Creates a new sorcerer
     *
     * @param hitPoints
     *     maximum hit points of the unit
     * @param movement
     *     the amount of cells this unit can move
     * @param position
     *     the initial position of this unit
     * @param items
     *     the items carried by this unit
     */
    public Sorcerer(final int hitPoints, final int movement, final Location position,
                  final IEquipableItem... items) {
        super(hitPoints, movement, position, 3, items);
    }

    /**
     * Sets the currently equipped item of this unit.
     * <p>
     * The <i>Archer</i> can <b>only equip Bows</b>.
     *
     * @param item
     *     the item to equip
     */
    public void equipItem(final IEquipableItem item) {
        item.equipSorcerer(this);
    }









}
