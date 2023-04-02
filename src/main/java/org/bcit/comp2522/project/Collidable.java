package org.bcit.comp2522.project;

/**
 * This is the abstract class for all collidable objects.
 *
 * @author Bardia Timouri
 * @version 1.0
 */
public abstract class Collidable {
  protected float xpos;


  /**
   * Constructs a Collidable object with a specified x-position.
   *
   * @param xpos the Collidable object's x-position
   */
  Collidable(float xpos) {
    this.xpos = xpos;
  }


  /**
   * Draws the Colliable object on the game Window.
   */
  abstract void draw();


  /**
   * Defines the behaviour of the Collidable object when it collides with another object.
   */
  abstract void collide();


  /**
   * Checks whether two Collidable objects are colliding.
   *
   * @param x1 the x-position of the first collidable object
   * @param y1 the y-position of the first collidable object
   * @param d1 the diameter of the first collidable object
   * @param x2 the x-position of the second collidable object
   * @param y2 the y-position of the second collidable object
   * @param d2 the diameter of the second collidable object
   * @return true if the objects are colliding, false otherwise
   */
  public static boolean collides(float x1, float y1, float d1, float x2, float y2, float d2) {
    float dx = x1 - x2;
    float dy = y1 - y2;
    float distance = (float) Math.sqrt(dx * dx + dy * dy);
    return distance < (d1 / 2 + d2 / 2);
  }


  /**
   * Updates the state of the Collidable object.
   */
  public abstract void update();

}