package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assert(fighter.getEquippedItem().IamNull());
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
  }

  // test equip

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  public void equipFighterTest() {
    assert(fighter.getEquippedItem().IamNull());
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  public void canTakeFighterTest() {
    Fighter fighter = new Fighter(50, 2, field.getCell(0, 0));
    assertEquals(true, fighter.canTake());

  }

  @Test
  public void cantTakeFighterTest() {
    Fighter fighter = new Fighter(50, 2, field.getCell(0, 0));
    fighter.addItem(bow);
    fighter.addItem(luzBook);
    fighter.addItem(staff);
    assertEquals(false, fighter.canTake());

  }

}