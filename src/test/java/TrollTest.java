package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

public class TrollTest {

  @Test
  public void testTrollConstructor() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    assertEquals(50, troll.getX());
    assertEquals(50, troll.getY());
    assertEquals(1, troll.getDiameter(), 0.01);
    assertTrue(troll.getAlive());
  }

  @Test
  public void testTrollSetX() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.setX(100);
    assertEquals(100, troll.getX());
  }

  @Test
  public void testTrollSetY() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.setY(100);
    assertEquals(100, troll.getY());
  }

  @Test
  public void testTrollSetX1() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.setX(-100);
    assertEquals(-100, troll.getX());
  }

  @Test
  public void testTrollSetY1() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.setY(-100);
    assertEquals(-100, troll.getY());
  }

  @Test
  public void testTrollSetDiameter() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.setDiameter(2);
    assertEquals(2, troll.getDiameter(), 0.01);
  }

  @Test
  public void testTrollMove() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    float startX = troll.getX();
    float startY = troll.getY();
    troll.move();
    assertEquals(50, troll.getX());
    assertEquals(50, troll.getY());
  }

  @Test
  public void testTrollTakeDamage() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.takeDamage(10.0);
    assertFalse(troll.getAlive());
  }

  @Test
  public void testTrollGetHealthStatus() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(50, 50, 1, true, window, trollImage);
    troll.getHealthStatus(false);
    assertFalse(troll.getAlive());
  }

  @Test
  public void testTrollConstructor2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    assertEquals(100, troll.getX());
    assertEquals(100, troll.getY());
    assertEquals(2, troll.getDiameter(), 0.01);
    assertTrue(troll.getAlive());
  }

  @Test
  public void testTrollSetX2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.setX(150);
    assertEquals(150, troll.getX());
  }

  @Test
  public void testTrollSetY2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.setY(150);
    assertEquals(150, troll.getY());
  }

  @Test
  public void testTrollSetX3() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.setX(-150);
    assertEquals(-150, troll.getX());
  }

  @Test
  public void testTrollSetY3() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.setY(-150);
    assertEquals(-150, troll.getY());
  }

  @Test
  public void testTrollSetDiameter2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.setDiameter(3);
    assertEquals(3, troll.getDiameter(), 0.01);
  }

  @Test
  public void testTrollMove2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    float startX = troll.getX();
    float startY = troll.getY();
    troll.move();
    assertEquals(100, troll.getX());
    assertEquals(100, troll.getY());
  }

  @Test
  public void testTrollTakeDamage2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.takeDamage(20.0);
    assertFalse(troll.getAlive());
  }

  @Test
  public void testTrollGetHealthStatus2() {
    Window window = new Window();
    PImage trollImage = new PImage();
    Troll troll = new Troll(100, 100, 2, true, window, trollImage);
    troll.getHealthStatus(false);
    assertFalse(troll.getAlive());
  }
}
