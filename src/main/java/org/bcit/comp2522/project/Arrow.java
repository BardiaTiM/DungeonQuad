package org.bcit.comp2522.project;

import processing.core.PImage;

public class Arrow {
  float x;
  float y;
  private final float vy;
  float size = 10;

  private final PImage arrowImage;
  private final Window window;
  private Skeleton skeleton;

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
  }

  public void drawArrow(float x, float y, float diameter) {
    window.image(arrowImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawArrow(this.x, this.y, 30);
  }

}
