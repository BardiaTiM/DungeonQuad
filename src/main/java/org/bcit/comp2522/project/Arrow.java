package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * Arrow class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Arrow {
  private static final int ZERO = 0;
  private static final int TWO = 2;
  private static final int THIRTY = 30;


  /**
   * Arrow's x position.
   */
  float x;

  /**
   * Arrow's y position.
   */
  float y;

  /**
   * Arrow's velocity.
   */
  private final float velocity;

  /**
   * Arrow's size.
   */
  float size;

  /**
   * Arrow's window.
   */
  private final Window window;

  /**
   * Arrow's image.
   */
  private final PImage arrowImage;

  /**
   * Arrow constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param velocity velocity
   * @param size     size
   * @param window   window
   */
  public Arrow(float x, float y, float velocity, float size, Window window) {
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.size = size;
    this.window = window;
    this.arrowImage = window.loadImage("images/weapons/arrow.png");
  }

  /**
   * Updates the arrow's position.
   */
  public void update() {
    y += velocity * TWO;

    // Check if the arrow is outside the window
    if (x < ZERO || x > window.width || y < ZERO || y > window.height) {
      Skeleton.arrows.remove(this);
    }
  }

  /**
   * Draws the arrow.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public void drawArrow(float x, float y, float diameter) {
    window.image(arrowImage, x, y, diameter, diameter);
  }

  /**
   * Draws the arrow.
   */
  public void draw() {
    this.drawArrow(this.x, this.y, THIRTY);
  }

  /**
   * Gets the x position.
   *
   * @return x position
   */
  public float getX() {
    return x;
  }

  /**
   * Gets the y position.
   *
   * @return y position
   */
  public float getY() {
    return y;
  }

  /**
   * Sets the x position.
   *
   * @param x x position
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y position.
   *
   * @param y y position
   */
  public void setY(float y) {
    this.y = y;
  }
}
