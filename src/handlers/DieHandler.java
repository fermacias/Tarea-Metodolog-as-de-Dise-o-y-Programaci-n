package handlers;

import controller.GameController;
import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for kill units of a Tactician.
 * Apply the observer design pattern
 *
 * @author Fernanda Macías Herrera
 * @since 0.1
 */
public class DieHandler implements PropertyChangeListener {
    private final Tactician tactician;

    /**
     * Creates a new handler for the unit
     *
     * @param tactician
     *      the subject
     */
    public DieHandler(final Tactician tactician) {
        this.tactician = tactician;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed. The changed event must be the unit.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        IUnit unit = (IUnit) event.getNewValue();
        unit.die();
    }
}






