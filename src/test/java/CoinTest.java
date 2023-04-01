package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

  Coin coin;
  Window window;
  PImage coinImage;
  Sprite player;

  @BeforeEach
  // Set up the coin object.
  void setUp() {
    window = new Window();
    coinImage = new PImage();
    coin = new Coin(100, 100, 20, 20, window, coinImage, player);
  }

  @Test
  // Test that the coin is collected if the player is touching it.
  void testCollide() {
    Sprite.x = 100;
    Sprite.y = 100;
    Sprite.diameter = 20;
    Coin.score = 0;
    coin.collide();
    assertTrue(coin.isCollected());
    assertEquals(5, Coin.score);
  }

  @Test
  // Test that the coin is not collected if the player is not touching it.
  void testUnspawn() {
    coin.setSpawnTime(System.currentTimeMillis() - 7000);
    assertTrue(coin.unspawn());
    coin.setSpawnTime(System.currentTimeMillis() - 5000);
    assertFalse(coin.unspawn());
  }

  @Test
    // Test that the coin is collected if the player is touching it.
  void testCollide2() {
    Sprite.x = 150;
    Sprite.y = 150;
    Sprite.diameter = 25;
    Coin.score = 5;
    coin.collide();
    assertTrue(coin.isCollected());
    assertEquals(10, Coin.score);
  }

  @Test
    // Test that the coin is not collected if the player is not touching it.
  void testUnspawn2() {
    coin.setSpawnTime(System.currentTimeMillis() - 3000);
    assertTrue(coin.unspawn());
    coin.setSpawnTime(System.currentTimeMillis() - 2000);
    assertFalse(coin.unspawn());
  }
}
