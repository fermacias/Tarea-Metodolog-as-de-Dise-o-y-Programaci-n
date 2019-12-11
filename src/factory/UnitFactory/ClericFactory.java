package factory.UnitFactory;

import model.map.Location;
import model.units.Cleric;

/**
 * This class represents a Factory that can create only Cleric units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class ClericFactory implements IUnitFactory {

    @Override
    public Cleric create() {
        return new Cleric(0,0, null);
    }
}
