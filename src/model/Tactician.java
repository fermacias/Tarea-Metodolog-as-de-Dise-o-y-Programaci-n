package model;

import controller.GameController;
import factory.ItemFactory.IItemFactory;
import factory.UnitFactory.IUnitFactory;
import handlers.DieHandler;
import model.items.IEquipableItem;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
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
    private IUnit selectedUnit;

    /* Factory */
    private IUnitFactory unitFactory;

    /* Observer */
    private final GameController controller;    // ver si lo uso o no jiji

    // Observer/listener
    // Crea referencia bidireccional entre el listener (handler) y el observer (game controller)
    private PropertyChangeSupport
            dieNotification = new PropertyChangeSupport(this);



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

        final DieHandler dieHandler = new DieHandler(this);
        dieNotification.addPropertyChangeListener(dieHandler);
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
     * @param i
     *      the position in the unitList
     * @return
     *      the required unit
     */
    public IUnit getUnit(int i) { return unitList.get(i); }


    /**
     *
     * @return the selected unit of this Tactician
     */
    public IUnit getSelectedUnit() { return selectedUnit; }


    /**
     *
     * @param i
     *      the position in the unitList
     * @return
     *      the itemsList of the unit
     */
    public List<IEquipableItem> getItems(int i) { return unitList.get(i).getItems(); }


    /**
     *
     * @param unit
     *      add this unit to the unitList
     */
    public void addUnit(IUnit unit) {
        this.unitList.add(unit);
    }


    /**
     *
     * @return the number of units
     */
    public int getUnitsNumber() {return unitList.size(); }

    /**
     *
     * @return
     *      the equipped item of the selected unit
     */
    public IEquipableItem getEquippedItem() { return selectedUnit.getEquippedItem(); }




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

    /* Observer */

    public void killUnits() {
        // saco a sus unidades del mapa
        for (IUnit unit : unitList) {
            dieNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitDied", null, unit));
        }
    }




}
