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
  private int bulletDamage;
  private boolean PlayerCollided = false;

  private int inventory;

  public ConcurrentLinkedQueue<Skeleton> skeletonsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Goblin> goblinsList = new ConcurrentLinkedQueue<>();
  public ConcurrentLinkedQueue<Troll> trollsList = new ConcurrentLinkedQueue<>();

  public Player player;

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
                Player player, Window window, int waveNumber) {
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

    //if waveNumber is less than 10 inventory is 1, if its less than 15 its 2, if its less than 20 its 3
    if (waveNumber < 10) {
      inventory = 1;
      bulletDamage = 1;
    } else if (waveNumber < 15) {
      inventory = 2;
      bulletDamage = 2;
    } else {
      inventory = 3;
      bulletDamage = 3;
    }

  }

  public Bullet(float x, float y, float vx, float vy, float size,
                ConcurrentLinkedQueue<Goblin> goblin,
                ConcurrentLinkedQueue<Skeleton> skeleton,
                ConcurrentLinkedQueue<Troll> troll,
                Player player, Window window) {
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

  public Bullet(int x, int y, Window window) {
    super(x);
    this.x = x;
    this.y = y;
    this.window = window;
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
    // Check if the bullet is outside the window
    if (x < 0 || x > window.width || y < 0 || y > window.height) {
      Window.bullets.remove(this);
    }
  }

  public void update(PVector direction) {
    x += direction.x * speed;
    y += direction.y * speed;

  }

  /**
   * This method draws the bullet.
   */
  public void drawBullet() {
    PImage bulletImage;
    switch (inventory) {
      case 1 -> { // Soul Reaper
        bulletImage = window.loadImage("images/weapons/souls.png");
        window.image(bulletImage, x, y, size + 10, size + 10);
      }
      case 2 -> { // Twin Souls
        bulletImage = window.loadImage("images/weapons/souls.png");
        window.image(bulletImage, x + 10, y, size + 20, size + 20);
        window.image(bulletImage, x - 30, y, size + 20, size + 20);
      }
      case 3 -> { // The Demise of a Million Dying Suns
        bulletImage = window.loadImage("images/weapons/suns.png");
        window.image(bulletImage, x + 30, y, size + 30, size + 30);
        window.image(bulletImage, x - 30, y, size + 30, size + 30);
        window.image(bulletImage, x, y - 30, size + 30, size + 30);
      }
    }
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
    collideWithSkeletons();
    collideWithGoblins();
    collideWithTrolls();
    collideWithPlayerAxes();
    collideWithPlayerArrows();
    collideWithPlayerBoulders();
  }

  private void collideWithSkeletons() {
    for (Skeleton skeleton : skeletonsList) {
      if (Collidable.collides(x, y, size, skeleton.x, skeleton.y, skeleton.diameter)) {
        bullets.remove(this);
        skeleton.health -= bulletDamage;
        if (skeleton.health <= 0) {
          Window.skeletons.remove(skeleton);
          skeleton.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(skeleton.x, skeleton.y, skeleton.diameter, Player.x, Player.y, Player.diameter)) {
        handlePlayerCollision();
        return; // exit the method after the first collision
      }
    }
  }

  private void collideWithGoblins() {
    for (Goblin goblin : goblins) {
      if (Collidable.collides(x, y, size, goblin.x, goblin.y, goblin.diameter)) {
        bullets.remove(this);
        goblin.health -= bulletDamage;
        if (goblin.health <= 0) {
          Window.goblins.remove(goblin);
          goblin.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(goblin.x, goblin.y, goblin.diameter, Player.x, Player.y, Player.diameter)) {
        handlePlayerCollision();
        return; // exit the method after the first collision
      }
    }
  }

  private void collideWithTrolls() {
    for (Troll troll : trollsList) {
      if (Collidable.collides(x, y, size, troll.x, troll.y, troll.diameter)) {
        bullets.remove(this);
        troll.health -= bulletDamage;
        if (troll.health <= 0) {
          Window.trolls.remove(troll);
          troll.getHealthStatus(false);
        }
        return; // exit the method after the first collision
      }
      if (Collidable.collides(troll.x, troll.y, troll.diameter, Player.x, Player.y, Player.diameter)) {
        handlePlayerCollision();
        return; // exit the method after the first collision
      }
    }
  }

  private void collideWithPlayerAxes() {
    for (Axe axe : Goblin.axes) {
      if (Collidable.collides(Player.x, Player.y, Player.diameter + 50, axe.x, axe.y, axe.size)) {
        Goblin.axes.remove(axe);
        Player.health -= 3;
        if (Player.health <= 0) {
          endGame();
        }
        return; // exit the method after the first collision
      }
    }
  }

  private void collideWithPlayerArrows() {
    for (Arrow arrow : Skeleton.arrows) {
      if (Collidable.collides(Player.x, Player.y, Player.diameter + 30, arrow.x, arrow.y, arrow.size)) {
        Skeleton.arrows.remove(arrow);
        Player.health -= 1;
        if (Player.health <= 0) {
          endGame();
        }
        return; // exit the method after the first collision
      }
    }
  }

  private void collideWithPlayerBoulders() {
    for (Boulder boulder : Troll.boulders) {
      if (Collidable.collides(Player.x, Player.y, Player.diameter + 50, boulder.x, boulder.y, boulder.size)) {
        Troll.boulders.remove(boulder);
        Player.health -= 5;
        if (Player.health <= 0) {
          endGame();
        }
        return; // exit the method after the first collision
      }
    }
  }

  private void handlePlayerCollision() {
    if (!PlayerCollided) {
      Player.health -= 1;
      PlayerCollided = true;
      if (gameOn && Player.health <= 0) {
        endGame();
      }
    }
  }

  private void endGame() {
    gameOn = false;
    window.setCurrentScreen(Screen.SCORE);
  }

}