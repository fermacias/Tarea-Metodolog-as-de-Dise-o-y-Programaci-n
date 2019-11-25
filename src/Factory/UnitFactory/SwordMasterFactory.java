package Factory.UnitFactory;

import model.map.Location;
import model.units.SwordMaster;

public class SwordMasterFactory implements UnitFactory {

    public SwordMaster create() { return new SwordMaster(0,0, new Location(0,0)); }
}
