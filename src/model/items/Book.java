package model.items;

import model.units.Archer;
import model.units.Sorcerer;

public abstract class Book extends AbstractItem{

    /**
     * Creates a new book.
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
    public Book(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }



}
