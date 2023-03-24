package org.bcit.comp2522.project;

import processing.core.PImage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Arrow {
  float x;
  float y;
  private float vy;
  float size = 10;

  private PImage arrowImage;
  private Window window;
  private Skeleton skeleton;

  public Arrow(float x, float y, float vy, float size, Window window, Skeleton skeleton) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.skeleton = skeleton;
    PImage arrowImage = window.loadImage("arrow.png");
    this.arrowImage = arrowImage;
  }

  public void setVelocity(float vy) {
    this.vy = vy * 10;
  }

  public void update() {
    y += vy * 5;
  }

  public void drawArrow(float x, float y, float diameter) {
    window.image(arrowImage, x, y, diameter, diameter);
  }

  public void draw() {
    this.drawArrow(this.x, this.y, 30);
  }

}
