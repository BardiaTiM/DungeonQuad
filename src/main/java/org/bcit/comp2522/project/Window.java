package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Window class.
 *
 * @author Gathrean Dela Cruz
 * @author Bardia Timouri
 * @version 1.0
 */
public class Window extends PApplet {

  static ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();

  ArrayList<Sprite> sprites;

  Waves waves;

  Sprite player;

  Bullet bullet;

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(700, 900);
  }

  PImage backgroundImage;
  float bgX = 0;
  float bgY = 0;
  float scrollSpeed = 2; // Adjust this to control the scrolling speed

  /**
   * Sets up the window.
   */
  public void setup() {
    size(700, 900);
    backgroundImage = loadImage("deep_slate.jpg");
    player = new Sprite(500, 700, 50, this, new PVector(0, 0));
    waves = new Waves(1, Window.this);
  }

  /**
   * Draws the window, player, and bullets.
   * The scrolling background is also drawn.
   */
  public void draw() {
    // Calculate the background position based on the player's movement
    bgY += scrollSpeed;
    bgX -= player.direction.x;
    bgY -= player.direction.y;

    // Tile the background image
    int offsetX = (int) (bgX % backgroundImage.width - backgroundImage.width);
    int offsetY = (int) (bgY % backgroundImage.height - backgroundImage.height);

    for (int x = offsetX; x < width; x += backgroundImage.width) {
      for (int y = offsetY; y < height; y += backgroundImage.height) {
        image(backgroundImage, x, y);
      }
    }

    player.draw();
    player.update(player.direction);
    waves.spawnWaves(1, 1, 1, 1);

    // Draw all the bullets in the list
    for (Bullet bullet : bullets) {
      bullet.draw();
      bullet.update();
      bullet.collide();
    }
  }

  /**
   * Moves the player when the arrow keys are pressed.
   */
  public void keyPressed() {
    if (keyCode == UP || key == 'w') {
      if (player.y - player.speed > 0) { // check if player is within top boundary
        player.direction.y = -1;
      }
    }
    if (keyCode == DOWN || key == 's') {
      if (player.y + player.speed < height) { // check if player is within bottom boundary
        player.direction.y = 1;
      }
    }
    if (keyCode == LEFT || key == 'a') {
      if (player.x - player.speed > 0) { // check if player is within left boundary
        player.direction.x = -1;
      }
    }
    if (keyCode == RIGHT || key == 'd') {
      if (player.x + player.speed < width) { // check if player is within right boundary
        player.direction.x = 1;
      }
    }
    redraw();
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (keyCode == UP || key == 'w' || keyCode == DOWN || key == 's') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || key == 'a' || keyCode == RIGHT || key == 'd') {
      player.direction.x = 0;
    }
    redraw();
  }

  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (mouseButton == LEFT) {
      /*
      Create a new bullet object and
      set its initial position to the current position of the player
      */
      Bullet bullet = new Bullet(player.x, player.y, 0, 0, 10, Waves.getGoblins(), this);

      float dx = mouseX - player.x;
      float dy = mouseY - player.y;
      float distance = sqrt(dx * dx + dy * dy);
      float vx = dx / distance;
      float vy = dy / distance;

      // Set the velocity of the new bullet
      bullet.setVelocity(vx, vy);

      bullets.add(bullet);
    }
  }

  /**
   * Main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

  /**
   * Returns the width of the window.
   *
   * @return width float
   */
  public float getWidth() {
    return width;
  }

  /**
   * Removes a bullet from the list.
   */
  public void removeBullet(Bullet bullet) {
    bullets.remove(bullet);
  }
}
