package model.items;

import org.junit.jupiter.api.Test;

public interface ITestItem {

    /**
     * Prueba metodos animaBookStrongerThan y
     * animaBookWeakerThan
     */
    @Test
    void animaBookStrongTest();

    /**
     * Prueba metodos luzBookStrongerThan y
     * luzBookWeakerThan
     */
    @Test
    void luzBookStrongTest();

    /**
     * Prueba metodos oscuridadBookStrongerThan y
     * oscuridadBookWeakerThan
     */
    @Test
    void oscuridadBookStrongTest();

    /**
     * Prueba metodos strong y weaker en books que
     * den false
     */
    @Test
    void wrongBookStrongtest();

    /**
     * Prueba metodos stronger y weaker para items
     * axe, sword y spear
     */
    @Test
    void generalItemsStrongTest();

    /**
     * Prueba metodos stronger y weaker que deben dar false
     * para metodos axe, sword y spear
     */
    @Test
    void wrongGeneralItemsStrongTest();

    /**
     * Prueba metodos stronger y weaker para items
     * staff y bow
     */
    @Test
    void specialItemsStrongTest();




}
