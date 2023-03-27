import org.bcit.comp2522.project.Collidable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollidableTest {

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

  @Test
  void testCollides1() {
    // Test colliding objects
    assertTrue(Collidable.collides(100, 0, 50, 100, 0, 50));

    // Test non-colliding objects
    assertFalse(Collidable.collides(100, 0, 50, 200, 0, 50));
  }

  @Test
  void testCollides2() {
    // Test colliding objects
    assertTrue(Collidable.collides(100, 0, 50, 100, 0, 50));

    // Test non-colliding objects
    assertFalse(Collidable.collides(0, 0, 50, 200, 0, 50));
  }

  @Test
  void testCollides3() {
    // Test colliding objects
    assertTrue(Collidable.collides(50, 0, 50, 0, 0, 100));

    // Test non-colliding objects
    assertFalse(Collidable.collides(200, 0, 50, 0, 0, 50));
  }

  @Test
  void testCollides4() {
    // Test colliding objects
    assertTrue(Collidable.collides(0, 0, 50, 0, 0, 50));

    // Test non-colliding objects
    assertFalse(Collidable.collides(0, 0, 50, 100, 0, 10));
  }




}
