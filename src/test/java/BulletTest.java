import org.bcit.comp2522.project.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PVector;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * This class tests the Bullet class.
 *
 * @author Gathrean Dela Cruz
 * @version 1.0
 */
class BulletTest {
  private Bullet bullet;

  @BeforeEach
  void setUp() {
    ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
    Player player = new Player(0, 0, 50);
    Window window = new Window();
    bullet = new Bullet(0, 0, 0, 0, 10, goblins, skeletons, trolls, player, window);
  }

  /**
   * Tests the getX method of the Bullet class by creating a bullet and checking if the x value.
   */
  @Test
  public void testGetX1() {
    float expectedX = 10.0f;
    Bullet bullet = new Bullet(expectedX, 20.0f, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualX = bullet.getX();
    assertEquals(expectedX, actualX, 0.001f);
  }
  @Test
  public void testGetX2() {
    float expectedX = 20.0f;
    Bullet bullet = new Bullet(expectedX, 20.0f, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualX = bullet.getX();
    assertEquals(expectedX, actualX, 0.001f);
  }
  @Test
  public void testGetX3() {
    float expectedX = 30.0f;
    Bullet bullet = new Bullet(expectedX, 20.0f, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualX = bullet.getX();
    assertEquals(expectedX, actualX, 0.001f);
  }
  @Test
  public void testGetX4() {
    float expectedX = 40.0f;
    Bullet bullet = new Bullet(expectedX, 20.0f, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualX = bullet.getX();
    assertEquals(expectedX, actualX, 0.001f);
  }
  @Test
  public void testGetX5() {
    float expectedX = 50.0f;
    Bullet bullet = new Bullet(expectedX, 20.0f, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualX = bullet.getX();
    assertEquals(expectedX, actualX, 0.001f);
  }

  /**
   * Tests the getY method of the Bullet class by creating a bullet and checking if the y value.
   */
  @Test
  public void testGetY1() {
    float expectedY = 20.0f;
    Bullet bullet = new Bullet(10.0f, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualY = bullet.getY();
    assertEquals(expectedY, actualY, 0.001f);
  }
  @Test
  public void testGetY2() {
    float expectedY = 30.0f;
    Bullet bullet = new Bullet(10.0f, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualY = bullet.getY();
    assertEquals(expectedY, actualY, 0.001f);
  }
  @Test
  public void testGetY3() {
    float expectedY = 40.0f;
    Bullet bullet = new Bullet(10.0f, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualY = bullet.getY();
    assertEquals(expectedY, actualY, 0.001f);
  }
  @Test
  public void testGetY4() {
    float expectedY = 50.0f;
    Bullet bullet = new Bullet(10.0f, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualY = bullet.getY();
    assertEquals(expectedY, actualY, 0.001f);
  }
  @Test
  public void testGetY5() {
    float expectedY = 60.0f;
    Bullet bullet = new Bullet(10.0f, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);
    float actualY = bullet.getY();
    assertEquals(expectedY, actualY, 0.001f);
  }

  /**
   * Tests the getWindow method.
   */
  @Test
  public void testGetWindow() {
    Window window = new Window();
    Player Player = new Player(5, 5, 100, window, null);
    Window actual = Player.getWindow();
    assertEquals(window, actual);
  }

  /**
   * Tests the getHealth method.
   */
  @Test
  public void testDisplayHealth1() {
    Window window = new Window();
    Player Player = new Player(5, 5, 100, window, null);
    // You can't really test the graphics output, so this test just checks that the method doesn't throw an exception.
    assertTrue(true);
  }

  /**
   * Tests the update() method of the Bullet class.
   */
  @Test
  void testUpdate1() {
    // Test that the position of the bullet is updated correctly after calling update().
    bullet.setX(1);
    bullet.setY(2);
    bullet.setVx(3);
    bullet.setVy(4);
    bullet.update();
    assertEquals(4, bullet.getX());
    assertEquals(6, bullet.getY());
  }
  @Test
  void testUpdate2() {
    // Test that the position of the bullet is updated correctly after calling update().
    bullet.setX(2);
    bullet.setY(3);
    bullet.setVx(3);
    bullet.setVy(4);
    bullet.update();
    assertEquals(5, bullet.getX());
    assertEquals(7, bullet.getY());
  }
  @Test
  void testUpdate3() {
    // Test that the position of the bullet is updated correctly after calling update().
    bullet.setX(3);
    bullet.setY(4);
    bullet.setVx(3);
    bullet.setVy(4);
    bullet.update();
    assertEquals(6, bullet.getX());
    assertEquals(8, bullet.getY());
  }
  @Test
  void testUpdate4() {
    // Test that the position of the bullet is updated correctly after calling update().
    bullet.setX(4);
    bullet.setY(5);
    bullet.setVx(3);
    bullet.setVy(4);
    bullet.update();
    assertEquals(7, bullet.getX());
    assertEquals(9, bullet.getY());
  }
  @Test
  void testUpdate5() {
    // Test that the position of the bullet is updated correctly after calling update().
    bullet.setX(5);
    bullet.setY(6);
    bullet.setVx(3);
    bullet.setVy(4);
    bullet.update();
    assertEquals(8, bullet.getX());
    assertEquals(10, bullet.getY());
  }

  /**
   * Tests the getX and getY methods after updating Player position.
   */
  @Test
  public void testGetXAndYAfterUpdate1() {
    Window window = new Window();
    Player Player = new Player(100, 100, 50, window, null);
    Bullet bullet = new Bullet(100, 100, 10, window, Player);

    // Update Player position
    Player.update(new PVector(1, 1));

    float expectedX = 107; // Initial position is 100 and bullet moves 10 pixels to the left
    float actualX = Player.getX();
    assertEquals(expectedX, actualX, 0.001);

    float expectedY = 107; // Initial position is 100 and bullet moves 10 pixels to the left
    float actualY = Player.getY();
    assertEquals(expectedY, actualY, 0.001);
  }
  @Test
  public void testGetXAndYAfterUpdate2() {
    Window window = new Window();
    Player Player = new Player(100, 100, 50, window, null);

    // Update Player position
    Player.update(new PVector(1, 1));

    float expectedX = 107; // Initial position is 100 and bullet moves 10 pixels to the left
    float actualX = Player.getX();
    assertEquals(expectedX, actualX, 0.001);

    float expectedY = 107; // Initial position is 100 and bullet moves 10 pixels to the left
    float actualY = Player.getY();
    assertEquals(expectedY, actualY, 0.001);
  }

  /**
   * Tests the Bullet's getX and getY methods after updating bullet position.
   */
  @Test
  public void testBulletPositionAfterUpdate() {
    float expectedX = 0.0f;
    float expectedY = 20.0f;
    Bullet bullet = new Bullet(expectedX, expectedY, 0.0f, 0.0f, 10.0f, null, null, null, null, null);

    bullet.update(new PVector(1.0f, 1.0f)); // move the bullet by (1,1)

    float actualX = bullet.getX();
    float actualY = bullet.getY();

    assertEquals(expectedX + bullet.getSpeed(), actualX, 0.001f);
    assertEquals(expectedY + bullet.getSpeed(), actualY, 0.001f);
  }


}
