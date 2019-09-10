package model.items;

import model.units.Sorcerer;

public class AnimaBook extends Book {
    /**
     * Creates a new anima book.
     *
     * @param name
     *     the name of the bow
     * @param power
     *     the damage power of the bow
     * @param minRange
     *     the minimum range of the bow
     * @param maxRange
     *     the maximum range of the bow
     */
    public AnimaBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    public void equipSorcerer(final Sorcerer sorcerer) {
        this.equipTo(sorcerer);
    }

    /*
  COMBATE
  */

    @Override
    public boolean stronger(IEquipableItem item) { return item.animaBookStrongerThan(); }

    @Override
    public boolean weaker(IEquipableItem item) { return item.animaBookWeakerThan(); }




    @Override
    public boolean oscuridadBookStrongerThan() {return true;}

    @Override
    public boolean luzBookWeakerThan() {return true;}


    //Un book es mas fuerte que to do lo que no es un book

    @Override
    public boolean axeStrongerThan() {return true;}

    @Override
    public boolean spearStrongerThan() {return true;}

    @Override
    public boolean swordStrongerThan() {return true;}

    @Override
    public boolean staffStrongerThan() {return true;}

    @Override
    public boolean bowStrongerThan() {return true;}



    //Un libro es mas debil que to do lo que no es un book

    @Override
    public boolean axeWeakerThan() {return true;}

    @Override
    public boolean spearWeakerThan() {return true;}

    @Override
    public boolean swordWeakerThan() {return true;}

    @Override
    public boolean staffWeakerThan() {return true;}

    @Override
    public boolean bowWeakerThan() {return true;}



}
