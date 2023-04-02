package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Player class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Player {
  static float x = 5;
  static float y = 5;
  static float diameter = 100;
  public int speed;
  static int health;
  static final int regularSpeed = 7;

  public PVector direction;
  private Window window;

  static PImage PlayerImage;

  /**
   * Player constructor.
   *
   * @param x         x position
   * @param y         y position
   * @param diameter  diameter
   * @param window    window
   * @param direction direction
   */
  public Player(float x, float y, float diameter, int health, Window window, PVector direction) {
    Player.x = x;
    Player.y = y;
    Player.diameter = diameter;
    this.window = window; // set the window variable of the Player
    this.direction = direction;
    Player.health = health;
    this.speed = regularSpeed;

  }

  /**
   * Player constructor for BulletTest.java.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public Player(int x, int y, int diameter) {
    Player.x = x;
    Player.y = y;
    Player.diameter = diameter;
  }

  /**
   * Sets the Player image.
   *
   * @param PlayerImage Player image
   */
  public void setPlayer(PImage PlayerImage) {
    Player.PlayerImage = PlayerImage;
  }

  /**
   * Draws the player.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public void drawPlayer(float x, float y, float diameter) {
    if (PlayerImage != null) {
      window.image(PlayerImage, x, y, diameter * 2, diameter * 2);
    }
  }

  public void draw() {
    this.drawPlayer(x, y, diameter);
  }

  public void update(PVector direction) {
    float newX = x + direction.x * this.speed;
    float newY = y + direction.y * this.speed;

    // Check if the new position is within the boundaries of the window
    if (newX > diameter - 40 && newX < window.width - diameter - 60) {
      x = newX;
    }
    if (newY > diameter - 10 && newY < window.height - diameter) {
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
    float circleSize = 30; // Set the size of each circle
    float spacing = 30; // Set the spacing between each circle
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

      PImage heartImage = null;
      if (health > i) {
        heartImage = window.loadImage("images/health/heart1.png");
      } else {
        heartImage = window.loadImage("images/health/heart0.png");
      }
      window.image(heartImage, xPos, yPos, circleSize, circleSize);

      xPos += spacing; // Update the x position for the next circle
    }
  }
}

