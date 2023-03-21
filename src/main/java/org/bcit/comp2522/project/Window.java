package org.bcit.comp2522.project;

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


  Waves waves;
  Sprite player;

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
    surface.setTitle("DUNGEON QUAD");

    backgroundImage = loadImage("deep_slate.jpg");

    PImage spriteImage = loadImage("mcW0.png");
    player = new Sprite(300, 700, 50, this, new PVector(0, 0));
    player.setSprite(spriteImage); // set the default player sprite

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

    // Draw the background image
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

  public void keyPressed() {
    if (keyCode == UP || key == 'w' || key == 'W') {
      if (player.y - player.speed > 0) {
        player.direction.y = -1;
        PImage spriteImage = loadImage("mcW0.png");
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == DOWN || key == 's' || key == 'S') {
      if (player.y + player.speed < height) {
        player.direction.y = 1;
        PImage spriteImage = loadImage("mcS0.png");
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == LEFT || key == 'a' || key == 'A') {
      if (player.x - player.speed > 0) {
        player.direction.x = -1;
        PImage spriteImage = loadImage("mcA0.png");
        player.setSprite(spriteImage);
      }
    }
    if (keyCode == RIGHT || key == 'd' || key == 'D') {
      if (player.x + player.speed < width) {
        player.direction.x = 1;
        PImage spriteImage = loadImage("mcD0.png");
        player.setSprite(spriteImage);
      }
    }
    redraw();
  }

  /**
   * Stops the player when the arrow keys are released.
   */
  public void keyReleased() {
    if (keyCode == UP || key == 'w' || keyCode == DOWN || key == 's' || key == 'W' || key == 'S') {
      player.direction.y = 0;
    }
    if (keyCode == LEFT || key == 'a' || keyCode == RIGHT || key == 'd' || key == 'A' || key == 'D') {
      player.direction.x = 0;
    }
    redraw();
  }

  /**
   * Creates a new bullet when the mouse is pressed.
   */
  public void mousePressed() {
    if (mouseButton == LEFT) {
      // Create a new bullet object and set its initial position to the current position of the player

      Bullet bullet = new Bullet((player.x + 50), (player.y + 40), 0, 0, 10, Waves.getGoblins(),
          Waves.getSkeletons(), Waves.getTrolls(), this);

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

  public float getWidth() {
    return width;
  }

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.project.Window");
  }

}
