package model;

import controller.GameController;
import factory.ItemFactory.IItemFactory;
import factory.UnitFactory.IUnitFactory;
import handlers.DieHandler;
import model.items.IEquipableItem;
import model.map.Location;
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
    private boolean inTurn = false;

    /* Factory */
    private IUnitFactory unitFactory;

    /* Observer */
    //private final GameController controller;    // ver si lo uso o no jiji

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
    public Tactician(String name) {
        this.name = name;
        //this.controller = controller;

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



    public void deleteUnitList() { unitList.clear(); }

    /**
     *
     * @return
     *      true if is the turn of this tactician
     */
    public boolean inTurn() { return inTurn; }


    /**
     * begin the turn of this tactician making true the variable inTurn
     */
    public void yourTurn() { inTurn = true; }


    /**
     * end the turn of this tactician making false the variable inTurn
     */
    public void yourTurnEnds() { inTurn = false; }

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
     * @param unit
     *      delete this unit from the unitList
     */
    public void deleteUnit(IUnit unit) {
        unitList.remove(unit);
    }


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
     * @param i
     *      the position of the unit in unit list
     * @return
     *      the location of the unit
     */
    public Location getLocation(int i) { return unitList.get(i).getLocation(); }


    /**
     *
     * @param unit
     *      add this unit to the unitList
     */
    public void addUnit(IUnit unit) {
        unitList.add(unit);
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
     * Defines the unitFactory
     *
     * @param unitFactory
     *      the unitFactory I want to use
     */
    public void unitFactory(IUnitFactory unitFactory) {
        this.unitFactory = unitFactory;
    }


    /**
     * Place a unit in a Location
     *
     * @param i
     *      the unit position in the unit list
     * @param location
     *      the new Location for the unit
     * @return
     *      true if the location was free and false if it was not
     */
    public boolean newLocation(int i, Location location) {
        if (location.getUnit()!= null) { return false; }
        if (unitList.get(i).getLocation()!=null) { unitList.get(i).getLocation().setUnit(null); }
        unitList.get(i).setLocation(location);
        location.setUnit(unitList.get(i));
        return true;
    }


    /**
     *
     * @return
     *      a new unit created by the selected unitFactory
     */
    public void newUnit() {
        this.addUnit(unitFactory.create());
        unitList.get(unitList.size()-1).setTactician(this);
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
    public void createItems(int n, ArrayList<IItemFactory> factories) {
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
        while (unitList.size() != 0)
            dieNotification.firePropertyChange(new PropertyChangeEvent(this, "UnitDied", null, unitList.get(0)));
    }






}
