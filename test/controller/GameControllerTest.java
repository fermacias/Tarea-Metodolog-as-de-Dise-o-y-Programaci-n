package controller;

import java.util.*;
import java.util.stream.IntStream;

import factory.UnitFactory.AlpacaFactory;
import factory.UnitFactory.HeroFactory;
import factory.UnitFactory.SwordMasterFactory;
import model.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @author Fernanda Macías Herrera
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 128, randomSeed);
    testTacticians = List.of("Player 1", "Player 2", "Player 3", "Player 4");
  }

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + (i + 1), tacticians.get(i).getName());
    }
  }


  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(128, gameMap.getSize());
    assertTrue(gameMap.isConnected());

    Field testMap = new Field();
    testMap.getRandom().setSeed(randomSeed);
    testMap.randomField(128);

    for (Map.Entry<String, Location> entry1 : gameMap.getMap().entrySet()) {
      String key = entry1.getKey();
      assertTrue(testMap.getMap().containsKey(key));
    }
  }

  @Test
  void bornUnitsTest() {
    controller.createUnits(2, new AlpacaFactory(), new HeroFactory());
    for (Tactician tactician : controller.getTacticians()) {
      assertEquals(tactician.getUnitsNumber(), 2);
      assertFalse(tactician.getUnit(0).isAHero());
      assertTrue(tactician.getUnit(1).isAHero());
      assertEquals(tactician.getItems(0).size(), 0);
      assertEquals(tactician.getItems(1).size(), 0);
    }
    controller.createUnits(1, new SwordMasterFactory(), new HeroFactory());
    int i = 0;
    for (Tactician tactician : controller.getTacticians()) {
      assertEquals(tactician.getUnitsNumber(), 3);
      if (i == 0 || i == 2)
        assertFalse(tactician.getUnit(2).isAHero());
      if (i == 1 || i == 3)
        assertTrue(tactician.getUnit(2).isAHero());
      i++;
    }
  }


  @Test
  void positionUnitsTest() {
    GameController gc =  new GameController(7, 22);
    gc.initGame(-1);
    for(Tactician tactician : gc.getTacticians())
      for (int i = 0; i < 3; i++)
        assertNotNull(tactician.getUnit(i).getLocation());
  }


  @Test
  void newDistributionTest() {
    controller.newDistribution();
    List<Tactician> tacticians = controller.getTacticians();
    for (Tactician tactician : tacticians) {
      assertEquals(3, tactician.getUnitsNumber());
      for (int i = 0; i < 3; i++) {
        assertEquals(4, tactician.getItems(i).size());
      }
    }
  }


  void updateLastTactician (Tactician lastTactician, Tactician newTactician) {
    lastTactician = newTactician;
  }


  @Test
  void getTurnOwner() {
    //controller.getRandom().setSeed(randomSeed);
    controller.initGame(-1);
    List<Tactician> tacticianTestList = new ArrayList<>();

    Tactician lastTactician = controller.getTurns().get(2);
    IntStream.range(0,50).forEach(it -> {
              tacticianTestList.addAll(controller.getTacticians());
              if (it != 0) {
                assertNotEquals(controller.getTurnOwner(), lastTactician);
                updateLastTactician(lastTactician, controller.getTurns().get(2));
              }
              IntStream.range(0, 4).forEach(i -> {
                assertTrue(tacticianTestList.remove(controller.getTurnOwner()));
                assertEquals(tacticianTestList.size(), controller.getTurns().size());
                controller.endTurn();
              });
            });
  }


  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }


  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).forEach(i -> {
      int random = randomTurnSequence.nextInt();
      controller.initGame(random);    // nro random de rondas
      assertEquals(random, controller.getMaxRounds());
      for (Tactician tactician : controller.getTacticians())
        tactician.killUnits();
    });

    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  /*
  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = new Tactician(); // <- Deben cambiar esto (!)
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
            .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 2").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes
  @Test
  void getSelectedUnit() {
  }

  @Test
  void selectUnitIn() {
  }

  @Test
  void getItems() {
  }

  @Test
  void equipItem() {
  }

  @Test
  void useItemOn() {
  }

  @Test
  void selectItem() {
  }

  @Test
  void giveItemTo() {
  }
*/

}