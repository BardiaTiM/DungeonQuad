import org.bcit.comp2522.project.Arrow;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrowTest {

  @Test
  public void testUpdate() {
    Window window = new Window();
    Arrow arrow = new Arrow(0, 0, 1.0f, 10.0f, window);
    float initialY = arrow.getY();
    arrow.update();
    float updatedY = arrow.getY();
    assertEquals(initialY + 2.0f, updatedY, "Arrow y position should update by vy * 2");
  }

  @Test
  public void testDraw() {
    Window window = new Window();
    Arrow arrow = new Arrow(0, 0, 1.0f, 10.0f, window);
    float expectedSize = 30.0f;
    arrow.draw();
    float actualSize = arrow.getSize();
    assertEquals(expectedSize, actualSize, "Arrow size should be set to 30 when drawn");
  }

  @Test
  public void testSettersAndGetters() {
    Window window = new Window();
    Arrow arrow = new Arrow(0, 0, 1.0f, 10.0f, window);
    float expectedX = 20.0f;
    float expectedY = 30.0f;
    float expectedSize = 40.0f;
    float expectedVY = 1.0f;
    arrow.setX(expectedX);
    arrow.setY(expectedY);
    arrow.setSize(expectedSize);
    float actualX = arrow.getX();
    float actualY = arrow.getY();
    float actualSize = arrow.getSize();
    float actualVY = arrow.getVY();
    assertEquals(expectedX, actualX, "Arrow x position should be set to 20");
    assertEquals(expectedY, actualY, "Arrow y position should be set to 30");
    assertEquals(expectedSize, actualSize, "Arrow size should be set to 40");
    assertEquals(expectedVY, actualVY, "Arrow vy should be set to 1.0");
  }
}
