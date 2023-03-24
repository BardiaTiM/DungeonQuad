package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Sprite class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Sprite {
  static float x = 5;
  static float y = 5;
  static float diameter = 100;
  public int speed;
  static int health = 2;
  static final int regularSpeed = 7;

  PVector direction;
  private final Window window;

  /**
   * Sprite constructor.
   *
   * @param x x position
   * @param y y position
   * @param diameter diameter
   * @param window window
   * @param direction direction
   */
  public Sprite(float x, float y, float diameter, Window window, PVector direction) {
    Sprite.x = x;
    Sprite.y = y;
    Sprite.diameter = diameter;
    this.window = window; // set the window variable of the sprite
    this.direction = direction;
    this.speed = regularSpeed;

  }

  static PImage spriteImage;

  /**
   * Sets the sprite image.
   *
   * @param spriteImage sprite image
   */
  public void setSprite(PImage spriteImage) {
    this.spriteImage = spriteImage;
  }

  /**
   * Draws the player.
   *
   * @param x x position
   * @param y  y position
   * @param diameter diameter
   */
  public void drawPlayer(float x, float y, float diameter) {
    if (spriteImage != null) {
      window.image(spriteImage, x, y, diameter * 2, diameter * 2);
    }
  }

  public void draw() {
    this.drawPlayer(x, y, diameter);
  }

  public void update(PVector direction) {
    x += direction.x * this.speed;
    y += direction.y * this.speed;
  }

  public static float getX() {
    return x;
  }

  public float getWidth() {
    return diameter * 2;
  }

  public static float getY() {
    return y;
  }

  public float getHeight() {
    return diameter * 2;
  }

  public void displayHealth() {
    // Draw ten circles
    for (int i = 0; i < 10; i++) {
      float xPos = i * 20 + 10; // Set the x position of each circle
      float yPos = 10; // Set the y position of each circle
      float circleSize = 15; // Set the size of each circle
      window.noStroke(); // Disable stroke
      // Draw a red circle if the current health is less than or equal to the current index
      if (health <= i) {
        window.fill(255, 0, 0); // Set fill color to red
      } else {
        window.fill(255, 255, 255); // Set fill color to white
      }
      window.ellipse(xPos, yPos, circleSize, circleSize); // Draw the circle
    }
  }
}