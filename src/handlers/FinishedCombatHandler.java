package handlers;

import controller.GameController;
import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for update a tactician state after one of his/her unit die in a combat.
 * Apply the observer design pattern
 *
 * @author Fernanda Macías Herrera
 * @since 0.1
 */
public class FinishedCombatHandler implements PropertyChangeListener {
    private final GameController controller;

    /**
     * Creates a new handler for the controller
     *
     * @param controller
     *      the subject
     */
    public FinishedCombatHandler(final GameController controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed. The changed event must be the died unit.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        controller.ownerState((IUnit) event.getNewValue());
    }
}