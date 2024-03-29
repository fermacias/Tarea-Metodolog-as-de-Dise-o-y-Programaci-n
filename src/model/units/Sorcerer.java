package model.units;

import model.items.IEquipableItem;
import model.items.LuzBook;
import model.map.Location;

/**
 * This class represents an <i>Sorcerer</i> type unit.
 * <p>
 * This kind of unit <b>can only use books</b>.
 *
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
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
     * The <i>Sorcerer</i> can <b>only equip Books</b>.
     *
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(final IEquipableItem item) {
        item.equipSorcerer(this);
    }

    @Override
    public void addEquipableItem() {
        this.addItem(new LuzBook("LuzBook", 20, 2, 10));
    }








}
