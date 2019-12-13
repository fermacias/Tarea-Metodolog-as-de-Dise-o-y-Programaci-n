package factory.ItemFactory;

import model.items.OscuridadBook;

/**
 * This class represents a Factory that can create only OscuridadBook items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class OscuridadBookFactory implements IItemFactory {

    @Override
    public OscuridadBook create() {
        return new OscuridadBook("OscuridadBook", 20, 2, 10);
    }

}
