package factory.UnitFactory;

import model.units.IUnit;


/**
 * This interface represents a Factory that can create all type of units.
 * <p>
 * Apply the Factory Desing Pattern.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public interface IUnitFactory {

    /**
     *
     * @return
     *      the created unit
     */
    IUnit create();

}
