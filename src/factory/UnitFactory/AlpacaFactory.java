package factory.UnitFactory;

import model.map.Location;
import model.units.Alpaca;

public class AlpacaFactory implements UnitFactory {

    public Alpaca create() { return new Alpaca(0,0, new Location(0,0)); }

}
