package factory.ItemFactory;

import model.items.Axe;

/**
 * This class represents a Factory that can create only Axe items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class AxeFactory implements IItemFactory {

    @Override
    public Axe create() {
        return new Axe("Axe", 20, 2, 10);
    }
}
