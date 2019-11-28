package factory.ItemFactory;

import model.items.Staff;

/**
 * This class represents a Factory that can create only Staff items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class StaffFactory implements IItemFactory {

    public Staff create() {
        return new Staff("Staff", 0, 0, 0);
    }

}
