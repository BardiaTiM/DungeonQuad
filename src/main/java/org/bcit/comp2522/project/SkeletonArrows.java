package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.Window.bullets;

public class SkeletonArrows extends Bullet {
  private Window window;

  public static SkeletonArrows[] skeletonArrows;

  public SkeletonArrows(float x, float y, float vx, float vy, float size, Skeleton[] skeleton, Window window) {
    super(x, y, vx, vy, size, skeleton, window);
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
    for (int i = 0; i < skeleton.length; i++) {
      if (Collidable.collides(x, y, size, Sprite.x, Sprite.y, Sprite.diameter)) {
        // Player is hit
        // TODO: Handle player hit
        bullets.remove(this);
        System.out.println("Player hit!");
      }
    }
  }


}




//  @Override
//  void collide() {
//    // Collision logic for Skeleton arrows
//    for (int i = 0; i < goblin.length; i++) {
//      if (Collidable.collides(x, y, size, goblin[i].x, goblin[i].y, goblin[i].diameter)) {
//        // Make goblin stop moving
//        goblin[i].y = 1000;
//        bullets.remove(this);
//        System.out.println("Goblin fainted!");
//      }
//    }
//    // No collision logic for Skeleton arrows with Skeleton or Troll
//  }
