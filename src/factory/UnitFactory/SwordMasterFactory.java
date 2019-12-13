package factory.UnitFactory;

import model.map.Location;
import model.units.SwordMaster;

/**
 * This class represents a Factory that can create only Sword Master units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class SwordMasterFactory implements IUnitFactory {

    @Override
    public SwordMaster create() {
        return new SwordMaster(100,0, null);
    }

}
