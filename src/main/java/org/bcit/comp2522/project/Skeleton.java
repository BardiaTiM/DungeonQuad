package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Skeleton class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Skeleton {
  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);// Skeleton position
  int xPos;
  int yPos;
  float x;
  float y;

  // Skeleton size
  float diameter = 1;
  public ConcurrentLinkedQueue<Skeleton> skeletons = new ConcurrentLinkedQueue<>();
  public static ConcurrentLinkedQueue<Arrow> arrows = new ConcurrentLinkedQueue<>();

  // Skeleton's bow
  int arrowSpeed;
  int fireRate;
  double arrowDamage;

  boolean isAlive = true;

  // Skeleton health
  boolean alive;

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
   * @param window   window
   */
  public Skeleton(float x, float y, float diameter, boolean isAlive, Window window, PImage skeletonImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeletonImage = skeletonImage;
    this.isAlive = isAlive;
    //print true or false if skeleton is alive
    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootArrow();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, 2, TimeUnit.SECONDS);
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
   */
  public void shootArrow() {
    if (isAlive) {
      Arrow arrow = new Arrow(this.x, this.y, 1, 5,this.window);
      arrows.add(arrow);
      arrow.draw();
    }
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
  public void getHealthStatus(boolean alive) {
      if (!alive) {
        isAlive = false;
      }
      System.out.println(isAlive);
  }
}
