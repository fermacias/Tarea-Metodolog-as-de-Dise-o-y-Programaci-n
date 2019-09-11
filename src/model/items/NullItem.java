package model.items;

import model.units.Archer;

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


    @Override
    public boolean stronger(IEquipableItem item) { return false; }

    @Override
    public boolean weaker(IEquipableItem item) { return  false; }

    @Override
    public void attack(IEquipableItem item) {  }




    @Override
    public boolean IamNull(){
        return true;
    }

}
