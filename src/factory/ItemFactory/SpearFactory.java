package factory.ItemFactory;

import model.items.Spear;

/**
 * This class represents a Factory that can create only Spear items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class SpearFactory implements IItemFactory {

    @Override
    public Spear create() {
        return new Spear("Spear", 20, 2, 10);
    }

}
