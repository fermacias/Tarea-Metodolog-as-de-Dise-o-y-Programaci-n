package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LuzBookTest extends AbstractTestItem {

    private LuzBook luzBook;
    private LuzBook wrongLuzBook;
    private Sorcerer sorcerer;

    /*
    private Axe axe;
    private Axe wrongAxe;
    private Fighter fighter;
    */

    @Override
    public void setTestItem() {
        expectedName = "Common LuzBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        luzBook = new LuzBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongLuzBook = new LuzBook("Wrong LuzBook", 10, 1, 1);
        //wrongLuzBook = new LuzBook("Wrong LuzBook", 0, -1, -2);
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
        return wrongLuzBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return luzBook;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }



}
