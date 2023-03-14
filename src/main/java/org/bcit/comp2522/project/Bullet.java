package org.bcit.comp2522.project;

class Bullet {
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;

  private Window window;

  public Bullet() {}

  public Bullet(float x, float y, float vx, float vy, float size, Window window) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.window = window;
  }

  public void setVelocity(float vx, float vy) {
    this.vx = vx * 10;
    this.vy = vy * 10;
  }

  public void update() {
    x += vx;
    y += vy;
  }

  public void drawBullet() {
    window.ellipse(x, y, size, size);
  }

  public void draw() {
    this.drawBullet();
  }
}