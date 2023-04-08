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
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);// Skeleton position

  /**
   * Skeleton's x position.
   */
  float x;

  /**
   * Skeleton's y position.
   */
  float y;

  /**
   * Skeleton's diameter.
   */
  float diameter;

  /**
   * Skeleton's Arrows.
   */
  public static ConcurrentLinkedQueue<Arrow> arrows = new ConcurrentLinkedQueue<>();

  /**
   * Skeleton's isAlive.
   */
  boolean isAlive;

  /**
   * Skeleton's health.
   */
  int health;

  /**
   * Skeleton's movingDown.
   */
  boolean movingDown = true;

  /**
   * Skeleton's movingRight.
   */
  boolean movingRight = true;

  /**
   * Skeleton's window.
   */
  private final Window window;

  /**
   * Skeleton's skeletonImage.
   */
  private final PImage skeletonImage;

  /**
   * Skeleton's randomNum.
   */
  int randomNum = (int) (Math.random() * 3 + 1);


  /**
   * Skeleton constructor.
   *
   * @param x             x position
   * @param y             y position
   * @param diameter      diameter
   * @param isAlive       isAlive
   * @param window        window
   * @param skeletonImage skeletonImage
   */
  public Skeleton(float x, float y, float diameter, int health, boolean isAlive,
                  Window window, PImage skeletonImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeletonImage = skeletonImage;
    this.health = health;
    this.isAlive = isAlive;

    // Skeleton will shoot arrows every 2-5 seconds
    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootArrow();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, randomNum, TimeUnit.SECONDS);
  }

  /**
   * Skeleton constructor.
   *
   * @param x             x position
   * @param y             y position
   * @param diameter      diameter
   * @param window        window
   * @param skeletonImage skeletonImage
   */
  public Skeleton(int x, int y, int diameter, boolean b, Window window, PImage skeletonImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.skeletonImage = skeletonImage;
    this.isAlive = b;
  }

  /**
   * Skeleton moves.
   */
  public void move() {

    if (movingRight) { // RIGHT
      if (this.x + 4 < window.getWidth() - 80) {
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
   */
  public void shootArrow() {
    if (isAlive && Window.gameOn) {
      Arrow arrow = new Arrow(this.x, this.y, 3, 5, this.window);
      arrows.add(arrow);
      arrow.draw();
    }
    if (!Window.gameOn) {
      scheduler.shutdown();
      arrows.clear();
    }
  }

  /**
   * Adds the correct image.
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
   * Gets Skeleton's health.
   *
   * @param alive alive
   */
  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
  }

  /**
   * Accessor method that returns the value of the x-coordinate of the Skeleton.
   *
   * @return The Skeleton's x-coordinate
   */
  public int getX() {
    return (int) x;
  }

  /**
   * Mutator method that sets the value of the Skeleton's y-coordinate.
   *
   * @return The Skeleton's y-coordinate
   */
  public int getY() {
    return (int) y;
  }

  /**
   * Accessor method that returns the diameter of the Skeleton.
   *
   * @return The Skeleton's diameter
   */
  public int getDiameter() {
    return (int) diameter;
  }

  /**
   * Accessor boolean method that returns whether the Skeleton is dead or alive.
   *
   * @return True if the Skeleton is alive, false otherwise
   */
  public boolean getAlive() {
    return isAlive;
  }

  /**
   * Mutator method for the Skeleton's x-coordinate.
   *
   * @param i The Skeleton's x-coordinate
   */
  public void setX(int i) {
    x = i;
  }

  /**
   * Mutator method that sets the value of the Skeleton's y-coordinate.
   *
   * @param i The Skeleton's y-coordinate
   */
  public void setY(int i) {
    y = i;
  }

  /**
   * Mutator method that sets the diameter of the Skeleton.
   *
   * @param i The Skeleton's diameter
   */
  public void setDiameter(int i) {
    diameter = i;
  }
}