package factory.ItemFactory;

import model.items.LuzBook;

/**
 * This class represents a Factory that can create only LuzBook items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class LuzBookFactory implements IItemFactory {

    @Override
    public LuzBook create() {
        return new LuzBook("LuzBook", 20, 2, 10);
    }

}
