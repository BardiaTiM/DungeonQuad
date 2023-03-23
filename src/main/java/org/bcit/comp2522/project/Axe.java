package org.bcit.comp2522.project;

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

  private Window window;

  public Axe(float x, float y, float vy, float size, Window window) {
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

  public void drawAxe() {
    window.fill(0, 255, 0); // set fill color to green
    window.rect(x - size / 4, y - size * 2, size / 2, size * 4); // draw a tall, narrow rectangle
  }

  public void draw() {
    this.drawAxe();
  }
}
