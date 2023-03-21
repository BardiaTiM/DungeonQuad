package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Sprite class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Sprite {

  float x = 5;
  float y = 5;
  float diameter = 1;
  protected double maxHealth;
  public int speed;

  static final int regularSpeed = 7;

  static final int wingSpeed = 13;
  protected double size;
  protected boolean isDead;
  protected boolean isFiring;
  protected boolean isReloading;
  private Weapon weapon;


  PApplet canvas;
  PVector direction;

  private Window window;

//  public Sprite(double maxHealth, double speed, double size, Weapon weapon, PVector direction) {
//    this.maxHealth = maxHealth;
//    this.speed = speed;
//    this.size = size;
//    this.weapon = weapon;
//    this.direction = direction;
//  }

  /**
   * Sprite constructor.
   *
   * @param x x position
   * @param y y position
   * @param diameter diameter
   * @param window window
   * @param direction direction
   */
  public Sprite(float x, float y, float diameter, Window window, PVector direction) {
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.window = window; // set the window variable of the sprite
    this.direction = direction;
    this.speed = regularSpeed;

  }

  PImage spriteImage;
  boolean isMoving = false;

  /**
   * Sets the sprite image.
   *
   * @param spriteImage sprite image
   */
  public void setSprite(PImage spriteImage) {
    this.spriteImage = spriteImage;
  }

  /**
   * Draws the player.
   *
   * @param x x position
   * @param y  y position
   * @param diameter diameter
   */
  public void drawPlayer(float x, float y, float diameter) {
    if (spriteImage != null) {
      window.image(spriteImage, x, y, diameter * 2, diameter * 2);
    }
  }

  public void draw() {
    this.drawPlayer(this.x, this.y, this.diameter);
  }

  public void update(PVector direction) {
    this.x += direction.x * this.speed;
    this.y += direction.y * this.speed;

  }

}