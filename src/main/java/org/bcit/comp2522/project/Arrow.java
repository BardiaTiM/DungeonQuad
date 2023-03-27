package org.bcit.comp2522.project;

import processing.core.PImage;

public class Arrow {
  float x;
  float y;
  private final float vy;
  float size;

  private final Window window;
  private final PImage arrowImage;

  public Arrow(float x, float y, float vy, float size, Window window) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.arrowImage = window.loadImage("images/weapons/arrow.png");
  }

  public void update() {
    y += vy * 2;

    // Check if the arrow is outside the window
    if (x < 0 || x > window.width || y < 0 || y > window.height) {
      Skeleton.arrows.remove(this);
      System.out.println("arrow removed");
    }
  }

  public void drawArrow(float x, float y, float diameter) {
    window.image(arrowImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawArrow(this.x, this.y, 30);
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

  public float getVY() {
    return vy;
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

}
