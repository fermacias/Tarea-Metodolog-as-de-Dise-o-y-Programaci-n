package model.items;

/**
 * This class represents an Null Item.
 * <p>
 *
 * @author Fernanda Macías
 * @since 1.0
 */
public class NullItem extends AbstractItem {

    /**
     * Creates a new NullItem.
     * <p>
     * Equipp to each new Unit.
     *
     */
    public NullItem() {
        super("NullItem", 0, 0, 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>NullItem/i> is stronger than nothing.
     */
    @Override
    public boolean stronger(IEquipableItem item) { return false; }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>NullItem/i> is weaker than nothing.
     */
    @Override
    public boolean weaker(IEquipableItem item) { return  false; }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>NullItem</i> cannot attack.
     */
    @Override
    public void attack(IEquipableItem item) {  }

    /**
     * {@inheritDoc}
     * <p>
     * The <i>NullItem</i> is a NullItem.
     */
    @Override
    public boolean IamNull(){
        return true;
    }

}
