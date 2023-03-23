package org.bcit.comp2522.project;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Arrow {
  private float x;
  private float y;
  private float vy;
  private float size = 10;

  private Window window;

  public Arrow(float x, float y, float vy, float size, Window window) {
    this.x = x;
    this.y = y;
    this.vy = vy;
    this.size = size;
    this.window = window;
  }

  public void setVelocity(float vy) {
    this.vy = vy * 10;
  }

  public void update() {
    y += vy * 5;
  }

  public void drawArrow() {
    window.fill(0, 255, 0); // set fill color to green
    window.rect(x - size / 2, y - size * 2, size, size * 2); // draw a rectangle with negative height to make it upside down
    window.triangle(x - size, y, x, y + size, x + size, y); // draw a triangle pointing downwards
  }

  public void draw() {
    this.drawArrow();
  }

}
