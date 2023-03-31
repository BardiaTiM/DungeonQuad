package org.bcit.comp2522.project;

import processing.core.PImage;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Troll class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Troll {

  /**
   * Troll scheduler.
   */
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  /** Troll's x position. */
  float x;

  /** Troll's y position. */
  float y;

  /** Troll's diameter. */
  float diameter = 1;

  /** Troll's Boulders. */
  public static ConcurrentLinkedQueue<Boulder> boulders = new ConcurrentLinkedQueue<>();

  /** Troll's isAlive. */
  boolean isAlive;

  /** Troll's health. */
  int health = 10;

  /** Troll's movingRight. */
  boolean movingRight = true;

  /** Troll's movingDown. */
  boolean movingDown = true;

  /** Troll's window. */
  private final Window window;

  /** Troll's trollImage. */
  private final PImage trollImage;

  /** Troll's randomNum. */
  int randomNum = (int) (Math.random() * 3 + 1);

  /**
   * Troll constructor.
   *
   * @param x             x position
   * @param y             y position
   * @param diameter      diameter
   * @param isAlive       isAlive
   * @param window        window
   * @param trollImage    trollImage
   */
  public Troll(float x, float y, float diameter, boolean isAlive, Window window, PImage trollImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.isAlive = isAlive;
    this.trollImage = trollImage;

    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootBoulder();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, randomNum, TimeUnit.SECONDS);
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

  /**
   * Throw boulder.
   */
  public void shootBoulder() {
    if (isAlive && Window.gameOn) {
      Boulder boulder = new Boulder(this.x, this.y, 1, 5,this.window);
      boulders.add(boulder);
      boulder.draw();
    }
    if(!Window.gameOn){
      scheduler.shutdown();
      boulders.clear();
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
   * Get health status.
   *
   * @param alive alive
   */
  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
  }
}
