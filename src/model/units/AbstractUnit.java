package model.units;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import factory.ItemFactory.IItemFactory;
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
 * @author Fernanda Macias Herrera
 *
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private IItemFactory itemFactory;


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
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    NullItem nullItem = new NullItem();
    nullItem.setOwner(this);
    this.equippedItem = nullItem;
  }



  @Override
  public int getMaxHitPoints() { return maxHitPoints; }

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
        this.equippedItem = new NullItem();
      }
      this.items.remove(item);
      unit2.addItem(item);
      item.setOwner(unit2);
    }
  }



  /*
  COMBAT
   */

  @Override
  public void combat(IUnit unit2) {
    IEquipableItem item1 = equippedItem;
    IEquipableItem item2 = unit2.getEquippedItem();
    if(currentHitPoints != 0 && unit2.getCurrentHitPoints() != 0) {
      item1.attack(item2);
      if(currentHitPoints != 0 && unit2.getCurrentHitPoints() != 0) {
        item2.attack(item1);
      }
    }
  }


  /*
  OBSERVER
   */
  @Override
  public void die() {
    location.setUnit(null);
  }


  /*
  FACTORY
   */

  @Override
  public void itemFactory(IItemFactory itemFactory) {
    this.itemFactory = itemFactory;
  }


  @Override
  public void newItem() {
    items.add(itemFactory.create());
  }




}
