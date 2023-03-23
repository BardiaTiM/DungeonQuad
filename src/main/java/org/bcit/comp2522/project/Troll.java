package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;
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
  float x;
  float y;

  // Troll size
  float diameter = 1;

  public ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();

  // Troll stats
  int boulderSpeed;
  int fireRate;
  double boulderDamage;

  int id;

  // Troll health
  boolean alive = true;

  // Troll direction
  boolean movingRight = true;
  boolean movingDown = true;

  private final Window window;

  private PImage trollImage;

  /**
   * Troll constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param window   window
   */
  public Troll(float x, float y, float diameter, int id, Window window, PImage trollImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.id = id;
    this.trollImage = trollImage;

  }


  /**
   * Moving the Troll.
   */
  public void move() {

    if (movingRight) { // RIGHT
      if (this.x + 50 < window.getWidth() - 200) {
        this.x += 4;
      } else { // LEFT
        movingRight = false;
      }

    } else { // LEFT
      if (this.x - 4 > 0) {
        this.x -= 4;
      } else { // RIGHT
        movingRight = true;
      }
    }

    if (movingDown) { // DOWN
      if (this.y < window.getHeight() / 4) {
        this.y += 4;
      } else { // UP
        movingDown = false;
      }

    } else { // UP
      if (this.y - 4 > 0) {
        this.y -= 4;
      } else { // DOWN
        movingDown = true;
      }

    }
  }
//  public void move() {
//    // Troll moves right by default
//    if (movingRight) {
//      // Move Troll to the right
//      if (this.x + 50 < window.getWidth() - 50) {
//        this.x += 3;
//      } else {
//        movingRight = false; // Change direction when Troll reaches the right side
//      }
//    } else {
//      // Move Troll to the left
//      if (this.x - 50 > 50) {
//        this.x -= 3;
//      } else {
//        movingRight = true; // Change direction when Troll reaches the left side
//      }
//    }
//  }

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
    window.image(trollImage, x, y, diameter, diameter);
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
