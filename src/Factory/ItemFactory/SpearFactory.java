package Factory.ItemFactory;

import model.items.Spear;

public class SpearFactory implements ItemFactory {

    public Spear create() { return new Spear("Spear", 0, 0, 0); }

}
