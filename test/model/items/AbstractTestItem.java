package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem implements ITestItem{

  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;

  protected Bow bow = new Bow("Bow", 10, 1, 2);
  protected Axe axe = new Axe("Axe", 10, 1, 2);
  protected Sword sword = new Sword("Sword", 10, 1, 2);
  protected Staff staff = new Staff("Staff", 10, 1, 2);
  protected Spear spear = new Spear("Spear", 10, 1, 2);
  protected LuzBook luzBook = new LuzBook("LuzBook", 10, 1, 2);
  protected OscuridadBook oscuridadBook = new OscuridadBook("OscuridadBook", 10, 1, 2);
  protected AnimaBook animaBook = new AnimaBook("AnimaBook", 10, 1, 2);

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setTestItem();
    setWrongRangeItem();
    setTestUnit();
  }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  public void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();



  //combate

  @Override
  @Test
  public void animaBookStrongTest() {
    assertEquals(true, animaBook.stronger(luzBook));
    assertEquals(true, animaBook.weaker(oscuridadBook));

    assertEquals(true, animaBook.stronger(staff));
    assertEquals(true, animaBook.weaker(staff));
    assertEquals(true, animaBook.stronger(bow));
    assertEquals(true, animaBook.weaker(bow));

    assertEquals(true, animaBook.stronger(axe));
    assertEquals(true, animaBook.weaker(axe));
    assertEquals(true, animaBook.stronger(spear));
    assertEquals(true, animaBook.weaker(spear));
    assertEquals(true, animaBook.stronger(sword));
    assertEquals(true, animaBook.weaker(sword));
  }

  @Override
  @Test
  public void luzBookStrongTest() {
    assertEquals(true, luzBook.stronger(oscuridadBook));
    assertEquals(true, luzBook.weaker(animaBook));

    assertEquals(true, luzBook.stronger(staff));
    assertEquals(true, luzBook.weaker(staff));
    assertEquals(true, luzBook.stronger(bow));
    assertEquals(true, luzBook.weaker(bow));

    assertEquals(true, luzBook.stronger(axe));
    assertEquals(true, luzBook.weaker(axe));
    assertEquals(true, luzBook.stronger(spear));
    assertEquals(true, luzBook.weaker(spear));
    assertEquals(true, luzBook.stronger(sword));
    assertEquals(true, luzBook.weaker(sword));

  }

  @Override
  @Test
  public void oscuridadBookStrongTest() {
    assertEquals(true, oscuridadBook.stronger(animaBook));
    assertEquals(true, oscuridadBook.weaker(luzBook));

    assertEquals(true, oscuridadBook.stronger(staff));
    assertEquals(true, oscuridadBook.weaker(staff));
    assertEquals(true, oscuridadBook.stronger(bow));
    assertEquals(true, oscuridadBook.weaker(bow));

    assertEquals(true, oscuridadBook.stronger(axe));
    assertEquals(true, oscuridadBook.weaker(axe));
    assertEquals(true, oscuridadBook.stronger(spear));
    assertEquals(true, oscuridadBook.weaker(spear));
    assertEquals(true, oscuridadBook.stronger(sword));
    assertEquals(true, oscuridadBook.weaker(sword));

  }

  @Override
  @Test
  public void wrongBookStrongtest() {
    assertEquals(false, animaBook.stronger(oscuridadBook));
    assertEquals(false, luzBook.stronger(animaBook));
    assertEquals(false, oscuridadBook.stronger(luzBook));

    assertEquals(false, animaBook.weaker(animaBook));
    assertEquals(false, luzBook.weaker(luzBook));
    assertEquals(false, oscuridadBook.weaker(oscuridadBook));

  }

  @Override
  @Test
  public void generalItemsStrongTest() {
    assertEquals(true, axe.stronger(spear));
    assertEquals(true, axe.weaker(sword));
    assertEquals(true, axe.stronger(animaBook));
    assertEquals(true, axe.weaker(animaBook));

    assertEquals(true, sword.stronger(axe));
    assertEquals(true, sword.weaker(spear));
    assertEquals(true, sword.stronger(luzBook));
    assertEquals(true, sword.weaker(luzBook));

    assertEquals(true, spear.stronger(sword));
    assertEquals(true, spear.weaker(axe));
    assertEquals(true, spear.stronger(oscuridadBook));
    assertEquals(true, spear.weaker(oscuridadBook));

  }

  @Override
  @Test
  public void wrongGeneralItemsStrongTest() {
    assertEquals(false, axe.stronger(sword));
    assertEquals(false, axe.weaker(spear));

    assertEquals(false, sword.stronger(spear));
    assertEquals(false, sword.weaker(axe));

    assertEquals(false, spear.stronger(axe));
    assertEquals(false, spear.weaker(sword));

  }

  @Override
  @Test
  public void specialItemsStrongTest() {
    assertEquals(false, staff.stronger(axe));
    assertEquals(false, staff.weaker(axe));
    assertEquals(false, staff.stronger(sword));
    assertEquals(false, staff.weaker(sword));
    assertEquals(true, staff.stronger(animaBook));
    assertEquals(true, staff.weaker(animaBook));

    assertEquals(true, bow.stronger(luzBook));
    assertEquals(true, bow.weaker(luzBook));
    assertEquals(false, bow.stronger(spear));
    assertEquals(false, bow.weaker(spear));
    assertEquals(true, bow.stronger(animaBook));
    assertEquals(true, bow.weaker(animaBook));

  }



}
