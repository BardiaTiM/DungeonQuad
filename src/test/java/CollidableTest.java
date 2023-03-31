import org.bcit.comp2522.project.Collidable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollidableTest {

  // Test colliding objects and assert that the collides() method returns true
  // for these objects
  @Test
  void testCollidesTrue() {
    float x1 = 0;
    float y1 = 0;
    float d1 = 2;
    float x2 = 1;
    float y2 = 1;
    float d2 = 2;
    assertTrue(Collidable.collides(x1, y1, d1, x2, y2, d2));
  }

  // Test non-colliding objects and assert that the collides() method returns false
  // for these objects
  @Test
  void testCollidesFalse() {
    float x1 = 0;
    float y1 = 0;
    float d1 = 2;
    float x2 = 5;
    float y2 = 5;
    float d2 = 2;
    assertFalse(Collidable.collides(x1, y1, d1, x2, y2, d2));
  }

  // Test colliding and non-colliding objects and assert that the expected boolean
  // value is returned for each set of objects
  @Test
  void testCollides1() {
    // Test colliding objects
    assertTrue(Collidable.collides(100, 0, 50, 100, 0, 50));
    System.out.println("Objects 1 collided as expected");

    // Test non-colliding objects
    assertFalse(Collidable.collides(100, 0, 50, 200, 0, 50));
    System.out.println("Objects 1 did not collide as expected");
  }

  // Test colliding and non-colliding objects and assert that the expected boolean
  // value is returned for each set of objects
  @Test
  void testCollides2() {
    // Test colliding objects
    assertTrue(Collidable.collides(100, 0, 50, 100, 0, 50));
    System.out.println("Objects 2 collided as expected");

    // Test non-colliding objects
    assertFalse(Collidable.collides(0, 0, 50, 200, 0, 50));
    System.out.println("Objects 2 did not collide as expected");
  }

  // Test colliding and non-colliding objects and assert that the expected boolean
  // value is returned for each set of objects
  @Test
  void testCollides3() {
    // Test colliding objects
    assertTrue(Collidable.collides(50, 0, 50, 0, 0, 100));
    System.out.println("Objects 3 collided as expected");

    // Test non-colliding objects
    assertFalse(Collidable.collides(200, 0, 50, 0, 0, 50));
    System.out.println("Objects 3 did not collide as expected");
  }

  // Test colliding and non-colliding objects and assert that the expected boolean
  // value is returned for each set of objects
  @Test
  void testCollides4() {
    // Test colliding objects
    assertTrue(Collidable.collides(0, 0, 50, 0, 0, 50));
    System.out.println("Objects 4 collided as expected");

    // Test non-colliding objects
    assertFalse(Collidable.collides(100, 0, 50, 0, 0, 50));
    System.out.println("Objects 4 did not collide as expected");
  }
}