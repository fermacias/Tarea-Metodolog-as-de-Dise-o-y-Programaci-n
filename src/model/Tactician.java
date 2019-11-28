package model;

import controller.GameController;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * <i>Tactician</> class represents a player.
 * Handle all user instructions and delegate messages
 * to model objects.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 *
 * @author Fernanda Macías Herrera
 * @since 1.0
 */
public class Tactician {

    private String name;
    private List<IUnit> unitList = new ArrayList<>();
    private GameController controller;
    private IUnit selectedUnit;


    /**
     *
     * Creates a new Tactician without opponents.
     *
     * @param name
     *     the name of the new Tactician
     *
     */
    public Tactician(String name, final GameController controller) {
        this.name = name;
        this.controller = controller;
    }

    /**
     *
     * @return the name of this Tactician
     */
    public String getName() { return name; }


    /**
     *
     * @return the unitList of this Tactician
     */
    public List<IUnit> getUnitList() { return unitList; }


    /**
     *
     * @return the selected unit of this Tactician
     */
    public IUnit getSelectedUnit() { return selectedUnit; }






}
