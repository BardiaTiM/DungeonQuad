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
  private float vy;
  float size = 10;
  private PImage axeImage;
  private Window window;
  private Goblin goblin;

  public Axe(float x, float y, float vy, float size, Window window, Goblin goblin) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.goblin = goblin;
    PImage axeImage = window.loadImage("axe.png");
    this.axeImage = axeImage;
  }

  public void setVelocity(float vy) {
    this.vy = vy * 10;
  }

  public void update() {
    y += vy * 3;
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

  public float getDiameter() {
    return size;
  }

  public float getSpeed() {
    return vy;
  }

  public Goblin getOwner() {
    return goblin;
  }
}
