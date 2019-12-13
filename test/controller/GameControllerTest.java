package controller;

import java.util.*;
import java.util.stream.IntStream;

import factory.ItemFactory.*;
import factory.UnitFactory.*;
import model.Tactician;
import model.items.IEquipableItem;
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
      assertEquals(testMap.getMap().get(key), gameMap.getMap().get(key));
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
    for (int i = 0; i < 10; i++) {
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
      controller.initGame(random);
      assertEquals(random, controller.getMaxRounds());
      for (Tactician tactician : controller.getTacticians()) {
        tactician.killUnits();
        controller.getTurns().remove(tactician);
      }
    });

    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

/*
  @Test
  void afterCombatTest() {
    controller.initGame(-1);

    Tactician tactician1 = controller.getTacticians().get(0);
    Tactician tactician2 = controller.getTacticians().get(1);

    IUnit heroTac1 = tactician1.getUnit(0);
    IUnit alpacaTac1 = tactician1.getUnit(1);
    IUnit archerTac2 = tactician2.getUnit(0);
    IUnit clericTac2 = tactician2.getUnit(1);



    // hero pierde en su turno
    // pierde el turno
    tactician1.yourTurn();
    heroTac1.setCurrentHitPoints(0);
    controller.afterCombat(heroTac1, alpacaTac2);
    assertFalse(tactician1.inTurn());
    assertTrue(controller.getTacticians().contains(tactician1));
    assertFalse(tactician1.getUnitList().contains(heroTac1));

    // hero pierde en otro turno
    // pierde la partida
    assertFalse(tactician2.inTurn());
    heroTac2.setCurrentHitPoints(0);
    controller.afterCombat(heroTac2, alpacaTac1);
    assertFalse(controller.getTacticians().contains(tactician2));
    assertFalse(tactician1.getUnitList().contains(heroTac1));


  }

 */

  @Test
  void endTurn() {
    controller.getRandom().setSeed(randomSeed);
    controller.newOrder();
    Tactician firstPlayer = controller.getTurnOwner();

    Tactician secondPlayer = controller.getTurns().get(0);
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

    controller.removeTactician("Player 1");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 2", tactician));
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 6");
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
    assertNull(controller.getWinners());    // por qué sería null?
    controller.removeTactician("Player 1");
    controller.removeTactician("Player 3");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 2", "Player 4").containsAll(winners));

    controller.initEndlessGame();
    assertNull(controller.getWinners());
    controller.removeTactician("Player " + 2);
    assertTrue(List.of("Player 4").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes uwu
  @Test
  void getSelectedUnit() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    Tactician currentTactician = controller.getTurnOwner();
    assertEquals(currentTactician.getName(), "Player 3");
    currentTactician.setSelectedUnit(2);
    assertEquals(controller.getSelectedUnit(), currentTactician.getSelectedUnit());
  }

  @Test
  void selectUnitIn() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();

    List<IUnit> gameUnits = new ArrayList<>();
    for(Tactician tactician : controller.getTacticians()) {
      for (IUnit unit : tactician.getUnitList()) {
        gameUnits.add(unit);
        assertNotNull(unit.getLocation());
        assertEquals(unit.getLocation().getUnit(), unit);
      }
    }

    int x=0, y=0;
    int s = (int)Math.sqrt(controller.getGameMap().getSize());
    while (gameUnits.size()!=0) {
      controller.selectUnitIn(x,y);
      assertEquals(gameUnits.remove(0), controller.getGcSelectedUnit());
      if ((x+1)*s<controller.getGameMap().getSize() || y==s-1) { x++; y=0; }
      else { y++; }
    }
  }

  @Test
  void getItems() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    for (int i=0; i<20; i++) {
      Tactician currentTactician = controller.getTurnOwner();
      currentTactician.setSelectedUnit(0);
      assertEquals(currentTactician.getItems(0), controller.getItems());
    }
  }

  @Test
  void equipItem() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    Tactician tactician = controller.getTacticians().get(1);
    controller.setGcSelectedUnit(tactician.getUnit(0));
    controller.equipItem(0);
    assertEquals(tactician.getItems(0).get(0), tactician.getUnit(0).getEquippedItem());
  }

  /*
  @Test
  void useItemOn() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    controller.getTurnOwner().setSelectedUnit(2);
    IUnit unit1 = controller.getSelectedUnit();
    IUnit unit2 = controller.getGameMap().getCell(5, 0).getUnit();
    unit1.equipItem(unit1.getItems().get(0));
    unit2.equipItem(unit2.getItems().get(0));

    controller.useItemOn(5,0);

    assertNotEquals(unit1.getCurrentHitPoints(), 100);
    assertNotEquals(unit2.getCurrentHitPoints(), 100);

  }

   */

  @Test
  void selectItem() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    Tactician tactician = controller.getTacticians().get(2);
    controller.setGcSelectedUnit(tactician.getUnit(0));
    controller.selectItem(0);
    assertEquals(controller.getGcSelectedUnit().getItems().get(0), controller.getGcSelectedItem());
  }

  @Test
  void giveItemTo() {
    controller.getRandom().setSeed(1);
    controller.initEndlessGame();
    controller.getTurnOwner().setSelectedUnit(2);
    IUnit unit1 = controller.getSelectedUnit();
    IUnit unit2 = controller.getGameMap().getCell(5, 0).getUnit();
    IEquipableItem item = unit1.getItems().get(0);
    unit1.equipItem(unit1.getItems().get(0));
    unit2.equipItem(unit2.getItems().get(0));

    controller.giveItemTo(5, 0);
    assertFalse(unit1.getItems().contains(item));
    assertTrue(unit2.getItems().contains(item));
  }

}