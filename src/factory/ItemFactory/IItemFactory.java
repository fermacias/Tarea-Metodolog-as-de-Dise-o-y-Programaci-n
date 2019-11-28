package factory.ItemFactory;

import model.items.IEquipableItem;

/**
 * This interface represents a Factory that can create all type of equipable items.
 * <p>
 * Apply the Factory Desing Pattern.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public interface IItemFactory {

    /**
     *
     * @return
     *      the created unit
     */
    IEquipableItem create();

}
