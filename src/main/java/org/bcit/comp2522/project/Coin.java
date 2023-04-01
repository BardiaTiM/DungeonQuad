package org.bcit.comp2522.project;

import processing.core.PImage;


/**
 * This class creates Coin instances that interact with the Player player.
 * If the Player player collides with a coin, the coin will be collected and disappear.
 *
 * @author Will Ondrik
 */
class Coin extends Collidable {
  private float x;
  private float y;
  int coinHeight;
  int coinWidth;
  private float size = 10;
  private long spawnTime;
  private final long lifetime = 6000; //5 seconds
  public static int score;
  private boolean isCollected = false;

  private Window window;
  PImage coinImage;
  Player player;



  /**
   * Initializes a new Coin instance.
   *
   * @param x         The x-coordinate of the coin.
   * @param y         The y-coordinate of the coin.
   * @param coinHeight The height of the coin image.
   * @param coinWidth  The width of the coin image.
   * @param window     The Window instance to render the coin in.
   * @param coinImage  The PImage instance for the coin image.
   * @param player     The Player instance representing the player.
   */
  public Coin(float x, float y, int coinHeight, int coinWidth, Window window, PImage coinImage, Player player) {
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


  public void draw() {
    window.image(coinImage, x - size / 2, y - size / 2);
  }

  @Override
  void collide() {
    if (Coin.collides(x, y, size, Player.x, Player.y, Player.diameter + 40)) {
      isCollected = true;
      Window.score += 5; //Adds score in window
    }
  }

  public boolean isCollected(){
    return isCollected;
  }

  @Override
  public void update() {

  }

  public boolean unspawn() {
    return System.currentTimeMillis() - spawnTime > lifetime;
  }

  public void setSpawnTime(long l) {
    spawnTime = l;
  }
}
