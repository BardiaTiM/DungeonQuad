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
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  // Goblin position
  float x;
  float y;

  // Goblin size
  float diameter = 1;

  public static ConcurrentLinkedQueue<Axe> axes = new ConcurrentLinkedQueue<>();

  // Goblin health
  boolean isAlive = true;
  int health = 5;

  // Goblin direction
  boolean movingRight = false;
  boolean movingDown = true;

  private final Window window;
  private final PImage goblinImage;

  /**
   * Goblin constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param window   window
   */
  public Goblin(float x, float y, float diameter, boolean isAlive,
                Window window, PImage goblinImage){
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window;
    this.isAlive = isAlive;
    this.goblinImage = goblinImage;

    scheduler.scheduleAtFixedRate(() -> {
      if (isAlive) {
        shootAxe();
      } else {
        scheduler.shutdown(); // stop scheduling new tasks
      }
    }, 2, 2, TimeUnit.SECONDS);
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
   */
  public void shootAxe() {
    if (isAlive) {
      Axe axe = new Axe(this.x, this.y, 1, 5,this.window);
      axes.add(axe);
      axe.draw();
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
   */
  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
    System.out.println(isAlive);
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void takeDamage(double v) {
    health -= v;
    if (health <= 0) {
      isAlive = false;
    }
  }

  public boolean getAliveStatus() {
    return isAlive;
  }
}
