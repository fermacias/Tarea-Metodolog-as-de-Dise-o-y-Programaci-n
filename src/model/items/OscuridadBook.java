package model.items;

import model.units.Sorcerer;

public class OscuridadBook extends Book{
    /**
     * Creates a new oscuridad book.
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
    public OscuridadBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>OscuridadBook</i> can equip a Sorcerer.
     */
    public void equipSorcerer(final Sorcerer sorcerer) {
        this.equipTo(sorcerer);
    }

    /*
  COMBATE
  */

    /**
     * {@inheritDoc}
     * <p>
     * The <i>OscuridadBooks</i> is stronger than AnimaBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean stronger(IEquipableItem item) {
        return item.oscuridadBookStrongerThan();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>OscuridadBooks</i> is weaker than LuzBook, Axe, Sword, Spear, Staff and Bow.
     */
    @Override
    public boolean weaker(IEquipableItem item) { return item.oscuridadBookWeakerThan(); }

    /**
     * {@inheritDoc}
     * <p>
     * The LuzBook is stronger than <i>OscuridadBooks</i>.
     */
    @Override
    public boolean luzBookStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The AnimaBook is weaker than <i>OscuridadBooks</i>.
     */
    @Override
    public boolean animaBookWeakerThan() {return true;}




}
