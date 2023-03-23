package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * This is the Goblin's weapon of choice, the Axe.
 *
 * @author Gathrean Dela Cruz
 * @author Heeho Ryou
 * @version 1.0
 */
public class Axe {

  private float x;
  private float y;
  private float vy;
  private float size = 10;
  private PImage axeImage;
  private Window window;

  public Axe(float x, float y, float vy, float size, Window window) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
    PImage axeImage = window.loadImage("axe.png");
    this.axeImage = axeImage;
  }

  public void setVelocity(float vy) {
    this.vy = vy * 10;
  }

  public void update() {
    y += vy * 5;
  }

  public void drawAxe(float x, float y, float diameter) {
    window.image(axeImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawAxe(this.x, this.y, 60);
  }
}
