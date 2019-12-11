import controller.GameController;
import factory.UnitFactory.*;
import model.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        gameController.initGame(10);
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
