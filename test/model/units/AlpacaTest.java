package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  public void AlpacaCanTake() {
    Alpaca alpaca = new Alpaca(50, 2, field.getCell(0, 0));
    alpaca.addItem(sword);
    alpaca.addItem(staff);
    alpaca.addItem(bow);
    assertEquals(true, alpaca.canTake());
    alpaca.addItem(luzBook);
    assertEquals(true, alpaca.canTake());
  }
}