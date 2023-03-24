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
  public Sprite player;

  private Window window;


  public Bullet(float x, float y, float vx, float vy, float size, ConcurrentLinkedQueue<Goblin> goblin, ConcurrentLinkedQueue<Skeleton> skeleton, ConcurrentLinkedQueue<Troll> troll, Sprite player, Window window) {
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
    this.player = player;

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
        return; // exit the method after the first collision
      }
    }

    for (Goblin goblin : goblins) {
      if (Collidable.collides(x, y, size, goblin.x, goblin.y, goblin.diameter)) {
        bullets.remove(this);
        goblin.getHealthStatus(false);
        Window.goblins.remove(goblin);
        return; // exit the method after the first collision
      }
    }

    for (Troll troll : trollsList) {
      if (Collidable.collides(x, y, size, troll.x, troll.y, troll.diameter)) {
        bullets.remove(this);
        troll.getHealthStatus(false);
        Window.trolls.remove(troll);
        return; // exit the method after the first collision
      }
    }

    for (Axe axe : Goblin.axes) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, axe.x, axe.y, axe.size)) {
        Goblin.axes.remove(axe);
        System.out.println("Axe collided with Sprite");
        return; // exit the method after the first collision
      }
    }

    for (Arrow arrow : Skeleton.arrows) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, arrow.x, arrow.y, arrow.size)) {
        Skeleton.arrows.remove(arrow);
        System.out.println("Arrow collided with Sprite");
        return; // exit the method after the first collision
      }
    }

    for (Boulder boulder : Troll.boulders) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, boulder.x, boulder.y, boulder.size)) {
        Troll.boulders.remove(boulder);
        System.out.println("Sword collided with Sprite");
        return; // exit the method after the first collision
      }
    }


  }

}