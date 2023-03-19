package org.bcit.comp2522.project;

public abstract class Collidable {
  Collidable(float xpos) {
    this.xpos = xpos;
  }

  abstract void draw();

  abstract void collide();

  public static boolean collides(float x1, float y1, float d1, float x2, float y2, float d2) {
    float dx = x1 - x2;
    float dy = y1 - y2;
    float distance = (float) Math.sqrt(dx * dx + dy * dy);
    return distance < (d1 / 2 + d2 / 2);
  }


  protected float xpos;

}