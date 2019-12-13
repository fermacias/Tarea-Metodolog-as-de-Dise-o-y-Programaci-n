import controller.GameController;
import factory.ItemFactory.AxeFactory;
import factory.ItemFactory.IItemFactory;
import factory.ItemFactory.LuzBookFactory;
import factory.ItemFactory.OscuridadBookFactory;
import factory.UnitFactory.*;
import model.Tactician;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Fernanda Macías Herrera
 * @since v2.0
 */
public class TacticianTest {
    private Tactician tactician1;
    private Tactician tactician2;
    private long randomSeed;
    private GameController controller;
    private List<Tactician> tacticians;

    @BeforeEach
    void setUp() {
        randomSeed = new Random().nextLong();
        controller = new GameController(2, 25);
        tacticians = controller.getTacticians();
        tactician1 = tacticians.get(0);
        tactician2 = tacticians.get(1);

    }

    void giveUnits() {
        tactician1.unitFactory(new AlpacaFactory());
        tactician2.unitFactory(new ArcherFactory());
        tactician1.newUnit();
        tactician2.newUnit();
        tactician1.unitFactory(new SorcererFactory());
        tactician2.unitFactory(new SwordMasterFactory());
        tactician1.newUnit();
        tactician2.newUnit();
        tactician1.unitFactory(new ClericFactory());
        tactician2.unitFactory(new FighterFactory());
        tactician1.newUnit();
        tactician2.newUnit();
    }


    @Test
    void newLocationTest() {
        giveUnits();
        Location location1 = controller.getGameMap().getCell(0,0);
        Location location2 = controller.getGameMap().getCell(1,0);

        assertNull(tactician1.getLocation(0));
        assertTrue(tactician1.newLocation(0, location1));
        assertEquals(tactician1.getLocation(0), location1);
        assertEquals(location1.getUnit(), tactician1.getUnit(0));

        assertTrue(tactician1.newLocation(0, location2));
        assertEquals(tactician1.getLocation(0), location2);
        assertNull(location1.getUnit());
        assertEquals(location2.getUnit(), tactician1.getUnit(0));

        assertFalse(tactician2.newLocation(0, location2));
        assertEquals(tactician1.getLocation(0), location2);
        assertEquals(location2.getUnit(), tactician1.getUnit(0));
    }


    @Test
    void createItemsTest() {
        controller.createUnits(3, new ClericFactory(), new SorcererFactory());
        ArrayList<IItemFactory> factories = new ArrayList<>();
        factories.add(new AxeFactory());
        factories.add(new OscuridadBookFactory());
        factories.add(new LuzBookFactory());
        tactician1.createItems(2, factories);
        int i=0;
        for (IUnit unit : tactician1.getUnitList()) {
            assertEquals(2, unit.getItems().size());
            if (i==0 || i==2) { assertTrue(unit.getItems().get(0).IAmStaff()); }
            if (i==1) { assertFalse(unit.getItems().get(0).IAmStaff()); }
            assertFalse(unit.getItems().get(1).IAmStaff());
            i++;
        }
    }
    

    @Test
    void killUnitsTest() {
        controller.createUnits(3, new ClericFactory(), new SorcererFactory());
        List<Location> locations = new ArrayList<>();
        for(Tactician tactician : tacticians) {
            for(IUnit unit : tactician.getUnitList())
                locations.add(unit.getLocation());
            tactician.killUnits();
            for (Location location: locations)
                assertNull(location.getUnit());
            locations.clear();
            assertEquals(0, tactician.getUnitsNumber());
        }
    }

    @Test
    void settersTest() {
        controller.initEndlessGame();
        for (Tactician tactician : controller.getTacticians()) {
            tactician.setSelectedUnit(0);
            assertEquals(tactician.getSelectedUnit(), tactician.getUnit(0));
            tactician.setEquippedItem(1);
            assertEquals(tactician.getEquippedItem(), tactician.getSelectedUnit().getEquippedItem());
        }

    }
}
