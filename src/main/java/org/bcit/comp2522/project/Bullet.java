package org.bcit.comp2522.project;

import java.util.ArrayList;

import static org.bcit.comp2522.project.Window.bullets;

class Bullet extends Collidable{
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;

  public Goblin[] goblin;

  private Window window;


  public Bullet(float x, float y, float vx, float vy, float size,Goblin[] goblin, Window window) {
    super(x);
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.goblin = goblin;
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


  @Override
  void collide() {
    for (int i = 0; i < goblin.length; i++) {
      if (Collidable.collides(x, y, size, goblin[i].x, goblin[i].y, goblin[i].diameter)) {
        //make goblin stop moving
        goblin[i].y = 1000;

        bullets.remove(this);

        System.out.println("hit");
      }
    }

  }

}