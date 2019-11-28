package factory.ItemFactory;

import model.items.AnimaBook;

/**
 * This class represents a Factory that can create only AnimaBook items.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class AnimaBookFactory implements IItemFactory {

    @Override
    public AnimaBook create() {
        return new AnimaBook("AnimaBook", 0, 0, 0);
    }

}
