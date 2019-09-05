package model.items;

import model.units.Sorcerer;

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
        //this.minRange = Math.max(minRange, 2);
        //this.maxRange = Math.max(maxRange, this.minRange);
    }

    public void equipSorcerer(final Sorcerer sorcerer) { this.equipTo(sorcerer); }



}
