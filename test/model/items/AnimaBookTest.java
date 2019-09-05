package model.items;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimaBookTest extends AbstractTestItem {

    private AnimaBook animaBook;
    private AnimaBook wrongAnimaBook;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common AnimaBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        animaBook = new AnimaBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongAnimaBook = new AnimaBook("Wrong AnimaBook", 10, 1, 1);
        //wrongAnimaBook = new AnimaBook("Wrong AnimaBook", 0, -1, -2);
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
        return wrongAnimaBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return animaBook;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }



}
