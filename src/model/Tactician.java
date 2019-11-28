package model;

import controller.GameController;
import factory.ItemFactory.IItemFactory;
import factory.UnitFactory.IUnitFactory;
import model.items.IEquipableItem;
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

    /* Factory */
    private IUnitFactory unitFactory;
    private IItemFactory itemFactory;


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
    public IUnit newUnit() {
        return unitFactory.create();
    }

    /**
     *
     * @param itemFactory
     *      the itemFactory I want to use
     */
    public void itemFactory(IItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    /**
     *
     * @return
     *      a new item created by the selected unitFactory
     */
    public IEquipableItem newItem() {
        return itemFactory.create();
    }

}
