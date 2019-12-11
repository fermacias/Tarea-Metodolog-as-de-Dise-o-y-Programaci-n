package factory.UnitFactory;

import model.map.Location;
import model.units.Sorcerer;

/**
 * This class represents a Factory that can create only Sorcerer units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class SorcererFactory implements IUnitFactory {

    @Override
    public Sorcerer create() {
        return new Sorcerer(0,0, null);
    }
}
