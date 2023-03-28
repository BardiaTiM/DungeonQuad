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
  static int health = 10;
  static final int regularSpeed = 7;

  PVector direction;
  private Window window;

  static PImage spriteImage;

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

  /**
   * Sprite constructor for BulletTest.java.
   *
   * @param x x position
   * @param y y position
   * @param diameter diameter
   */
  public Sprite(int x, int y, int diameter) {
    Sprite.x = x;
    Sprite.y = y;
    Sprite.diameter = diameter;
  }

  /**
   * Sets the sprite image.
   *
   * @param spriteImage sprite image
   */
  public void setSprite(PImage spriteImage) {
    Sprite.spriteImage = spriteImage;
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
    float newX = x + direction.x * this.speed;
    float newY = y + direction.y * this.speed;

    // Check if the new position is within the boundaries of the window
    if (newX > diameter - 100 && newX < window.width - diameter) {
      x = newX;
    }
    if (newY > diameter - 100 && newY < window.height - diameter) {
      y = newY;
    }
  }

  public static float getX() {
    return x;
  }

  public static float getY() {
    return y;
  }

  public Window getWindow() {
    return window;
  }

  public void displayHealth() {
    float circleSize = 15; // Set the size of each circle
    float spacing = 20; // Set the spacing between each circle
    float xPos = 10; // Set the x position of the first circle
    float yPos = window.height - 10 - circleSize; // Set the y position of the circles

    for (int i = 0; i < 10; i++) {
      if (health > i) {
        // Draw a white circle if the current health is greater than the current index
        window.fill(255, 255, 255);
      } else {
        // Draw a red circle if the current health is less than or equal to the current index
        window.fill(255, 0, 0);
      }

      window.ellipse(xPos, yPos, circleSize, circleSize);
      xPos += spacing; // Update the x position for the next circle
    }
  }
}

