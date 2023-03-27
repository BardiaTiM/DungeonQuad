package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import processing.core.PImage;

/**
 * Skeleton class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Skeleton {
  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);// Skeleton position

  // Skeleton position
  float x;
  float y;

  // Skeleton size
  float diameter = 1;

  public static ConcurrentLinkedQueue<Arrow> arrows = new ConcurrentLinkedQueue<>();

  // Skeleton's bow
  int arrowSpeed;
  int fireRate;
  double arrowDamage;

  boolean isAlive = true;

  // Skeleton health
  boolean alive;
  int health = 3;

  // Skeleton direction
  boolean movingDown = true;
  boolean movingRight = true;

  private final Window window;
  private final PImage skeletonImage;
  int randomNum = (int) (Math.random() * 3 + 1);


  /**
   * Skeleton constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param window   window
   */
  public Skeleton(float x, float y, float diameter, boolean isAlive,
                  Window window, PImage skeletonImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeletonImage = skeletonImage;
    this.isAlive = isAlive;

    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootArrow();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, randomNum, TimeUnit.SECONDS);
  }

  /**
   * Skeleton moves.
   */
  public void move() {

    if (movingRight) { // RIGHT
      if (this.x + 4 < window.getWidth() - 100) {
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
      if (this.y + 4 < window.getHeight() / 2) {
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
   * Skeleton shoots arrow.
   *
   */
  public void shootArrow() {
    if (isAlive && Window.gameOn) {
      Arrow arrow = new Arrow(this.x, this.y, 3, 5,this.window);
      arrows.add(arrow);
      arrow.draw();
    }
    if(!Window.gameOn){
      scheduler.shutdown();
      arrows.clear();
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
