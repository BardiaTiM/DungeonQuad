package org.bcit.comp2522.project;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import processing.core.PImage;

/**
 * Goblin class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Goblin {

  /**
   * Goblin scheduler.
   */
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  /**
   * Goblin's x position.
   */
  float x;

  /**
   * Goblin's y position.
   */
  float y;

  /**
   * Goblin's diameter.
   */
  float diameter;

  /**
   * Goblin's Axes.
   */
  public static ConcurrentLinkedQueue<Axe> axes = new ConcurrentLinkedQueue<>();

  /**
   * Goblin's isAlive.
   */
  boolean isAlive;

  /**
   * Goblin's health.
   */
  int health = 5;

  /**
   * Goblin's movingRight.
   */
  boolean movingRight = false;

  /**
   * Goblin's movingDown.
   */
  boolean movingDown = true;

  /**
   * Goblin's window.
   */
  private final Window window;

  /**
   * Goblin's goblinImage.
   */
  private final PImage goblinImage;

  /**
   * Goblin's randomNum.
   */
  int randomNum = (int) (Math.random() * 3 + 1);

  /**
   * Goblin constructor.
   *
   * @param x           x position
   * @param y           y position
   * @param diameter    diameter
   * @param isAlive     isAlive
   * @param window      window
   * @param goblinImage goblinImage
   */
  public Goblin(float x, float y, float diameter, boolean isAlive,
                Window window, PImage goblinImage) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.isAlive = isAlive;
    this.goblinImage = goblinImage;

    // Goblin will shoot arrows every 2-5 seconds
    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootAxe();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, randomNum, TimeUnit.SECONDS);
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
   */
  public void shootAxe() {
    if (isAlive && Window.gameOn) {
      Axe axe = new Axe(this.x, this.y, 1, 5, this.window);
      axes.add(axe);
      axe.draw();
    }
    if (!Window.gameOn) {
      scheduler.shutdown();
      axes.clear();
    }
  }

  /**
   * Adds the correct image.
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
   * Gets the Goblin's health.
   *
   * @param alive alive
   */
  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
  }
}
