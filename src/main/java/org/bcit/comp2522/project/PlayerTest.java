package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {

  private Player player;
  private Window window;
  private PVector direction;
  private PImage playerImage;

  @BeforeEach
  public void setUp() {
    window = new Window();
    player = new Player(5, 5, 100, window, new PVector(1, 1));
    direction = new PVector(1, 1);
    playerImage = new PImage();
  }

  @Test
  public void testUpdate() {
    player.update(direction);
    Assertions.assertEquals(12, player.getX());
    Assertions.assertEquals(12, player.getY());
  }

  @Test
  public void testSetPlayer() {
    player.setPlayer(playerImage);
    Assertions.assertEquals(playerImage, Player.PlayerImage);
  }

  @Test
  void testUpdate1() {
    Player player = new Player(50, 50, 50, window, new PVector(1, 1));

    player.update(new PVector(1, 0)); // move player to the right
    Assertions.assertEquals(57, player.getX(), 0.01);

    player.update(new PVector(0, -1)); // move player up
    Assertions.assertEquals(43, player.getY(), 0.01);
  }

  @Test
  void testIsAliveFalse() {
    Player player = new Player(50, 50, 50, window, new PVector(1, 1));
    player.health = 0;

    Assertions.assertFalse(player.isAlive());
  }
  @Test
  void testIsAliveFalse1() {
    Player player = new Player(50, 50, 50, window, new PVector(1, 1));
    player.health = 0;

    Assertions.assertFalse(player.isAlive());
  }


}