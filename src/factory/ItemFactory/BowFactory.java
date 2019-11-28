package factory.ItemFactory;

import model.items.Bow;

/**
 * This class represents a Factory that can create only Bow items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class BowFactory implements IItemFactory {

    @Override
    public Bow create() {
        return new Bow("Bow", 0, 0, 0);
    }

}
