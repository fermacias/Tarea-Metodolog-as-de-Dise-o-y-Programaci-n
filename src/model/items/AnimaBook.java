package model.items;

import model.units.Sorcerer;

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

    public void equipSorcerer(final Sorcerer sorcerer) {
        this.equipTo(sorcerer);
    }




}
