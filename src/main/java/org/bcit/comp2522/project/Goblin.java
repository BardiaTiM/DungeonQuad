package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;
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
  float x;
  float y;

  // Goblin size
  float diameter = 1;

  public ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();

  // Goblin axe
  int axeSpeed;
  int fireRate;
  double axeDamage;

  int id;

  // Goblin health
  boolean alive = true;

  // Goblin direction
  boolean movingRight = false;
  boolean movingDown = true;
  private final Window window;
  private PImage goblinImage;

  /**
   * Goblin constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param window   window
   */
  public Goblin(float x, float y, float diameter, int id, Window window, PImage goblinImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.id = id;
    this.goblinImage = goblinImage;
  }

  /**
   * Moving the Goblin.
   */
  public void move() {

    if (movingRight) { // RIGHT
      if (this.x + 4 < window.getWidth() - 150) {
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
      if (this.y + 4 < window.getHeight() / 3) {
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

  /**
   * Throws an axe.
   *
   * @param axeSpeed  axe speed
   * @param fireRate  fire rate
   * @param axeDamage axe damage
   */
  public void throwAxe(int axeSpeed, int fireRate, double axeDamage) {
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
    window.image(goblinImage, x, y, diameter, diameter);
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
