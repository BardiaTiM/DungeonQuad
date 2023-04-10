package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * This is the Goblin's weapon of choice, the Axe.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @author Heeho Ryou
 * @version 1.0
 */
public class Axe {
  private static final int ZERO = 0;
  private static final int THREE = 3;
  private static final int SIXTY = 60;


  /**
   * The x position of the axe.
   */
  float x;

  /**
   * The y position of the axe.
   */
  float y;

  /**
   * The velocity of the axe.
   */
  private final float velocity;

  /**
   * The size of the axe.
   */
  float size;

  /**
   * The image of the axe.
   */
  private final PImage axeImage;

  /**
   * The window that the axe will be drawn on.
   */
  private final Window window;

  /**
   * This is the constructor for the Axe class.
   *
   * @param x        the x coordinate of the axe
   * @param y        the y coordinate of the axe
   * @param velocity the velocity of the axe
   * @param size     the size of the axe
   * @param window   the window that the axe will be drawn on
   */
  public Axe(float x, float y, float velocity, float size, Window window) {
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.size = size;
    this.window = window;
    this.axeImage = window.loadImage("images/weapons/axe.png");
  }

  /**
   * This method updates the axe's position.
   */
  public void update() {
    y += velocity * THREE;

    // Check if the axe is outside the window
    if (x < ZERO || x > window.width || y < ZERO || y > window.height) {
      Goblin.axes.remove(this);
    }
  }

  /**
   * This method draws the axe.
   *
   * @param x        the x coordinate of the axe
   * @param y        the y coordinate of the axe
   * @param diameter the diameter of the axe
   */
  public void drawAxe(float x, float y, float diameter) {
    window.image(axeImage, x, y, diameter, diameter);
  }

  /**
   * This method returns the x position of the axe.
   */
  public void draw() {
    this.drawAxe(this.x, this.y, SIXTY);
  }

  /**
   * This method returns the x position of the axe.
   *
   * @return the x position of the axe
   */
  public float getX() {
    return x;
  }

  /**
   * This method returns the y position of the axe.
   *
   * @return the y position of the axe
   */
  public float getY() {
    return y;
  }

  /**
   * This method returns the X of the axe.
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * This method returns the Y of the axe.
   */
  public void setY(float y) {
    this.y = y;
  }

}
