package factory.ItemFactory;

import model.items.Bow;

public class BowFactory implements ItemFactory {

    public Bow create() { return new Bow("Bow", 0, 0, 0); }

}
