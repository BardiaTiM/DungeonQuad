//import org.bcit.comp2522.project.Sprite;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import processing.core.PImage;
//import processing.core.PVector;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SpriteTest {
//  private Sprite sprite;
//  private PImage image;
//
//  @BeforeEach
//  void setUp() {
//    sprite = new Sprite(0, 0, 50, null, new PVector(0, 0));
//    image = new PImage(50, 50);
//    sprite.setSprite(image);
//  }
//
//  @Test
//  void testGettersAndSetters4() {
//    sprite.setX(100);
//    sprite.setY(200);
//
//    assertEquals(100, sprite.getX(), 0.1);
//    assertEquals(200, sprite.getY(), 0.1);
//  }
//
////  @Test
////  void testUpdate4() {
////    sprite.update(new PVector(1, 1));
////    assertEquals(7, sprite.getX(), 0.1);
////    assertEquals(7, sprite.getY(), 0.1);
////  }
//
//
//  @Test
//  void testSetSprite4() {
//    PImage newImage = new PImage(50, 50);
//    sprite.setSprite(newImage);
//    assertEquals(newImage, Sprite.getSpriteImage());
//  }
//
//  @Test
//  void testGettersAndSetters5() {
//    sprite.setX(100);
//    sprite.setY(200);
//
//    assertEquals(100, sprite.getX(), 0.1);
//    assertEquals(200, sprite.getY(), 0.1);
//  }
//
////  @Test
////  void testUpdate5() {
////    sprite.update(new PVector(1, 1));
////    assertEquals(7, sprite.getX(), 0.1);
////    assertEquals(7, sprite.getY(), 0.1);
////  }
//
//
//  @Test
//  void testSetSprite5() {
//    PImage newImage = new PImage(50, 50);
//    sprite.setSprite(newImage);
//    assertEquals(newImage, Sprite.getSpriteImage());
//  }
//
//  @Test
//  void testGettersAndSetters() {
//    sprite.setX(50);
//    sprite.setY(75);
//
//    assertEquals(50, sprite.getX(), 0.1);
//    assertEquals(75, sprite.getY(), 0.1);
//  }
//
////  @Test
////  void testUpdate() {
////    sprite.update(new PVector(-1, -1));
////    assertEquals(-7, sprite.getX(), 0.1);
////    assertEquals(-7, sprite.getY(), 0.1);
////  }
//
//  @Test
//  void testSetSprite() {
//    PImage newImage = new PImage(25, 25);
//    sprite.setSprite(newImage);
//    assertEquals(newImage, Sprite.getSpriteImage());
//  }
//
//  @Test
//  void testGettersAndSetters2() {
//    sprite.setX(75);
//    sprite.setY(50);
//
//    assertEquals(75, sprite.getX(), 0.1);
//    assertEquals(50, sprite.getY(), 0.1);
//  }
//
////  @Test
////  void testUpdate2() {
////    sprite.update(new PVector(0, 1));
////    assertEquals(0, sprite.getX(), 0.1);
////    assertEquals(7, sprite.getY(), 0.1);
////  }
//
//  @Test
//  void testSetSprite2() {
//    PImage newImage = new PImage(75, 75);
//    sprite.setSprite(newImage);
//    assertEquals(newImage, Sprite.getSpriteImage());
//  }
//
//  @Test
//  void testGettersAndSetters3() {
//    sprite.setX(25);
//    sprite.setY(75);
//
//    assertEquals(25, sprite.getX(), 0.1);
//    assertEquals(75, sprite.getY(), 0.1);
//  }
//
////  @Test
////  void testUpdate3() {
////    sprite.update(new PVector(1, 0));
////    assertEquals(7, sprite.getX(), 0.1);
////    assertEquals(0, sprite.getY(), 0.1);
////  }
//
//  @Test
//  void testSetSprite3() {
//    PImage newImage = new PImage(100, 100);
//    sprite.setSprite(newImage);
//    assertEquals(newImage, Sprite.getSpriteImage());
//  }
//
//}