package org.bcit.comp2522.project;

import processing.core.PImage;

import static org.bcit.comp2522.project.Window.bullets;

/**
 * This is the Goblin's weapon of choice, the Axe.
 *
 * @author Gathrean Dela Cruz
 * @author Heeho Ryou
 * @version 1.0
 */
public class Axe {
  float x;
  float y;
  private float velocity;
  float size = 10;
  private final PImage axeImage;
  private final Window window;

  public Axe(float x, float y, float velocity, float size, Window window) {
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.size = size;
    this.window = window;
    this.axeImage = window.loadImage("images/weapons/axe.png");
  }

  public void setVelocity(float velocity) {
    this.velocity = velocity * 10;
  }

  public void update() {
    y += velocity * 3;

    // Check if the axe is outside the window
    if (x < 0 || x > window.width || y < 0 || y > window.height) {
      Goblin.axes.remove(this);
    }
  }

  public void drawAxe(float x, float y, float diameter) {
    window.image(axeImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawAxe(this.x, this.y, 60);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getSize() {
    return size;
  }

  public float getvelocity() {
    return velocity;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public void setvelocity(float velocity) {
    this.velocity = velocity;
  }

}
