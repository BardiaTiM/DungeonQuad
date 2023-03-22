package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Skeleton class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Skeleton {

  // Skeleton position
  int xPos;
  int yPos;
  float x = 2;
  float y = 2;

  // Skeleton size
  float diameter = 1;
  public ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();

  // Skeleton's bow
  int arrowSpeed;
  int fireRate;
  double arrowDamage;

  int id;

  // Skeleton health
  boolean alive = true;

  // Skeleton direction
  boolean movingRight = true;

  private final Window window;

  private PImage skeletonImage;


  /**
   * Skeleton constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param window   window
   */
  public Skeleton(float x, float y, float diameter, int id, Window window, PImage skeletonImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.id = id;
    this.skeletonImage = skeletonImage;

  }

  /**
   * Skeleton moves.
   */

  boolean movingDown = true;
  public void move() {
    if (movingRight) {
      if (this.xPos + 4 < window.getWidth()) {
        this.xPos += 4;
        this.x = xPos;
      } else {
        movingRight = false;
      }
    } else {
      if (this.xPos - 4 > 0) {
        this.xPos -= 4;
        this.x = xPos;
      } else {
        movingRight = true;
      }
    }

    if (movingDown) {
      if (this.yPos + 4 < window.getHeight() - 500) {
        this.yPos += 4;
        this.y = yPos;
      } else {
        movingDown = false;
      }
    } else {
      if (this.yPos - 4 > 0) {
        this.yPos -= 4;
        this.y = yPos;
      } else {
        movingDown = true;
      }
    }
  }




  /**
   * Skeleton shoots arrow.
   *
   * @param arrowSpeed  arrow speed
   * @param fireRate    fire rate
   * @param arrowDamage arrow damage
   */
  public void shootArrow(int arrowSpeed, int fireRate, double arrowDamage) {
    // Skeleton shoots arrow
  }

  /**
   * Skeleton takes damage.
   *
   * @param damage damage
   */
  public void takeDamage(double damage) {
    // Update health status when Skeleton takes damage
    if (damage >= 1.0) {
      alive = false;
    }
  }

  /**
   * Draws Skeleton.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   */
  public void drawSkeleton(float x, float y, float diameter) {
    window.image(skeletonImage, x, y, diameter, diameter);
  }



  /**
   * Draws Skeleton.
   */
  public void draw() {
    this.drawSkeleton(this.x, this.y, this.diameter);
  }

  /**
   * Checks if Skeleton is alive.
   *
   * @return true if alive, false otherwise
   */
  public boolean isAlive() {
    return alive;
  }
}
