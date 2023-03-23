package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Goblin class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Goblin {
  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  // Goblin position
  int xPos;
  int yPos;
  float x;
  float y;

  // Goblin size
  float diameter = 1;

  public ConcurrentLinkedQueue<Goblin> goblins = new ConcurrentLinkedQueue<>();
  public static ConcurrentLinkedQueue<Axe> axes = new ConcurrentLinkedQueue<>();
  // Goblin axe
  int axeSpeed;
  int fireRate;
  double axeDamage;

  int id;

  boolean isAlive = true;

  // Goblin health
  boolean alive = true;

  // Goblin direction
  boolean movingRight = true;

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
  public Goblin(float x, float y, float diameter, boolean isAlive, Window window, PImage goblinImage) {
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
    // Goblin moves right by default
    if (movingRight) {
      // Move Goblin to the right
      if (this.xPos + 50 < window.getWidth() - 50) {
        this.xPos += 5;
        this.x = xPos;
      } else {
        movingRight = false; // Change direction when Goblin reaches the right side
      }
    } else {
      // Move Goblin to the left
      if (this.xPos - 50 > 50) {
        this.xPos -= 5;
        this.x = xPos;
      } else {
        movingRight = true; // Change direction when Goblin reaches the left side
      }
    }
  }

  /**
   * Throws an axe.
   *
   * @param axe_speed  axe speed
   * @param fire_rate  fire rate
   * @param axe_damage axe damage
   */
  public void shootAxe() {
    if (isAlive) {
      Axe axe = new Axe(this.x, this.y, 1, 5,this.window);
      axes.add(axe);
      axe.draw();
    }
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
  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
    System.out.println(isAlive);
  }
}
