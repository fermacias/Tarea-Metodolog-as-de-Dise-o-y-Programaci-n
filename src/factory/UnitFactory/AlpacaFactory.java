package factory.UnitFactory;

import model.map.Location;
import model.units.Alpaca;

/**
 * This class represents a Factory that can create only Alpaca units.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class AlpacaFactory implements IUnitFactory {

    @Override
    public Alpaca create() {
        return new Alpaca(0,0, null);
    }

}
