package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.Window.bullets;

public class SkeletonArrows extends Bullet {
  private Window window;
  private Skeleton skeleton;
  public static SkeletonArrows[] skeletonArrows;

  public SkeletonArrows(float x, float y, float vx, float vy, float size, Skeleton skeleton, Window window) {
    super(x, y, (int) vx, (int) vy, (int) size, skeleton, window);
  }

  public void setVelocity(float vx, float vy) { // Velocity of Skeleton arrows
    this.vx = vx * 10;
    this.vy = vy * 10;
  }

  public void update() { // Update Skeleton arrows
    x += vx;
    y += vy;
  }

  public void drawBullet() { // Draw Skeleton arrows
    window.ellipse(x, y, size, size);
  }

  public void draw() { // Draw Skeleton arrows
    this.drawBullet();
  }

  @Override
  void collide() { // Collision logic for Skeleton arrows
    for (int i = 0; i < skeleton.diameter; i++) {
      if (Collidable.collides(x, y, size, Sprite.x, Sprite.y, Sprite.diameter)) {
        // Player is hit
        // TODO: Handle player hit
        bullets.remove(this);
        System.out.println("Player hit!");
      }
    }
  }

}