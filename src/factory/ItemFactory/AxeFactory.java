package factory.ItemFactory;

import model.items.Axe;

public class AxeFactory implements ItemFactory {

    public Axe create() { return new Axe("Axe", 0, 0, 0); }
}
