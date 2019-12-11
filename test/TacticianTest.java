import controller.GameController;
import factory.UnitFactory.*;
import model.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import model.units.Sorcerer;
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
    private GameController gameController;
    private List<Tactician> tacticians;

    @BeforeEach
    void setUp() {
        randomSeed = new Random().nextLong();
        gameController = new GameController(2, 25);
        tacticians = gameController.getTacticians();
        tactician1 = tacticians.get(0);
        tactician2 = tacticians.get(1);
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
        Location location1 = gameController.getGameMap().getCell(0,0);
        Location location2 = gameController.getGameMap().getCell(1,0);

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
    void killUnitsTest() {
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
}
