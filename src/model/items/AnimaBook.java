package model.items;

import model.units.Sorcerer;

/**
 * This class represents an AnimaBook.
 * <p>
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
public class AnimaBook extends Book {
    /**
     * Creates a new anima book.
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
    public AnimaBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>AnimaBook</i> can equip a Sorcerer.
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
     * The <i>AnimaBooks</i> is stronger than LuzBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean stronger(IEquipableItem item) { return item.animaBookStrongerThan(); }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>AnimaBooks</i> is weaker than OscuridadBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean weaker(IEquipableItem item) { return item.animaBookWeakerThan(); }


    /**
     * {@inheritDoc}
     * <p>
     * The oscuridadBook is stronger than <i>AnimaBooks</i>.
     */
    @Override
    public boolean oscuridadBookStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The LuzBook is weaker than <i>AnimaBooks</i>.
     */
    @Override
    public boolean luzBookWeakerThan() {return true;}




}
