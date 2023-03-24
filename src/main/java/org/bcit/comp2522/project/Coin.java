package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

class Coin extends Collidable {
  private float x;
  private float y;
  int coinHeight;
  int coinWidth;
  private float size = 10;
  private long spawnTime;
  private final long lifetime = 6000; //6 seconds
  boolean isCollected = false;

  private Window window;
  PImage coinImage;
  Sprite player;

  public Coin(float x, float y, int coinHeight, int coinWidth, Window window, PImage coinImage, Sprite player) {
    super(x);
    this.x = x;
    this.y = y;
    this.coinHeight = coinHeight;
    this.coinWidth = coinWidth;
    this.window = window;
    this.spawnTime = System.currentTimeMillis();
    this.coinImage = coinImage;
    this.player = player;
  }

  public void drawCoin() {
    window.ellipse(x, y, size, size);
  }

  public void draw() {
    window.image(coinImage, x - size / 2, y - size / 2);
  }

  @Override
  void collide() {
    if (Coin.collides(x, y, size, player.x, player.y, player.diameter)) {
      isCollected = true;
      Window.score += 1;
      System.out.println("Coin collected");
    }
  }

  boolean isCollected(){
    return isCollected;
  }

  @Override
  public void update() {

  }

  public boolean unspawn() {
    return System.currentTimeMillis() - spawnTime > lifetime;
  }
}
