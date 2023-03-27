package org.bcit.comp2522.project;

import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

  private static class TestSketch extends PApplet {
    public void settings() {
      size(1, 1);
    }

    public void init() {
      super.initSurface();
    }
  }

  private PImage coinImage;

  @Test
  public void testCoinCollect() {
    TestSketch applet = new TestSketch();
    applet.init();
    Window window = new Window();
    Sprite player = new Sprite(0, 0, 10, window, PVector.fromAngle(0));
    float x = 100;
    float y = 100;
    int coinHeight = 20;
    int coinWidth = 20;
    Coin coin = new Coin(x, y, coinHeight, coinWidth, window, coinImage, player);

    // Check that the coin is not collected initially
    assertFalse(coin.isCollected());

    // Move the player to the coin's location and check that the coin is collected
    player.setX(x);
    player.setY(y);
    coin.collide();
    assertTrue(coin.isCollected());
  }

  public void setup() {
    TestSketch applet = new TestSketch();
    applet.init();
    coinImage = applet.loadImage("coin.png");
  }


  @Test
  void testCoinCreation() {
    TestSketch applet = new TestSketch();
    applet.init();
    Window window = new Window();
    Sprite player = new Sprite(0, 0, 10, window, PVector.fromAngle(0));
    Coin coin = new Coin(100, 100, 10, 10, window, coinImage, player);
    assertNotNull(coin);
  }

  @Test
  void testCoinCollection() {
    TestSketch applet = new TestSketch();
    applet.init();
    Window window = new Window();
    Sprite player = new Sprite(100, 100, 10, window,PVector.fromAngle(20));
    Coin coin = new Coin(100, 100, 10, 10, window, coinImage, player);
    assertFalse(coin.isCollected());
    coin.collide();
    assertTrue(coin.isCollected());
  }

  @Test
  void testCoinUnspawn() {
    TestSketch applet = new TestSketch();
    applet.init();
    Window window = new Window();
    Sprite player = new Sprite(0, 0, 10, window, PVector.fromAngle(60));
    Coin coin = new Coin(100, 100, 10, 10, window, coinImage, player);
    assertFalse(coin.unspawn());
    coin = new Coin(100, 100, 10, 10, window, coinImage, player);
    long currentTime = System.currentTimeMillis();
    coin.setSpawnTime(currentTime - 6000);
    assertFalse(coin.unspawn());
  }
}
