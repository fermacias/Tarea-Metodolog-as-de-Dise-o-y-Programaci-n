package model;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

// metodos para acceder a todas las funcionalidades ya creadas
// debe conocer todas la unidades que posee y tener conocimiento del mapa
// dentro de un turno el jugador puede mover todas las unidades una sola vez
// el jugador debe mantener una referencia a la unidad actualmente seleccionada
// un usuario debe tener la capacidad de ver los datos de sus unidades, tales como puntos de
// vida actuales y máximos, nombre, inventario, poder, etc.

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
    private Field field;
    private IUnit selectedUnit;
    //selected item

    /**
     *
     * Creates a new Tactician.
     *
     * @param name
     *     the name of the new Tactician
     *
     */
    public Tactician(String name) {
        this.name = name;
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
     * @return the fiels of the Tactitian
     */
    public Field getField() { return field; }


    /**
     *
     * @return the selected unit of this Tactician
     */
    public IUnit getSelectedUnit() { return selectedUnit; }


    /**
     *
     * @param unit
     *      the unit
     * @return true if unitList contains this unit,
     *      else return false
     */
    public boolean owns(IUnit unit) {
        return unitList.contains(unit);
    }


    /**
     *
     * @return max hit points of each unit on unitList in order
     */
    public List<Integer> getMaxHitPointsList() {
        List<Integer> HPlist = new ArrayList<>();
        for(IUnit unit : unitList)
            HPlist.add(unit.getMaxHitPoints());
        return HPlist;
    }


    /**
     *
     * @return current hit points of each unit on unitList in order
     */
    public List<Integer> getCurrentHitPointsList() {
        List<Integer> HPlist = new ArrayList<>();
        for(IUnit unit : unitList)
            HPlist.add(unit.getCurrentHitPoints());
        return HPlist;
    }


    /**
     *
     * @return the equipped items by each unit in order
     */
    public List<IEquipableItem> getEquippedItems() {
        List<IEquipableItem> EIList = new ArrayList<>();
        for(IUnit unit : unitList)
            EIList.add(unit.getEquippedItem());
        return EIList;
    }


    /**
     *
     * @param unit
     *      the unit
     * @return the unit`s itemList, if unitList doesnt contain the unit return an empty list
     */
    public List<IEquipableItem> getUItems(IUnit unit) {
        if(this.owns(unit)) {
            return unit.getItems();
        }
        return new ArrayList<IEquipableItem>() ;
    }


    /**
     *
     * @param unit
     *      the unit (must be on unitList to do something)
     * @param item
     *      set this item to the unit`s list
     */
    public void addUItem(IUnit unit, IEquipableItem item) { unit.addItem(item); }


    /**
     *
     * @param unit
     *      the unit (must be on unitList to do something)
     * @param item
     *      equip this item to the unit (it must be on the unit´s item list)
     */
    public void setUEquippedItem(IUnit unit, IEquipableItem item){
        if(this.owns(unit)) { unit.setEquippedItem(item); }
    }


    /**
     *
     * @return the location of each unit on unitList
     */
    public List<Location> getULocations() {
        List<Location> locList = new ArrayList<>();
        for(IUnit unit : unitList)
            locList.add(unit.getLocation());
        return locList;
    }


    /**
     *
     * @param unit
     *      the unit (must be on unitList to do something)
     * @param location
     *      set this location to the unit
     */
    public void setULocation(IUnit unit, final Location location) {
        if(this.owns(unit)) { unit.setLocation(location); }
    }


    /**
     *
     * @param unit
     *      the unit
     * @return the number of cells of the unit can move only if unitList contains the unit,
     *      else return -1
     */
    public int getUMovement(IUnit unit) {
        if(this.owns(unit)) { return unit.getMovement(); }
        return -1;
    }


    /**
     *
     * @param unit
     *      the unit (must be on unitList to do something)
     * @param targetLocation
     *      move the unit to this targetLocation (it must be on the unit`s movement range to do it)
     */
    public void moveUTo(IUnit unit, Location targetLocation) {
        if(this.owns(unit)) { unit.moveTo(targetLocation); }
    }

  /*
  EQUIP ITEM
   */

    /**
     *
     * @param unit
     *      the unit (must be on unitList to do something)
     * @param item
     *      the item to equip (must be on the unit´s itemsList to do it)
     */
    public void equipUItem(IUnit unit, IEquipableItem item) {
        if(this.owns(unit)) { unit.equipItem(item); }
    }


  /*
  EXCHANGE ITEMS
   */

    /**
     *
     * An Tactician`s unit give an item to another unit
     *
     * @param unit1
     *      the unit that gives the item (it must be on unitList to do it)
     * @param item
     *      the item (it must be on unit1`s item list to do it)
     * @param unit2
     *      the unit that recieves the item
     */
    public void getUItem(IUnit unit1, IEquipableItem item, IUnit unit2) {
        if(this.owns(unit1)) { unit1.giveItem(item, unit2); }
    }


  /*
  COMBATE
   */

    /**
     *
     * Makes a combat under the combat´s rules.
     * Unit1 begin the combat.
     *
     * @param unit1
     *      the unit begins the combat (must be on unitList to do it)
     * @param unit2
     *      the unit that recieves the combat
     */
    public void uCombat(IUnit unit1, IUnit unit2) {
        if(this.owns(unit1)) { unit1.combat(unit2); }
    }



}
