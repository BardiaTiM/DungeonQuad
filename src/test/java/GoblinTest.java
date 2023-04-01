import org.bcit.comp2522.project.Axe;
import org.bcit.comp2522.project.Goblin;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.testng.AssertJUnit.assertFalse;

class GoblinTest {
  private Window window;
  private PImage goblinImage;
  private Goblin goblin;

  @BeforeEach
  //set up the window and the goblin
  void setUp() {
    PApplet.runSketch(new String[]{"Window Test"}, window = new Window());
    goblinImage = new PImage();
    goblin = new Goblin(0, 0, 1, true, window, goblinImage);
  }

  @Test
  //test if the goblin is shooting an axe
  void testShootAxe() {
    ConcurrentLinkedQueue<Axe> axes = Goblin.axes;
    goblin.shootAxe();
    Assertions.assertEquals(1, axes.size());
    Axe axe = axes.poll();
    Assertions.assertNotNull(axe);
    Assertions.assertEquals(0, axe.getX());
    Assertions.assertEquals(0, axe.getY());
    Assertions.assertEquals(5, axe.getDiameter());
    Assertions.assertEquals(1, axe.getSpeed());
    Assertions.assertEquals(goblin, axe.getOwner());
  }

  @Test
  //test if the goblin is alive after the damage is taken
  void testTakeDamage() {
    Assertions.assertTrue(goblin.isAlive());
    goblin.takeDamage(10.0);
    Assertions.assertFalse(goblin.isAlive());
  }

  @Test
  //test if the goblin is moving
  void testMove() {
    goblin.move();
    Assertions.assertEquals(0, goblin.getX());
    Assertions.assertNotEquals(0, goblin.getY());
  }

  @Test
  //test if the goblin is alive after the damage is taken
  void testGetHealthStatus() {
    Assertions.assertTrue(goblin.isAlive());
    goblin.getHealthStatus(false);
    Assertions.assertFalse(goblin.isAlive());
  }

  @Test
    //test if the goblin is shooting an axe multiple times
  void testShootMultipleAxes() {
    ConcurrentLinkedQueue<Axe> axes = Goblin.axes;
    goblin.shootAxe();
    goblin.shootAxe();
    goblin.shootAxe();
    Assertions.assertEquals(3, axes.size());
    Axe axe1 = axes.poll();
    Assertions.assertNotNull(axe1);
    Assertions.assertEquals(0, axe1.getX());
    Assertions.assertEquals(0, axe1.getY());
    Assertions.assertEquals(5, axe1.getDiameter());
    Assertions.assertEquals(1, axe1.getSpeed());
    Assertions.assertEquals(goblin, axe1.getOwner());
    Axe axe2 = axes.poll();
    Assertions.assertNotNull(axe2);
    Assertions.assertEquals(0, axe2.getX());
    Assertions.assertEquals(0, axe2.getY());
    Assertions.assertEquals(5, axe2.getDiameter());
    Assertions.assertEquals(1, axe2.getSpeed());
    Assertions.assertEquals(goblin, axe2.getOwner());
    Axe axe3 = axes.poll();
    Assertions.assertNotNull(axe3);
    Assertions.assertEquals(0, axe3.getX());
    Assertions.assertEquals(0, axe3.getY());
    Assertions.assertEquals(5, axe3.getDiameter());
    Assertions.assertEquals(1, axe3.getSpeed());
    Assertions.assertEquals(goblin, axe3.getOwner());
  }

  @Test
    //test if the goblin is alive after taking different amount of damage
  void testTakeDifferentAmountsOfDamage() {
    Assertions.assertTrue(goblin.isAlive());
    goblin.takeDamage(5.0);
    Assertions.assertTrue(goblin.isAlive());
    goblin.takeDamage(20.0);
    Assertions.assertFalse(goblin.isAlive());
  }

  @Test
    //test if the goblin is moving in different directions
  void testMoveInDifferentDirections() {
    goblin.move();
    Assertions.assertEquals(0, goblin.getX());
    Assertions.assertNotEquals(0, goblin.getY());
    goblin.move();
    Assertions.assertNotEquals(0, goblin.getX());
    Assertions.assertNotEquals(0, goblin.getY());
  }

  @Test
    //test if the goblin is alive after receiving health status in different ways
  void testGetHealthStatusInDifferentWays() {
    Assertions.assertTrue(goblin.isAlive());
    goblin.getHealthStatus(true);
    Assertions.assertTrue(goblin.isAlive());
    goblin.getHealthStatus(false);
    Assertions.assertFalse(goblin.isAlive());
  }

}
