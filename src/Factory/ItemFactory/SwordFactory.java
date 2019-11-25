package Factory.ItemFactory;

import model.items.Sword;

public class SwordFactory implements ItemFactory {

    public Sword create() { return new Sword("Sword", 0, 0, 0); }

}
