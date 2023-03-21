package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * Goblin class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Goblin {

  // Goblin position
  int xPos;
  int yPos;
  float x = 2;
  float y = 2;

  // Goblin size
  float diameter = 1;

  private Goblin[] goblin;

  // Goblin axe
  int axeSpeed;
  int fireRate;
  double axeDamage;

  int id;

  // Goblin health
  boolean alive = true;

  // Goblin direction
  boolean movingRight = true;

  private final Window window;

  /**
   * Goblin constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param goblin   goblin
   * @param window   window
   */
  public Goblin(float x, float y, float diameter, int id, Goblin[] goblin, Window window) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.goblin = goblin;
    this.id = id;
  }

  /**
   * Moving the Goblin.
   */
  public void move() {
    // Goblin moves right by default
    if (movingRight) {
      // Move Goblin to the right
      if (this.xPos + 5 < window.getWidth()) {
        this.xPos += 5;
        this.x = xPos;
      } else {
        movingRight = false; // Change direction when Goblin reaches the right side
      }
    } else {
      // Move Goblin to the left
      if (this.xPos - 5 > 0) {
        this.xPos -= 5;
        this.x = xPos;
      } else {
        movingRight = true; // Change direction when Goblin reaches the left side
      }
    }
  }

  /**
   * Throws an axe.
   *
   * @param axe_speed  axe speed
   * @param fire_rate  fire rate
   * @param axe_damage axe damage
   */
  public void throwAxe(int axe_speed, int fire_rate, double axe_damage) {
    //throw axe
  }

  /**
   * Goblin takes damage.
   *
   * @param damage damage
   */
  public void takeDamage(double damage) {
    // Update health status when Goblin takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  /**
   * Draws the Goblin.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public void drawGoblin(float x, float y, float diameter) {
    PImage goblinImage = window.loadImage("goblin.png");
    window.image(goblinImage, x - diameter / 2, y - diameter / 2, diameter, diameter);
  }

  /**
   * Draws the Goblin.
   */
  public void draw() {
    this.drawGoblin(this.x, this.y, this.diameter);
  }

  /**
   * Returns the Goblin's x position.
   *
   * @return x position
   */
  public boolean isAlive() {
    return alive;
  }
}
