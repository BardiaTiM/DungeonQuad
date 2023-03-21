package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * Troll class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Troll {

  // Troll position
  int xPos;
  int yPos;
  float x = 2;
  float y = 2;

  // Troll size
  float diameter = 1;

  private Troll[] troll;

  // Troll stats
  int boulderSpeed;
  int fireRate;
  double boulderDamage;

  int id;

  // Troll health
  boolean alive = true;

  // Troll direction
  boolean movingRight = true;

  private final Window window;


  /**
   * Troll constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param troll    troll
   * @param window   window
   */
  public Troll(float x, float y, float diameter, int id, Troll[] troll, Window window) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.troll = troll;
    this.id = id;
  }


  /**
   * Moving the Troll.
   */
  public void move() {
    // Troll moves right by default
    if (movingRight) {
      // Move Troll to the right
      if (this.xPos + 3 < window.getWidth()) {
        this.xPos += 3;
        this.x = xPos;
      } else {
        movingRight = false; // Change direction when Troll reaches the right side
      }
    } else {
      // Move Troll to the left
      if (this.xPos - 3 > 0) {
        this.xPos -= 3;
        this.x = xPos;
      } else {
        movingRight = true; // Change direction when Troll reaches the left side
      }
    }
  }

  /**
   * Throw boulder.
   *
   * @param boulderSpeed  boulder speed
   * @param fireRate      fire rate
   * @param boulderDamage boulder damage
   */
  public void throwBoulder(int boulderSpeed, int fireRate, double boulderDamage) {
    //throw boulder
  }

  /**
   * Take damage.
   *
   * @param damage damage
   */
  public void takeDamage(double damage) {
    // Update health status when Troll takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  /**
   * Draw Troll.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public void drawTroll(float x, float y, float diameter) {
    PImage trollImage = window.loadImage("troll.png"); // replace "Troll.png" with the filename of your image
    window.image(trollImage, x - diameter / 2, y - diameter / 2, diameter, diameter);
  }

  /**
   * Draw Troll.
   */
  public void draw() {
    this.drawTroll(this.x, this.y, this.diameter);
  }

  /**
   * Check to see if Troll is alive.
   *
   * @return true if Troll is alive, false otherwise
   */
  public boolean isAlive() {
    return alive;
  }
}
