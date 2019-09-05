package model.units;


import model.items.*;
import model.map.Field;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Set up the game field  //deja listo el campo (field)
   */
  void setField();

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  //crea una alpaca que va a ser target de wea
  void setTargetAlpaca();

  /**
   * Creates a set of testing weapons     //CREA UN SET DE ARMAS TESTEABLES
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.    //chequea constructores
   */
  @Test           //se va a ejecutar como un test
  void constructorTest();

  /**
   * @return the current unit being tested        //retorna la unidad que estoy testeando
   */
  IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit    //chequea si el axe (hacha) fue equipada correctamente
   */
  @Test
  void equipAxeTest();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped    //tarta de equipar un arma en un lugar y verifica que no fue eequipada
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * @return the test axe     //retorna el hacha testeada
   */
  Axe getAxe();

  @Test
  void equipSwordTest();

  /**
   * @return the test sword
   */
  Sword getSword();

  @Test
  void equipSpearTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  @Test
  void equipStaffTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  @Test
  void equipBowTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  /**
   * Checks if the unit moves correctly     //testea movimiento
   */
  @Test
  void testMovement();

  /**
   * @return the test field         //retorna el campo
   */
  Field getField();

  /**
   * @return the target Alpaca      //retorna la alpaca que cree como blanco
   */
  Alpaca getTargetAlpaca();
}
