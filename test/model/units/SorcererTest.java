package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }


    @Override
    @Test
    public void equipLuzBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(luzBook);
        assertEquals(luzBook, sorcerer.getEquippedItem());
    }


    @Override
    @Test
    public void equipOscuridadBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(oscuridadBook);
        assertEquals(oscuridadBook, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void equipAnimaBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(animaBook);checkEquippedItem(getAnimaBook());
        assertEquals(animaBook, sorcerer.getEquippedItem());
    }




}