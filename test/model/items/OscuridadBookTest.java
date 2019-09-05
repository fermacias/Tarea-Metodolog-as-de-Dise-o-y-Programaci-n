package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OscuridadBookTest extends AbstractTestItem {

    private OscuridadBook oscuridadBook;
    private OscuridadBook wrongOscuridadBook;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common OscuridadBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        oscuridadBook = new OscuridadBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongOscuridadBook = new OscuridadBook("Wrong OscuridadBook", 10, 1, 1);
        //wrongOscuridadBook = new OscuridadBook("Wrong OscuridadBook", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongOscuridadBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return oscuridadBook;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }



}
