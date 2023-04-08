import org.bcit.comp2522.project.Skeleton;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Skeleton class.
 *
 * @author Heeho Ryou
 * @version 1.0
 */
public class SkeletonTest {
  @Test
  public void testSkeletonConstructor() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    assertEquals(50, skeleton.getX());
    assertEquals(50, skeleton.getY());
    assertEquals(1, skeleton.getDiameter(), 0.01);
    assertTrue(skeleton.getAlive());
  }

  @Test
  public void testSkeletonSetX() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setX(100);
    assertEquals(100, skeleton.getX());
  }

  @Test
  public void testSkeletonSetY() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setY(100);
    assertEquals(100, skeleton.getY());
  }

  @Test
  public void testSkeletonSetDiameter() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setDiameter(2);
    assertEquals(2, skeleton.getDiameter(), 0.01);
  }

  @Test
  public void testSkeletonGetHealthStatus() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.getHealthStatus(false);
    assertFalse(skeleton.getAlive());
  }

  @Test
  public void testSkeletonGetHealthStatus1() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.getHealthStatus(true);
    assertTrue(skeleton.getAlive());
  }

  @Test
  public void SkeletonTestMove() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.move();
    assertEquals(50, skeleton.getX());
    assertEquals(50, skeleton.getY());
  }

  @Test
  public void testSkeletonConstructor2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(100, 75, 2, true, window, skeletonImage);
    assertEquals(100, skeleton.getX());
    assertEquals(75, skeleton.getY());
    assertEquals(2, skeleton.getDiameter(), 0.01);
    assertTrue(skeleton.getAlive());
  }

  @Test
  public void testSkeletonSetX2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setX(200);
    assertEquals(200, skeleton.getX());
  }

  @Test
  public void testSkeletonSetY2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setY(200);
    assertEquals(200, skeleton.getY());
  }

  @Test
  public void testSkeletonSetDiameter2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.setDiameter(3);
    assertEquals(3, skeleton.getDiameter(), 0.01);
  }

  @Test
  public void testSkeletonGetHealthStatus2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.getHealthStatus(true);
    assertTrue(skeleton.getAlive());
  }

  @Test
  public void testSkeletonGetHealthStatus3() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.getHealthStatus(false);
    assertFalse(skeleton.getAlive());
  }

  @Test
  public void SkeletonTestMove2() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(50, 50, 1, true, window, skeletonImage);
    skeleton.move();
    assertEquals(50, skeleton.getX());
    assertEquals(50, skeleton.getY());
  }

  @Test
  public void SkeletonTestMove3() {
    Window window = new Window();
    PImage skeletonImage = new PImage();
    Skeleton skeleton = new Skeleton(75, 75, 1, true, window, skeletonImage);
    skeleton.move();
    assertEquals(75, skeleton.getX());
    assertEquals(75, skeleton.getY());
  }
}
