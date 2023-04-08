package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * This is the Troll's weapon of choice, the Boulder.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Boulder {
  /**
   * The x position of the boulder.
   */
  float x;

  /**
   * The y position of the boulder.
   */
  float y;

  /**
   * The velocity of the boulder.
   */
  private final float velocity;

  /**
   * The size of the boulder.
   */
  float size;

  /**
   * The image of the boulder.
   */
  private final PImage boulderImage;

  /**
   * The window that the boulder will be drawn on.
   */
  private final Window window;

  /**
   * This is the constructor for the Boulder class.
   *
   * @param x        the x coordinate of the boulder
   * @param y        the y coordinate of the boulder
   * @param velocity the velocity of the boulder
   * @param size     the size of the boulder
   * @param window   the window that the boulder will be drawn on
   */
  public Boulder(float x, float y, float velocity, float size, Window window) {
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.size = size;
    this.window = window;
    this.boulderImage = window.loadImage("images/weapons/boulder.png");
  }

  /**
   * This method updates the boulder's position.
   */
  public void update() {
    y += velocity * 2.5;

    // Check if the boulder is outside the window
    if (x < 0 || x > window.width || y < 0 || y > window.height) {
      Troll.boulders.remove(this);
    }
  }

  /**
   * This method draws the boulder.
   *
   * @param x        the x coordinate of the boulder
   * @param y        the y coordinate of the boulder
   * @param diameter the diameter of the boulder
   */
  public void drawBoulder(float x, float y, float diameter) {
    window.image(boulderImage, x, y, diameter, diameter);
  }

  /**
   * This method draws the boulder.
   */
  public void draw() {
    this.drawBoulder(this.x, this.y, 100);
  }

  /**
   * This method returns the x coordinate of the boulder.
   *
   * @return the x coordinate of the boulder
   */
  public float getX() {
    return x;
  }

  /**
   * This method returns the y coordinate of the boulder.
   *
   * @return the y coordinate of the boulder
   */
  public float getY() {
    return y;
  }

  /**
   * This method sets the x coordinate of the boulder.
   *
   * @param x the x coordinate of the boulder
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * This method sets the y coordinate of the boulder.
   *
   * @param y the y coordinate of the boulder
   */
  public void setY(float y) {
    this.y = y;
  }
}
