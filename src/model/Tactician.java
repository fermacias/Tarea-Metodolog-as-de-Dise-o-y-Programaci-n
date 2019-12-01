package model;

import controller.GameController;
import factory.ItemFactory.IItemFactory;
import factory.UnitFactory.IUnitFactory;
import model.items.IEquipableItem;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.Arrays;
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

    /* Factory */
    private IUnitFactory unitFactory;



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

    /* Funcionalidades */

    public void addUnit(IUnit unit) {
        this.unitList.add(unit);
    }



    /* Factory */

    /**
     *
     * @param unitFactory
     *      the unitFactory I want to use
     */
    public void unitFactory(IUnitFactory unitFactory) {
        this.unitFactory = unitFactory;
    }

    /**
     *
     * @return
     *      a new unit created by the selected unitFactory
     */
    public void newUnit() {
        this.addUnit(unitFactory.create());
    }



    /**
     *
     * Creates n item for each unit in unitList
     *
     * @param n
     *      the number of items
     * @param factories
     *      a list with the factories to use
     */
    public void createItems(int n, List<IItemFactory> factories) {
        for (IUnit unit : unitList) {
            unit.addEquipableItem();
            for (int i=0; i<n-1; i++) {
                unit.itemFactory(factories.get(0));
                unit.newItem();
                factories.add(factories.remove(0));
            }
        }
    }






}
