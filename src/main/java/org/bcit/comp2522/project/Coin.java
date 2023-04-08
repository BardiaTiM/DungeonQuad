package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * This class creates Coin instances that interact with the Player.
 * If the Player collides with a coin, the coin will be collected and disappear.
 *
 * @author Will Ondrik
 */
public class Coin extends Collidable {
  private final float x;
  private final float y;
  int coinHeight;
  int coinWidth;
  private final float size = 10;
  private final long spawnTime;
  public static int score;
  private boolean isCollected = false;

  private final Window window;
  PImage coinImage;
  Player player;


  /**
   * Initializes a new Coin instance.
   *
   * @param x          The x-coordinate of the coin.
   * @param y          The y-coordinate of the coin.
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

  /**
   * Draws the Coin image onto the window.
   */
  public void draw() {
    window.image(coinImage, x - size / 2, y - size / 2);
  }

  /**
   * Overrides the Collide method from the Collidable interface.
   * If a Coin collides with the Player, isCollected is set to true and the score is increased.
   */
  @Override
  void collide() {
    if (Coin.collides(x, y, size, Player.x, Player.y, Player.diameter + 40)) {
      isCollected = true;
      Window.score += 5; //Adds score in window
    }
  }

  /**
   * Returns true when a Coin has been collected by the Player.
   *
   * @return True if the coin is collected, false otherwise
   */
  public boolean isCollected() {
    return isCollected;
  }

  /**
   * Override the Update method from the Collidable interface.
   */
  @Override
  public void update() {

  }

  /**
   * Removes the Coin from the Window if the Coin isn't collected within 6 seconds.
   *
   * @return true/false
   */
  public boolean unspawn() {
    final long lifetime = 6000; //5 seconds
    return System.currentTimeMillis() - spawnTime > lifetime;
  }
}
