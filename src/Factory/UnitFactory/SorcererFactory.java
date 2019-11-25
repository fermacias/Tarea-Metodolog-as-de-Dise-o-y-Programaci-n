package Factory.UnitFactory;

import model.map.Location;
import model.units.Sorcerer;

public class SorcererFactory implements UnitFactory {
    public Sorcerer create() { return new Sorcerer(0,0, new Location(0,0)); }
}
