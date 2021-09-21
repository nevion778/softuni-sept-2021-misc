package bg.softuni.test.di;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BartenderClassTest {

  private BartenderClass toTest;

  @Test
  void serveBeerTest() {
    toTest = new BartenderClass(new BeerMachineIfc() {
      @Override
      public String makeBeer() {
        return "test beer";
      }
    });

    Assertions.assertEquals("test beer", toTest.serveBeer());
  }

}
