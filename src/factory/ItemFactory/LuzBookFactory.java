package factory.ItemFactory;

import model.items.LuzBook;

public class LuzBookFactory implements ItemFactory {

    public LuzBook create() { return new LuzBook("LuzBook", 0, 0, 0); }

}
