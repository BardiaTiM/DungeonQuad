import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;

class GoblinTest {

  private Goblin goblin;
  private Window window;

  @BeforeEach
  void setUp() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    window = new Window();
    PImage image = applet.loadImage("images/enemies/goblin.png");
    goblin = new Goblin(100, 100, 50, true, window, image);
  }


  @Test
  void testMove() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    // Test moving right
    goblin.movingRight = true;
    float initialX = goblin.x;
    goblin.move();
    Assertions.assertEquals(initialX, goblin.x);

    // Test moving left
    goblin.movingRight = false;
    initialX = goblin.x;
    goblin.move();
    Assertions.assertEquals(initialX - 4, goblin.x);

    // Test moving down
    goblin.movingDown = true;
    float initialY = goblin.y;
    goblin.move();
    Assertions.assertEquals(initialY, goblin.y);

    // Test moving up
    goblin.movingDown = false;
    initialY = goblin.y;
    goblin.move();
    Assertions.assertEquals(initialY - 4, goblin.y);
  }


  @Test
  void testGetHealthStatus() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    Assertions.assertTrue(goblin.isAlive);

    goblin.getHealthStatus(false);
    Assertions.assertFalse(goblin.isAlive);

    goblin.getHealthStatus(true);
    Assertions.assertFalse(goblin.isAlive);
  }

  @Test
  void testSetX() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float x = 100;
    goblin.setX(x);
    Assertions.assertEquals(x, goblin.x);
  }

  void testSetY() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float y = 100;
    goblin.setY(y);
    Assertions.assertEquals(y, goblin.y);
  }

  @Test
  void testSetX1() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float x = -100;
    goblin.setX(x);
    Assertions.assertEquals(x, goblin.x);
  }

  void testSetY1() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float y = -100;
    goblin.setY(y);
    Assertions.assertEquals(y, goblin.y);
  }

  @Test
  void testDie() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    Assertions.assertTrue(goblin.getAliveStatus());

    goblin.takeDamage(100);
    Assertions.assertFalse(goblin.getAliveStatus());

    float initialY = goblin.y;
    goblin.move();
    Assertions.assertEquals(initialY, goblin.y);
  }
  @Test
  public void testMoveRight() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    goblin.movingRight = true;
    float initialX = goblin.x;
    goblin.move();
    Assertions.assertEquals(initialX, goblin.x);
  }

  @Test
  public void testMoveLeft() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    goblin.movingRight = false;
    float initialX = goblin.x;
    goblin.move();
    Assertions.assertEquals(initialX - 4, goblin.x);
  }

  @Test
  public void testMoveDown() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    goblin.movingDown = true;
    float initialY = goblin.y;
    goblin.move();
    Assertions.assertEquals(initialY, goblin.y);
  }

  @Test
  public void testMoveUp() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    goblin.movingDown = false;
    float initialY = goblin.y;
    goblin.move();
    Assertions.assertEquals(initialY - 4, goblin.y);
  }

  @Test
  public void testGetHealthStatusAlive() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    Assertions.assertTrue(goblin.getAliveStatus());
  }

  @Test
  public void testGetHealthStatusDead() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    goblin.takeDamage(100);
    Assertions.assertFalse(goblin.getAliveStatus());
  }

  @Test
  public void testSetXPositive() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float x = 100;
    goblin.setX(x);
    Assertions.assertEquals(x, goblin.x);
  }

  @Test
  public void testSetXNegative() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float x = -100;
    goblin.setX(x);
    Assertions.assertEquals(x, goblin.x);
  }

  @Test
  public void testSetYPositive() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float y = 100;
    goblin.setY(y);
    Assertions.assertEquals(y, goblin.y);
  }

  @Test
  public void testSetYNegative() {
    PApplet applet = new PApplet();
    applet.sketchPath("");
    float y = -100;
    goblin.setY(y);
    Assertions.assertEquals(y, goblin.y);
  }


}
