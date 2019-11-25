package factory.UnitFactory;

import model.units.IUnit;

public interface UnitFactory {

    /**
     *
     * @return
     *      unidad creada
     */
    public IUnit create();

}
