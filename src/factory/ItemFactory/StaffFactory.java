package factory.ItemFactory;

import model.items.Staff;

public class StaffFactory implements ItemFactory {

    public Staff create() { return new Staff("Staff", 0, 0, 0); }

}
