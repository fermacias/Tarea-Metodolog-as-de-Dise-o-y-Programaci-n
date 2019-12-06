package handlers;

import controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the loser Tactician of a Game Controller.
 * Apply the observer design pattern
 *
 * @author Fernanda Macías Herrera
 * @since 0.1
 */
public class LoseHandler implements PropertyChangeListener {

    private final GameController controller;

    /**
     * Creates a new handler for the died Tactician
     *
     * @param controller
     *      the subject
     */
    public LoseHandler(final GameController controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed. The changed event must be the loser name.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        controller.removeTactician((String) event.getNewValue());
    }

}
