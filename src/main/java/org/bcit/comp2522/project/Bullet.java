package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.bcit.comp2522.project.Window.bullets;

class Bullet extends Collidable {
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;

  public Goblin[] goblin;

  public ConcurrentLinkedQueue<Skeleton> skeletonsList = new ConcurrentLinkedQueue<>();

  public Troll[] troll;

  private Window window;


  public Bullet(float x, float y, float vx, float vy, float size,
                Goblin[] goblin, ConcurrentLinkedQueue<Skeleton> skeleton, Troll[] troll, Window window) {
    super(x);
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.window = window;
    this.goblin = goblin;
    this.skeletonsList = skeleton;
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

//  float myFloat = 10.5f;
//  myFloat = (float) ((int) myFloat);
  @Override
  void collide() {
    for (Skeleton skeleton : skeletonsList) {
      float NewSX = (float) ((int) skeleton.x);
      float NewSY = (float) ((int) skeleton.y);
      float NewX = (float) ((int) x);
      float NewY = (float) ((int) y);


      if (Collidable.collides(NewX, NewY, size, NewSX, NewSY, skeleton.diameter)) {;
        bullets.remove(this);
        skeleton.alive = false;
        Window.skeletons.remove(skeleton);
//        skeleton.removeDraw();
        System.out.println("Skeleton hit");
        return; // exit the method after the first collision
      }
    }
  }

}