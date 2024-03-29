package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assert(archer.getEquippedItem().IamNull());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void canTakeArcherTest() {
    Archer archer = new Archer(50, 2, field.getCell(0, 0));
    assertEquals(true, archer.canTake());

  }

  @Test
  public void cantTakeArcherTest() {
    Archer archer = new Archer(50, 2, field.getCell(0, 0));
    archer.addItem(bow);
    archer.addItem(luzBook);
    archer.addItem(staff);
    assertEquals(false, archer.canTake());

  }


}