package factory.UnitFactory;

import model.map.Location;
import model.units.Fighter;

/**
 * This class represents a Factory that can create only Fighter units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class FighterFactory implements IUnitFactory {

    @Override
    public Fighter create() {
        return new Fighter(0,0, null);
    }
}