package factory.UnitFactory;

import model.map.Location;
import model.units.Hero;

/**
 * This class represents a Factory that can create only Hero units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class HeroFactory implements IUnitFactory {

    @Override
    public Hero create() {
        return new Hero(0,0, null);
    }
}