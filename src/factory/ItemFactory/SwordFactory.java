package factory.ItemFactory;

import model.items.Sword;

/**
 * This class represents a Factory that can create only Sword items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class SwordFactory implements IItemFactory {

    @Override
    public Sword create() {
        return new Sword("Sword", 20, 2, 10);
    }

}
