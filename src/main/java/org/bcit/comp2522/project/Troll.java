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
  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  // Troll position
  int xPos;
  int yPos;
  float x;
  float y;

  // Troll size
  float diameter = 1;

  public ConcurrentLinkedQueue<Troll> trolls = new ConcurrentLinkedQueue<>();
  public static ConcurrentLinkedQueue<Boulder> boulders = new ConcurrentLinkedQueue<>();
  // Troll stats
  int boulderSpeed;
  int fireRate;
  double boulderDamage;

  boolean isAlive = true;
  // Troll health
  boolean alive = true;

  // Troll direction
  boolean movingRight = true;

  private final Window window;

  private PImage trollImage;


  /**
   * Troll constructor.
   *
   * @param x        x position
   * @param y        y position
   * @param diameter diameter
   * @param id       id
   * @param window   window
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
    }, 2, 2, TimeUnit.SECONDS);
  }


  /**
   * Moving the Troll.
   */
  public void move() {
    // Troll moves right by default
    if (movingRight) {
      // Move Troll to the right
      if (this.xPos + 3 < window.getWidth()) {
        this.xPos += 3;
        this.x = xPos;
      } else {
        movingRight = false; // Change direction when Troll reaches the right side
      }
    } else {
      // Move Troll to the left
      if (this.xPos - 3 > 0) {
        this.xPos -= 3;
        this.x = xPos;
      } else {
        movingRight = true; // Change direction when Troll reaches the left side
      }
    }
  }

  /**
   * Throw boulder.
   *
   * @param boulderSpeed  boulder speed
   * @param fireRate      fire rate
   * @param boulderDamage boulder damage
   */
  public void shootBoulder() {
    if (isAlive) {
      Boulder boulder = new Boulder(this.x, this.y, 1, 5,this.window);
      boulders.add(boulder);
      boulder.draw();
    }
  }

  /**
   * Take damage.
   *
   * @param damage damage
   */
  public void takeDamage(double damage) {
    // Update health status when Troll takes damage
    if (damage >= 1.0) {
      alive = false;
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
   * Check to see if Troll is alive.
   *
   * @return true if Troll is alive, false otherwise
   */
  public boolean isAlive() {
    return alive;
  }

  public void getHealthStatus(boolean alive) {
    if (!alive) {
      isAlive = false;
    }
    System.out.println(isAlive);
  }
}
