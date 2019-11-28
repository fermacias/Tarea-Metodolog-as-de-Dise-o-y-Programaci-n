package factory.UnitFactory;

import model.map.Location;
import model.units.Archer;

/**
 * This class represents a Factory that can create only Archer units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class ArcherFactory implements IUnitFactory {

    @Override
    public Archer create() {
        return new Archer(0,0, new Location(0,0));
    }

}
