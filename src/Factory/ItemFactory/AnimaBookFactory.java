package Factory.ItemFactory;

import model.items.AnimaBook;

public class AnimaBookFactory implements ItemFactory {

    public AnimaBook create() { return new AnimaBook("AnimaBook", 0, 0, 0); }

}
