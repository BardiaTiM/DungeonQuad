package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;

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

  private Skeleton[] skeleton;

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


  /**
   * Skeleton constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param skeleton skeleton
   * @param window   window
   */
  public Skeleton(float x, float y, float diameter, int id, Skeleton[] skeleton, Window window) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeleton = skeleton;
    this.id = id;
  }

  /**
   * Skeleton moves.
   */
  public void move() {
    // Skeleton moves right by default
    if (movingRight) {
      // Move Skeleton to the right
      if (this.xPos + 4 < window.getWidth()) {
        this.xPos += 4;
        this.x = xPos;
      } else {
        movingRight = false; // Change direction when Skeleton reaches the right side
      }
    } else {
      // Move Skeleton to the left
      if (this.xPos - 4 > 0) {
        this.xPos -= 4;
        this.x = xPos;
      } else {
        movingRight = true; // Change direction when Skeleton reaches the left side
      }
    }
  }

  /**
   * Skeleton shoots arrow.
   *
   * @param arrowSpeed arrow speed
   * @param fireRate fire rate
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
   * @param x       x position
   * @param y      y position
   * @param diameter diameter
   */
  public void drawSkeleton(float x, float y, float diameter) {
    PImage skeletonImage = window.loadImage("skeleton.png");
    window.image(skeletonImage, x - diameter / 2, y - diameter / 2, diameter, diameter);
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
