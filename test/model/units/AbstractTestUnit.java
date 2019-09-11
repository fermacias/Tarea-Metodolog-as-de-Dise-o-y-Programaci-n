package model.units;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected LuzBook luzBook;
  protected OscuridadBook oscuridadBook;
  protected AnimaBook animaBook;

  //crea la alpaca target
  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field    //crea el campo de juego
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    //inicializa las armas declaradas anteriormente
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.luzBook = new LuzBook("LuzBook", 10,  1, 2);
    this.oscuridadBook = new OscuridadBook("OscuridadBook", 10,  1, 2);
    this.animaBook = new AnimaBook("AnimaBook", 10,  1, 2);

  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assert(getTestUnit().getEquippedItem().IamNull());
    checkEquippedItem(getAxe());
  }


  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assert(getTestUnit().getEquippedItem().IamNull());
    getTestUnit().equipItem(item);
    assert(getTestUnit().getEquippedItem().IamNull());
  }


  /*
  TESTS PARA EQUIPAR ITEM
  */

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() { checkEquippedItem(getSword()); }

  /**
   * @return the test sword
   */
  @Override
  @Test
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  @Override
  @Test
  public void equipLuzBookTest() { checkEquippedItem(getLuzBook()); }

  /**
   * @return the test luzBook
   */
  @Override
  public LuzBook getLuzBook() {
    return luzBook;
  }

  @Override
  @Test
  public void equipOscuridadBookTest() {
    checkEquippedItem(getOscuridadBook());
  }

  /**
   * @return the test luzBook
   */
  @Override
  public OscuridadBook getOscuridadBook() {
    return oscuridadBook;
  }

  @Override
  @Test
  public void equipAnimaBookTest() {
    checkEquippedItem(getAnimaBook());
  }

  /**
   * @return the test luzBook
   */
  @Override
  public AnimaBook getAnimaBook() {
    return animaBook;
  }



  /*
  TESTS PARA INTERCAMBIO DE ITEMS
   */
  @Override
  @Test
  public void giveSpear(){
    Alpaca alpaca = new Alpaca(50, 2, field.getCell(1, 0));
    alpaca.addItem(axe);
    alpaca.addItem(spear);

    Archer archer = new Archer(50, 2, field.getCell(0, 0));
    archer.addItem(luzBook);

    alpaca.giveItem(spear, archer);
    assertEquals(true, archer.items.contains(spear));
  }

  @Override
  @Test
  public void wrongGiveSpear() {
    Alpaca alpaca = new Alpaca(50, 2, field.getCell(1, 0));
    alpaca.addItem(axe);
    alpaca.addItem(spear);

    //no podra recibirlo porque ya tiene tres items en su inventario
    Archer archer = new Archer(50, 2, field.getCell(0, 0));
    archer.addItem(oscuridadBook);
    archer.addItem(bow);
    archer.addItem(luzBook);

    alpaca.giveItem(spear, archer);
    assertEquals(false, archer.items.contains(spear));
  }

  @Override
  @Test
  public void giveLuzBook() {
    Hero hero = new  Hero(50, 2, field.getCell(1, 0));
    hero.addItem(animaBook);
    hero.addItem(luzBook);

    Fighter fighter = new Fighter(50, 2, field.getCell(0, 0));
    fighter.addItem(animaBook);
    fighter.addItem(oscuridadBook);

    hero.giveItem(luzBook, fighter);
    assertEquals(true, fighter.items.contains(luzBook));
  }

  @Override
  @Test
  public void wrongGiveLuzBook() {
    //no podra recibirlo porque hero no tiene un luzBook
    Hero hero = new  Hero(50, 2, field.getCell(1, 0));
    hero.addItem(animaBook);

    Fighter fighter = new Fighter(50, 2, field.getCell(0, 0));

    fighter.giveItem(luzBook, fighter);
    assertEquals(false, fighter.items.contains(luzBook));
  }

  @Override
  @Test
  public void wrongGiveSword() {
    //no podra recibirlo porque la distancia entre las unidades es distinta de 1
    Sorcerer sorcerer = new  Sorcerer(50, 2, field.getCell(2, 0));
    sorcerer.addItem(sword);
    
    SwordMaster swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));

    sorcerer.giveItem(sword, swordMaster);
    assertEquals(false, swordMaster.items.contains(luzBook));
  }

  /*
  OTROS
   */


  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }


  /*
  COMBAT
   */

  @Override
  @Test
  public void alpacaCombat() {
    Alpaca alpaca1 = new Alpaca(50, 2, field.getCell(1, 0));
    alpaca1.addItem(axe);

    Hero hero = new Hero(50, 2, field.getCell(2, 0));
    hero.addItem(spear);
    hero.equipItem(spear);

    alpaca1.combat(hero);
    assertEquals(50, alpaca1.getCurrentHitPoints());

    hero.combat(alpaca1);
    assertEquals(40, alpaca1.getCurrentHitPoints());  //10
    assertEquals(50, hero.getCurrentHitPoints());

  }

  @Override
  @Test
  public void clericCombat() {

    Cleric cleric1 = new Cleric(50, 2, field.getCell(1, 0));
    cleric1.addItem(staff);
    cleric1.equipItem(staff);

    Fighter fighter = new Fighter(50, 2, field.getCell(2, 0));
    fighter.setCurrentHitPoints(30);
    fighter.addItem(axe);
    fighter.equipItem(axe);


    staff.heal(fighter);
    assertEquals(50, cleric1.getCurrentHitPoints());
    assertEquals(40, fighter.getCurrentHitPoints());

    fighter.combat(cleric1);
    assertEquals(40, cleric1.getCurrentHitPoints());
    assertEquals(40, fighter.getCurrentHitPoints());


  }


  @Override
  @Test
  public void completeCombat() {
    Fighter fighter = new Fighter(50, 2, field.getCell(2, 0));
    fighter.addItem(axe);
    fighter.equipItem(axe);

    SwordMaster swordMaster = new SwordMaster(50, 2, field.getCell(1, 0));
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);

    //Cubre caso en que un item es fuerte y debil contra otra
    fighter.combat(swordMaster);
    assertEquals(50, swordMaster.getCurrentHitPoints());
    assertEquals(35, fighter.getCurrentHitPoints());

  }

  @Override
  @Test
  public void incompleteCombat() {
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 0));
    sorcerer.addItem(animaBook);
    sorcerer.equipItem(animaBook);

    Alpaca alpaca = new Alpaca(50, 2, field.getCell(1, 0));
    //alpaca.addItem();

  }

  @Override
  @Test
  public void wrongCombat() {
    Fighter fighter = new Fighter(50, 2, field.getCell(8, 0));
    fighter.addItem(axe);
    fighter.equipItem(axe);

    SwordMaster swordMaster = new SwordMaster(50, 2, field.getCell(1, 0));
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);

    fighter.combat(swordMaster);
    assertEquals(50, swordMaster.getCurrentHitPoints());
    assertEquals(50, fighter.getCurrentHitPoints());

    Alpaca alpaca = new Alpaca(50, 2, field.getCell(1, 0));
    alpaca.addItem(animaBook);
    alpaca.equipItem(animaBook);

    alpaca.combat(swordMaster);
    assertEquals(50, swordMaster.getCurrentHitPoints());
    assertEquals(50, alpaca.getCurrentHitPoints());

    Cleric cleric = new Cleric(50, 2, field.getCell(7, 0));
    cleric.addItem(staff);
    cleric.equipItem(staff);

    cleric.combat(fighter);
    assertEquals(50, fighter.getCurrentHitPoints());
    assertEquals(50, cleric.getCurrentHitPoints());

  }





}
