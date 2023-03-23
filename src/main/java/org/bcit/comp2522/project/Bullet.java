package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.bcit.comp2522.project.Window.bullets;
import static org.bcit.comp2522.project.Window.goblins;

class Bullet extends Collidable {
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;

  public ConcurrentLinkedQueue<Skeleton> skeletonsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Goblin> goblinsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Troll> trollsList = new ConcurrentLinkedQueue<>();

  private Window window;


  public Bullet(float x, float y, float vx, float vy, float size, ConcurrentLinkedQueue<Goblin> goblin, ConcurrentLinkedQueue<Skeleton> skeleton, ConcurrentLinkedQueue<Troll> troll, Window window) {
    super(x);
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.goblinsList = goblin;
    this.skeletonsList = skeleton;
    this.trollsList = troll;
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

//  float myFloat = 10.5f;
//  myFloat = (float) ((int) myFloat);
  @Override
  void collide() {
    for (Skeleton skeleton : skeletonsList) {
      if (Collidable.collides(x, y, size, skeleton.x, skeleton.y, skeleton.diameter)) {;
        bullets.remove(this);
        skeleton.getHealthStatus(false);
        Window.skeletons.remove(skeleton);
        System.out.println("Skeleton hit");
        return; // exit the method after the first collision
      }
    }

    for (Goblin goblin : goblins) {
      if (Collidable.collides(x, y, size, goblin.x, goblin.y, goblin.diameter)) {
        bullets.remove(this);
        goblin.alive = false;
        Window.goblins.remove(goblin);
        System.out.println("Goblin hit");
        return; // exit the method after the first collision
      }
    }

    for (Troll troll : trollsList) {
      if (Collidable.collides(x, y, size, troll.x, troll.y, troll.diameter)) {
        bullets.remove(this);
        troll.alive = false;
        Window.trolls.remove(troll);
        System.out.println("Troll hit");
        return; // exit the method after the first collision
      }
    }
  }

}