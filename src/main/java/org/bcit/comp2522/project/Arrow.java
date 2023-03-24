package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Arrow {
  private float x;
  private float y;
  private float vy;
  private float size = 10;

  private PImage arrowImage;
  private Window window;

  public Arrow(float x, float y, float vy, float size, Window window) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
    PImage arrowImage = window.loadImage("arrow.png");
    this.arrowImage = arrowImage;
  }

  public void setVelocity(float vy) {
    this.vy = vy * 10;
  }

  public void update() {
    y += vy * 3;
  }

  public void drawArrow(float x, float y, float diameter) {
    window.image(arrowImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawArrow(this.x, this.y, 30);
  }

}
