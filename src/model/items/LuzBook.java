package model.items;

import model.units.Sorcerer;

/**
 * This class represents an LuzBook.
 * <p>
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class LuzBook extends Book {
    /**
     * Creates a new luz book.
     *
     * @param name
     *     the name of the bow
     * @param power
     *     the damage power of the bow
     * @param minRange
     *     the minimum range of the bow
     * @param maxRange
     *     the maximum range of the bow
     */
    public LuzBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>LuzBook</i> can equip a Sorcerer.
     */
    @Override
    public void equipSorcerer(final Sorcerer sorcerer) {
        this.equipTo(sorcerer);
    }

    /*
    COMBATE
    */

    /**
     * {@inheritDoc}
     * <p>
     * The <i>LuzBooks</i> is stronger than OscuridadBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean stronger(IEquipableItem item) {
        return item.luzBookStrongerThan();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>LuzBooks</i> is weaker than AnimaBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean weaker(IEquipableItem item) { return item.luzBookWeakerThan(); }

    /**
     * {@inheritDoc}
     * <p>
     * The AnimaBook is stronger than <i>LuzBooks</i>.
     */
    @Override
    public boolean animaBookStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The oscuridadBook is weaker than <i>LuzBooks</i>.
     */
    @Override
    public boolean oscuridadBookWeakerThan() {return true;}







}
