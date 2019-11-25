package Factory.ItemFactory;


import model.items.OscuridadBook;

public class OscuridadBookFactory implements ItemFactory {

    public OscuridadBook create() { return new OscuridadBook("OscuridadBook", 0, 0, 0); }

}
