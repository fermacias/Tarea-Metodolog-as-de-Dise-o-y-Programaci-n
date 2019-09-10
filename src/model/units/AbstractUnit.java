package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import model.items.*;
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
  private int currentHitPoints;                                   //Cantidad de daño actual max que puede recibir la unidad
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
  protected AbstractUnit(int hitPoints, final int movement,
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
  public void setCurrentHitPoints(int hp) { this.currentHitPoints = hp; }

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



  /**
   *
   * @return boolean
   *      indica si tiene menos de 3 items
   */
  @Override
  public boolean canTake() {
    if(this.items.size()<3)
      return true;
    return false;
  }



  @Override
  public void giveItem(IEquipableItem item, IUnit unit2){
    Location loc1 = this.location;
    Location loc2 = unit2.getLocation();
    if(this.items.contains(item) && loc1.distanceTo(loc2) == 1 && unit2.canTake()) {
      if( this.equippedItem == item ) {
        this.equippedItem = null;
      }
      this.items.remove(item);
      unit2.addItem(item);
      item.setOwner(unit2);
    }
  }


  @Override
  public void attack(IUnit unit2) {
    IEquipableItem item1 = equippedItem, item2= unit2.getEquippedItem();
    int minDist = item1.getMinRange(), maxDist = item1.getMaxRange();
    double dist = location.distanceTo(unit2.getLocation());

    //si la unidad1 esta equipada y la otra unidad esta en el rango del item
    if (item1 != null && minDist <= dist && dist <= maxDist) {
      int damage = item1.getPower();

      if(item2 != null) {
        if (item1.stronger(item2)) { damage = damage * 3 / 2; }
        else if (item2 != null && item2.weaker(item1)) { damage = damage - 20; }
      }

      unit2.setCurrentHitPoints( unit2.getCurrentHitPoints() - damage );

      if(unit2.getCurrentHitPoints()<0){ unit2.setCurrentHitPoints(0); }

    }
  }



  //COMBATE
  @Override
  public void combat (IUnit unit2) {
    if ( currentHitPoints != 0) {
      this.attack(unit2);
      if ( unit2.getCurrentHitPoints() != 0) {
        unit2.attack(this);
      }
    }
  }


}
