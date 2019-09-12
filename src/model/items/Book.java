package model.items;

/**
 * Abstract class that defines the behaviour of Magic Books.
 *
 * @author Fernanda Macías Herrera
 *
 * @since 1.0
 */
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


    /**
     * {@inheritDoc}
     * <p>
     * The axe is stronger than <i>Books</i>.
     */
    @Override
    public boolean axeStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The spear is stronger than <i>Books</i>.
     */
    @Override
    public boolean spearStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The sword is stronger than <i>Books</i>.
     */
    @Override
    public boolean swordStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The staff is stronger than <i>Books</i>.
     */
    @Override
    public boolean staffStrongerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The bow is stronger than <i>Books</i>.
     */
    @Override
    public boolean bowStrongerThan() {return true;}


    /**
     * {@inheritDoc}
     * <p>
     * The axe is weaker than <i>Books</i>.
     */
    @Override
    public boolean axeWeakerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The spear is weaker than <i>Books</i>.
     */
    @Override
    public boolean spearWeakerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The sword is weaker than <i>Books</i>.
     */
    @Override
    public boolean swordWeakerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The staff is weaker than <i>Books</i>.
     */
    @Override
    public boolean staffWeakerThan() {return true;}

    /**
     * {@inheritDoc}
     * <p>
     * The bow is weaker than <i>Books</i>.
     */
    @Override
    public boolean bowWeakerThan() {return true;}



}
