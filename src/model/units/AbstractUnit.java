package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>(); //Lista con los objetos que porta la unidad
  private final int currentHitPoints;                             //Cantidad de daño actual max que puede recibir la unidad
  private final int movement;                                     //Cantinan max de celdas que se puede desplazar en un turno
  protected IEquipableItem equippedItem;                          //Item que esta equipado (lo que tiene en la mano)
  private Location location;                                      //Ubicación actual en el mapa

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
                         final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public void addItem(IEquipableItem item) { this.items.add(item); }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
            && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public void giveItem(IEquipableItem item, IUnit unit2) {
    Location loc1 = this.location;
    Location loc2 = unit2.getLocation();
    if(this.items.contains(item) && loc1.distanceTo(loc2) == 1) {
      //si estaba equipado con dicho item
      if( this.equippedItem == item ) {
        this.equippedItem = null;
      }
      this.items.remove(item);
      unit2.addItem(item);
      item.setOwner(unit2);
    }
  }


}
