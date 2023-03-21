package org.bcit.comp2522.project;

import java.util.ArrayList;

import static org.bcit.comp2522.project.Window.bullets;

class Bullet extends Collidable {
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;

  public Goblin[] goblin;

  public Skeleton[] skeleton;

  public Troll[] troll;

  private Window window;


  public Bullet(float x, float y, float vx, float vy, float size,
                Goblin[] goblin, Skeleton[] skeleton, Troll[] troll, Window window) {
    super(x);
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.goblin = goblin;
    this.skeleton = skeleton;
    this.troll = troll;
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
        // Make goblin stop moving
        goblin[i].y = 1000;
        bullets.remove(this);
        System.out.println("Goblin fainted!");
      }
      if (Collidable.collides(x, y, size, skeleton[i].x, skeleton[i].y, skeleton[i].diameter)) {
        // Make Skeleton stop moving
        skeleton[i].y = 1000;
        bullets.remove(this);
        System.out.println("Skeleton fainted!");
      }
      if (Collidable.collides(x, y, size, troll[i].x, troll[i].y, troll[i].diameter)) {
        // Make Troll stop moving
        troll[i].y = 1000;
        bullets.remove(this);
        System.out.println("Troll fainted");
      }

    }

  }

}