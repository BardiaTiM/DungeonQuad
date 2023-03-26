import org.bcit.comp2522.project.Axe;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Axe class.
 */
public class AxeTest {

  /**
   * Tests if the Axe constructor sets the correct values.
   */
  @Test
  public void testSetVelocity() {
    Window window = new Window();
    Axe axe = new Axe(0, 0, 1.0f, 10.0f, window);
    float initialVY = axe.getVY();
    axe.setVelocity(2.0f);
    float updatedVY = axe.getVY();
    assertEquals(initialVY * 10 * 2.0f, updatedVY, "Axe vy should be set to vy * 10 * 2.0");
  }

  /**
   * Tests if update() updates the y position correctly.
   */
  @Test
  public void testUpdate() {
    Window window = new Window();
    Axe axe = new Axe(0, 0, 1.0f, 10.0f, window);
    float initialY = axe.getY();
    axe.update();
    float updatedY = axe.getY();
    assertEquals(initialY + 3.0f, updatedY, "Axe y position should update by vy * 3");
  }

  /**
   * Tests if draw() sets the correct size.
   */
  @Test
  public void testDraw() {
    Window window = new Window();
    Axe axe = new Axe(0, 0, 1.0f, 10.0f, window);
    float expectedSize = 60.0f;
    axe.draw();
    float actualSize = axe.getSize();
    assertEquals(expectedSize, actualSize, "Axe size should be set to 60 when drawn");
  }

  /**
   * Tests if the setters and getters work correctly.
   */
  @Test
  public void testSettersAndGetters() {
    Window window = new Window();
    Axe axe = new Axe(0, 0, 1.0f, 10.0f, window);

    float expectedX = 20.0f;
    float expectedY = 30.0f;
    float expectedSize = 40.0f;
    float expectedVY = 2.0f;

    axe.setX(expectedX);
    axe.setY(expectedY);
    axe.setSize(expectedSize);
    axe.setVY(expectedVY);

    float actualX = axe.getX();
    float actualY = axe.getY();
    float actualSize = axe.getSize();
    float actualVY = axe.getVY();

    assertEquals(expectedX, actualX, "Axe x position should be set to 20");
    assertEquals(expectedY, actualY, "Axe y position should be set to 30");
    assertEquals(expectedSize, actualSize, "Axe size should be set to 40");
    assertEquals(expectedVY, actualVY, "Axe vy should be set to 2.0");
  }
}
