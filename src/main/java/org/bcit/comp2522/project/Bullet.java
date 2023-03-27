package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.bcit.comp2522.project.Window.*;

/**
 * Bullet class.
 *
 * @author Bardia Timouri
 * @author Gathrean Dela Cruz
 * @author Will Ondrik
 * @version 1.0
 */
public class Bullet extends Collidable {
  private float x;
  private float y;
  private float vx;
  private float vy;
  private float size = 10;
  private final int speed = 10;
  private boolean spriteCollided = false;

  public ConcurrentLinkedQueue<Skeleton> skeletonsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Goblin> goblinsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Troll> trollsList = new ConcurrentLinkedQueue<>();

  public Sprite player;

  private final Window window;

  /**
   * Bullet constructor.
   *
   * @param x        the x coordinate of the bullet
   * @param y        the y coordinate of the bullet
   * @param vx       the x velocity of the bullet
   * @param vy       the y velocity of the bullet
   * @param size     the size of the bullet
   * @param goblin   the goblin list
   * @param skeleton the skeleton list
   * @param troll    the troll list
   * @param player   the player
   * @param window   the window that the bullet will be drawn on
   */
  public Bullet(float x, float y, float vx, float vy, float size,
                ConcurrentLinkedQueue<Goblin> goblin,
                ConcurrentLinkedQueue<Skeleton> skeleton,
                ConcurrentLinkedQueue<Troll> troll,
                Sprite player, Window window) {
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

  public Bullet(int x, int y, int vx, Window window, Sprite sprite) {
    super(x);
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.window = window;
    this.player = sprite;
  }

  /**
   * This method sets the velocity of the bullet.
   *
   * @param vx the velocity of the bullet
   * @param vy the velocity of the bullet
   */
  public void setVelocity(float vx, float vy) {
    this.vx = vx * speed;
    this.vy = vy * speed;
  }

  /**
   * This method updates the bullet's position.
   */
  public void update() {
    x += vx;
    y += vy;
  }

  public void update(PVector direction) {
    x += direction.x * speed;
    y += direction.y * speed;
  }

  /**
   * This method draws the bullet.
   */
  public void drawBullet() {
    PImage bulletImage = window.loadImage("images/player/bullet.png");
    window.image(bulletImage, x, y, size + 10, size + 10);
  }

  /**
   * This method draws the bullet.
   */
  public void draw() {
    this.drawBullet();
  }

  /**
   * Getter for the x coordinate of the bullet.
   *
   * @return the x coordinate of the bullet
   */
  public float getX() {
    return x;
  }

  /**
   * Getter for the y coordinate of the bullet.
   *
   * @return the y coordinate of the bullet
   */
  public float getY() {
    return y;
  }

  /**
   * Getter for the x velocity of the bullet.
   *
   * @return the x velocity of the bullet
   */
  public float getVx() {
    return vx;
  }

  /**
   * Getter for the y velocity of the bullet.
   *
   * @return the y velocity of the bullet
   */
  public float getVy() {
    return vy;
  }

  /**
   * Setter for the x coordinate of the bullet.
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Setter for the y coordinate of the bullet.
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Setter for the x velocity of the bullet.
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * Setter for the y velocity of the bullet.
   */
  public void setVy(float vy) {
    this.vy = vy;
  }

  public float getSpeed() {
    return speed;
  }

  @Override
  void collide() {
    for (Skeleton skeleton : skeletonsList) {
      if (Collidable.collides(x, y, size, skeleton.x, skeleton.y, skeleton.diameter)) {

        bullets.remove(this);
        skeleton.health -= 1;
        if (skeleton.health <= 0) {
          Window.skeletons.remove(skeleton);
          skeleton.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(skeleton.x, skeleton.y, skeleton.diameter, Sprite.x, Sprite.y, Sprite.diameter)) {
        if (!spriteCollided) {
          Sprite.health -= 1;
          spriteCollided = true;
          if (gameOn && Sprite.health <= 0) {
            gameOn = false;
            window.setCurrentScreen(Screen.SCORE);
          }
        }
        return; // exit the method after the first collision
      }
    }

    for (Goblin goblin : goblins) {
      if (Collidable.collides(x, y, size, goblin.x, goblin.y, goblin.diameter)) {
        bullets.remove(this);
        goblin.health -= 1;
        if (goblin.health <= 0) {
          Window.goblins.remove(goblin);
          goblin.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(goblin.x, goblin.y, goblin.diameter, Sprite.x, Sprite.y, Sprite.diameter)) {
        if (!spriteCollided) {
          Sprite.health -= 1;
          spriteCollided = true;
          if (gameOn && Sprite.health <= 0) {
            gameOn = false;
            window.setCurrentScreen(Screen.SCORE);
          }
        }
        return; // exit the method after the first collision
      }
    }

    for (Troll troll : trollsList) {
      if (Collidable.collides(x, y, size, troll.x, troll.y, troll.diameter)) {
        bullets.remove(this);
        troll.health -= 1;
        if (troll.health <= 0) {
          Window.trolls.remove(troll);
          troll.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(troll.x, troll.y, troll.diameter, Sprite.x, Sprite.y, Sprite.diameter)) {
        if (!spriteCollided) {
          Sprite.health -= 1;
          spriteCollided = true;
          if (gameOn && Sprite.health <= 0) {
            gameOn = false;
            window.setCurrentScreen(Screen.SCORE);
          }
        }
        return; // exit the method after the first collision
      }
    }

    for (Axe axe : Goblin.axes) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, axe.x, axe.y, axe.size)) {
        Goblin.axes.remove(axe);
        Sprite.health -= 3;
        if (gameOn && Sprite.health <= 0) {
          gameOn = false;
          window.setCurrentScreen(Screen.SCORE);
        }
        return; // exit the method after the first collision
      }
    }

    for (Arrow arrow : Skeleton.arrows) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, arrow.x, arrow.y, arrow.size)) {
        Skeleton.arrows.remove(arrow);
        System.out.println("Arrow collided with Sprite");
        Sprite.health -= 1;
        if (gameOn && Sprite.health <= 0) {
          gameOn = false;
          window.setCurrentScreen(Screen.SCORE);
        }
        return; // exit the method after the first collision
      }
    }

    for (Boulder boulder : Troll.boulders) {
      if (Collidable.collides(Sprite.x, Sprite.y, Sprite.diameter + 50, boulder.x, boulder.y, boulder.size)) {
        Troll.boulders.remove(boulder);
        System.out.println("Sword collided with Sprite");
        Sprite.health -= 5;
        if (gameOn && Sprite.health <= 0) {
          gameOn = false;
          window.setCurrentScreen(Screen.SCORE);
        }
        return; // exit the method after the first collision
      }
    }

  }

}